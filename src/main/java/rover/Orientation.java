package rover;

public enum Orientation {
    NORTH('N'),
    SOUTH('S'),
    WEST('W'),
    EAST('E');

    private final Character orientation;

    Orientation(Character orientation) {
        this.orientation = orientation;
    }

    public Character getOrientation() {
        return orientation;
    }

    public static Orientation valueOf(Character orientation) {
        for (Orientation value : Orientation.values()) {
            if (value.getOrientation().equals(orientation)) {
                return value;
            }
        }
        return null;
    }
}

