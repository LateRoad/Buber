package com.lateroad.buber.command.impl.user.client;

import com.lateroad.buber.command.ICommand;
import com.lateroad.buber.entity.role.CommonUser;
import com.lateroad.buber.exception.BuberLogicException;
import com.lateroad.buber.exception.BuberSQLException;
import com.lateroad.buber.service.role.ClientService;
import com.lateroad.buber.switcher.JSPSwitcher;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TakeTaxiCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, HttpServlet servlet) {
        ClientService clientService = new ClientService();

        String driver = req.getParameter("driver");
        String money = req.getParameter("money");
        CommonUser currentUser = (CommonUser) req.getSession().getAttribute("user");
        req.getSession().setAttribute("nearestDrivers", null);
        try {
            clientService.takeTaxi(currentUser.getLogin(), driver, money);
            JSPSwitcher.redirect(req, resp, "success", null);
        } catch (BuberSQLException | BuberLogicException e) {
            JSPSwitcher.redirect(req, resp, e, null);
        }
    }
}
