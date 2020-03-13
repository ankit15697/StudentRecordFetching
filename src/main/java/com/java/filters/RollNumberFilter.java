package com.java.filters;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class RollNumberFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        RequestDispatcher rdObj = null;

        String searchRoll = servletRequest.getParameter("roll");
        PrintWriter out = servletResponse.getWriter();
        String rollNo = servletRequest.getParameter("rollNo");
        if( searchRoll !=null && isValid(searchRoll)) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else if(searchRoll!=null){
            servletResponse.getWriter().write("Error 400 Bad Request !!! ");
            rdObj = servletRequest.getRequestDispatcher("/index.html");
            rdObj.include(servletRequest, servletResponse);
        }
        else if( rollNo !=null && isValid(rollNo)) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else if(rollNo!=null ){
            servletResponse.getWriter().write("Error 400 Bad Request !!! ");
            rdObj = servletRequest.getRequestDispatcher("/index.html");
            rdObj.include(servletRequest, servletResponse);
        }
        else {
            servletResponse.getWriter().write("Error 400 Bad Request !!! ");
            rdObj = servletRequest.getRequestDispatcher("/index.html");
            rdObj.include(servletRequest, servletResponse);
        }

    }

    public void destroy() {

    }
    private boolean isValid(String roll) {
        if(roll.length() < 3) {
            return  false;
        }
        if(roll.charAt(0) == 'R' && roll.charAt(1) == 'N') {
            for (int i=2; i < roll.length(); i++) {
                if(!isNumeric(roll.charAt(i))) {
                    return false;
                }
            }
        }
        else {
            return false;
        }
        return true;
    }
    private boolean isNumeric(char ch) {
        if(ch>='0' && ch<='9') {
            return  true;
        }
        return false;
    }
}
