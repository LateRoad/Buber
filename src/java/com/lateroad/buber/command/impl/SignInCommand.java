package com.lateroad.buber.command.impl;

import com.lateroad.buber.command.ICommand;
import com.lateroad.buber.entity.User;
import com.lateroad.buber.exception.BuberLogicException;
import com.lateroad.buber.service.AdminService;
import com.lateroad.buber.service.ClientService;
import com.lateroad.buber.service.DriverService;
import com.lateroad.buber.service.UserService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

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

    private void auth(HttpServletRequest req, HttpServletResponse resp, HttpServlet servlet, UserService service) {
        HttpSession session = req.getSession();
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        try {
            if (login != null && password != null) {
                User user;
                user = service.userGetByEmailAndPassword(login, password);

                if (user != null) {
                    session.setAttribute("user", user);

//                    RequestDispatcher requestDispatcher = servlet.getServletContext().getRequestDispatcher("/home.jsp");
//                    requestDispatcher.forward(req, resp);
                    resp.setStatus(200);
                    resp.getWriter().write(user.getRole());
//                    resp.sendRedirect("/home.jsp");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (BuberLogicException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
