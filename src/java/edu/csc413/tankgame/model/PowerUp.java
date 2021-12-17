package edu.csc413.tankgame.model;

import edu.csc413.tankgame.Constants;

import java.util.ConcurrentModificationException;

public class PowerUp extends Entity {

    private static int nextId = 0;

    public PowerUp(double x, double y, double angle) {
        super(generateId(), x, y, angle);
    }

    private static String generateId() {
        String id = "power up-" + nextId;
        nextId++;
        return id;
    }

    @Override
    public double getXBound() {
        return getX() + Constants.POWER_UP_WIDTH;
    }

    @Override
    public double getYBound() {
        return getY() + Constants.POWER_UP_HEIGHT;
    }

    @Override
    public void move(GameWorld gameWorld) {
        //do nothing
    }

    @Override
    public void checkBounds(GameWorld gameWorld) {
        //do nothing
    }
}
