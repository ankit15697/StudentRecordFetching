package com.java.studentrecord;

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
public class StudentDetails extends HttpServlet {
    HashMap<String, LinkedList<String>> studentRecord;

    @Override
    public void init() throws ServletException {

        studentRecord = new HashMap<String, LinkedList<String>>();
        System.out.println("Servlet started");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       /* resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.println("<html>");
        out.println("<head><title>Student Record</title></head>");
        out.println("<body>");
        out.println("<h1>Search Student</h3>");
        out.println("<form method='post' action='student'>");
        out.println("<p>Enter Roll No : <input type='text' name='roll'></p>");
        out.println("<p><input type='submit' value='search'></p>");
        out.println("</form>");

        out.println("<form method='post' action='student' name='action'>");
        out.println("<p>Enter Roll No : <input type='text' name='rollNo'></p>");
        out.println("<p>Enter Name : <input type='text' name='name'></p>");
        out.println("<p>Enter University : <input type='text' name='university'></p>");
        out.println("<p><input type='submit' value='enter'></p>");
        out.println("</form>");

        out.println("</body>");
        out.println("</html>");*/
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        ServletContext context =getServletContext();
        String userName = context.getInitParameter("userName");
        String action = req.getParameter("roll");
        resp.getWriter().write("Your user name is : " +userName+":  ");
        if(action !=null && !action.isEmpty()) {
            String rollNo  = req.getParameter("roll");
            if(studentRecord.get(rollNo) == null) {
                resp.getWriter().write("No student present with this roll no");
            }
            else{
                String name = studentRecord.get(rollNo).get(0);
                String university = studentRecord.get(rollNo).get(1);
                resp.getWriter().write("Student Details are as follows : "+ name +": "+university);
            }
        }
        else {
            String rollNo = req.getParameter("rollNo");
            if(studentRecord.get(rollNo)==null) {
                String name = req.getParameter("name");
                String university = req.getParameter("university");
                LinkedList<String> details = new LinkedList<String>();
                details.add(name);
                details.add(university);
                studentRecord.put(rollNo,details);
                resp.getWriter().write("Details of student has been updated successfully "+rollNo +": "+name+" : "+university);
            }
            else{
                resp.getWriter().write("There is a student present with this roll no already : " +rollNo);
            }
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        System.out.println(this);
    }

}
