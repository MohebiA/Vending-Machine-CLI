package com.techelevator.view;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class ItemTests {



    Item item = new Item("A1","Doritos",1.15, "Chip");

    @Test
    public void item_test(){
      String[] actual = new String[]{item.getSlot(), item.getName(),  String.valueOf(item.getPrice()),  item.getCategory()};
      String[] expected = new String[]{"A1","Doritos","1.15","Chip"};
        Assert.assertArrayEquals(actual, expected);
    }

    @Test
    public void message_test(){
        String actual = item.getMessage();
        String expected = "Crunch Crunch, Yum!";
        Assert.assertEquals(actual,expected);
    }
}
