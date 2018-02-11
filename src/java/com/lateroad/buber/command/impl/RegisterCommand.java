package com.lateroad.buber.command.impl;

import com.google.gson.Gson;
import com.lateroad.buber.builder.role.ClientBuilder;
import com.lateroad.buber.builder.role.DriverBuilder;
import com.lateroad.buber.builder.role.RoleBuilder;
import com.lateroad.buber.command.ICommand;
import com.lateroad.buber.entity.role.CommonUser;
import com.lateroad.buber.entity.type.UserType;
import com.lateroad.buber.exception.BuberLogicException;
import com.lateroad.buber.exception.BuberSQLException;
import com.lateroad.buber.service.CommonUserService;
import com.lateroad.buber.service.role.ClientService;
import com.lateroad.buber.service.role.DriverService;
import com.lateroad.buber.switcher.JSPSwitcher;
import com.lateroad.buber.timer.SessionTimer;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The command which create new user in database and set "user" for session. Then it
 * redirects client to relevant jsp and send response.
 *
 * @author LateRoad
 * @see ICommand
 * @since JDK1.8
 */
public class RegisterCommand implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(RegisterCommand.class);

    /**
     * Executes command for server. At the end of execution get possibility for
     * send response and send redirect to client.
     *
     * @param req for getting params from client.
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        UserType role = UserType.valueOf(req.getParameter("role").toUpperCase());
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("conformPassword");

        RoleBuilder builder;
        CommonUserService service;
        try {
            CommonUser currentUser;
            switch (role) {
                case CLIENT:
                    builder = new ClientBuilder();
                    service = new ClientService();
                    break;
                case DRIVER:
                    builder = new DriverBuilder();
                    service = new DriverService();
                    break;
                default:
                    LOGGER.error("ERROR: UNEXPECTED ROLE FIND DURING REGISTRATION: " + role + ".");
                    throw new BuberLogicException("Something went wrong.");
            }
            currentUser = register(builder.build(req), password, confirmPassword, service);
            if (currentUser != null) {
                req.getSession().setAttribute("user", currentUser);
                SessionTimer.setInactiveInterval(req.getSession(), role);
                String json = new Gson().toJson(currentUser);
                JSPSwitcher.redirect(req, resp, json, "home.jsp", 200);
            }
        } catch (BuberLogicException e) {
            JSPSwitcher.redirect(req, resp, e.getMessage(), null, 400);
        } catch (BuberSQLException e) {
            JSPSwitcher.redirect(req, resp, e.getMessage(), null, 500);
        }
    }


    private CommonUser register(CommonUser user, String password, String confirmPassword, CommonUserService service) throws BuberLogicException, BuberSQLException {
        return service.registration(user, password, confirmPassword);
    }
}
