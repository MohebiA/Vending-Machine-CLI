package com.techelevator.view;

import com.techelevator.VendingMachineCLI;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryTests {

    private Map<String, List<Item>> inventoryMap = new HashMap<>();

    Menu menu = new Menu(System.in, System.out);
    VendingMachineCLI cli = new VendingMachineCLI(menu);
    File inventoryFile = new File("vendingmachine.csv");

    @Before
    public void setUp() {
        cli.initializeInventory();
    }

    @Test
    public void check_item_stock_starts_at_5() {
        Inventory.loadInventory(inventoryMap);
        int actual = inventoryMap.get("A1").size();
        int expected = 6;
        Assert.assertEquals(expected,actual);
    }

}
