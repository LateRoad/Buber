package com.lateroad.buber.logic.command.impl;

import com.lateroad.buber.logic.command.ICommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignOutCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp, HttpServlet servlet) {
        req.getSession().invalidate();
        RequestDispatcher requestDispatcher = servlet.getServletContext().getRequestDispatcher("/index.jsp");
        try {
            requestDispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
            return "Fail";
        } catch (IOException e) {
            e.printStackTrace();
            return "Fail";
        }
        return "Success";
    }
}
