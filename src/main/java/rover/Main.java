package rover;

import rover.command.Command;
import rover.util.InputUtil;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Plateau plateau = InputUtil.parsePlateauInput("5 5");

        Rover rover1 = InputUtil.createRover()  ;
        InputUtil.parseRoverToPlateau(plateau, rover1, "1 2 N");
        List<Command> commands1 = InputUtil.parseCommands("LMLMLMLMM");

        Rover rover2 = InputUtil.createRover();
        InputUtil.parseRoverToPlateau(plateau, rover2, "3 3 E");
        List<Command> commands2 = InputUtil.parseCommands("MMRMMRMRRM");

//        Rover rover3 = InputUtil.createRover();
//        InputUtil.parseRoverToPlateau(plateau, rover3, "6 3 E");
//        List<Command> commands3 = InputUtil.parseCommands("MMRMMRMRRM");

        rover1.executeCommands(commands1);
        rover2.executeCommands(commands2);
//        rover3.executeCommands(commands3);

        System.out.println(rover1);
        System.out.println(rover2);
//        System.out.println(rover3);
    }
}
