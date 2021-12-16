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

        for (int i = 0; i< entities.size(); i++) {
            if (entities.get(i).getId().equals(id)) {
                entitiesToRemove.add(entities.get(i));
                entities.remove(entities.get(i));
                System.out.println("Removing " + entities.get(i).getId());
                System.out.println("Array after removed:");
                for (int j = 0; j < entities.size(); j++) {
                    System.out.print(entities.get(j).getId() + " ");
                }
                System.out.println();
                System.out.println("Array to remove:");
                for (int j = 0; j < entitiesToRemove.size(); j++) {
                    System.out.print(entitiesToRemove.get(j).getId() + " ");
                }
                System.out.println();
            }
        }
    }

    public void moveEntityToRemove(String id) {
//        for (Entity entity : entities) {
//            if (entity.getId().equals(id)) {
//                entitiesToRemove.add(entity);
//            }
//        }
    }

    public List<Entity> getEntitiesToRemove() {
        return entitiesToRemove;
    }
}
