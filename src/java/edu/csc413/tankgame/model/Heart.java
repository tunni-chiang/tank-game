package edu.csc413.tankgame.model;

import edu.csc413.tankgame.Constants;

public class Heart extends Entity {

    private static int nextId = 0;

    public Heart(double x, double y) {
        super(generateId(), x, y, Math.toRadians(0.0));
    }

    private static String generateId() {
        String id = "heart-" + nextId;
        nextId++;
        return id;
    }

    @Override
    public double getXBound() {
        return 0;
    }

    @Override
    public double getYBound() {
        return 0;
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
