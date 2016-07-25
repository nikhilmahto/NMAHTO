package com.au.poc.csv;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.io.FileNotFoundException;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AsynCallAPI {
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
         CsvReader reader = new CsvReader("C:\\Users\\Nikhil Mahto\\Desktop\\OneCall\\SampleRequest_old.csv");
         CsvWriter writer=new CsvWriter("C:\\Users\\Nikhil Mahto\\Desktop\\OneCall\\Response.csv");
         reader.readHeaders();
         ArrayList<String> strHeadersList=new ArrayList<String>(Arrays.asList(reader.getHeaders()));
         strHeadersList.add("JobId");
         strHeadersList.add("Message");
         strHeadersList.add("Outcome");
         String[] headerarray=new String[strHeadersList.size()];
         for(int i=0;i<strHeadersList.size();i++)
         {
         headerarray[i]=strHeadersList.get(i);
         }
         writer.writeRecord(headerarray);
         
       final BlockingQueue<String[]> bq = new ArrayBlockingQueue<String[]>(10);
       final ExecutorService executor = Executors.newFixedThreadPool(5);
       Runnable producer;
       producer = new Runnable() {
           
         public void run() {
         
                try {
                    while (reader.readRecord()) {
                        try {
                            bq.put(reader.getValues());
                            System.out.println("putting:" + reader.get("Id"));
                        } catch (InterruptedException ie) {
                            assert false;
                        } catch (IOException io) {
                            assert false;
                        }
                    }
                } catch (IOException e) {
                }
            }
       };
           
       executor.execute(producer);
       Runnable consumer;
       consumer = new Runnable() {
         public void run() {
          String[] array=null;
                try {
               while (reader.get("Id") != "null")      {
                        try {
                         // array=  bq.poll(1, TimeUnit.SECONDS);
                            
                            array = bq.take();
                          
                            System.out.println("getting C:" + array[0]);
                      
                            
                        } catch (InterruptedException ie) {
                        }
                    } 
                } catch (IOException e) {
                }
                executor.shutdownNow();
         }
       };
       executor.execute(consumer);
  
    }
}
