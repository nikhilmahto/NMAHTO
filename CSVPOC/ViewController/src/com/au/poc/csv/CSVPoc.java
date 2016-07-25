package com.au.poc.csv;

import com.csvreader.CsvReader;

import com.csvreader.CsvWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVPoc {
    
   public static void main(String[] args)
   {
       asyncServiceMethod();
    }
   
   
    public static void asyncServiceMethod(){ 
           Runnable task = new Runnable() {

               @Override 
               public void run() { 
                   try { 
                      readCSV();
                   } catch (Exception ex) { 
                       //handle error which cannot be thrown back 
                   } 
               } 
           }; 
           new Thread(task, "ServiceThread").start(); 
       }
    
   public static void readCSV()
   {
        try {
                                
                                CsvReader reader = new CsvReader("C:\\Users\\Nikhil Mahto\\Desktop\\OneCall\\SampleRequest.csv");
                                CsvWriter writer=new CsvWriter("C:\\Users\\Nikhil Mahto\\Desktop\\OneCall\\Response.csv");
                                reader.readHeaders();
            ArrayList<String> strHeadersList=new ArrayList<String>(Arrays.asList(reader.getHeaders()));
                                strHeadersList.add("JobId");
                            strHeadersList.add("Message");
                            strHeadersList.add("Outcome");
                            strHeadersList.add("TimeStamp");
            String[] headerarray=new String[strHeadersList.size()];
            for(int i=0;i<strHeadersList.size();i++)
            {
                headerarray[i]=strHeadersList.get(i);
            }
                                writer.writeRecord(headerarray);
                                
                                while (reader.readRecord())
                                {
                                        String activityId = reader.get("ActivityId");
                                        String commenceOn = reader.get("CommencesOn");
                                        String completesOn = reader.get("CompletesOn");
                                        String country = reader.get("Country");
                                        String code = reader.get("Postcode");
                                        String state = reader.get("State");
                                        String street = reader.get("Street");
                                        String streetType = reader.get("StreetType");
                                        String suburb = reader.get("Suburb");
                                        String purposeId = reader.get("PurposeId");
                                    String spatialObjectDescription = reader.get("SpatialObjectDescription");
                                    String spatialObjectProjection = reader.get("SpatialObjectProjection");
                                    String username = reader.get("Username");
                                    String workingOnBehalfOfAuthorityId = reader.get("WorkingOnBehalfOfAuthorityId");
                                    String workingOnBehalfOfId = reader.get("WorkingOnBehalfOfId");
                                    String workplaceLocationCode = reader.get("WorkplaceLocationCode");
                                    
                                    ArrayList<String> strValueList=new ArrayList<String>(Arrays.asList(reader.getValues()));
                                                        strValueList.add("Job Value");
                                                    strValueList.add("Message Value");
                                                    strValueList.add("Outcome Value");
                                    java.util.Date date= new java.util.Date();

                                    strValueList.add(new Timestamp(date.getTime()).toString());
                                    String[] valuearray=new String[strValueList.size()];
                                    for(int i=0;i<strValueList.size();i++)
                                    {
                                        valuearray[i]=strValueList.get(i);
                                    }
                                        //System.out.println(activityId );
                                    
                                      writer.writeRecord(valuearray);
                                }
                                writer.flush();
                                writer.close();
                                reader.close();
                                
                        } catch (FileNotFoundException e) {
                                e.printStackTrace();
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
    }
   
  
}
