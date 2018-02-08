package com.lateroad.buber.servlet;

import com.lateroad.buber.command.CommandManager;
import org.apache.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet({"/commonOperation", "/userOperation"})
public class UserServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(UserServlet.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.info("Executing " + req.getParameter("action") + " action. Get request.");
        CommandManager.execute(req.getParameter("action"), req, resp, this);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.info("Executing " + req.getParameter("action") + " action. Post request.");
        CommandManager.execute(req.getParameter("action"), req, resp, this);
    }
}
