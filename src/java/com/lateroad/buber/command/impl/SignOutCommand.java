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
import java.io.IOException;

public class SignOutCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, HttpServlet servlet) {
        try {
            CurrentModel currentUser = (CurrentModel) req.getSession().getAttribute("user");
            CommonUserService commonUserService = new ClientService();

            commonUserService.setOnline(currentUser.getCurrentUser().getLogin(), false);
            req.getSession().invalidate();
            RequestDispatcher requestDispatcher = servlet.getServletContext().getRequestDispatcher("/index.jsp");
            requestDispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        } catch (BuberSQLException e) {
            e.printStackTrace();
        }
    }
}
