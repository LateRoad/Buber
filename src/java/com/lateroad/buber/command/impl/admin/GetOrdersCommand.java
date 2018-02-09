package com.lateroad.buber.command.impl.admin;

import com.lateroad.buber.command.ICommand;
import com.lateroad.buber.entity.Order;
import com.lateroad.buber.exception.BuberLogicException;
import com.lateroad.buber.exception.BuberSQLException;
import com.lateroad.buber.service.role.AdminService;
import com.lateroad.buber.switcher.JSPSwitcher;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetOrdersCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, HttpServlet servlet) {
        AdminService adminService = new AdminService();
        try {
            List<Order> orders = adminService.findAllOrders();
            req.setAttribute("orders", orders);
            JSPSwitcher.redirect(req, resp, "success", "/orders.jsp");
        } catch (BuberSQLException | BuberLogicException e) {
            JSPSwitcher.redirect(req, resp, e, null);
        }
    }
}
