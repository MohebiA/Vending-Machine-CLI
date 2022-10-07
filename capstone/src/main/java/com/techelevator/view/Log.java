package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Log {

    private LocalDate dayStamp = LocalDate.now();
    private LocalTime timeStamp = LocalTime.now();
    private String logFilePath = "Log.txt";
    private File transactionLog = new File(logFilePath);
    private PrintWriter writer = null;
    private PrintWriter salesLogger = null;

    public void loggerMethod(String message){
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("dd/MM/YY");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss a");

        try (PrintWriter logWrite = new PrintWriter(new FileOutputStream (transactionLog, true))) {
            writer = logWrite;

            writer.append(dayFormatter.format(dayStamp) + " " + timeStamp.format(timeFormatter) + " " + message + "\n");

        } catch (FileNotFoundException e){
            System.out.println("Log not found");
        }
    }

    public void salesReport(String message){
        Date date = new Date();
        Format formatter = new SimpleDateFormat("YYYY-dd-mm_hh-mm");
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
