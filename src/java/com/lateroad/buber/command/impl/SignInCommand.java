package com.lateroad.buber.command.impl;

import com.lateroad.buber.command.ICommand;
import com.lateroad.buber.entity.role.CommonUser;
import com.lateroad.buber.entity.type.UserType;
import com.lateroad.buber.exception.BuberLogicException;
import com.lateroad.buber.exception.BuberSQLException;
import com.lateroad.buber.service.CommonUserService;
import com.lateroad.buber.service.role.AdminService;
import com.lateroad.buber.service.role.ClientService;
import com.lateroad.buber.service.role.DriverService;
import com.lateroad.buber.switcher.JSPSwitcher;
import com.lateroad.buber.timer.SessionTimer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The command which find user in database and set "user" for session. Then it
 * redirects client to relevant jsp and send response.
 *
 * @author LateRoad
 * @see ICommand
 * @since JDK1.8
 */
public class SignInCommand implements ICommand {

    /**
     * Executes command for server. At the end of execution get possibility for
     * send response and send redirect to client.
     *
     * @param req for getting params from client.
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        switch (UserType.valueOf(req.getParameter("role").toUpperCase())) {
            case CLIENT:
                auth(req, resp, new ClientService());
                break;
            case DRIVER:
                auth(req, resp, new DriverService());
                break;
            case ADMIN:
                auth(req, resp, new AdminService());
                break;
            default:
                //logger
                break;
        }
    }

    private void auth(HttpServletRequest req, HttpServletResponse resp, CommonUserService service) {
        HttpSession session = req.getSession();
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        try {
            if (login != null && password != null) {
                CommonUser currentUser = service.authentication(login, password);
                if (currentUser != null) {
                    SessionTimer.setInactiveInterval(session, currentUser.getRole());
                    UserType role = currentUser.getRole();
                    session.setAttribute("user", currentUser);
                    if (UserType.ADMIN.equals(role)) {
                        JSPSwitcher.redirect(req, resp, role.name(), "/clients.jsp",200);
                    } else {
                        JSPSwitcher.redirect(req, resp, role.name(), "/home.jsp", 200);
                    }
                }
            }
        }  catch (BuberLogicException e) {
            JSPSwitcher.redirect(req, resp, e.getMessage(), null, 400);
        } catch (BuberSQLException e) {
            JSPSwitcher.redirect(req, resp, e.getMessage(), null, 500);
        }
    }
}
