package com.lateroad.buber.command.impl.user;

import com.lateroad.buber.command.ICommand;
import com.lateroad.buber.entity.role.CommonUser;
import com.lateroad.buber.exception.BuberSQLException;
import com.lateroad.buber.service.role.ClientService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetCurrentLocation implements ICommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, HttpServlet servlet) {
        try {
            String lat = req.getParameter("lat");
            String lng = req.getParameter("lng");
            CommonUser user = (CommonUser) req.getSession().getAttribute("user");
            ClientService service = new ClientService();
            service.setLocation(user.getLogin(), lat, lng);
        } catch (BuberSQLException e) {
            e.printStackTrace();
        }
    }
}
