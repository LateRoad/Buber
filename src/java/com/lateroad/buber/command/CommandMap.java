package com.lateroad.buber.command;


import com.lateroad.buber.command.impl.ChangeLanguage;
import com.lateroad.buber.command.impl.RegisterCommand;
import com.lateroad.buber.command.impl.SignInCommand;
import com.lateroad.buber.command.impl.SignOutCommand;
import com.lateroad.buber.command.impl.admin.GetClientsCommand;
import com.lateroad.buber.command.impl.admin.GetDriversCommand;
import com.lateroad.buber.command.impl.admin.GetOrdersCommand;
import com.lateroad.buber.command.impl.admin.SetMutedCommand;
import com.lateroad.buber.command.impl.user.*;
import com.lateroad.buber.command.impl.user.client.GetRouteInfoCommand;
import com.lateroad.buber.command.impl.user.client.TakeTaxiCommand;
import com.lateroad.buber.command.impl.user.driver.AcceptOrderCommand;
import com.lateroad.buber.command.impl.user.driver.UpdateActiveOrdersCommand;
import com.lateroad.buber.exception.BuberLogicException;

import java.util.HashMap;

/**
 * Class that represent a map with command for server and relevant <code>String</code>
 * representation for this commands.
 *
 * @author LateRoad
 * @see ICommand
 * @since JDK1.8
 */
public class CommandMap {
    private HashMap<String, ICommand> commandsMap;
    private static final CommandMap instance = new CommandMap();

    /**
     * Returns a singleton of the map.
     *
     * @return CommandMap instance.
     */
    public static CommandMap getInstance() {
        return instance;
    }

    /**
     * Private constructor of CommandMap in which occurs an initialization of the map.
     */
    private CommandMap() {
        commandsMap = new HashMap<>();
        commandsMap.put("signIn", new SignInCommand());
        commandsMap.put("signOut", new SignOutCommand());
        commandsMap.put("register", new RegisterCommand());
        commandsMap.put("getTrips", new GetTripsCommand());
        commandsMap.put("getRouteInfo", new GetRouteInfoCommand());
        commandsMap.put("takeTaxi", new TakeTaxiCommand());
        commandsMap.put("acceptOrder", new AcceptOrderCommand());
        commandsMap.put("updateActiveOrders", new UpdateActiveOrdersCommand());
        commandsMap.put("getDrivers", new GetDriversCommand());
        commandsMap.put("getClients", new GetClientsCommand());
        commandsMap.put("getOrders", new GetOrdersCommand());
        commandsMap.put("getCards", new GetCardsCommand());
        commandsMap.put("updateInfo", new UpdateInfoCommand());
        commandsMap.put("changeLanguage", new ChangeLanguage());
        commandsMap.put("setCurrentLocation", new SetCurrentLocation());
        commandsMap.put("setMuted", new SetMutedCommand());
        commandsMap.put("setOrderStatus", new SetOrderStatusCommand());
    }

    /**
     * Method which return relevant command based on String representation of the command.
     *
     * @return ICommand entity
     */
    public ICommand getCommand(String key) throws BuberLogicException {
        ICommand command = commandsMap.get(key);
        if (command == null) {
            throw new BuberLogicException("Something went wrong");
        }
        return command;
    }
}
