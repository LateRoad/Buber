package com.lateroad.buber.command;

import com.lateroad.buber.command.impl.*;

import java.util.HashMap;

public class CommandMap {
    private HashMap<String, ICommand> commandsMap;
    private static final CommandMap instance = new CommandMap();

    public static CommandMap getInstance() {
        return instance;
    }

    private CommandMap() {
        commandsMap = new HashMap<>();
        commandsMap.put("signIn", new SignInCommand());
        commandsMap.put("signOut", new SignOutCommand());
        commandsMap.put("getTrips", new GetTripsCommand());
        commandsMap.put("getRouteInfo", new GetRouteInfoCommand());
        commandsMap.put("takeTaxi", new TakeTaxiCommand());
        commandsMap.put("acceptClient", new AcceptOrderCommand());
        commandsMap.put("updateActiveOrders", new UpdateActiveOrdersCommand());
        commandsMap.put("getDrivers", new GetDriversCommand());
        commandsMap.put("getClients", new GetClientsCommand());
        commandsMap.put("getOrders", new GetOrdersCommand());
        commandsMap.put("updateInfo", new UpdateInfoCommand());
        commandsMap.put("changeLanguage", new ChangeLanguage());
//        commandsMap.put("deleteUser", new DeleteUserCommand());
//        commandsMap.put("setDiscount", new SetDiscount());
    }

    public ICommand getCommandsMap(String key) {
        return commandsMap.get(key);
    }
}
