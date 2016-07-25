package com.au.poc.concurrency;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class SubmitEnquiry {
    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException,
                                                  ExecutionException {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

       // List<Future<String[]>> resultList = new ArrayList<>();

        CsvReader reader = new CsvReader("C:\\Users\\Nikhil Mahto\\Desktop\\OneCall\\SampleRequest_old.csv");
        CsvWriter writer = new CsvWriter("C:\\Users\\Nikhil Mahto\\Desktop\\OneCall\\Response.csv");
        reader.readHeaders();
        ArrayList<String> strHeadersList = new ArrayList<String>(Arrays.asList(reader.getHeaders()));
        strHeadersList.add("JobId");
        strHeadersList.add("Message");
        strHeadersList.add("Outcome");
        String[] headerarray = new String[strHeadersList.size()];
        for (int i = 0; i < strHeadersList.size(); i++) {
            headerarray[i] = strHeadersList.get(i);
        }
        writer.writeRecord(headerarray);
        while (reader.readRecord()) {
            MyCallable myCallable = new MyCallable(reader.getValues());
            Future<String[]> result = executor.submit(myCallable);
                  writer.writeRecord(result.get());
        }
       
        writer.flush();
        writer.close();
        reader.close();


        executor.shutdown();
    }
}

