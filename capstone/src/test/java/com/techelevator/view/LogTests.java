package com.techelevator.view;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LogTests {
    File logTestFile = new File("Log.txt");

    Log log = new Log();

    @Test
    public void log_method_test(){
        boolean expected = true;
        boolean actual = false;
        log.loggerMethod("cats");
        try(Scanner readLog = new Scanner(logTestFile)) {
            while (readLog.hasNextLine()) {
                String line = readLog.nextLine();
                System.out.println(line);
                if (line.endsWith("cats")) {
                    actual = true;
                }

            }
        } catch(FileNotFoundException e){
            System.out.println("File not found ");
        }
        Assert.assertEquals(expected,actual);
    }
}
