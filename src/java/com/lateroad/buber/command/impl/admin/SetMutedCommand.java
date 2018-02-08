package com.lateroad.buber.command.impl.admin;

import com.lateroad.buber.command.ICommand;
import com.lateroad.buber.exception.BuberSQLException;
import com.lateroad.buber.service.role.AdminService;

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
        } catch (BuberSQLException e) {
            e.printStackTrace();
        }
    }
}
