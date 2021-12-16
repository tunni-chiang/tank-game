package edu.csc413.tankgame.model;

import edu.csc413.tankgame.Constants;

public class Wall extends Entity {
    public Wall(String id, double x, double y) {
        super(id, x, y, 0);
    }

    @Override
    public double getXBound() {
        return getX() + Constants.WALL_WIDTH;
    }

    @Override
    public double getYBound() {
        return getY() + Constants.WALL_HEIGHT;
    }

    @Override
    public void move(GameWorld gameWorld) {
        //do nothing
    }

    //TODO no need to check bounds
    @Override
    public void checkBounds(GameWorld gameWorld) {
        //do nothing
    }
}
