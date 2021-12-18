package edu.csc413.tankgame.model;

import edu.csc413.tankgame.Constants;

public class AwareAiTank extends AiTank {

    public AwareAiTank(String id, double x, double y, double angle) {
        super(id, x, y, angle);
    }
    private double radiansLeftToMove = 0;
    private double distanceLeftToMove = 0;
    private boolean isTurningLeft = false;

    @Override
    public void move(GameWorld gameWorld) {
        if(radiansLeftToMove > 0){
            radiansLeftToMove -= Constants.AITANK_TURN_SPEED;
            if(isTurningLeft){
                turnLeft(Constants.AITANK_TURN_SPEED);
            }else {
                turnRight(Constants.AITANK_TURN_SPEED);
            }
            return;
        }
        if(distanceLeftToMove > 0){
            distanceLeftToMove--;
            moveForward(Constants.TANK_MOVEMENT_SPEED);
            return;
        }
        Entity playerTank = gameWorld.getEntity(Constants.PLAYER_TANK_ID);

        // To figure out what angle the AI tank needs to face, we'll use the
        // change in the x and y axes between the AI and player tanks.

        double dx = playerTank.getX() - getX();
        double dy = playerTank.getY() - getY();

        // figure out of there is a bullet coming our way
        // if so dodge it
        for(Entity entity : gameWorld.getEntities()){
            if(entity instanceof Shell){
                Shell shell = (Shell)entity;
                double shootingAngle = shell.getAngle();
//                double slope = Math.tan(shootingAngle);
//                double constant = shell.getY() - (slope*shell.getX());
//                if(getY() == slope * getX() + constant){
//                    System.out.println("collide");
//                }

                double distanceToTank = Math.pow(
                        Math.pow((getX() - shell.getX()), 2) - Math.pow((getY() - shell.getY()), 2)
                , 0.5);
                double predictedCordinateX = shell.getX() + (distanceToTank * Math.cos(shootingAngle));
                double predictedCordinateY = shell.getY() + (distanceToTank * Math.sin(shootingAngle));

                if((predictedCordinateX - getX()) < Constants.TANK_WIDTH && (predictedCordinateY - getY()) < Constants.TANK_HEIGHT){
                    // a bullet is going to hit us
                    // move
                    distanceLeftToMove = 50 + Math.random() * 50;
                    radiansLeftToMove = Math.toRadians(45 + (Math.random()*45));
                    isTurningLeft = Math.random() < 0.5;
                    return;
                }
            }
        }

        // atan2 applies arctangent to the ratio of the two provided values.
        double angleToPlayer = Math.atan2(dy, dx);
        double angleDifference = getAngle() - angleToPlayer;

        // We want to keep the angle difference between -180 degrees and 180
        // degrees for the next step. This ensures that anything outside of that
        // range is adjusted by 360 degrees at a time until it is, so that the
        // angle is still equivalent

        angleDifference -=
                Math.floor(angleDifference / Math.toRadians(360.0) + 0.5)
                        * Math.toRadians(360.0);

        // The angle difference being positive or negative determines if we turn
        // left or right. However, we don’t want the Tank to be constantly
        // bouncing back and forth around 0 degrees, alternating between left
        // and right turns, so we build in a small margin of error.

        if (angleDifference < -Math.toRadians(3.0)) {
            turnRight(Constants.TANK_TURN_SPEED);
        } else if (angleDifference > Math.toRadians(3.0)) {
            turnLeft(Constants.TANK_TURN_SPEED);
        }

//        if (count_down < 100) {
//            moveForward(Constants.TANK_MOVEMENT_SPEED);
//            if (count_down == 0) count_down = COUNT_DOWN_INIT;
//        } else moveBackward(Constants.TANK_MOVEMENT_SPEED);

        count_down--;

        if (getShellCoolDown() > 0) reduceShellCoolDown();
        fireShell(gameWorld);
    }
}
