package com.lateroad.buber.servlet;

import com.lateroad.buber.logic.command.CommandManager;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        CommandManager.execute(req.getParameter("action"), req, resp, this);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        CommandManager.execute(req.getParameter("action"), req, resp, this);
    }
}
