package com.lateroad.buber.command.impl.user;

import com.lateroad.buber.command.ICommand;
import com.lateroad.buber.entity.Order;
import com.lateroad.buber.entity.role.CommonUser;
import com.lateroad.buber.exception.BuberLogicException;
import com.lateroad.buber.exception.BuberSQLException;
import com.lateroad.buber.service.OrderService;
import com.lateroad.buber.switcher.JSPSwitcher;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetTripsCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, HttpServlet servlet) {
        OrderService orderService = new OrderService();
        try {
            List<Order> trips = orderService.findTrips(((CommonUser) req.getSession().getAttribute("user")));
            req.setAttribute("trips", trips);
            JSPSwitcher.redirect(req, resp, "success", "/trips.jsp");
        } catch (BuberSQLException | BuberLogicException e) {
            JSPSwitcher.redirect(req, resp, e, null);
        }
    }
}
