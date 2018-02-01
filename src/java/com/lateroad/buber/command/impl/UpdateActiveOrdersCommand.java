package com.lateroad.buber.command.impl;

import com.lateroad.buber.command.ICommand;
import com.lateroad.buber.entity.Order;
import com.lateroad.buber.entity.User;
import com.lateroad.buber.service.OrderService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UpdateActiveOrdersCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, HttpServlet servlet) {
        OrderService orderService = new OrderService();
        User driver = (User) req.getSession().getAttribute("user");

        try {
            List<Order> activeOrders = orderService.findActiveOrders(driver);
            req.getSession().setAttribute("activeOrders", activeOrders);
            System.out.println(activeOrders);
            resp.getWriter().write("{value : success}");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
