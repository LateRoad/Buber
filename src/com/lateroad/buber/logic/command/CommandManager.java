package com.lateroad.buber.logic.command;

public class CommandManager {
    private static CommandMap commandMap = CommandMap.getInstance();


    public static String execute(String message) {
        String[] command = message.split("%21");
        return commandMap.getCommandsMap(command[0]).execute(command[1]);
    }
}
