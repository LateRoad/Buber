package com.lateroad.buber.command.impl.user.driver;

import com.lateroad.buber.command.ICommand;
import com.lateroad.buber.database.dao.role.ClientDAO;
import com.lateroad.buber.entity.Order;
import com.lateroad.buber.entity.role.CommonUser;
import com.lateroad.buber.entity.role.User;
import com.lateroad.buber.entity.type.OrderType;
import com.lateroad.buber.exception.BuberLogicException;
import com.lateroad.buber.exception.BuberSQLException;
import com.lateroad.buber.service.role.UserService;
import com.lateroad.buber.switcher.JSPSwitcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The command which return all <code>Order</code> objects with status UNDONE for client. Then it
 * redirects client to relevant jsp and send response.
 *
 * @author LateRoad
 * @see ICommand
 * @see OrderType
 * @since JDK1.8
 */
public class UpdateActiveOrdersCommand implements ICommand {

    /**
     * Executes command for server. At the end of execution get possibility for
     * send response and send redirect to client.
     *
     * @param req for getting params from client.
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        UserService service = new UserService();
        CommonUser driver = (CommonUser) req.getSession().getAttribute("user");
        Map<Integer, User> map = new HashMap<>();
        try {
            List<Order> activeOrders = service.findActiveOrders(driver.getLogin(), driver.getRole());
            for (Order activeOrder : activeOrders) {
                map.put(activeOrder.getId(), ClientDAO.getInstance().find(activeOrder.getClientLogin()));
            }
            req.getSession().setAttribute("activeOrders", map);
            JSPSwitcher.redirect(req, resp, "success", null, 200);
        } catch (BuberLogicException e) {
            JSPSwitcher.redirect(req, resp, e.getMessage(), null, 400);
        } catch (BuberSQLException e) {
            JSPSwitcher.redirect(req, resp, e.getMessage(), null, 500);
        }

    }
}
