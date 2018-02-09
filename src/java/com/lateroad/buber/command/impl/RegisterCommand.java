package com.lateroad.buber.command.impl;

import com.google.gson.Gson;
import com.lateroad.buber.builder.role.ClientBuilder;
import com.lateroad.buber.command.ICommand;
import com.lateroad.buber.entity.role.Client;
import com.lateroad.buber.entity.role.CommonUser;
import com.lateroad.buber.entity.role.Driver;
import com.lateroad.buber.entity.type.UserType;
import com.lateroad.buber.exception.BuberLogicException;
import com.lateroad.buber.exception.BuberSQLException;
import com.lateroad.buber.service.CommonUserService;
import com.lateroad.buber.service.role.ClientService;
import com.lateroad.buber.switcher.JSPSwitcher;
import com.lateroad.buber.timer.SessionTimer;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterCommand implements ICommand {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, HttpServlet servlet) {
        UserType role = UserType.valueOf(req.getParameter("role").toUpperCase());

        try {
            CommonUser currentUser = null;
            switch (role) {
                case CLIENT:
                    Client client = ClientBuilder();
                    currentUser = register(client, password, confirmPassword, new ClientService());
                    break;
                case DRIVER:
                    Driver driver = new Driver(login, name, surname, lastname, email, phoneNumber, carNumber);
                    currentUser = register(driver, password, confirmPassword, new ClientService());
                    break;
                default:
                    //logger
                    break;
            }

            if (currentUser != null) {
                req.getSession().setAttribute("user", currentUser);
                String json = new Gson().toJson(currentUser);
                JSPSwitcher.redirect(req, resp, json, "home.jsp");
                SessionTimer.setInactiveInterval(req.getSession(), currentUser.getRole());
            }
        } catch (BuberLogicException e) {
            e.printStackTrace();
        } catch (BuberSQLException e) {
            e.printStackTrace();
        }
    }


    private CommonUser register(CommonUser user, String password, String confirmPassword, CommonUserService service) throws BuberLogicException, BuberSQLException {
        return service.registration(user, password, confirmPassword);
    }
}
