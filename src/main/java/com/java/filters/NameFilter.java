package com.java.filters;

import com.java.response.JsonConversion;
import com.java.response.XMLConversion;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class NameFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        RequestDispatcher rdObj = null;

        String regName = servletRequest.getParameter("name");
        PrintWriter out = servletResponse.getWriter();
        if( regName !=null && !isValid(regName)) {
            out.print("<p id='errMsg' style='color: red; font-size: larger;'>Name is InValid</p>");
            rdObj = servletRequest.getRequestDispatcher("/index.html");
            rdObj.include(servletRequest, servletResponse);
        }
        else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

        String contentType = (String) servletRequest.getAttribute("contentType");
        HashMap<String,String> currentData = (HashMap<String, String>) servletRequest.getAttribute("data");

        if(contentType == null) {
            servletResponse.getWriter().write(currentData.toString());
        }
        if(contentType.equals("text/xml")) {
                servletResponse.setContentType("text/xml");
                servletResponse.getWriter().write(XMLConversion.convert(currentData));
        }
        else if(contentType.equals("application/json")) {
            servletResponse.setContentType("application/json");
            servletResponse.getWriter().write(JsonConversion.convert(currentData));
        }
        else {
            servletResponse.getWriter().write(currentData.toString());
        }

    }
    private boolean isValid(String name) {
        for(int i=0; i < name.length();i++) {
            char current  = name.charAt(i);
            if((current >= 'a' && current <= 'z') ||  (current >= 'A' && current <= 'Z')) {
                continue;
            }
            else{
                return false;
            }
        }
        return true;
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
}
