package com.lateroad.buber.servlet;

import com.lateroad.buber.command.CommandManager;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        CommandManager.execute(req.getParameter("action"), req, resp, this);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println(req.getParameter("action"));
        CommandManager.execute(req.getParameter("action"), req, resp, this);
    }
}
