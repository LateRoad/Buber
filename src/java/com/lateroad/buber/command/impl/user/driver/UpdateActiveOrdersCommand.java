package com.lateroad.buber.command.impl.user.driver;

import com.lateroad.buber.command.ICommand;
import com.lateroad.buber.database.dao.role.ClientDAO;
import com.lateroad.buber.entity.Order;
import com.lateroad.buber.entity.role.User;
import com.lateroad.buber.exception.BuberSQLException;
import com.lateroad.buber.model.CurrentModel;
import com.lateroad.buber.service.CommonUserService;
import com.lateroad.buber.service.OrderService;
import com.lateroad.buber.service.role.ClientService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdateActiveOrdersCommand implements ICommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, HttpServlet servlet) {
        OrderService orderService = new OrderService();
        CommonUserService clientService = new ClientService();
        CurrentModel driver = (CurrentModel) req.getSession().getAttribute("user");
        Map<Integer, User> map = new HashMap<>();
        List<Integer> ordersID = new ArrayList<>();
        try {
            List<Order> activeOrders = orderService.findActiveOrders(driver.getCurrentUser().getLogin(), driver.getCurrentUser().getRole());
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
        }

    }
}
