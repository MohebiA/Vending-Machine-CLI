package com.techelevator;

import com.techelevator.view.Inventory;
import com.techelevator.view.Item;
import com.techelevator.view.Menu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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

	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items


				for(Map.Entry<String, List<Item>> stock : currentStock.entrySet() ){

					boolean soldOut = true;
					if(stock.getValue().size() > 0){
						soldOut = false;
					}

					System.out.println(stock.getKey() + " " + stock.getValue().get(0).getName() + ", Price: " + stock.getValue().get(0).getPrice() + ", Stock: "
							+ ((soldOut? "Sold Out" : (stock.getValue().size()))));

				}


			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase

				 choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

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
		// System.out.println(currentStock.get("A1").get(0).getName());
	}
}
