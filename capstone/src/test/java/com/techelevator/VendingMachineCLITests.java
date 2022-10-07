package com.techelevator;

import com.techelevator.view.Menu;
import org.junit.Assert;
import org.junit.Test;

public class VendingMachineCLITests {

    private double testBalance = 0;

    private Menu menu = new Menu(System.in, System.out);
    private VendingMachineCLI cli = new VendingMachineCLI(menu);

    @Test
    public void checkFeedMoney() {
        String userInput = "2.00";
        double expected = 2.00;

        try {
            double deposit = Double.parseDouble(userInput.strip());
            testBalance += deposit;
        } catch(NumberFormatException e) {
            System.out.println("Please enter a valid price: ");
        }
        Assert.assertEquals(expected, testBalance, 0.00);
    }

    @Test
    public void checkFinishTransaction() {
        testBalance = 4.35;
        double expected = 0.00;
        double actual = cli.finishTransaction();

        Assert.assertEquals(expected, actual, 0.00);
    }

}
