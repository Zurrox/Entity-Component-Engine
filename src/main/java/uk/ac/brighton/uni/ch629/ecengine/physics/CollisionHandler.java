package uk.ac.brighton.uni.ch629.ecengine.physics;

import uk.ac.brighton.uni.ch629.ecengine.component.CollisionComponent;

import java.util.HashSet;
import java.util.Set;

/**
 * A class to handle all Collisions
 */
public class CollisionHandler {
    /**
     * A list of all Colliders in the game
     */
    public final Set<CollisionComponent> colliders;
    public final Set<CollisionComponent> collidersToAdd = new HashSet<>();

    public CollisionHandler() {
        colliders = new HashSet<>();
    }

    /**
     * Add a Collider to the list of Colliders
     *
     * @param collider - Collider Component to be added
     */
    public void addCollider(CollisionComponent collider) {
        if (!colliders.contains(collider) && !collidersToAdd.contains(collider)) collidersToAdd.add(collider);
    }

    public void addAllColliders() {
        for(CollisionComponent collider : collidersToAdd) colliders.add(collider);
        collidersToAdd.clear();
    }

    /**
     * Remove a Collider from the list
     *
     * @param collider The Collider Component to be removed
     * @return Whether the Collider was removed
     */
    public boolean removeCollider(CollisionComponent collider) {
        return colliders.remove(collider);
    }
}