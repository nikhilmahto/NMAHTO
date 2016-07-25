package com.au.poc.csv;

import com.au.poc.concurrency.MyCallable;


import com.csvreader.CsvWriter;

import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class OpenCSV {
    
    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException,
                                                  ExecutionException {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        String[] row = null;
       // List<Future<String[]>> resultList = new ArrayList<>();
       CSVReader csvReader = new CSVReader(new FileReader("C:\\Users\\Nikhil Mahto\\Desktop\\OneCall\\SampleRequest_old.csv"));
      //  CsvReader reader = new CsvReader("C:\\Users\\Nikhil Mahto\\Desktop\\OneCall\\SampleRequest_old.csv");
        List content = csvReader.readAll();
        
        for (Object object : content) {
                row = (String[]) object;
                
                System.out.println(row[0]
                           + " # " + row[1]
                           + " #  " + row[2]);
        }
//        while (csvReader.) {
//            MyCallable myCallable = new MyCallable(reader.getValues());
//            Future<String[]> result = executor.submit(myCallable);
//           //       writer.writeRecord(result.get());
//        }
       
       
        csvReader.close();


        executor.shutdown();
    }
    
}
