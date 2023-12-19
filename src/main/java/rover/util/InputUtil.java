package rover.util;

import rover.Plateau;
import rover.Rover;
import rover.command.Command;
import rover.command.Commands;
import rover.exception.InvalidArgumentException;

import java.util.List;

public class InputUtil {

    public static Plateau parsePlateauInput(String plateauInput) {
        String[] input = plateauInput.split(" ");
        if (!isPlateauInputValid(input)) {
            throw new InvalidArgumentException("Invalid plateau input: '" + plateauInput + "'.");
        }
        int x = Integer.parseInt(input[0]);
        int y = Integer.parseInt(input[1]);
        return createPlateau(x, y);
    }

    public static Plateau createPlateau(int x, int y) {
        return Plateau.builder()
                .upperX(x)
                .upperY(y)
                .build();
    }

    public static Rover createRover() {
        return new Rover();
    }

    public static void parseRoverToPlateau(Plateau plateau, Rover rover, String roverData) {
        String[] input = roverData.split(" ");
        if (!isRoverDataInputValid(input)) {
            throw new InvalidArgumentException("Invalid rover data: '" + roverData + "'.");
        }
        int x = Integer.parseInt(input[0]);
        int y = Integer.parseInt(input[1]);
        char orientation = input[2].charAt(0);
        addRoverToPlateau(plateau, rover, x, y, orientation);
    }

    public static void addRoverToPlateau(Plateau plateau, Rover rover, int x, int y, char orientation) {
        rover.addToPlateau(plateau, x, y, orientation);
    }

    public static List<Command> parseCommands(String input) {
        return Commands.commands(input);
    }

    private static boolean isPlateauInputValid(String[] input) {
        return input.length == 2 &&
                input[0].chars().allMatch(Character::isDigit) &&
                input[1].chars().allMatch(Character::isDigit);
    }

    private static boolean isRoverDataInputValid(String[] input) {
        return input.length == 3  &&
                input[0].chars().allMatch(Character::isDigit) &&
                input[1].chars().allMatch(Character::isDigit) &&
                input[2].length() == 1;
    }

}
