package edu.csc413.tankgame.model;

import edu.csc413.tankgame.Constants;
import edu.csc413.tankgame.KeyboardReader;

public class PlayerTank extends Tank {

    public PlayerTank(String id, double x, double y, double angle) {
        super(id, x, y, angle);
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
    }
}
