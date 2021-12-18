package edu.csc413.tankgame;

public class Constants {
    public static final double TANK_WIDTH = 55.0;
    public static final double TANK_HEIGHT = 47.0;
    public static final double TANK_MOVEMENT_SPEED = 2.0;
    public static final double TANK_TURN_SPEED = Math.toRadians(2.0);
    public static final double AITANK_TURN_SPEED = Math.toRadians(3.0);

    public static final double SHELL_WIDTH = 12.0;
    public static final double SHELL_HEIGHT = 8.0;
    public static final double SHELL_MOVEMENT_SPEED = 4.0;
    public static final double SHELL_TURN_SPEED = Math.toRadians(5.0);

    public static final double WALL_WIDTH = 32.0;
    public static final double WALL_HEIGHT = 32.0;

    public static final double POWER_UP_WIDTH = 19.0;
    public static final double POWER_UP_HEIGHT = 18.0;

    public static final double HEART_WIDTH = 43.0;
    public static final double HEART_HEIGHT = 40.0;
    public static final int LIVES_INITIAL = 5;

    public static final String PLAYER_TANK_ID = "player-tank";
    public static final String AI_TANK_1_ID = "ai-tank-1";
    public static final String AI_TANK_2_ID = "ai-tank-2";

    public static final double PLAYER_TANK_INITIAL_X = 250.0;
    public static final double PLAYER_TANK_INITIAL_Y = 200.0;
    public static final double PLAYER_TANK_INITIAL_ANGLE = Math.toRadians(0.0);

    public static final double AI_TANK_1_INITIAL_X = 700.0;
    public static final double AI_TANK_1_INITIAL_Y = 500.0;
    public static final double AI_TANK_1_INITIAL_ANGLE = Math.toRadians(180.0);

    public static final double AI_TANK_2_INITIAL_X = 700.0;
    public static final double AI_TANK_2_INITIAL_Y = 200.0;
    public static final double AI_TANK_2_INITIAL_ANGLE = Math.toRadians(180.0);

    public static final double POWER_UP_INITIAL_ANGLE = Math.toRadians(0.0);

    public static final double PLAYER_HEART_INITIAL_X = 0.0;
    public static final double PLAYER_HEART_INITIAL_Y = 0.0;

    public static final double AI_HEART_INITIAL_X = 1024.0 - LIVES_INITIAL * HEART_WIDTH;
    public static final double AI_HEART_INITIAL_Y = 0.0;

    public static final double TANK_X_LOWER_BOUND = 30.0;
    public static final double TANK_X_UPPER_BOUND = 924.0;
    public static final double TANK_Y_LOWER_BOUND = 30.0;
    public static final double TANK_Y_UPPER_BOUND = 648.0;

    public static final double SHELL_X_LOWER_BOUND = -10.0;
    public static final double SHELL_X_UPPER_BOUND = 1024.0;
    public static final double SHELL_Y_LOWER_BOUND = -10.0;
    public static final double SHELL_Y_UPPER_BOUND = 768.0;
}
