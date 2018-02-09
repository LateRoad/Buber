package com.lateroad.buber.command.impl.admin;

import com.lateroad.buber.command.ICommand;
import com.lateroad.buber.exception.BuberLogicException;
import com.lateroad.buber.exception.BuberSQLException;
import com.lateroad.buber.service.role.AdminService;
import com.lateroad.buber.switcher.JSPSwitcher;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetMutedCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, HttpServlet servlet) {
        try {
            boolean status = Boolean.parseBoolean(req.getParameter("status"));
            String login = req.getParameter("user");
            AdminService adminService = new AdminService();
            adminService.setMuted(login, status);
            JSPSwitcher.redirect(req, resp, "success", null, 200);
        } catch (BuberSQLException | BuberLogicException e) {
            JSPSwitcher.redirect(req, resp, e, null, 500);
        }
    }
}
