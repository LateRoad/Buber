package com.lateroad.buber.command.impl;

import com.lateroad.buber.command.ICommand;
import com.lateroad.buber.entity.role.CommonUser;
import com.lateroad.buber.entity.type.UserType;
import com.lateroad.buber.exception.BuberLogicException;
import com.lateroad.buber.exception.BuberSQLException;
import com.lateroad.buber.service.CommonUserService;
import com.lateroad.buber.service.role.ClientService;
import com.lateroad.buber.service.role.DriverService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterCommand implements ICommand {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, HttpServlet servlet) {
        String login = req.getParameter("login");
        String role = req.getParameter("role");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String lastname = req.getParameter("lastname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        String carNumber = req.getParameter("carNumber");
        String phoneNumber = req.getParameter("phoneNumber");

        CommonUserService service;
        CommonUser user = null;

        try {
            if (!"".equals(login) && !"".equals(name) && !"".equals(surname) && !"".equals(password) && !"".equals(confirmPassword)) {
                if (password.equals(confirmPassword)) {
                    switch (role) {
                        case "client":
                            service = new ClientService();
                            user = service.registration(login, password, name, surname, lastname, email, phoneNumber);
                            break;
                        case "driver":
                            service = new DriverService();
                            user = service.registration(login, password, name, surname, lastname, email, phoneNumber, carNumber);
                            break;
                        default:
                            //logger
                            break;
                    }
                } else {
                    throw new BuberLogicException("Passwords are different.");
                }
            } else {
                throw new BuberLogicException("Fill in all fields.");
            }
            if (user != null) {
                req.getSession().setAttribute("user", user);
                resp.setStatus(200);
                resp.getWriter().write(user.getRole().toString());
                if (user.getRole().equals(UserType.CLIENT)) {
                    req.getSession().setMaxInactiveInterval(60);
                }
            }
        } catch (BuberLogicException e) {
            e.printStackTrace();
        } catch (BuberSQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
