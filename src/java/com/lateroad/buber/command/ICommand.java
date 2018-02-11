package com.lateroad.buber.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * An object that represent a command for server. Using for CommandMap.
 *
 * @author LateRoad
 * @see CommandMap
 * @since JDK1.8
 */
@FunctionalInterface
public interface ICommand {

    /**
     * Executes command for server. At the end of execution get possibility for
     * send response and send redirect to client.
     *
     * @param req for getting params from client.
     *
     */
    void execute(HttpServletRequest req, HttpServletResponse resp);
}