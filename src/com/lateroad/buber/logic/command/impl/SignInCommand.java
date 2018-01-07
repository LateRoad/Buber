package com.lateroad.buber.logic.command.impl;

import com.lateroad.buber.logic.command.ICommand;
import com.lateroad.buber.logic.entity.User;
import com.lateroad.buber.logic.exception.UserNotFoundException;
import com.lateroad.buber.logic.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignInCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp, HttpServlet servlet) {
        UserService userService = new UserService();
        HttpSession session = req.getSession();
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (login != null && password != null) {
            User user = null;
            try {
                user = userService.userGetByEmailAndPassword(login, password);
            } catch (UserNotFoundException e) {
                e.printStackTrace();
            }
            if (user != null) {
                session.setAttribute("user", user);
                RequestDispatcher requestDispatcher = servlet.getServletContext().getRequestDispatcher("/home.jsp");

                try {
                    requestDispatcher.forward(req, resp);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return "Success";
            }
        }
        try {
            resp.sendRedirect("/login.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Fail";
    }
}
