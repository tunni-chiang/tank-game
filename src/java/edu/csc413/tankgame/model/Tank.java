package edu.csc413.tankgame.model;

import edu.csc413.tankgame.Constants;

public abstract class Tank extends Entity {
    private static final int INITIAL_SHELL_COOLDOWN = 80;
    private int shellCoolDown;
    private static final int INITIAL_TANK_LIVES = 10;
    private int lives;

    public Tank(String id, double x, double y, double angle) {
        super(id, x, y, angle);
        shellCoolDown = INITIAL_SHELL_COOLDOWN;
        lives = INITIAL_TANK_LIVES;
    }

    protected int getShellCoolDown() {
        return shellCoolDown;
    }

    protected void reduceShellCoolDown() {
        shellCoolDown--;
    }

    protected void resetShellCoolDown() {
        shellCoolDown = INITIAL_SHELL_COOLDOWN;
    }

    // You can use getShellX() and getShellY() to determine the x and y coordinates of a Shell that
    // is fired by this tank. The Shell's angle should be the same as the Tank's angle.

    protected double getShellX() {
        return getX() + Constants.TANK_WIDTH / 2 + 45.0 * Math.cos(getAngle()) - Constants.SHELL_WIDTH / 2;
    }

    protected double getShellY() {
        return getY() + Constants.TANK_HEIGHT / 2 + 45.0 * Math.sin(getAngle()) - Constants.SHELL_HEIGHT / 2;
    }

    protected void fireShell(GameWorld gameWorld) {
        if (getShellCoolDown() == 0) {
            Shell newShell = new Shell(getShellX(), getShellY(), getAngle());
            gameWorld.addEntity(newShell);
            resetShellCoolDown();
        }
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

    @Override
    public double getXBound() {
        return getX() + Constants.TANK_WIDTH;
    }

    @Override
    public double getYBound() {
        return getY() + Constants.TANK_HEIGHT;
    }
}
