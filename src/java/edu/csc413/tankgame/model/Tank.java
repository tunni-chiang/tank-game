package edu.csc413.tankgame.model;

import edu.csc413.tankgame.Constants;

public abstract class Tank extends Entity {

    public Tank(String id, double x, double y, double angle) {
        super(id, x, y, angle);
    }

    // You can use getShellX() and getShellY() to determine the x and y coordinates of a Shell that
    // is fired by this tank. The Shell's angle should be the same as the Tank's angle.

    protected double getShellX() {
        return getX() + Constants.TANK_WIDTH / 2 + 45.0 * Math.cos(getAngle()) - Constants.SHELL_WIDTH / 2;
    }

    protected double getShellY() {
        return getY() + Constants.TANK_HEIGHT / 2 + 45.0 * Math.sin(getAngle()) - Constants.SHELL_HEIGHT / 2;
    }

    @Override
    public void checkBounds(GameWorld gameWorld) {
        //if tank's x < minimum x, do something
        if (getX() < Constants.TANK_X_LOWER_BOUND) {
            setX(Constants.TANK_X_LOWER_BOUND);
        } else if (getX() > Constants.TANK_X_UPPER_BOUND) {
            setX(Constants.TANK_X_UPPER_BOUND);
        } else if (getY() < Constants.TANK_Y_LOWER_BOUND) {
            setY(Constants.TANK_Y_LOWER_BOUND);
        } else if (getY() > Constants.TANK_Y_UPPER_BOUND) {
            setY(Constants.TANK_Y_UPPER_BOUND);
        }
    }
}
