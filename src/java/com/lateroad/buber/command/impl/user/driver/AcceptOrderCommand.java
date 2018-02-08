package com.lateroad.buber.command.impl.user.driver;

import com.lateroad.buber.command.ICommand;
import com.lateroad.buber.entity.role.CommonUser;
import com.lateroad.buber.exception.BuberSQLException;
import com.lateroad.buber.service.role.DriverService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AcceptOrderCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, HttpServlet servlet) {
        DriverService driverService = new DriverService();

        int id = Integer.parseInt(req.getParameter("id"));
        CommonUser user = (CommonUser) req.getSession().getAttribute("user");
        req.getSession().setAttribute("activeOrders", null);
        try {
            driverService.acceptOrder(user.getLogin(), id);
            RequestDispatcher requestDispatcher = servlet.getServletContext().getRequestDispatcher("/home.jsp");
            requestDispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BuberSQLException e) {
            e.printStackTrace();
        }
    }
}
