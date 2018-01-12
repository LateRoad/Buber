package com.lateroad.buber.logic.command.impl;

import com.lateroad.buber.logic.command.ICommand;
import com.lateroad.buber.logic.entity.Order;
import com.lateroad.buber.logic.entity.User;
import com.lateroad.buber.logic.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetTripsCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, HttpServlet servlet) {
        UserService userService = new UserService();
        try {
            List<Order> trips = userService.findTrips((User) req.getSession().getAttribute("user"));
            req.setAttribute("trips", trips);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            RequestDispatcher requestDispatcher = servlet.getServletContext().getRequestDispatcher("/trips.jsp");
            requestDispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
