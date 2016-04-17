package uk.ac.brighton.uni.ch629.ecengine.physics;

import uk.ac.brighton.uni.ch629.ecengine.component.CollisionComponent;

import java.util.HashSet;
import java.util.Set;

public class CollisionHandler {
    public final Set<CollisionComponent> colliders;

    public CollisionHandler() {
        colliders = new HashSet<CollisionComponent>();
    }

    public void addCollider(CollisionComponent collider) {
        if(!colliders.contains(collider)) colliders.add(collider);
    }

    public boolean removeCollider(CollisionComponent collider) {
        return colliders.remove(collider);
    }
}