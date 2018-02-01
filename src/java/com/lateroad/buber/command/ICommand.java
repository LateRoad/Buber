package com.lateroad.buber.command;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ICommand {
    void execute(HttpServletRequest req, HttpServletResponse resp, HttpServlet servlet);
}