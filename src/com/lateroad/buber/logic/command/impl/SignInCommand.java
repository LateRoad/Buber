package com.lateroad.buber.logic.command.impl;

import com.lateroad.buber.logic.command.ICommand;
import com.lateroad.buber.logic.database.CommonDAO;
import com.lateroad.buber.logic.database.dao.ClientDAO;
import com.lateroad.buber.logic.database.dao.DriverDAO;
import com.lateroad.buber.logic.entity.User;
import com.lateroad.buber.logic.exception.UserNotFoundException;
import com.lateroad.buber.logic.service.AdminService;
import com.lateroad.buber.logic.service.ClientService;
import com.lateroad.buber.logic.service.CommonService;
import com.lateroad.buber.logic.service.DriverService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class SignInCommand implements ICommand {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, HttpServlet servlet) {
        switch ((String) req.getAttribute("role")) {
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

    private void auth(HttpServletRequest req, HttpServletResponse resp, HttpServlet servlet, CommonService service) {
        HttpSession session = req.getSession();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        RequestDispatcher requestDispatcher;

        if (login != null && password != null) {
            User user = null;
            try {
                user = service.userGetByEmailAndPassword(login, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (user != null) {
                System.out.println(user);
                session.setAttribute("user", user);

                if ("admin".equals(user.getRole())) {
                    requestDispatcher = servlet.getServletContext().getRequestDispatcher("/users.jsp");
                } else {
                    requestDispatcher = servlet.getServletContext().getRequestDispatcher("/home.jsp");
                }
                try {
                    requestDispatcher.forward(req, resp);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            resp.sendRedirect("/login.jsp?=invalid");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
