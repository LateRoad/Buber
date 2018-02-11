package com.lateroad.buber.command.impl.user;

import com.lateroad.buber.command.ICommand;
import com.lateroad.buber.entity.type.OrderType;
import com.lateroad.buber.exception.BuberLogicException;
import com.lateroad.buber.exception.BuberSQLException;
import com.lateroad.buber.service.role.UserService;
import com.lateroad.buber.switcher.JSPSwitcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The command which set order status. Then it
 * redirects client to relevant jsp and send response.
 *
 * @author LateRoad
 * @see ICommand
 * @see OrderType
 * @since JDK1.8
 */
public class SetOrderStatusCommand implements ICommand {

    /**
     * Executes command for server. At the end of execution get possibility for
     * send response and send redirect to client.
     *
     * @param req for getting params from client.
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int orderID = Integer.parseInt(req.getParameter("id"));
            OrderType status = OrderType.valueOf(req.getParameter("status"));
            UserService service = new UserService();
            service.setOrderStatus(orderID, status);
            JSPSwitcher.redirect(req, resp, "success", null, 200);
        } catch (BuberLogicException e) {
            JSPSwitcher.redirect(req, resp, e.getMessage(), null, 400);
        } catch (BuberSQLException e) {
            JSPSwitcher.redirect(req, resp, e.getMessage(), null, 500);
        }
    }
}
