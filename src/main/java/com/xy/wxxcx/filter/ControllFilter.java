package com.xy.wxxcx.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ControllFilter implements Filter {
    private static Logger logger = Logger.getLogger(ControllFilter.class);


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE,PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.addHeader("Access-Control-Allow-Headers", "x-requested-with,Content-Type,token");
        chain.doFilter(request, res);

    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }



}