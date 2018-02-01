package com.lateroad.buber.command.impl;

import com.lateroad.buber.command.ICommand;
import com.lateroad.buber.entity.User;
import com.lateroad.buber.service.DriverService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AcceptOrderCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, HttpServlet servlet) {
        DriverService driverService = new DriverService();

        int id = Integer.parseInt(req.getParameter("id"));
        User currentUser = (User) req.getSession().getAttribute("user");
        req.getSession().setAttribute("nearestDrivers", null);

        try {
            driverService.acceptOrder(currentUser.getLogin(), id);
            RequestDispatcher requestDispatcher = servlet.getServletContext().getRequestDispatcher("/home.jsp");
            requestDispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
