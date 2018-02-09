package com.lateroad.buber.switcher;

import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class JSPSwitcher {
    private static final Logger LOGGER = Logger.getLogger(JSPSwitcher.class);


    public static void redirect(HttpServletRequest request, HttpServletResponse response, String answer, String jspPath) {
        boolean ajax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));

        try {
            if (ajax) {
                response.setStatus(200);
                response.setContentType("application/json");
                response.getWriter().write(answer);
            } else {
                if (jspPath != null) {
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher(jspPath);
                    requestDispatcher.forward(request, response);
                }
            }
        } catch (IOException | ServletException e) {
            LOGGER.error("ERROR: CAN'T SEND RESPONSE.", e);
        }
    }

    public static void redirect(HttpServletRequest request, HttpServletResponse response, Exception exception, String jspPath) {
        boolean ajax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
        try {
            if (ajax) {
                response.setStatus(500);
                response.setContentType("text");
                response.getWriter().write(exception.getMessage());
            } else {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(jspPath);
                requestDispatcher.forward(request, response);
            }
        } catch (IOException | ServletException e) {
            LOGGER.error("ERROR: CAN'T SEND RESPONSE.", e);
        }
    }
}
