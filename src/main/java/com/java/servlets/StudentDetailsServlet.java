package com.java.servlets;

import com.java.response.JsonConversion;
import com.java.response.XMLConversion;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.io.*;
public class StudentDetailsServlet extends HttpServlet {
    HashMap<String, LinkedList<String>> studentRecord;

    @Override
    public void init() throws ServletException {

        studentRecord = new HashMap<String, LinkedList<String>>();
        System.out.println("Servlet started");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context =getServletContext();
        String contentType = req.getContentType();
        StringBuffer result =new StringBuffer();
        String userName = context.getInitParameter("userName");
        result.append("Your user name is : " + userName+" : ");
        String rollNo  = req.getParameter("roll");
        if(studentRecord.get(rollNo) == null) {
            result.append("No student present with this roll no");
        }
        else{
            String name = studentRecord.get(rollNo).get(0);
            String university = studentRecord.get(rollNo).get(1);
            result.append("Student Details are as follows : "+ name +": "+university);
        }
        if(contentType == null) {
            resp.getWriter().write(result.toString());
        }
        else if(contentType.equals("text/xml")) {
                resp.setContentType("text/xml");
                resp.getWriter().write(XMLConversion.convert(result.toString()));
        }
        else if(contentType.equals("application/json")) {
            resp.setContentType("application/json");
            resp.getWriter().write(JsonConversion.convert(result.toString()));
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        ServletContext context =getServletContext();
        String userName = context.getInitParameter("userName");

        String rollNo = req.getParameter("rollNo");

        String contentType = req.getContentType();
        StringBuffer result =new StringBuffer();
        result.append("Your user name is : " +userName+":  ");
        if(studentRecord.get(rollNo)==null) {
            String name = req.getParameter("name");
            String university = req.getParameter("university");
            LinkedList<String> details = new LinkedList<String>();
            details.add(name);
            details.add(university);
            studentRecord.put(rollNo,details);
            result.append("Details of student has been updated successfully "+rollNo +": "+name+" : "+university);
        }
        else{
            result.append("There is a student present with this roll no already : " +rollNo);
        }

        if(contentType == null) {
            resp.getWriter().write(result.toString());
        }
        else if(contentType.equals("text/xml")) {
            resp.setContentType("text/xml");
            resp.getWriter().write(XMLConversion.convert(result.toString()));
        }
        else if(contentType.equals("application/json")) {
            resp.setContentType("application/json");
            resp.getWriter().write(JsonConversion.convert(result.toString()));
        }
        else {
            resp.getWriter().write(result.toString());
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        System.out.println(this);
    }

}
