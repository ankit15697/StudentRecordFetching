package com.java.response;

public class JsonConversion{
    public static String convert(String result) {

        String  response = "{ Data" + ":" + result + "}";
        return response;
    }
}
