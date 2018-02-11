package com.lateroad.buber.timer;

import com.lateroad.buber.entity.type.UserType;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;

/**
 * Service class for setting session max inactive interval for any kind of
 * users.
 *
 * @author LateRoad
 * @since JDK1.8
 */
public final class SessionTimer {
    private static final Logger LOGGER = Logger.getLogger(SessionTimer.class);

    private static final int CLIENT_INACTIVE_SESSION_TIME = 60 * 10;
    private static final int DRIVER_INACTIVE_SESSION_TIME = -1;
    private static final int ADMIN_INACTIVE_SESSION_TIME = -1;
    private static final int DEFAULT_INACTIVE_SESSION_TIME = 60 * 10;

    private SessionTimer() {
    }

    /**
     * Set session max inactive time.
     *
     * @param role role of user.
     */
    public static void setInactiveInterval(HttpSession session, UserType role) {
        switch (role) {
            case CLIENT:
                session.setMaxInactiveInterval(CLIENT_INACTIVE_SESSION_TIME);
                break;
            case DRIVER:
                session.setMaxInactiveInterval(DRIVER_INACTIVE_SESSION_TIME);
                break;
            case ADMIN:
                session.setMaxInactiveInterval(ADMIN_INACTIVE_SESSION_TIME);
                break;
            default:
                LOGGER.warn("WARNING: UNEXPECTED ROLE FIND WHILE SETTING SESSION INACTIVE TIME: " + role + ".");
                session.setMaxInactiveInterval(DEFAULT_INACTIVE_SESSION_TIME);
                break;
        }
    }
}
