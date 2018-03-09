package com.lateroad.buber.command;

import com.lateroad.buber.exception.BuberLogicException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class kind of executor, which execute command based on action param.
 *
 * @author LateRoad
 * @see CommandMap
 * @since JDK1.8
 */
public class CommandManager {
    private static CommandMap commandMap = CommandMap.getInstance();

    /**
     * Executes command for server. At the end of execution get possibility for
     * send response and send redirect to client.
     *
     * @param action the executing command depends on this param.
     */
    public static void execute(String action, HttpServletRequest req, HttpServletResponse resp) throws BuberLogicException {
        commandMap.getCommand(action).execute(req, resp);
    }
}
