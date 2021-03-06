package com.lateroad.buber.command.impl.admin;

import com.lateroad.buber.command.ICommand;
import com.lateroad.buber.entity.role.Client;
import com.lateroad.buber.exception.BuberLogicException;
import com.lateroad.buber.exception.BuberSQLException;
import com.lateroad.buber.service.role.AdminService;
import com.lateroad.buber.switcher.JSPSwitcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * The command which return all <code>Client</code> objects for client. Then it
 * redirects client to relevant jsp and send response.
 *
 * @author LateRoad
 * @see ICommand
 * @since JDK1.8
 */
public class GetClientsCommand implements ICommand {

    /**
     * Executes command for server. At the end of execution get possibility for
     * send response and send redirect to client.
     *
     * @param req for getting params from client.
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        AdminService adminService = new AdminService();
        try {
            List<Client> clients = adminService.findAllClients();
            req.getSession().setAttribute("clients", clients);
            JSPSwitcher.redirect(req, resp, "success", "/clients.jsp", 200);
        } catch (BuberSQLException | BuberLogicException e) {
            JSPSwitcher.redirect(req, resp, e.getMessage(), null, 500);
        }
    }
}
