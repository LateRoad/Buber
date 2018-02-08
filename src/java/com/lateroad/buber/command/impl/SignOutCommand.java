package com.lateroad.buber.command.impl;

import com.lateroad.buber.command.ICommand;
import com.lateroad.buber.entity.role.CommonUser;
import com.lateroad.buber.exception.BuberSQLException;
import com.lateroad.buber.service.CommonUserService;
import com.lateroad.buber.service.role.ClientService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignOutCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, HttpServlet servlet) {
        try {
            CommonUser user = (CommonUser) req.getSession().getAttribute("user");
            CommonUserService commonUserService = new ClientService();
            if (user != null) {
                commonUserService.setOnline(user.getLogin(), false);
            }
            req.getSession().invalidate();
            RequestDispatcher requestDispatcher = servlet.getServletContext().getRequestDispatcher("/index.jsp");
            requestDispatcher.forward(req, resp);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BuberSQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    public void execute(HttpSession session) {
        try {
            CommonUser user = (CommonUser) session.getAttribute("user");
            CommonUserService commonUserService = new ClientService();
            commonUserService.setOnline(user.getLogin(), false);
            session.invalidate();
        } catch (BuberSQLException e) {
            e.printStackTrace();
        }
    }
}
