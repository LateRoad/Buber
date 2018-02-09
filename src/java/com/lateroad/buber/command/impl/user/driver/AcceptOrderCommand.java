package com.lateroad.buber.command.impl.user.driver;

import com.lateroad.buber.command.ICommand;
import com.lateroad.buber.database.dao.OrderDAO;
import com.lateroad.buber.entity.role.CommonUser;
import com.lateroad.buber.entity.role.User;
import com.lateroad.buber.entity.type.OrderType;
import com.lateroad.buber.exception.BuberLogicException;
import com.lateroad.buber.exception.BuberSQLException;
import com.lateroad.buber.service.role.DriverService;
import com.lateroad.buber.switcher.JSPSwitcher;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class AcceptOrderCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, HttpServlet servlet) {
        DriverService driverService = new DriverService();
        Map<Integer, User> orderMap = (Map<Integer, User>) req.getSession().getAttribute("activeOrders");
        int id = Integer.parseInt(req.getParameter("id"));
        CommonUser user = (CommonUser) req.getSession().getAttribute("user");
        try {
            for (Map.Entry<Integer, User> entry : orderMap.entrySet()) {
                OrderDAO.getInstance().update(entry.getKey(), OrderType.CANCELLED);
            }
            driverService.acceptOrder(user.getLogin(), id);
            req.getSession().setAttribute("activeOrders", null);
            JSPSwitcher.redirect(req, resp, "success", null, 200);
        } catch (BuberLogicException e) {
            JSPSwitcher.redirect(req, resp, e, null, 400);
        } catch (BuberSQLException e) {
            JSPSwitcher.redirect(req, resp, e, null, 500);
        }
    }
}
