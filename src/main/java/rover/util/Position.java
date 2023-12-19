package rover.util;

import rover.Plateau;

public class Position {

    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isValid(Plateau plateau) {
        return x >= 0 && x <= plateau.getUpperX() &&
                y >= 0 && y <= plateau.getUpperY();
    }

    public Position move(Orientation orientation) {
        return switch(orientation) {
            case NORTH -> new Position(x, y + 1);
            case SOUTH -> new Position(x, y - 1);
            case WEST -> new Position(x - 1, y);
            case EAST -> new Position(x + 1, y);
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public String toString() {
        return x + " " + y;
    }
}