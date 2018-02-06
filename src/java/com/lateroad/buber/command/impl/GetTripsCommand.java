package com.lateroad.buber.command.impl;

import com.lateroad.buber.command.ICommand;
import com.lateroad.buber.entity.Order;
import com.lateroad.buber.exception.BuberSQLException;
import com.lateroad.buber.model.CurrentModel;
import com.lateroad.buber.service.OrderService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetTripsCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, HttpServlet servlet) {
        OrderService orderService = new OrderService();
        try {
            List<Order> trips = orderService.findTrips(((CurrentModel) req.getSession().getAttribute("user")).getCurrentUser());
            req.setAttribute("trips", trips);

            RequestDispatcher requestDispatcher = servlet.getServletContext().getRequestDispatcher("/trips.jsp");
            requestDispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        } catch (BuberSQLException e) {
            e.printStackTrace();
        }
    }
}
