package com.example.javaservletassignment.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        HttpSession session = httpServletRequest.getSession();
        if (session.getAttribute("currentLogin") != null
                && session.getAttribute("isAdmin") != null) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            httpServletResponse.getWriter().println("No Permission!");
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
