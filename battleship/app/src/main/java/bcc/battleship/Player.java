package bcc.battleship;

public class Player {
    private Grid myGrid;           // The player's grid
    private Grid opponentGrid;     // The opponent's grid
    private Ship[] ships;          // Array of ships for the player

    // Constructor: Initialize the grids and the ships.
    public Player() {
        myGrid = new Grid();                // Create a new grid for the player
        opponentGrid = new Grid();          // Create a new grid for the opponent
        ships = new Ship[Constants.SHIP_LENGTHS.length];

        // Initialize ships based on the predefined lengths
        for (int i = 0; i < Constants.SHIP_LENGTHS.length; i++) {
            ships[i] = new Ship(Constants.SHIP_LENGTHS[i]);
        }
    }

    /**
     * This method is used for testing to set a ship's location.
     * It sets the ship's row, column, and direction, then adds it to the player's grid.
     */
    public boolean chooseShipLocation(int index, int row, int col, int direction) {
        // Check if the ship is already placed
        if (ships[index].isLocationSet()) {
            return false; // Cannot place a ship if it's already placed
        }

        // Set the location and direction of the ship
        ships[index].setLocation(row, col);
        ships[index].setDirection(direction);

        // Try to add the ship to the grid
        if (myGrid.addShip(ships[index])) {
            return true;
        }
        return false;
    }

    /**
     * Record a guess from the opponent.
     * This method checks the player's grid at (row, col). If there is a ship,
     * it marks a hit and returns true; otherwise, it marks a miss and returns false.
     */
    public boolean recordOpponentGuess(int row, int col) {
        // Check if the opponent has already guessed this location
        if (opponentGrid.alreadyGuessed(row, col)) {
            return false;  // If the opponent has already guessed this location, return false
        }

        // Check if the location has a ship
        if (myGrid.hasShip(row, col)) {
            opponentGrid.markHit(row, col);  // Mark as a hit in the opponent's grid
            myGrid.markHit(row, col);        // Mark as a hit in the player's grid
            return true;  // Return true if a hit was registered
        } else {
            opponentGrid.markMiss(row, col);  // Mark as a miss in the opponent's grid
            myGrid.markMiss(row, col);        // Mark as a miss in the player's grid
            return false;  // Return false if no ship was hit
        }
    }

    // Get the player's grid.
    public Grid getMyGrid() {
        return myGrid;
    }

    // Get the opponent's grid.
    public Grid getOpponentGrid() {
        return opponentGrid;
    }

    // Check if all ships have been placed on the grid.
    public boolean allShipsPlaced() {
        for (Ship ship : ships) {
            if (!ship.isLocationSet()) {
                return false;  // If any ship's location is not set, return false
            }
        }
        return true;  // All ships are placed
    }

    // Check if all ships are sunk (i.e., all ships have been hit).
    public boolean allShipsSunk() {
        return myGrid.allShipsSank();  // Check if all ships have been sunk using the grid method
    }
}
