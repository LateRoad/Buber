package com.lateroad.buber.servlet;

import com.lateroad.buber.command.CommandManager;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/adminServlet")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        CommandManager.execute(req.getParameter("action"), req, resp, this);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        CommandManager.execute(req.getParameter("action"), req, resp, this);
    }
}
