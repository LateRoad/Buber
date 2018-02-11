package com.lateroad.buber.command.impl;

import com.lateroad.buber.command.ICommand;
import com.lateroad.buber.switcher.JSPSwitcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The command which set current locale for "translation" ResourceBundle. Then it
 * redirects client to relevant jsp and send response.
 *
 * @author LateRoad
 * @see ICommand
 * @since JDK1.8
 */
public class ChangeLanguage implements ICommand {

    /**
     * Executes command for server. At the end of execution get possibility for
     * send response and send redirect to client.
     *
     * @param req for getting params from client.
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        String language = req.getParameter("lang");
        req.getSession().setAttribute("language", language);
        JSPSwitcher.redirect(req, resp, "success", null, 200);
    }
}
