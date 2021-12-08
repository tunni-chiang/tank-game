package edu.csc413.tankgame.model;

import edu.csc413.tankgame.Constants;
import edu.csc413.tankgame.KeyboardReader;

public class PlayerTank extends Tank {

    private static int INITIAL_SHELL_COOLDOWN = 20;
    private int shellCoolDown;

    public PlayerTank(String id, double x, double y, double angle) {
        super(id, x, y, angle);
        shellCoolDown = INITIAL_SHELL_COOLDOWN;
    }

    @Override
    public void move(GameWorld gameWorld) {
        if (KeyboardReader.instance().upPressed()) moveForward(Constants.TANK_MOVEMENT_SPEED);
        if (KeyboardReader.instance().downPressed()) moveBackward(Constants.TANK_MOVEMENT_SPEED);
        if (KeyboardReader.instance().rightPressed()) turnRight(Constants.TANK_TURN_SPEED);
        if (KeyboardReader.instance().leftPressed()) turnLeft(Constants.TANK_TURN_SPEED);
        if (KeyboardReader.instance().spacePressed()) {
            shellCoolDown--;
            fireShell(gameWorld);
        }
        System.out.println("cool down: " + shellCoolDown);
    }

    private void fireShell(GameWorld gameWorld) {
        if (shellCoolDown == 0) {
            Shell newShell = new Shell(getShellX(), getShellY(), getAngle());
            gameWorld.addEntity(newShell);
            shellCoolDown = INITIAL_SHELL_COOLDOWN;
        }
    }
}
