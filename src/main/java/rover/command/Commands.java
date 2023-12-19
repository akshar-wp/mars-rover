package rover.command;

import rover.exception.UnknownCommandException;

import java.util.ArrayList;
import java.util.List;

public class Commands {

    public static List<Command> commands(String commands) {
        List<Command> result = new ArrayList<>();
        for (Character c : commands.toCharArray()) {
            switch (c) {
                case 'L' -> result.add(Command.LEFT);
                case 'R' -> result.add(Command.RIGHT);
                case 'M' -> result.add(Command.MOVE);
                default -> throw new UnknownCommandException(c);
            }
        }
        return result;
    }
}
