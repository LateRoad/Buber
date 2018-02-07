package com.lateroad.buber.service.role;

import com.lateroad.buber.database.dao.LocationInfoDAO;
import com.lateroad.buber.database.dao.OrderDAO;
import com.lateroad.buber.database.dao.UserDAO;
import com.lateroad.buber.database.dao.role.ClientDAO;
import com.lateroad.buber.database.dao.role.DriverDAO;
import com.lateroad.buber.entity.Location;
import com.lateroad.buber.entity.Order;
import com.lateroad.buber.entity.role.Client;
import com.lateroad.buber.entity.role.User;
import com.lateroad.buber.entity.type.OrderType;
import com.lateroad.buber.entity.type.UserType;
import com.lateroad.buber.exception.BuberSQLException;
import com.lateroad.buber.model.CurrentModel;
import com.lateroad.buber.service.CommonUserService;

import java.util.List;

public class ClientService implements CommonUserService<CurrentModel> {

    @Override
    public CurrentModel authentication(String login, String password) {
        CurrentModel model = null;
        try {
            Client client = ClientDAO.getInstance().find(login, password);
            Order currentOrder = OrderDAO.getInstance().find(login, UserType.CLIENT, OrderType.ACCEPTED);
            List<Order> orders = OrderDAO.getInstance().findAll(login, UserType.CLIENT);
            Location location = LocationInfoDAO.getInstance().find(login);
            model = new CurrentModel(client, location, currentOrder, orders);
            UserDAO.getInstance().setOnline(login, true);
            UserDAO.getInstance().setRole(login, UserType.CLIENT);
        } catch (BuberSQLException e) {
            e.printStackTrace();
        }
        return model;
    }


//    private ClientDAO clientInfoDAO = ClientDAO.getInstance();
//
//    @Override
//    public User buildUser() {
//
//        return null;
//    }

    //
//
//    @Override
//    public User userGetByEmailAndPassword(String login, String password) throws SQLException, BuberSQLException {
//        User user = clientInfoDAO.find(login, password);
//        setOnline(user.getLogin(), true);
//        return user;
//    }
//
    public void takeTaxi(String login, String driverLogin, String money) throws BuberSQLException {
        User driver = DriverDAO.getInstance().find(driverLogin, false);
        if(driver == null){
            throw new BuberSQLException("Водитель занят");
        }
        OrderDAO.getInstance().insert(new Order(login, driver.getLogin(), money, OrderType.UNDONE));
        DriverDAO.getInstance().update(login, true);
    }
}
