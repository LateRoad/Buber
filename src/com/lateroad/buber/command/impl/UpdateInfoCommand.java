package com.lateroad.buber.command.impl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateInfoCommand implements com.lateroad.buber.command.ICommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, HttpServlet servlet) {
        System.out.println("!!!!!!");
    }
}