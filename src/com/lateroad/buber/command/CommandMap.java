package com.lateroad.buber.command;

import com.lateroad.buber.command.impl.GetRouteInfoCommand;
import com.lateroad.buber.command.impl.GetTripsCommand;
import com.lateroad.buber.command.impl.SignInCommand;
import com.lateroad.buber.command.impl.SignOutCommand;

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
    }

    public ICommand getCommandsMap(String key) {
        return commandsMap.get(key);
    }
}
