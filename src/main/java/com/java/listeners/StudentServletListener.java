package com.java.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class StudentServletListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("Listener invoked !! for starting");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.print("Listener invoked !! for destroying");
    }
}
