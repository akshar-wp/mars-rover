package rover;

import rover.command.Command;
import rover.exception.InvalidArgumentException;
import rover.exception.InvalidCommandException;
import rover.util.Orientation;
import rover.util.Position;

import java.util.List;
import java.util.Objects;

public class Rover {

    private Position position;
    private Orientation orientation;
    private Plateau plateau;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public boolean isInPosition(Position position) {
        return this.position.equals(position);
    }

    public void executeCommands(List<Command> commands) {
        for (Command command : commands) {
            switch (command) {
                case LEFT -> spinLeft();
                case RIGHT -> spinRight();
                case MOVE -> move();
            }
        }
    }

    private void spinLeft() {
        switch (orientation) {
            case NORTH -> orientation = Orientation.WEST;
            case SOUTH -> orientation = Orientation.EAST;
            case WEST -> orientation = Orientation.SOUTH;
            case EAST -> orientation = Orientation.NORTH;
        }
    }

    private void spinRight() {
        switch (orientation) {
            case NORTH -> orientation = Orientation.EAST;
            case SOUTH -> orientation = Orientation.WEST;
            case WEST -> orientation = Orientation.NORTH;
            case EAST -> orientation = Orientation.SOUTH;
        }
    }

    private void move() {
        Position newPosition = position.move(orientation);
        if (!newPosition.isValid(plateau)) {
            throw new InvalidCommandException("Rover moved out of plateau.");
        }
        if (plateau.isOccupied(newPosition)) {
            throw new InvalidCommandException("Rover tried to move into a position occupied by another Rover.");
        }
        position = newPosition;
    }

    public void addToPlateau(Plateau plateau, int x, int y, char orientation) {
        try {
            if (Objects.nonNull(this.plateau)) {
                throw new RuntimeException("This rover is already in a plateau.");
            }

            this.position = new Position(x, y);
            if (!position.isValid(plateau)) {
                throw new InvalidArgumentException("The starting position " + position
                        + " for this rover is not valid.");
            }

            this.orientation = Orientation.valueOf(orientation);
            if (Objects.isNull(this.orientation)) {
                throw new InvalidArgumentException("Orientation '" + orientation + "' provided is not valid.");
            }

            this.plateau = plateau;
            plateau.addRover(this);
        } catch (Exception e) {
            reset();
            throw e;
        }
    }

    private void reset() {
        position = null;
        plateau = null;
        orientation = null;
    }

    @Override
    public String toString() {
        return position + " " + orientation.getOrientation();
    }
}

