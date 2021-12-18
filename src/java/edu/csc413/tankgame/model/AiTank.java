package edu.csc413.tankgame.model;

import edu.csc413.tankgame.Constants;

public class AiTank extends Tank {

    private static int numOfAiTank = 0;
    protected static final int COUNT_DOWN_INIT = 200;
    protected int count_down;
    private double radiansLeftToMove = 0;
    private double distanceLeftToMove = 0;
    private boolean isTurningLeft = false;

    public AiTank(String id, double x, double y, double angle) {
        super(id, x, y, angle);
        count_down = COUNT_DOWN_INIT;
        numOfAiTank++;
    }

    public void reduceNumOfAiTank() {
        numOfAiTank--;
    }

    public boolean stillHaveAiTank() {
        return numOfAiTank > 0;
    }

    @Override
    public void move(GameWorld gameWorld) {
        if(distanceLeftToMove > 0){
            moveForward(Constants.TANK_MOVEMENT_SPEED);
            distanceLeftToMove--;
        }else{
            if(radiansLeftToMove <= 0){
                radiansLeftToMove = Math.toRadians(45 + (Math.random() * 180));
                isTurningLeft = Math.random() < 0.5;
                distanceLeftToMove = 50 + (Math.random() * 150);
            }
            radiansLeftToMove -= Constants.AITANK_TURN_SPEED;
            if(isTurningLeft){
                turnLeft(Constants.AITANK_TURN_SPEED);
            }else {
                turnRight(Constants.AITANK_TURN_SPEED);
            }
        }


//        count_down--;
//        if (count_down < 80) {
//            turnLeft(Constants.AITANK_TURN_SPEED);
//        } else {
//            moveForward(Constants.TANK_MOVEMENT_SPEED);
//        }
//        if (count_down == 0) count_down = COUNT_DOWN_INIT;
//
        if (getShellCoolDown() > 0) reduceShellCoolDown();
        fireShell(gameWorld);
    }
}
