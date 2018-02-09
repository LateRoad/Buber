package com.lateroad.buber.command.impl.user;

import com.lateroad.buber.command.ICommand;
import com.lateroad.buber.entity.type.OrderType;
import com.lateroad.buber.exception.BuberLogicException;
import com.lateroad.buber.exception.BuberSQLException;
import com.lateroad.buber.service.role.UserService;
import com.lateroad.buber.switcher.JSPSwitcher;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetOrderStatusCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, HttpServlet servlet) {
        try {
            int orderID = Integer.parseInt(req.getParameter("id"));
            OrderType status = OrderType.valueOf(req.getParameter("status"));
            UserService service = new UserService();
            service.setOrderStatus(orderID, status);
            JSPSwitcher.redirect(req, resp, "success", null);
        } catch (BuberSQLException | BuberLogicException e) {
            JSPSwitcher.redirect(req, resp, e, null);
        }
    }
}
