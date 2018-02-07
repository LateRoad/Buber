package com.lateroad.buber.command.impl.user.client;

import com.lateroad.buber.command.ICommand;
import com.lateroad.buber.entity.role.User;
import com.lateroad.buber.exception.BuberSQLException;
import com.lateroad.buber.service.role.ClientService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TakeTaxiCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, HttpServlet servlet) {
        ClientService clientService = new ClientService();

        String driver = req.getParameter("driver");
        String money = req.getParameter("money");
        User currentUser = (User) req.getSession().getAttribute("user");
        req.getSession().setAttribute("nearestDrivers", null);

        try {
            clientService.takeTaxi(currentUser.getLogin(), driver, money);
            RequestDispatcher requestDispatcher = servlet.getServletContext().getRequestDispatcher("/home.jsp");
            requestDispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BuberSQLException e) {
            e.printStackTrace();
        }
    }
}
