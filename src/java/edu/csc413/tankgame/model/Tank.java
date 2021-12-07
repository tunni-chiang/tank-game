package edu.csc413.tankgame.model;

import edu.csc413.tankgame.Constants;

public class Tank extends Entity {
    private static int INITIAL_SHELL_COOLDOWN = 20;
    private int shellCoolDown;

    public Tank(String id, double x, double y, double angle) {
        super(id, x, y, angle);
        shellCoolDown = INITIAL_SHELL_COOLDOWN;
    }

    // You can use getShellX() and getShellY() to determine the x and y coordinates of a Shell that
    // is fired by this tank. The Shell's angle should be the same as the Tank's angle.

    private double getShellX() {
        return getX() + Constants.TANK_WIDTH / 2 + 45.0 * Math.cos(getAngle()) - Constants.SHELL_WIDTH / 2;
    }

    private double getShellY() {
        return getY() + Constants.TANK_HEIGHT / 2 + 45.0 * Math.sin(getAngle()) - Constants.SHELL_HEIGHT / 2;
    }

    @Override
    public void move(GameWorld gameWorld) {
        moveForward(Constants.TANK_MOVEMENT_SPEED);
        turnRight(Constants.TANK_TURN_SPEED);
        if (shellCoolDown > 0) shellCoolDown--;
        fireShell(gameWorld);
    }

    @Override
    public void checkBounds(GameWorld gameWorld) {
        //if tank's x < minimum x, do something
    }

    private void fireShell(GameWorld gameWorld) {
        if (shellCoolDown == 0) {
            Shell newShell = new Shell(getShellX(), getShellY(), getAngle());
            gameWorld.addEntity(newShell);
            shellCoolDown = INITIAL_SHELL_COOLDOWN;
        }
    }
}
