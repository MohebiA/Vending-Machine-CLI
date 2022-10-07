package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Log {

    private LocalDate dayStamp = LocalDate.now();
    private LocalTime timeStamp = LocalTime.now();
    private File transactionLog = new File("Log.txt");
    private PrintWriter writer = null;

    public void loggerMethod(String message){
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("dd/MM/YY");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss a");

    try( PrintWriter logWrite = new PrintWriter(new FileOutputStream (transactionLog, true))){
        writer = logWrite;

        writer.append(dayFormatter.format(dayStamp) + " " + timeStamp.format(timeFormatter) + " " + message + "\n");

    } catch (FileNotFoundException e){
        System.out.println("Log not found");
    }





    }
}
