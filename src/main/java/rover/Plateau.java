package rover;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Plateau {

    private final int upperX;
    private final int upperY;
    private final List<Rover> rovers;

    public Plateau(int upperX, int upperY) {
        this.upperX = upperX;
        this.upperY = upperY;
        this.rovers = new ArrayList<>();
    }

    public int getUpperX() {
        return upperX;
    }

    public int getUpperY() {
        return upperY;
    }

    public void addRover(Rover rover) {
        if (isOccupied(rover.getPosition())) {
            throw new IllegalArgumentException("Can not add rover here. Position " +
                    rover.getPosition() + " is occupied.");
        }
        rovers.add(rover);
    }

    public boolean isOccupied(Position position) {
        for (Rover rover : rovers) {
            if (rover.isInPosition(position)) {
                return true;
            }
        }
        return false;
    }

    public static PlateauBuilder builder() {
        return new PlateauBuilder();
    }

    public static class PlateauBuilder {
        private Integer upperX;
        private Integer upperY;

        private PlateauBuilder() {
        }

        public PlateauBuilder upperX(int upperX) {
            this.upperX = upperX;
            return this;
        }

        public PlateauBuilder upperY(int upperY) {
            this.upperY = upperY;
            return this;
        }

        public Plateau build() {
            if (Objects.isNull(upperX) || upperX <= 0) {
                throw new IllegalArgumentException("X should be bigger than 0");
            }

            if (Objects.isNull(upperY) || upperY <= 0) {
                throw new IllegalArgumentException("Y should be bigger than 0");
            }

            return new Plateau(upperX, upperY);
        }
    }
}
