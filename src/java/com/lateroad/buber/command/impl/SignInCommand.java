package com.lateroad.buber.command.impl;

import com.lateroad.buber.command.ICommand;
import com.lateroad.buber.entity.role.CommonUser;
import com.lateroad.buber.exception.BuberLogicException;
import com.lateroad.buber.exception.BuberSQLException;
import com.lateroad.buber.service.CommonUserService;
import com.lateroad.buber.service.role.AdminService;
import com.lateroad.buber.service.role.ClientService;
import com.lateroad.buber.service.role.DriverService;
import com.lateroad.buber.timer.SessionTimer;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignInCommand implements ICommand {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, HttpServlet servlet) {
        switch (req.getParameter("role")) {
            case "client":
                auth(req, resp, servlet, new ClientService());
                break;
            case "driver":
                auth(req, resp, servlet, new DriverService());
                break;
            case "admin":
                auth(req, resp, servlet, new AdminService());
                break;
            default:
                //logger
                break;
        }
    }

    private void auth(HttpServletRequest req, HttpServletResponse resp, HttpServlet servlet, CommonUserService service) {
        HttpSession session = req.getSession();
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        try {
            if (login != null && password != null) {
                CommonUser user = service.authentication(login, password);
                if (user != null) {
                    session.setAttribute("user", user);
                    resp.setStatus(200);
                    resp.getWriter().write(user.getRole().toString());
                    SessionTimer.setInactiveInterval(session, user.getRole());
                }
            }
        } catch (BuberSQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BuberLogicException e) {
            e.printStackTrace();
        }
    }
}
