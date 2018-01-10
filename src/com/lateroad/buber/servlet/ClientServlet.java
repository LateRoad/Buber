package com.lateroad.buber.servlet;

import com.lateroad.buber.logic.command.CommandManager;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClientServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("role" , "client");
        CommandManager.execute(req.getParameter("action"), req, resp, this);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("role" , "client");
        CommandManager.execute(req.getParameter("action"), req, resp, this);
    }
}
