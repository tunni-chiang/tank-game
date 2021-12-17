package edu.csc413.tankgame.model;

import edu.csc413.tankgame.Constants;

public class HomingShell extends Shell {

    public HomingShell(double x, double y, double angle) {
        super(x, y, angle);
    }

    @Override
    public void move(GameWorld gameWorld) {
        Entity aiTank;
        if (gameWorld.getEntity(Constants.AI_TANK_1_ID) != null) {
            aiTank = gameWorld.getEntity(Constants.AI_TANK_1_ID);
        } else {
            aiTank = gameWorld.getEntity(Constants.AI_TANK_2_ID);
        }

        // To figure out what angle the homing shell  needs to face, we'll use the
        // change in the x and y axes between the shell and ai tanks.

        double dx = aiTank.getX() - getX();
        double dy = aiTank.getY() - getY();

        // atan2 applies arctangent to the ratio of the two provided values.
        double angleToAi = Math.atan2(dy, dx);
        double angleDifference = getAngle() - angleToAi;

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
            turnRight(Constants.SHELL_TURN_SPEED);
        } else if (angleDifference > Math.toRadians(3.0)) {
            turnLeft(Constants.SHELL_TURN_SPEED);
        }
        moveForward(Constants.SHELL_MOVEMENT_SPEED);
    }
}
