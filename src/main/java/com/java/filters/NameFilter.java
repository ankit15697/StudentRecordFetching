package com.java.filters;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

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
