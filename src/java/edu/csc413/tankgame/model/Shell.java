package edu.csc413.tankgame.model;

import edu.csc413.tankgame.Constants;

import java.util.ConcurrentModificationException;

public class Shell extends Entity {

    private static int nextId = 0;

    public Shell(double x, double y, double angle) {
        super(generateId(), x, y, angle);
    }

    private static String generateId() {
        String id = "shell-" + nextId;
        nextId++;
        return id;
    }

    @Override
    public void move(GameWorld gameWorld) {
        moveForward(Constants.SHELL_MOVEMENT_SPEED);
    }

    @Override
    public void checkBounds(GameWorld gameWorld) {
        //check if shell is out of bounds
        //if it is, remove it from gameWorld
    }
}
