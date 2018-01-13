package com.lateroad.buber.logic.command.impl;

import com.google.gson.Gson;
import com.lateroad.buber.logic.command.ICommand;
import com.lateroad.buber.logic.entity.User;
import com.lateroad.buber.logic.service.DriverService;
import com.lateroad.buber.logic.service.MapService;
import org.json.JSONException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class GetRouteInfoCommand implements ICommand {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, HttpServlet servlet) {
        MapService mapService = new MapService();
        DriverService driverService = new DriverService();
        String price = null;
        String distance = null;
        try {
            price = mapService.calculatePrice(req.getParameter("from"), req.getParameter("to"));
            distance = mapService.calculateDistance(req.getParameter("from"), req.getParameter("to"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        List<User> drivers = null;
        try {
            drivers = driverService.getNearestDrivers(req.getParameter("from"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.getSession().setAttribute("nearestDrivers", drivers);
        req.getSession().setAttribute("price", price);
        req.getSession().setAttribute("distance", distance);
        System.out.println((String)req.getSession().getAttribute("price"));
        System.out.println((List<User>)req.getSession().getAttribute("nearestDrivers"));
        System.out.println((String)req.getSession().getAttribute("distance"));
        try {
            resp.getWriter().write("{value : success}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
