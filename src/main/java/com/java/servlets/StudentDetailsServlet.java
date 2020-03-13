package com.java.servlets;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
public class StudentDetailsServlet extends HttpServlet {
    HashMap<String, LinkedList<String>> studentRecord;

    @Override
    public void init() throws ServletException {
        studentRecord = new HashMap<String, LinkedList<String>>();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HashMap<String,String> currentData = new HashMap<String, String>();
        String rollNo  = req.getParameter("roll");
        if(studentRecord.get(rollNo) == null) {

            currentData.put("msg","No student present with this roll no");
        }
        else{
            String name = studentRecord.get(rollNo).get(0);
            String university = studentRecord.get(rollNo).get(1);
            currentData.put("name",name);
            currentData.put("university",university);
        }
        req.setAttribute("data",currentData);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


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

    @Override
    public void destroy() {
        super.destroy();
        System.out.println(this);
    }

}
