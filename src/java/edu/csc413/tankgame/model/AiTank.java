package edu.csc413.tankgame.model;

import edu.csc413.tankgame.Constants;

public class AiTank extends Tank {

    private static int INITIAL_SHELL_COOLDOWN = 20;
    private  int shellCoolDown;

    public AiTank(String id, double x, double y, double angle) {
        super(id, x, y, angle);
        shellCoolDown = INITIAL_SHELL_COOLDOWN;
    }

    @Override
    public void move(GameWorld gameWorld) {
        moveForward(Constants.TANK_MOVEMENT_SPEED);
        turnLeft(Constants.TANK_TURN_SPEED);
        if (shellCoolDown > 0) shellCoolDown--;
        fireShell(gameWorld);
    }

    private void fireShell(GameWorld gameWorld) {
        if (shellCoolDown == 0) {
            Shell newShell = new Shell(getShellX(), getShellY(), getAngle());
            gameWorld.addEntity(newShell);
            shellCoolDown = INITIAL_SHELL_COOLDOWN;
        }
    }
}
