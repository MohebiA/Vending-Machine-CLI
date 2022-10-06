package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Inventory {

    private Map<String, List<Item>> inventoryMap = new HashMap<>();
    private static File inventoryFile = new File("vendingmachine.csv");
    private static final int DEFAULT_STOCK_COUNT = 5;

    public static Map<String, List<Item>> loadInventory(Map destinationMap) {

        try (Scanner readInventory = new Scanner(inventoryFile)) {
            while (readInventory.hasNextLine()) {
                String[] currentLine = readInventory.nextLine().split("\\|");

                String currentSlot = currentLine[0];
                Item currentItem = new Item(currentSlot,
                        currentLine[1],
                        Double.parseDouble(currentLine[2]),
                        currentLine[3]);

                List<Item> currentItemStock = new ArrayList<>();
                for (int i=0; i < DEFAULT_STOCK_COUNT; i++) {
                    currentItemStock.add(currentItem);
                }
                destinationMap.put(currentSlot, currentItemStock);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error reading from inventory file.");
        }

        return destinationMap;
    }
}
