// This class will be responsible for calling the API methods
package com.java.ApiCall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class ApiApplication {
    public static void main(String [] args)  {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter url to be fetched");
        BufferedReader inp = new BufferedReader (new InputStreamReader(System.in));
        String userUrl = null;
        try {
            userUrl = inp.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        URL url = null;
        try {
            url = new URL(userUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            URLConnection conn = url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                System.out.println(inputLine);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
