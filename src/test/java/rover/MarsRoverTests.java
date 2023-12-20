package rover;

import org.junit.jupiter.api.Test;
import rover.command.Command;
import rover.exception.InvalidArgumentException;
import rover.exception.InvalidCommandException;
import rover.exception.UnknownCommandException;
import rover.util.InputUtil;
import rover.util.Orientation;
import rover.util.Position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

public class MarsRoverTests {

    private Plateau plateau = new Plateau(5, 5);

    @Test
    public void movingRover1ShouldSucceed() {
        Rover rover = new Rover();
        rover.addToPlateau(plateau, 1, 2, 'N');
        List<Command> commands2 = InputUtil.parseCommands("LMLMLMLMM");
        rover.executeCommands(commands2);

        Rover expectedRover = new Rover();
        expectedRover.addToPlateau(plateau, 1, 2, 'N');
        expectedRover.setOrientation(Orientation.NORTH);
        expectedRover.setPosition(new Position(1, 3));

//        assertThat(expectedRover).isEqualTo(rover);

        assertThat(expectedRover)
                .usingRecursiveComparison()
//                .ignoringFields("plateau")
                .isEqualTo(rover);
//        assertEquals(expectedRover, rover);
    }

    @Test
    public void movingRover2ShouldSucceed() {
        Rover movingRover2 = new Rover();
        movingRover2.addToPlateau(plateau, 3, 3, 'E');
        List<Command> movingRover2Commands = InputUtil.parseCommands("MMRMMRMRRM");
        movingRover2.executeCommands(movingRover2Commands);

        Rover expectedMovingRover2 = new Rover();
        expectedMovingRover2.addToPlateau(plateau, 3, 3, 'E');
        expectedMovingRover2.setOrientation(Orientation.EAST);
        expectedMovingRover2.setPosition(new Position(5, 1));

        assertThat(movingRover2)
                .usingRecursiveComparison()
                .isEqualTo(expectedMovingRover2);
    }

    @Test
    public void addRoverToAnotherRover() {
        Rover initialRover = new Rover();
        initialRover.addToPlateau(plateau, 3, 3, 'E');
        Rover secondRover = new Rover();
        assertThrows(RuntimeException.class, () ->
                secondRover.addToPlateau(plateau, 3, 3, 'E')
        );
    }

    @Test
    public void startingCoordinatesAreOutOfBounds() {
        Rover outOfBoundsRover = new Rover();
        assertThrows(InvalidArgumentException.class, () ->
                outOfBoundsRover.addToPlateau(plateau, 10, 10, 'E')
        );
    }

    @Test
    public void roverMovedOutOfBoundsDuringPlateauTraversal() {
        Rover badRover = new Rover();
        badRover.addToPlateau(plateau, 3, 3, 'E');
        List<Command> badRoverCommands = InputUtil.parseCommands("MMRMMRMRRMMMMMMMMMMM");

        assertThrows(InvalidCommandException.class, () ->
                badRover.executeCommands(badRoverCommands)
        );
    }

    @Test
    public void roverReceivesUnknownInstructions() {

        assertThrows(UnknownCommandException.class, () ->
                processUnknownInstructions()
        );
    }

    public void processUnknownInstructions() {
        Rover confusedRover = new Rover();
        confusedRover.addToPlateau(plateau, 3, 3, 'E');
        List<Command> confusedRoverCommands = InputUtil.parseCommands("ZMRMMRMRRM");
    }
}
