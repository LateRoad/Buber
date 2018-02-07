package com.lateroad.buber.command.impl;

import com.lateroad.buber.command.ICommand;
import com.lateroad.buber.exception.BuberLogicException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterCommand implements ICommand {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, HttpServlet servlet) {
        String login = req.getParameter("login");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String lastname = req.getParameter("lastname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        try {
            if ("".equals(login) && "".equals(name) && "".equals(surname) && "".equals(lastname) && "".equals(email) && "".equals(password) && "".equals(confirmPassword)) {
                if (password.equals(confirmPassword)) {

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
