package bcc.battleship;

public class Ship {

    private int length;   // Length of the ship
    private int row;      // Row of the ship's starting position
    private int col;      // Column of the ship's starting position
    private int direction; // Direction of the ship (Constants.HORIZONTAL or Constants.VERTICAL)
    private boolean locationSet;  // Whether the ship's location is set
    private boolean directionSet; // Whether the ship's direction is set

    // Constructor. Create a ship and set its length.
    public Ship(int length) {
        this.length = length;
        this.row = Constants.UNSET;
        this.col = Constants.UNSET;
        this.direction = Constants.UNSET;
        this.locationSet = false;
        this.directionSet = false;
    }

    // Has the location been initialized?
    public boolean isLocationSet() {
        return locationSet;
    }

    // Has the direction been initialized?
    public boolean isDirectionSet() {
        return directionSet;
    }

    // Set the location of the ship
    public void setLocation(int row, int col) {
        this.row = row;
        this.col = col;
        this.locationSet = true;
    }

    // Set the direction of the ship (horizontal or vertical)
    public void setDirection(int direction) {
        if (direction == Constants.HORIZONTAL || direction == Constants.VERTICAL) {
            this.direction = direction;
            this.directionSet = true;
        }
    }

    // Getter for the row value
    public int getRow() {
        return row;
    }

    // Getter for the column value
    public int getCol() {
        return col;
    }

    // Getter for the length of the ship
    public int getLength() {
        return length;
    }

    // Getter for the direction
    public int getDirection() {
        return direction;
    }

    // Helper method to get a string value from the direction
    private String directionToString() {
        if (direction == Constants.HORIZONTAL) {
            return "Horizontal";
        } else if (direction == Constants.VERTICAL) {
            return "Vertical";
        }
        return "Unset";
    }

    // Helper method to get a (row, col) string value from the location
    private String locationToString() {
        return "(" + row + ", " + col + ")";
    }

    // toString value for this Ship, provides a simple summary of its properties
    @Override
    public String toString() {
        return "Ship [Length=" + length + ", Location=" + locationToString() + ", Direction=" + directionToString() + "]";
    }
}
