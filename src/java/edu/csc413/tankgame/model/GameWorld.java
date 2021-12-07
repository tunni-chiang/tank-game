package edu.csc413.tankgame.model;

import java.util.*;

/**
 * GameWorld holds all of the model objects present in the game. GameWorld tracks all moving entities like tanks and
 * shells, and provides access to this information for any code that needs it (such as GameDriver or entity classes).
 */
public class GameWorld {
    // TODO: Implement. There's a lot of information the GameState will need to store to provide contextual information.
    //       Add whatever instance variables, constructors, and methods are needed.
    private ArrayList<Entity> entities;
    private ArrayList<Entity> entitiesToAdd;

    public GameWorld() {
        // TODO: Implement.
        entities = new ArrayList<>();
        entitiesToAdd = new ArrayList<>();
    }

    /** Returns a list of all entities in the game. */
    public List<Entity> getEntities() {
        // TODO: Implement.
        return entities;
    }

    public List<Entity> getEntitiesToAdd() {
        return entitiesToAdd;
    }

    /** Adds a new entity to the game. */
    public void addEntity(Entity entity) {
        // TODO: Implement.
        entitiesToAdd.add(entity);
    }

    public void moveEntitiesToAdd() {
        entities.addAll(entitiesToAdd);
        entitiesToAdd.clear();
    }

    /** Returns the Entity with the specified ID. */
    public Entity getEntity(String id) {
        // TODO: Implement.
        return null;
    }

    /** Removes the entity with the specified ID from the game. */
    public void removeEntity(String id) {
        // TODO: Implement.
    }
}
