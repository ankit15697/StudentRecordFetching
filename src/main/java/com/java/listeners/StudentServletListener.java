package com.java.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class StudentServletListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.log("Inside listener init");
        String url = servletContext.getInitParameter("propertyUrl");
        File file = new File(url);
        FileInputStream fileInput = null;
        StringBuffer propertyData = new StringBuffer();
        try {
            fileInput = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            servletContext.log(String.valueOf(e));
            return;
        }
        Properties properties = new Properties();
        try {
            properties.load(fileInput);
        } catch (IOException e) {
            servletContext.log(String.valueOf(e));
            return;
        }
        try {
            fileInput.close();
        } catch (IOException e) {
            servletContext.log(String.valueOf(e));
            return;
        }

        Enumeration enuKeys = properties.keys();
        while (enuKeys.hasMoreElements()) {
            String key = (String) enuKeys.nextElement();
            String value = properties.getProperty(key);
            propertyData.append(key + ": " + value);
        }
        servletContext.setAttribute("property", propertyData.toString());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.log("Inside listener  destroy");
    }
}
