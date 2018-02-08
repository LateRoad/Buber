package com.lateroad.buber.command.impl;

import com.lateroad.buber.command.ICommand;
import com.lateroad.buber.exception.BuberLogicException;
import com.lateroad.buber.service.CommonUserService;
import com.lateroad.buber.service.role.ClientService;
import com.lateroad.buber.service.role.DriverService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

        CommonUserService service;

        try {
            if ("".equals(login) && "".equals(name) && "".equals(surname) && "".equals(password) && "".equals(confirmPassword)) {
                if (password.equals(confirmPassword)) {
                    switch (req.getParameter("role")) {
                        case "client":
                            service = new ClientService();
                            service.registration(login, password, name, surname, lastname, email);
                            break;
                        case "driver":
                            service = new DriverService();
                            service.registration(login, password, name, surname, lastname, email, carNumber);
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
        } catch (BuberLogicException e) {
            e.printStackTrace();
        }
    }
}
