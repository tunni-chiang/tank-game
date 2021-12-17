package edu.csc413.tankgame.model;

import edu.csc413.tankgame.Constants;

public class AiTank extends Tank {

    private static int numOfAiTank = 0;

    public AiTank(String id, double x, double y, double angle) {
        super(id, x, y, angle);
        numOfAiTank++;
    }

    public void reduceNumOfAiTank() {
        numOfAiTank--;
    }

    public boolean stillHaveAiTank() {
        System.out.println("Ai Tank num: " + numOfAiTank);
        return numOfAiTank > 0;
    }

    @Override
    public void move(GameWorld gameWorld) {
        moveForward(Constants.TANK_MOVEMENT_SPEED);
        turnLeft(Constants.TANK_TURN_SPEED);
        if (getShellCoolDown() > 0) reduceShellCoolDown();
        fireShell(gameWorld);
    }
}
