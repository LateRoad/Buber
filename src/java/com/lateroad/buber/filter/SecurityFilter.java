package com.lateroad.buber.filter;

import com.lateroad.buber.model.CurrentModel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class SecurityFilter implements Filter {
    static {
        new DOMConfigurator().doConfigure("log4j2.xml", LogManager.getLoggerRepository());
    }

    private static final Logger logger = Logger.getLogger(SecurityFilter.class);

    private static final List<String> adminFilterPaths = Arrays.asList(
            "/index.jsp",
            "/orders.jsp",
            "/clients.jsp",
            "/drivers.jsp",
            "/adminServlet");

    private static final List<String> clientFilterPaths = Arrays.asList(
            "/index.jsp",
            "/home.jsp",
            "/payment.jsp",
            "/profile.jsp",
            "/trips.jsp",
            "/userServlet");

    private static final List<String> driverFilterPaths = Arrays.asList(
            "/index.jsp",
            "/home.jsp",
            "/car.jsp",
            "/payment.jsp",
            "/profile.jsp",
            "/trips.jsp",
            "/userServlet");

    private static final List<String> beforeAuthFilter = Arrays.asList(
            "/index.jsp",
            "/auth-admin.jsp",
            "/auth-driver.jsp",
            "/auth-client.jsp",
            "/signin.jsp");

    private static final List<String> serviceUriPaths = Arrays.asList(
            "/favicon.ico",
            "/src",
            "/commonOperation");


    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String errorJSP = "/error.jsp";
        String indexJSP = "/index.jsp";
        String uri = ((HttpServletRequest) request).getRequestURI();
        HttpSession session = ((HttpServletRequest) request).getSession();

        logger.info("URI: " + uri + ((HttpServletRequest) request).getMethod());
        CurrentModel user = (CurrentModel) session.getAttribute("user");

        if ("/".equals(uri)) {
//            RequestDispatcher dispatcher = request.getRequestDispatcher(indexJSP);
//            dispatcher.forward(request, response);
            filterChain.doFilter(request, response);
            return;
        }

        for (String serviceUri : serviceUriPaths) {
            if (uri.contains(serviceUri)) {
                filterChain.doFilter(request, response);
                return;
            }
        }

        if (user == null) {
            if (!beforeAuthFilter.contains(uri)) {
                RequestDispatcher dispatcher = request.getRequestDispatcher(errorJSP);
                dispatcher.forward(request, response);
                return;
            }
        }

        if (user != null) {
            switch (user.getCurrentUser().getRole()) {
                case CLIENT:
                    if (!clientFilterPaths.contains(uri)) {
                        RequestDispatcher dispatcher = request.getRequestDispatcher(errorJSP);
                        dispatcher.forward(request, response);
                        return;
                    }
                    break;
                case DRIVER:
                    if (!driverFilterPaths.contains(uri)) {
                        RequestDispatcher dispatcher = request.getRequestDispatcher(errorJSP);
                        dispatcher.forward(request, response);
                        return;
                    }
                    break;
                case ADMIN:
                    if (!adminFilterPaths.contains(uri)) {
                        RequestDispatcher dispatcher = request.getRequestDispatcher(errorJSP);
                        dispatcher.forward(request, response);
                        return;
                    }
                    break;
                default:
                    break;
            }
        }

        filterChain.doFilter(request, response);
//
//        if(user != null){
//            if("/signin.html".equals(uri) || "/signin.jsp".equals(uri)){
//                ((HttpServletResponse)response).sendRedirect("/index.jsp");
//                return;
//            }
//            else{
//                filterChain.doFilter(request, response);
//                return;
//            }
//        }
//        if (!pathFilters.contains(uri)) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
////?????
//        ((HttpServletResponse)response).sendRedirect("signin.jsp?=invalid");

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

//        if("/index.jsp".equals(uri) || "/".equals(uri)) {
//            System.out.println(getClientIp((HttpServletRequest)request));
//            if("0:0:0:0:0:0:0:1".equals(getClientIp((HttpServletRequest)request))){
//                ((HttpServletResponse)response).sendRedirect("/auth-admin.jsp");
//                return;
//            }
//        }