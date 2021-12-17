package edu.csc413.tankgame;

import edu.csc413.tankgame.model.*;
import edu.csc413.tankgame.view.*;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameDriver {
    private final MainView mainView;
    private final RunGameView runGameView;
    private final GameWorld gameWorld;

    public GameDriver() {
        mainView = new MainView(this::startMenuActionPerformed);
        runGameView = mainView.getRunGameView();
        gameWorld = new GameWorld();
    }

    public void start() {
        mainView.setScreen(MainView.Screen.START_GAME_SCREEN);
    }

    private void startMenuActionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()) {
            case StartMenuView.START_BUTTON_ACTION_COMMAND -> runGame();
            case StartMenuView.EXIT_BUTTON_ACTION_COMMAND -> mainView.closeGame();
            default -> throw new RuntimeException("Unexpected action command: " + actionEvent.getActionCommand());
        }
    }

    private void runGame() {
        mainView.setScreen(MainView.Screen.RUN_GAME_SCREEN);
        Runnable gameRunner = () -> {
            setUpGame();
            while (updateGame() && !KeyboardReader.instance().escapePressed()) {
                runGameView.repaint();
                try {
                    Thread.sleep(10L);
                } catch (InterruptedException exception) {
                    throw new RuntimeException(exception);
                }
            }
            mainView.setScreen(MainView.Screen.END_MENU_SCREEN);
            resetGame();
        };
        new Thread(gameRunner).start();
    }

    /**
     * setUpGame is called once at the beginning when the game is started. Entities that are present from the start
     * should be initialized here, with their corresponding sprites added to the RunGameView.
     */
    private void setUpGame() {
        Tank playerTank = new PlayerTank(
                Constants.PLAYER_TANK_ID,
                Constants.PLAYER_TANK_INITIAL_X,
                Constants.PLAYER_TANK_INITIAL_Y,
                Constants.PLAYER_TANK_INITIAL_ANGLE);

        Tank aiTank = new AiTank(
                Constants.AI_TANK_1_ID,
                Constants.AI_TANK_1_INITIAL_X,
                Constants.AI_TANK_1_INITIAL_Y,
                Constants.AI_TANK_1_INITIAL_ANGLE);

        Tank aiTank2 = new AiTank(
                Constants.AI_TANK_2_ID,
                Constants.AI_TANK_2_INITIAL_X,
                Constants.AI_TANK_2_INITIAL_Y,
                Constants.AI_TANK_2_INITIAL_ANGLE);

        List<WallInformation> wallInfos = WallInformation.readWalls();
        int id = 0;
        for (WallInformation wallInfo : wallInfos) {
            Wall wall = new Wall("wall-" + id++, wallInfo.getX(), wallInfo.getY());
            gameWorld.addEntity(wall);
            runGameView.addSprite(wall.getId(), wallInfo.getImageFile(), wall.getX(), wall.getY(), wall.getAngle());
        }

        gameWorld.addEntity(playerTank);
        gameWorld.addEntity(aiTank);
        gameWorld.addEntity(aiTank2);
        gameWorld.moveEntitiesToAdd();

        runGameView.addSprite(
                playerTank.getId(),
                RunGameView.PLAYER_TANK_IMAGE_FILE,
                playerTank.getX(),
                playerTank.getY(),
                playerTank.getAngle());
        runGameView.addSprite(
                aiTank.getId(),
                RunGameView.AI_TANK_IMAGE_FILE,
                aiTank.getX(),
                aiTank.getY(),
                aiTank.getAngle());
        runGameView.addSprite(
                aiTank2.getId(),
                RunGameView.AI_TANK_IMAGE_FILE,
                aiTank2.getX(),
                aiTank2.getY(),
                aiTank2.getAngle());

    }

    /**
     * updateGame is repeatedly called in the gameplay loop. The code in this method should run a single frame of the
     * game. As long as it returns true, the game will continue running. If the game should stop for whatever reason
     * (e.g. the player tank being destroyed, escape being pressed), it should return false.
     */
    private boolean updateGame() {

        //move the entities then check bounds
        for (Entity entity : gameWorld.getEntities()) {
            entity.move(gameWorld);
            entity.checkBounds(gameWorld);
        }

        //check if entities are still alive
        for (Entity entity : gameWorld.getEntities()) {
            if (!entity.checkAlive()) {
                gameWorld.removeEntity(entity.getId());
                runGameView.addAnimation(
                        RunGameView.BIG_EXPLOSION_ANIMATION,
                        RunGameView.BIG_EXPLOSION_FRAME_DELAY,
                        entity.getX(),
                        entity.getY());
                if (entity instanceof PlayerTank) return false;
                else if (entity instanceof AiTank) {
                    ((AiTank) entity).reduceNumOfAiTank();
                    if (!((AiTank) entity).stillHaveAiTank()) return false;
                }
            }
        }

        //collision detection and handling
        for (int i = 0; i < gameWorld.getEntities().size(); i++) {
            for (int j = i + 1; j < gameWorld.getEntities().size(); j++) {
                if (areEntitiesColliding(gameWorld.getEntities().get(i), gameWorld.getEntities().get(j))) {
                    handleCollision(gameWorld.getEntities().get(i), gameWorld.getEntities().get(j));
                }
            }
        }

        //draw picture on screen
        for (Entity entity : gameWorld.getEntitiesToAdd()) {
            runGameView.addSprite(entity.getId(), RunGameView.SHELL_IMAGE_FILE, entity.getX(), entity.getY(), entity.getAngle());
        }
        gameWorld.moveEntitiesToAdd();

        //remove the picture of the ready to be removed entities
        for (Entity entity : gameWorld.getEntitiesToRemove()) {
            runGameView.removeSprite(entity.getId());
        }

        gameWorld.removeEntityFromEntities();

        //draw or update entities with new location
        for (Entity entity : gameWorld.getEntities()) {
            runGameView.setSpriteLocationAndAngle(entity.getId(), entity.getX(), entity.getY(), entity.getAngle());
        }

        return true;
    }

    private boolean areEntitiesColliding(Entity entity1, Entity entity2) {
        return !((entity2.getX() > entity1.getXBound()) || (entity1.getX() > entity2.getXBound()) ||
                (entity2.getY() > entity1.getYBound()) || (entity1.getY() > entity2.getYBound()));
    }

    private void handleCollision(Entity entity1, Entity entity2) {
        ArrayList<Double> distances = new ArrayList<>();
        distances.add(entity2.getXBound() - entity1.getX());
        distances.add(entity1.getXBound() - entity2.getX());
        distances.add(entity1.getYBound() - entity2.getY());
        distances.add(entity2.getYBound() - entity1.getY());
        double shortest = Collections.min(distances);
        if (entity1 instanceof Tank && entity2 instanceof Tank) {
            if (entity1.getXBound() - entity2.getX() == shortest) {
                entity1.setX(entity1.getX() - shortest/2);
                entity2.setX(entity2.getX() + shortest/2);
            } else if (entity2.getXBound() - entity1.getX() == shortest) {
                entity1.setX(entity1.getX() + shortest/2);
                entity2.setX(entity2.getX() - shortest/2);
            } else if (entity1.getYBound() - entity2.getY() == shortest) {
                entity1.setY(entity1.getY() - shortest/2);
                entity2.setY(entity2.getY() + shortest/2);
            } else {
                entity1.setY(entity1.getY() + shortest/2);
                entity2.setY(entity2.getY() - shortest/2);
            }
        } else if (entity1 instanceof Tank && entity2 instanceof Shell) {
            gameWorld.removeEntity(entity2.getId());
            runGameView.addAnimation(
                    RunGameView.SHELL_EXPLOSION_ANIMATION,
                    RunGameView.SHELL_EXPLOSION_FRAME_DELAY,
                    entity2.getX(),
                    entity2.getY());
            ((Tank) entity1).reduceLives();
        } else if (entity1 instanceof Shell && entity2 instanceof Tank) {
            gameWorld.removeEntity(entity1.getId());
            runGameView.addAnimation(
                    RunGameView.SHELL_EXPLOSION_ANIMATION,
                    RunGameView.SHELL_EXPLOSION_FRAME_DELAY,
                    entity1.getX(),
                    entity1.getY());
            ((Tank) entity2).reduceLives();
        } else if (entity1 instanceof Shell && entity2 instanceof Shell) {
            gameWorld.removeEntity(entity2.getId());
            runGameView.addAnimation(
                    RunGameView.SHELL_EXPLOSION_ANIMATION,
                    RunGameView.SHELL_EXPLOSION_FRAME_DELAY,
                    entity1.getX(),
                    entity1.getY());
            runGameView.addAnimation(
                    RunGameView.SHELL_EXPLOSION_ANIMATION,
                    RunGameView.SHELL_EXPLOSION_FRAME_DELAY,
                    entity2.getX(),
                    entity2.getY());
        } else if (entity1 instanceof Wall && entity2 instanceof Tank) {
            if (entity1.getXBound() - entity2.getX() == shortest) {
                entity2.setX(entity2.getX() + shortest);
            } else if (entity2.getXBound() - entity1.getX() == shortest) {
                entity2.setX(entity2.getX() - shortest);
            } else if (entity1.getYBound() - entity2.getY() == shortest) {
                entity2.setY(entity2.getY() + shortest);
            } else {
                entity2.setY(entity2.getY() - shortest);
            }
        } else if (entity1 instanceof Shell && entity2 instanceof Wall) {
            gameWorld.removeEntity(entity1.getId());
            runGameView.addAnimation(
                    RunGameView.SHELL_EXPLOSION_ANIMATION,
                    RunGameView.SHELL_EXPLOSION_FRAME_DELAY,
                    entity1.getX(),
                    entity1.getY());
            entity2.reduceLives();
        } else if (entity1 instanceof Wall && entity2 instanceof Shell) {
            gameWorld.removeEntity(entity2.getId());
            runGameView.addAnimation(
                    RunGameView.SHELL_EXPLOSION_ANIMATION,
                    RunGameView.SHELL_EXPLOSION_FRAME_DELAY,
                    entity2.getX(),
                    entity2.getY());
            entity1.reduceLives();
        }
    }

    /**
     * resetGame is called at the end of the game once the gameplay loop exits. This should clear any existing data from
     * the game so that if the game is restarted, there aren't any things leftover from the previous run.
     */
    private void resetGame() {
        // TODO: Implement.
        for (Entity entity : gameWorld.getEntities()) {
            gameWorld.removeEntity(entity.getId());
        }
        runGameView.reset();
        gameWorld.removeEntityFromEntities();
    }

    public static void main(String[] args) {
        GameDriver gameDriver = new GameDriver();
        gameDriver.start();
    }
}
