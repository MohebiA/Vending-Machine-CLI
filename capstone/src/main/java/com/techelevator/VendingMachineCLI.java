package com.techelevator;

import com.techelevator.view.Inventory;
import com.techelevator.view.Item;
import com.techelevator.view.Menu;

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

	private Map<String, List<Item>> currentStock = new TreeMap<>();
	private static double balance = 0;
	private String[] currentMenu = MAIN_MENU_OPTIONS;

	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				displayItems(currentStock);

			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase
				 choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

				 if(choice.equals(PURCHASE_MENU_OPTION_FEED)) {
					 feedMoney();

				 } else if (choice.equals(PURCHASE_MENU_OPTION_SELECT)) {
					 selectProduct();
				 } else if (choice.equals(PURCHASE_MENU_OPTION_FINISH)) {
					 finishTransaction();
				 }

			} else if(choice.equals(MAIN_MENU_OPTION_EXIT)){
				System.exit(1);
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);

		cli.initializeInventory();

		cli.run();

	}

	public void initializeInventory() {
		currentStock = Inventory.loadInventory(currentStock);
	}

	public double feedMoney(){
		System.out.print("Enter an amount to deposit: ");

		try {
			double deposit = Double.parseDouble(menu.getUserInput().strip());
			balance += deposit;
			NumberFormat currency = NumberFormat.getCurrencyInstance();
			System.out.println("\nYou deposited: " + currency.format(deposit) + "\nBalance: " + currency.format(balance));

			String choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
		} catch(NumberFormatException e) {
			System.out.println("Please enter a valid price: ");
		}

		return balance;
	}

	public void selectProduct() {
		displayItems(currentStock);
		System.out.print("\nPlease enter the item slot number: ");

		try {
			String userSlot = menu.getUserInput().strip();
			// Check if it's valid
			String currentItem = currentStock.get(userSlot).get(0).getName();
			System.out.println("\nYou selected " + currentItem);
		} catch(Exception e) {
			System.out.println("Please enter a valid slot number.");
			String choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
		}
	}

	public static void dispenseItem() {

	}

	public static void finishTransaction() {

	}

	public static void displayItems(Map<String, List<Item>> inventory){
		for(Map.Entry<String, List<Item>> stock : inventory.entrySet() ){

			boolean soldOut = true;
			if(stock.getValue().size() > 0){
				soldOut = false;
			}

			System.out.println(stock.getKey() + " " + stock.getValue().get(0).getName()
					+ ", Price: " + stock.getValue().get(0).getPrice() + ", Stock: "
					+ ((soldOut? "Sold Out" : (stock.getValue().size()))));

		}
	}
}
