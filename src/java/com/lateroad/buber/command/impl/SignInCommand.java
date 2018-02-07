package com.lateroad.buber.command.impl;

import com.lateroad.buber.command.ICommand;
import com.lateroad.buber.entity.type.UserType;
import com.lateroad.buber.exception.BuberSQLException;
import com.lateroad.buber.model.CurrentModel;
import com.lateroad.buber.service.CommonUserService;
import com.lateroad.buber.service.role.AdminService;
import com.lateroad.buber.service.role.ClientService;
import com.lateroad.buber.service.role.DriverService;

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
                CurrentModel user = service.authentication(login, password);
                if (user != null) {
                    session.setAttribute("user", user);

//                    RequestDispatcher requestDispatcher = servlet.getServletContext().getRequestDispatcher("/home.jsp");
//                    requestDispatcher.forward(req, resp);
                    resp.setStatus(200);
                    resp.getWriter().write(user.getCurrentUser().getRole().toString());
                    if (user.getCurrentUser().getRole().equals(UserType.CLIENT)) {
                        session.setMaxInactiveInterval(60);
                    }
                }
            }
        } catch (BuberSQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
