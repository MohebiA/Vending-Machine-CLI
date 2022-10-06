package com.techelevator;

import com.techelevator.view.Inventory;
import com.techelevator.view.Item;
import com.techelevator.view.Menu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE };
	private Map<String, List<Item>> currentStock = new HashMap<>();

	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase
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
