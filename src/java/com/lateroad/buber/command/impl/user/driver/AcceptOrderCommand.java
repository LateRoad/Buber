package com.lateroad.buber.command.impl.user.driver;

import com.lateroad.buber.command.ICommand;
import com.lateroad.buber.entity.role.CommonUser;
import com.lateroad.buber.exception.BuberLogicException;
import com.lateroad.buber.exception.BuberSQLException;
import com.lateroad.buber.service.role.DriverService;
import com.lateroad.buber.switcher.JSPSwitcher;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AcceptOrderCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, HttpServlet servlet) {
        DriverService driverService = new DriverService();

        int id = Integer.parseInt(req.getParameter("id"));
        CommonUser user = (CommonUser) req.getSession().getAttribute("user");
        req.getSession().setAttribute("activeOrders", null);
        try {
            driverService.acceptOrder(user.getLogin(), id);
            JSPSwitcher.redirect(req, resp, "success", null, 200);
        }  catch (BuberLogicException e) {
            JSPSwitcher.redirect(req, resp, e, null, 400);
        } catch (BuberSQLException e) {
            JSPSwitcher.redirect(req, resp, e, null, 500);
        }
    }
}
