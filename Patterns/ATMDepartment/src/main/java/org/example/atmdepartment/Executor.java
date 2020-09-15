package org.example.atmdepartment;

import java.util.ArrayList;
import java.util.List;

public class Executor {
    private final List<Command> commands = new ArrayList<>();
    private int counter = 0;

    public void addCommand(Command command) {
        commands.add(command);
    }

    public void executeCommands() {
        for (Command command : commands) {
            command.execute();
            counter++;
        }
    }

    public int size() {
        return commands.size();
    }

    public int getCounter() {
        return counter;
    }
}
