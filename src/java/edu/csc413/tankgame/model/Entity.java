package edu.csc413.tankgame.model;

import edu.csc413.tankgame.Constants;

import java.lang.constant.Constable;

/**
 * A general concept for an entity in the Tank Game. This includes everything that can move or be interacted with, such
 * as tanks, shells, walls, power ups, etc.
 */
public abstract class Entity {
    private String id;
    private double x;
    private double y;
    private double angle;
    private int lives;

    public Entity(String id, double x, double y, double angle) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.angle = angle;
        lives = Constants.LIVES_INITIAL;
    }

    public String getId() {
        return id;
    }

    public double getX() {
        return x;
    }

//    public double getXBound() {}
//    xBound = x + width

    public void setX(double x) { this.x = x; }

    public double getY() {
        return y;
    }

//    public double getYBound() {}
//    yBound = y + height

    public void setY(double y) { this.y = y; }

    public double getAngle() {
        return angle;
    }

    public void moveForward(double movementSpeed) {
        x += movementSpeed * Math.cos(angle);
        y += movementSpeed * Math.sin(angle);
    }

    public void moveBackward(double movementSpeed) {
        x -= movementSpeed * Math.cos(angle);
        y -= movementSpeed * Math.sin(angle);
    }

    public void turnLeft(double turnSpeed) {
        angle -= turnSpeed;
    }

    public void turnRight(double turnSpeed) {
        angle += turnSpeed;
    }

    public void reduceLives() {
        lives--;
    }

    public boolean checkAlive() {
        return lives > 0;
    }

    public abstract double getXBound();

    public abstract double getYBound();

    /** All entities can move, even if the details of their move logic may vary based on the specific type of Entity. */
    public abstract void move(GameWorld gameWorld);

    public abstract void checkBounds(GameWorld gameWorld);

}
