package com.lateroad.buber.command.impl;

import com.lateroad.buber.command.ICommand;
import com.lateroad.buber.entity.role.CommonUser;
import com.lateroad.buber.exception.BuberLogicException;
import com.lateroad.buber.exception.BuberSQLException;
import com.lateroad.buber.service.CommonUserService;
import com.lateroad.buber.service.role.ClientService;
import com.lateroad.buber.switcher.JSPSwitcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The command which set user offline in database and invalidate session. Then it
 * redirects client to relevant jsp and send response.
 *
 * @author LateRoad
 * @see ICommand
 * @since JDK1.8
 */
public class SignOutCommand implements ICommand {

    /**
     * Executes command for server. At the end of execution get possibility for
     * send response and send redirect to client.
     *
     * @param req for getting params from client.
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            CommonUser user = (CommonUser) req.getSession().getAttribute("user");
            CommonUserService commonUserService = new ClientService();
            if (user != null) {
                commonUserService.setOnline(user.getLogin(), false);
            }
            req.getSession().invalidate();
            JSPSwitcher.redirect(req, resp, "success", "/index.jsp", 200);
        } catch (BuberLogicException e) {
            JSPSwitcher.redirect(req, resp, e.getMessage(), null, 400);
        } catch (BuberSQLException e) {
            JSPSwitcher.redirect(req, resp, e.getMessage(), null, 500);
        }
    }

    public void execute(HttpSession session) {
        try {
            CommonUser user = (CommonUser) session.getAttribute("user");
            CommonUserService commonUserService = new ClientService();
            commonUserService.setOnline(user.getLogin(), false);
            session.invalidate();
        } catch (BuberSQLException | BuberLogicException e) {

        }
    }
}
