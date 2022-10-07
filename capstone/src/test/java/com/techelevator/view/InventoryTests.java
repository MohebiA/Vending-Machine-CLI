package com.techelevator.view;

import com.techelevator.VendingMachineCLI;
import org.junit.Before;
import org.junit.Test;

public class InventoryTests {

    Menu menu = new Menu(System.in, System.out);
    VendingMachineCLI cli = new VendingMachineCLI(menu);

    @Before
    public void setUp() {
        cli.initializeInventory();
    }

    @Test
    public void check_item_stock_starts_at_5() {

    }
}
