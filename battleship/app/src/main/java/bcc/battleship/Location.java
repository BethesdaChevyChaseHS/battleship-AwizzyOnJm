package bcc.battleship;

public class Location {
    // Instance variables
    private boolean hasShip;
    private int status; // UNGUESSED, HIT, MISSED
    private boolean isShipSet;

    // Constructor. Initializes with no ship and status UNGUESSED.
    public Location() {
        this.hasShip = false;
        this.status = Constants.UNGUESSED;
        this.isShipSet = false;
    }

    // Was this Location a hit?
    public boolean checkHit() {
        return status == Constants.HIT;
    }

    // Was this location a miss?
    public boolean checkMiss() {
        return status == Constants.MISSED;
    }

    // Was this location unguessed?
    public boolean isUnguessed() {
        return status == Constants.UNGUESSED;
    }

    // Mark this location as a hit.
    public void markHit() {
        this.status = Constants.HIT;
    }

    // Mark this location as a miss.
    public void markMiss() {
        this.status = Constants.MISSED;
    }

    // Return whether or not this location has a ship.
    public boolean hasShip() {
        return this.hasShip;
    }

    // Set whether this location has a ship.
    public void setShip(boolean val) {
        this.hasShip = val;
        this.isShipSet = true;
    }

    // Set the status of this Location.
    public void setStatus(int status) {
        this.status = status;
    }

    // Get the status of this Location.
    public int getStatus() {
        return this.status;
    }

    // Return whether the ship is placed at this location.
    public boolean isShipSet() {
        return isShipSet;
    }
}
