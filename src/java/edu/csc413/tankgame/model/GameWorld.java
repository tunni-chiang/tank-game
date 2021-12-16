package edu.csc413.tankgame.model;

import java.util.*;

/**
 * GameWorld holds all of the model objects present in the game. GameWorld tracks all moving entities like tanks and
 * shells, and provides access to this information for any code that needs it (such as GameDriver or entity classes).
 */
public class GameWorld {
    private ArrayList<Entity> entities;
    private ArrayList<Entity> entitiesToAdd;
    private ArrayList<Entity> entitiesToRemove;

    public GameWorld() {
        entities = new ArrayList<>();
        entitiesToAdd = new ArrayList<>();
        entitiesToRemove = new ArrayList<>();
    }

    /** Returns a list of all entities in the game. */
    public List<Entity> getEntities() {
        return entities;
    }

    public List<Entity> getEntitiesToAdd() {
        return entitiesToAdd;
    }

    /** Adds a new entity to the game. */
    public void addEntity(Entity entity) {
        entitiesToAdd.add(entity);
    }

    public void moveEntitiesToAdd() {
        entities.addAll(entitiesToAdd);
        entitiesToAdd.clear();
    }

    /** Returns the Entity with the specified ID. */
    public Entity getEntity(String id) {
        for (Entity entity : entities) {
            if (entity.getId().equals(id)) return entity;
        }
        return null;
    }

    /** Removes the entity with the specified ID from the game. */
    public void removeEntity(String id) {
        // TODO: Implement.
        for (Entity entity : entities) {
            if (entity.getId().equals(id)) {
                entitiesToRemove.add(entity);
                System.out.println("Removing " + entity.getId());
            }
        }
    }

    public void removeEntityFromEntities() {
        for (Entity entity : entitiesToRemove) {
            entities.remove(entity);
        }
        entitiesToRemove.clear();
    }

    public List<Entity> getEntitiesToRemove() {
        return entitiesToRemove;
    }
}
