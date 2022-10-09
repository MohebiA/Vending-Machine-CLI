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
    public void checkFinishTransaction_7_65() {

        double expected = 0.00;
        double actual = pennyMath(7.65);

        Assert.assertEquals(expected, actual, 0.00);


    }


    @Test
    public void coin_count_test_40(){
        int[] expected = new int[]{1,1,1,0};
        double testBalance = .40;
        int[] actual = coinCount(testBalance);

        Assert.assertArrayEquals(expected,actual);
    }

    @Test
    public void coin_count_test_265(){
        int[] expected = new int[]{10,1,1,0};
        double testBalance = 2.65;
        int[] actual = coinCount(testBalance);

        Assert.assertArrayEquals(expected,actual);
    }

    public double pennyMath(double balanceAmount){

        // Penny math:
        int balancePennies = (int)(balanceAmount * 100);
        int quarterCount = (balancePennies / 25);
        balancePennies -= quarterCount * 25;
        int dimeCount = (balancePennies / 10);
        balancePennies -= dimeCount * 10;
        int nickelCount = (balancePennies / 5);
        balancePennies -= nickelCount * 5;

        // Update balance in dollars too
        balanceAmount = balancePennies / 100;

        return balanceAmount;
    }

    public int[] coinCount(double balanceAmount){
        int[] coinArray = new int[4];

        // Penny math:
        int balancePennies = (int)(balanceAmount * 100);
        int quarterCount = (balancePennies / 25);
        balancePennies -= quarterCount * 25;
        int dimeCount = (balancePennies / 10);
        balancePennies -= dimeCount * 10;
        int nickelCount = (balancePennies / 5);
        balancePennies -= nickelCount * 5;

        coinArray[0] = quarterCount;
        coinArray[1] = dimeCount;
        coinArray[2] = nickelCount;
        coinArray[3] = balancePennies;

        return coinArray;

    }

}
