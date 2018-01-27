package com.lateroad.buber.filter;

import com.lateroad.buber.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AuthFilter implements Filter {

    private List<String> pathFilters = Arrays.asList(new String[]{"/home.html", "/home.jsp"});

    public AuthFilter() {

    }

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String uri = ((HttpServletRequest) request).getRequestURI();
        HttpSession session = ((HttpServletRequest) request).getSession();
//        if("/index.jsp".equals(uri) || "/".equals(uri)) {
//            System.out.println(getClientIp((HttpServletRequest)request));
//            if("0:0:0:0:0:0:0:1".equals(getClientIp((HttpServletRequest)request))){
//                ((HttpServletResponse)response).sendRedirect("/auth-admin.jsp");
//                return;
//            }
//        }

        User user = (User)session.getAttribute("user");
        if(user != null){
            if("/signin.html".equals(uri) || "/signin.jsp".equals(uri)){
                ((HttpServletResponse)response).sendRedirect("/index.jsp");
                return;
            }
            else{
                filterChain.doFilter(request, response);
                return;
            }
        }
        if (!pathFilters.contains(uri)) {
            filterChain.doFilter(request, response);
            return;
        }

//?????
        ((HttpServletResponse)response).sendRedirect("signin.jsp?=invalid");

    }

    public void destroy() {

    }

    private static String getClientIp(HttpServletRequest request) {

        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }
}
