package com.lateroad.buber.filter;

import com.lateroad.buber.entity.role.CommonUser;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebFilter(urlPatterns = {"*.jsp", "/userOperation", "/commonOperation"},
        initParams = {@WebInitParam(name = "INDEX_PATH", value = "/index.jsp"),
                @WebInitParam(name = "ERROR_PATH", value = "/error.jsp")})
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
            "/userOperation",
            "commonOperation");

    private static final List<String> clientFilterPaths = Arrays.asList(
            "/index.jsp",
            "/home.jsp",
            "/payment.jsp",
            "/profile.jsp",
            "/trips.jsp",
            "/userOperation",
            "commonOperation");

    private static final List<String> driverFilterPaths = Arrays.asList(
            "/index.jsp",
            "/home.jsp",
            "/car.jsp",
            "/payment.jsp",
            "/profile.jsp",
            "/trips.jsp",
            "/userOperation",
            "commonOperation");

    private static final List<String> guestFilterPaths = Arrays.asList(
            "/index.jsp",
            "/auth-admin.jsp",
            "/auth-driver.jsp",
            "/auth-client.jsp",
            "/signin.jsp",
            "/commonOperation");


    private String indexPath;
    private String errorPath;


    public void init(FilterConfig filterConfig) {
        indexPath = filterConfig.getInitParameter("INDEX_PATH");
        errorPath = filterConfig.getInitParameter("ERROR_PATH");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        CommonUser user = (CommonUser) session.getAttribute("user");

        String uri = req.getRequestURI();
        logger.info("URI: " + uri + req.getMethod());

        try {
            if ("/".equals(uri)) {
                req.getRequestDispatcher(indexPath).forward(req, resp);
                return;
            }

            if ("/userOperation".equals(uri) && user == null) {
                req.getRequestDispatcher(indexPath).forward(req, resp);
                return;
            }


            if (user == null) {
                if (!guestFilterPaths.contains(uri)) {
                    req.getRequestDispatcher(errorPath).forward(req, resp);
                    return;
                }
            }

            if (user != null) {
                if (guestFilterPaths.contains(uri)) {
                    filterChain.doFilter(req, resp);
                    return;
                }

                switch (user.getRole()) {
                    case CLIENT:
                        if (!clientFilterPaths.contains(uri)) {
                            req.getRequestDispatcher(errorPath).forward(req, resp);
                            return;
                        }
                        break;
                    case DRIVER:
                        if (!driverFilterPaths.contains(uri)) {
                            req.getRequestDispatcher(errorPath).forward(req, resp);
                            filterChain.doFilter(req, resp);
                            return;
                        }
                        break;
                    case ADMIN:
                        if (!adminFilterPaths.contains(uri)) {
                            req.getRequestDispatcher(errorPath).forward(req, resp);
                            return;
                        }
                        break;
                    default:
                        break;
                }
            }
            filterChain.doFilter(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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