package com.java.response;

public class XMLConversion {
    public static String convert(String result) {
        String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<Data>" + result + "<Data>";
        return response;
    }
}
