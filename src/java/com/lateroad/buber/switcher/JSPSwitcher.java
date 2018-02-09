package com.lateroad.buber.switcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JSPSwitcher {

    public static void redirect(HttpServletRequest request, HttpServletResponse response, String json, String jspPath) {
        boolean ajax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));

        try {
            if (ajax) {
                response.setStatus(200);
                response.setContentType("application/json");
                response.getWriter().write(json);
            } else {
                // Handle regular (JSP) response.
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
