package com.commands.commandsMethods;

import com.commands.ICommand;

public class CommandExit implements ICommand {
    @Override
    public void execute() {
        System.exit(0);
    }
}
