package com.techelevator;

import com.techelevator.view.Inventory;
import com.techelevator.view.Item;
import com.techelevator.view.Log;
import com.techelevator.view.Menu;
import jdk.swing.interop.SwingInterOpUtils;

import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.*;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };
	private static final String PURCHASE_MENU_OPTION_FEED = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_FEED, PURCHASE_MENU_OPTION_SELECT, PURCHASE_MENU_OPTION_FINISH};

	private Log cashLogger = new Log();

	private Map<String, List<Item>> currentStock = new TreeMap<>();
	private static double balance = 0;
	private NumberFormat currency = NumberFormat.getCurrencyInstance();
	private String[] currentMenu = MAIN_MENU_OPTIONS;

	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		while (true) {

			String choice = (String) menu.getChoiceFromOptions(currentMenu);

			if (currentMenu.equals(MAIN_MENU_OPTIONS)) {
				//String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
				chooseFromMain(choice);
			}
			else if (currentMenu.equals(PURCHASE_MENU_OPTIONS)) {
				//String choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
				chooseFromPurchaseMenu(choice);
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);

		cli.initializeInventory();

		// Clear log file when app first runs
		try (PrintWriter clearWriter = new PrintWriter("Log.txt")) {
			clearWriter.close();
		} catch (Exception e) {
			System.out.println("Could not access or clear file.");
		}

		cli.run();

	}

	public void initializeInventory() {
		currentStock = Inventory.loadInventory(currentStock);
	}

	public void chooseFromMain(String choice) {

		if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
			// display vending machine items
			displayItems(currentStock);

		} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
			chooseFromPurchaseMenu(choice);

		} else if(choice.equals(MAIN_MENU_OPTION_EXIT)){
			System.exit(1);
		}
	}

	public void chooseFromPurchaseMenu(String choice) {
		// do purchase
		if(choice.equals(PURCHASE_MENU_OPTION_FEED)) {
			feedMoney();
		} else if (choice.equals(PURCHASE_MENU_OPTION_SELECT)) {
			selectProduct();
		} else if (choice.equals(PURCHASE_MENU_OPTION_FINISH)) {
			finishTransaction();
		}

		// Choose which menu to switch to
		if (choice.equals(PURCHASE_MENU_OPTION_FINISH)) {
			currentMenu = MAIN_MENU_OPTIONS;
		} else {
			currentMenu = PURCHASE_MENU_OPTIONS;
		}
	}

	public double feedMoney(){
		System.out.print("Enter an amount to deposit: ");

		try {
			double deposit = Double.parseDouble(menu.getUserInput().strip());
			balance += deposit;
			cashLogger.loggerMethod("FEED MONEY: " + currency.format(deposit) + " " + currency.format(balance));
			System.out.println("\nYou deposited: " + currency.format(deposit) + "\nBalance: " + currency.format(balance));
		} catch(NumberFormatException e) {
			System.out.println("Please enter a valid price: ");
		}

		return balance;
	}

	public void selectProduct() {
		displayItems(currentStock);
		System.out.print("\nPlease enter the item slot number: ");

		try {
			String userSlot = menu.getUserInput().strip().toUpperCase();
			// Check if it's valid
			List<Item> currentItemList = currentStock.get(userSlot);
			//Check if item in stock
			if(currentItemList.size() > 1){
				dispenseItem(userSlot);
			}else{
				System.out.println(userSlot + " is SOLD OUT");
			}
		} catch(Exception e) {
			System.out.println("Please enter a valid slot number.");
			String choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
		}
	}

	public void dispenseItem(String userSlot) {
		Item currentItem = currentStock.get(userSlot).remove(0);
		System.out.println("You selected " + currentItem.getName() + ", " + currency.format(currentItem.getPrice()));
		if(currentItem.getPrice() > balance){
			System.out.println("Insufficient funds XXX");
		}else {
			balance -= currentItem.getPrice();
			cashLogger.loggerMethod(currentItem.getName() + " " + userSlot + " " + currency.format(currentItem.getPrice()) + " " + currency.format(balance));
			System.out.println("Current balance: " + currency.format(balance) + " \n" + currentItem.getMessage());
		}

	}

	public void finishTransaction() {
		int quarterCount = 0;
		int dimeCount = 0;
		int nickelCount = 0;

		System.out.println("Change due: " + currency.format(balance));
		double startBalance = balance;
		// Penny math:
		int balancePennies = (int)(balance * 100);
		quarterCount = (int)(balancePennies / 25);
		balancePennies -= quarterCount * 25;
		dimeCount = (int)(balancePennies / 10);
		balancePennies -= dimeCount * 10;
		nickelCount = (int)(balancePennies / 5);
		balancePennies -= nickelCount * 5;

		// Update balance in dollars too
		balance = balancePennies / 100;

		System.out.println("Returning: " + quarterCount + " quarters, " + dimeCount + " dimes, " + nickelCount + " nickels.");
		cashLogger.loggerMethod("GIVE CHANGE: " + currency.format(startBalance) + " " + currency.format(balance));
		System.out.println("Updated balance: " + currency.format(balance));

		System.out.println("\n------------------------");
		System.out.println("Returning to Main Menu:");
		System.out.println("------------------------");
	}

	public void displayItems(Map<String, List<Item>> inventory){
		for(Map.Entry<String, List<Item>> stock : inventory.entrySet() ){

			boolean soldOut = true;
			if(stock.getValue().size() > 1){
				soldOut = false;
			}

			System.out.println(stock.getKey() + " " + stock.getValue().get(0).getName()
					+ ", Price: " + currency.format(stock.getValue().get(0).getPrice()) + ", Stock: "
					+ ((soldOut? "SOLD OUT" : (stock.getValue().size() - 1))));

		}
	}

	public void transactionLogger(String message){
		cashLogger.loggerMethod(message);
	}


}
