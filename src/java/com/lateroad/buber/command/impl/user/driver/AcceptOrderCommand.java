package com.lateroad.buber.command.impl.user.driver;

import com.lateroad.buber.command.ICommand;
import com.lateroad.buber.database.dao.OrderDAO;
import com.lateroad.buber.entity.role.CommonUser;
import com.lateroad.buber.entity.role.User;
import com.lateroad.buber.entity.type.OrderType;
import com.lateroad.buber.exception.BuberLogicException;
import com.lateroad.buber.exception.BuberSQLException;
import com.lateroad.buber.service.role.DriverService;
import com.lateroad.buber.switcher.JSPSwitcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * The command which set order status as ACCEPTED. Then it
 * redirects client to relevant jsp and send response.
 *
 * @author LateRoad
 * @see ICommand
 * @see OrderType
 * @since JDK1.8
 */
public class AcceptOrderCommand implements ICommand {

    /**
     * Executes command for server. At the end of execution get possibility for
     * send response and send redirect to client.
     *
     * @param req for getting params from client.
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        DriverService driverService = new DriverService();
        Map<Integer, User> orderMap = (Map<Integer, User>) req.getSession().getAttribute("activeOrders");
        int id = Integer.parseInt(req.getParameter("id"));
        CommonUser user = (CommonUser) req.getSession().getAttribute("user");
        try {
            for (Map.Entry<Integer, User> entry : orderMap.entrySet()) {
                OrderDAO.getInstance().update(entry.getKey(), OrderType.CANCELLED);
            }
            driverService.acceptOrder(user.getLogin(), id);
            req.getSession().setAttribute("activeOrders", null);
            JSPSwitcher.redirect(req, resp, "success", null, 200);
        } catch (BuberLogicException e) {
            JSPSwitcher.redirect(req, resp, e.getMessage(), null, 400);
        } catch (BuberSQLException e) {
            JSPSwitcher.redirect(req, resp, e.getMessage(), null, 500);
        }
    }
}
