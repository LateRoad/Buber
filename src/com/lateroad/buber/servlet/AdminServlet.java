package com.lateroad.buber.servlet;

import com.lateroad.buber.command.CommandManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("role" , "admin");
        CommandManager.execute(req.getParameter("action"), req, resp, this);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("role" , "admin");
        CommandManager.execute(req.getParameter("action"), req, resp, this);
    }
}
