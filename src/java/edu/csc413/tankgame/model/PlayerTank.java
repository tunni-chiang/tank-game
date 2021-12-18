package edu.csc413.tankgame.model;

import edu.csc413.tankgame.Constants;
import edu.csc413.tankgame.KeyboardReader;

public class PlayerTank extends Tank {

    private boolean powerUp;
    private int POWER_UP_EXPIRED_INIT_VALUE = 1000;
    private int powerUpExpiredTime;

    public PlayerTank(String id, double x, double y, double angle) {
        super(id, x, y, angle);
        powerUp = false;
    }

    public void addPowerUp() {
        powerUpExpiredTime = POWER_UP_EXPIRED_INIT_VALUE;
        powerUp = true;
    }

    @Override
    public void move(GameWorld gameWorld) {
        if (KeyboardReader.instance().upPressed()) moveForward(Constants.TANK_MOVEMENT_SPEED);
        if (KeyboardReader.instance().downPressed()) moveBackward(Constants.TANK_MOVEMENT_SPEED);
        if (KeyboardReader.instance().rightPressed()) turnRight(Constants.TANK_TURN_SPEED);
        if (KeyboardReader.instance().leftPressed()) turnLeft(Constants.TANK_TURN_SPEED);
        if (KeyboardReader.instance().spacePressed()) {
            reduceShellCoolDown();
            fireShell(gameWorld);
        }
        if (powerUp) powerUpExpiredTime--;
        if (powerUpExpiredTime == 0 && powerUp) powerUp = false;
    }

    @Override
    protected void fireShell(GameWorld gameWorld) {
        if (getShellCoolDown() == 0) {
            Shell newShell;
            if (powerUp) {
                newShell = new HomingShell(getShellX(), getShellY(), getAngle());
            } else {
                newShell = new Shell(getShellX(), getShellY(), getAngle());
            }
            gameWorld.addEntity(newShell);
            resetShellCoolDown();
        }
    }
}
