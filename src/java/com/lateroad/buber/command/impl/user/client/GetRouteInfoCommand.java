package com.lateroad.buber.command.impl.user.client;


import com.lateroad.buber.command.ICommand;
import com.lateroad.buber.entity.role.CommonUser;
import com.lateroad.buber.entity.role.Driver;
import com.lateroad.buber.exception.BuberLogicException;
import com.lateroad.buber.exception.BuberSQLException;
import com.lateroad.buber.service.MapService;
import com.lateroad.buber.service.role.DriverService;
import com.lateroad.buber.switcher.JSPSwitcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * The command which return route info such as distance, time, price and find nearest drivers
 * if there are such. Then it redirects client to relevant jsp and send response.
 *
 * @author LateRoad
 * @see ICommand
 * @since JDK1.8
 */
public class GetRouteInfoCommand implements ICommand {

    /**
     * Executes command for server. At the end of execution get possibility for
     * send response and send redirect to client.
     *
     * @param req for getting params from client.
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        MapService mapService = new MapService();
        DriverService driverService = new DriverService();
        String from = req.getParameter("fromLat") + ", " + req.getParameter("fromLng");
        String to = req.getParameter("toLat") + ", " + req.getParameter("toLng");
        String time;
        String distance;
        try {
            time = mapService.calculateDuration(from, to);
            distance = mapService.calculateDistance(from, to);
            List<Driver> drivers = null;
            drivers = driverService.getNearestDrivers(((CommonUser) req.getSession().getAttribute("user")).getLogin());

            req.getSession().setAttribute("nearestDrivers", drivers);
            req.getSession().setAttribute("time", time);
            req.getSession().setAttribute("distance", distance);
            req.getSession().setAttribute("price", calculatePrice(time, distance));
            JSPSwitcher.redirect(req, resp, "success", null, 200);
        } catch (BuberLogicException e) {
            JSPSwitcher.redirect(req, resp, e.getMessage(), null, 400);
        } catch (BuberSQLException e) {
            JSPSwitcher.redirect(req, resp, e.getMessage(), null, 500);
        }
    }

    private int calculatePrice(String time, String distance) {
        double iTime = Double.parseDouble(time.replaceAll("[\\p{L}\\s]+", ""));
        double iDistance = Double.parseDouble(distance.replaceAll("[\\p{L}\\s]+", "").replace(",", "."));
        return (int) (iTime * iDistance);
    }
}
