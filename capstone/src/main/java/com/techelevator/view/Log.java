package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {

    private String logFilePath = "Log.txt";
    private File transactionLog = new File(logFilePath);
    private PrintWriter writer = null;
    private PrintWriter salesLogger = null;

    private Date date = new Date();

    public void loggerMethod(String message){
        Format formatter = new SimpleDateFormat("MM/dd/YYYY hh:mm:ss a");

        try (PrintWriter logWrite = new PrintWriter(new FileOutputStream (transactionLog, true))) {
            writer = logWrite;

            writer.append(formatter.format(date) + " " + message + "\n");

        } catch (FileNotFoundException e){
            System.out.println("Log not found");
        }
    }

    public void salesReport(String message){
        Format formatter = new SimpleDateFormat("yyyy-MM-dd_hh-mm");
        String dateTimeSales = ("Sales_" + formatter.format(date) + ".txt");
        File salesReportFile = new File(dateTimeSales);

        try (PrintWriter salesWriter = new PrintWriter(new FileOutputStream (salesReportFile, true))) {
            salesLogger = salesWriter;
            salesLogger.append(message + "\n");

        } catch (FileNotFoundException e){
            System.out.println("Sales Report not found");
        }
    }

}
