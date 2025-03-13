package bcc.battleship;

public class Grid {
    private Location[][] locations;  // 2D array of Location objects

    // Constructor: Initialize the grid with 10 rows and 10 columns, all set to UNGUESSED.
    public Grid() {
        locations = new Location[Constants.NUM_ROWS][Constants.NUM_COLS];

        // Initialize each location in the grid
        for (int i = 0; i < Constants.NUM_ROWS; i++) {
            for (int j = 0; j < Constants.NUM_COLS; j++) {
                locations[i][j] = new Location();  // Create a new Location at each (row, col)
            }
        }
    }

    // Mark a hit in the specified location.
    public void markHit(int row, int col) {
        if (isValidLocation(row, col)) {
            locations[row][col].markHit();  // Mark the location as a hit
        }
    }

    // Mark a miss in the specified location.
    public void markMiss(int row, int col) {
        if (isValidLocation(row, col)) {
            locations[row][col].markMiss();  // Mark the location as a miss
        }
    }

    // Set the status of the Location at (row, col).
    public void setStatus(int row, int col, int status) {
        if (isValidLocation(row, col)) {
            locations[row][col].setStatus(status);  // Set the status of the location
        }
    }

    // Get the status of the Location at (row, col).
    public int getStatus(int row, int col) {
        if (isValidLocation(row, col)) {
            return locations[row][col].getStatus();  // Get the status of the location
        }
        return Constants.UNSET;  // Return a default value if the location is invalid
    }

    // Return whether this Location has already been guessed.
    public boolean alreadyGuessed(int row, int col) {
        if (isValidLocation(row, col)) {
            Location location = locations[row][col];
            return location.checkHit() || location.checkMiss();  // Return true if it's already hit or missed
        }
        return false;
    }

    // Set whether there is a ship at this location.
    public void setShip(int row, int col, boolean val) {
        if (isValidLocation(row, col)) {
            locations[row][col].setShip(val);  // Set the ship status at the location
        }
    }

    // Return whether there is a ship at this location.
    public boolean hasShip(int row, int col) {
        if (isValidLocation(row, col)) {
            return locations[row][col].hasShip();  // Return true if there is a ship at the location
        }
        return false;
    }

    // Get the Location object at this row and column.
    public Location get(int row, int col) {
        if (isValidLocation(row, col)) {
            return locations[row][col];  // Return the Location object at (row, col)
        }
        return null;  // Return null if the location is invalid
    }

    // Return the number of rows in the grid.
    public int numRows() {
        return Constants.NUM_ROWS;
    }

    // Return the number of columns in the grid.
    public int numCols() {
        return Constants.NUM_COLS;
    }

    // Add a ship to the grid. This method can be used to add ships at specific locations.
    public boolean addShip(Ship s) {
        int row = s.getRow();
        int col = s.getCol();
        int direction = s.getDirection();

        // Check if the ship can be placed at the specified location
        if (canPlaceShip(s)) {
            // Place the ship on the grid
            for (int i = 0; i < s.getLength(); i++) {
                if (direction == Constants.HORIZONTAL) {
                    locations[row][col + i].setShip(true);
                } else if (direction == Constants.VERTICAL) {
                    locations[row + i][col].setShip(true);
                }
            }
            return true;
        }
        return false;  // Return false if the ship cannot be placed
    }

    // Check if all ships are sunk.
    public boolean allShipsSank() {
        for (int i = 0; i < Constants.NUM_ROWS; i++) {
            for (int j = 0; j < Constants.NUM_COLS; j++) {
                if (locations[i][j].hasShip() && !locations[i][j].checkHit()) {
                    return false;  // A ship is still present and not yet hit
                }
            }
        }
        return true;  // All ships have been sunk
    }

    // Helper method to check if the coordinates are within the grid bounds.
    private boolean isValidLocation(int row, int col) {
        return row >= 0 && row < Constants.NUM_ROWS && col >= 0 && col < Constants.NUM_COLS;
    }

    // Helper method to check if a ship can be placed at the given coordinates.
    private boolean canPlaceShip(Ship s) {
        int row = s.getRow();
        int col = s.getCol();
        int direction = s.getDirection();

        // Check if the ship can fit within the grid boundaries
        if (direction == Constants.HORIZONTAL) {
            if (col + s.getLength() > Constants.NUM_COLS) return false;  // Ship exceeds grid width
            for (int i = 0; i < s.getLength(); i++) {
                if (locations[row][col + i].hasShip()) return false;  // Another ship already exists in the location
            }
        } else if (direction == Constants.VERTICAL) {
            if (row + s.getLength() > Constants.NUM_ROWS) return false;  // Ship exceeds grid height
            for (int i = 0; i < s.getLength(); i++) {
                if (locations[row + i][col].hasShip()) return false;  // Another ship already exists in the location
            }
        }

        return true;  // Ship can be placed
    }
}
