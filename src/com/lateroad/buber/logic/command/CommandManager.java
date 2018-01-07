package com.lateroad.buber.logic.command;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandManager {
    private static CommandMap commandMap = CommandMap.getInstance();


    public static String execute(String action, HttpServletRequest req, HttpServletResponse resp, HttpServlet servlet) {
        return commandMap.getCommandsMap(action).execute(req, resp, servlet);
    }
}
