package com.lateroad.buber.switcher;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class provides switching between jsp pages. Provided for ajax request header type.
 * Automatically send response to client side.
 *
 * @author LateRoad
 * @since JDK1.8
 */
public final class JSPSwitcher {
    private static final Logger LOGGER = Logger.getLogger(JSPSwitcher.class);


    private JSPSwitcher() {
    }


    /**
     * Send a response to client side and redirect relevant jsp page.
     *
     * @param answer  sending to client side.
     * @param jspPath path to jsp page.
     */
    public static void redirect(HttpServletRequest request, HttpServletResponse response, String answer, String jspPath, int respStatus) {
        boolean ajax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));

        try {
            if (ajax) {
                response.setStatus(respStatus);
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(answer);
            } else {
                if (jspPath != null) {
                    response.sendRedirect(jspPath);
                }
            }
        } catch (IOException e) {
            LOGGER.error("ERROR: CAN'T SEND RESPONSE.", e);
        }
    }
}
