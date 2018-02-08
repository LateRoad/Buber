package com.lateroad.buber.command.impl.user.driver;

import com.lateroad.buber.command.ICommand;
import com.lateroad.buber.database.dao.role.ClientDAO;
import com.lateroad.buber.entity.Order;
import com.lateroad.buber.entity.role.CommonUser;
import com.lateroad.buber.entity.role.User;
import com.lateroad.buber.exception.BuberLogicException;
import com.lateroad.buber.exception.BuberSQLException;
import com.lateroad.buber.service.OrderService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdateActiveOrdersCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, HttpServlet servlet) {
        OrderService orderService = new OrderService();
        CommonUser driver = (CommonUser) req.getSession().getAttribute("user");
        Map<Integer, User> map = new HashMap<>();
        try {
            List<Order> activeOrders = orderService.findActiveOrders(driver.getLogin(), driver.getRole());
            for (Order activeOrder : activeOrders) {
                map.put(activeOrder.getId(), ClientDAO.getInstance().find(activeOrder.getClientLogin()));
            }
            req.getSession().setAttribute("activeOrders", map);
            System.out.println(activeOrders);
            resp.getWriter().write("success");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BuberSQLException e) {
            e.printStackTrace();
        } catch (BuberLogicException e) {
            e.printStackTrace();
        }

    }
}
