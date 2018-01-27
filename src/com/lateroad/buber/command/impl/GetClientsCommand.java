package com.lateroad.buber.command.impl;

import com.lateroad.buber.command.ICommand;
import com.lateroad.buber.entity.User;
import com.lateroad.buber.service.AdminService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class GetClientsCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, HttpServlet servlet) {
        AdminService adminService = new AdminService();
        try {
            List<User> clients = adminService.findAllClients();
            req.setAttribute("clients", clients);

            RequestDispatcher requestDispatcher = servlet.getServletContext().getRequestDispatcher("/clients.jsp");
            requestDispatcher.forward(req, resp);
        } catch (ServletException | SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
