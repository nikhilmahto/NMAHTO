package com.au.poc.concurrency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Callable;

public class MyCallable implements Callable<String[]> {

    private String[] inputArray;

    public MyCallable(String[] input) {
        this.inputArray = input;
    }

    @Override
    public String[] call() throws Exception {
        ArrayList<String> outputArray = new ArrayList<String>(Arrays.asList(inputArray));
        outputArray.add("Job Value");
        outputArray.add("Message Value");
        outputArray.add("Outcome Value");
        String[] responsearray = new String[outputArray.size()];
        for (int i = 0; i < outputArray.size(); i++) {
            responsearray[i] = outputArray.get(i);
        }
        return responsearray;
    }
}
