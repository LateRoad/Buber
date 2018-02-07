package com.lateroad.buber.command.impl;

import com.lateroad.buber.command.ICommand;
import com.lateroad.buber.exception.BuberSQLException;
import com.lateroad.buber.model.CurrentModel;
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
            CurrentModel currentUser = (CurrentModel) req.getSession().getAttribute("user");
            CommonUserService commonUserService = new ClientService();
            if (currentUser != null) {
                commonUserService.setOnline(currentUser.getCurrentUser().getLogin(), false);
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
            CurrentModel currentUser = (CurrentModel) session.getAttribute("user");
            CommonUserService commonUserService = new ClientService();
            commonUserService.setOnline(currentUser.getCurrentUser().getLogin(), false);
            session.invalidate();
        } catch (BuberSQLException e) {
            e.printStackTrace();
        }
    }
}
