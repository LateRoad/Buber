package com.lateroad.buber.logic.command;

import com.lateroad.buber.logic.command.impl.SignInCommand;
import com.lateroad.buber.logic.command.impl.SignOutCommand;

import java.util.HashMap;

public class CommandMap {
    private HashMap<String, ICommand> commandsMap;
    private static final CommandMap instance = new CommandMap();

    public static CommandMap getInstance() {
        return instance;
    }

    private CommandMap() {
        commandsMap = new HashMap<>();
        commandsMap.put("signin", new SignInCommand());
        commandsMap.put("signout", new SignOutCommand());
    }

    public ICommand getCommandsMap(String key) {
        return commandsMap.get(key);
    }
}
