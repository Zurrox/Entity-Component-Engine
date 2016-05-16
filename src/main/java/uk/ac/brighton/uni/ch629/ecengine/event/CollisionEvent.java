package uk.ac.brighton.uni.ch629.ecengine.event;

import uk.ac.brighton.uni.ch629.ecengine.entity.Entity;

public class CollisionEvent implements IEvent {
    public Entity entity1, entity2;

    public CollisionEvent(Entity entity1, Entity entity2) {
        this.entity1 = entity1;
        this.entity2 = entity2;
    }

    public Class<? extends IEvent> getType() {
        return CollisionEvent.class;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CollisionEvent) {
            return ((CollisionEvent) obj).entity1.getID() == entity1.getID() && ((CollisionEvent) obj).entity2.getID() == entity2.getID() ||
                    ((CollisionEvent) obj).entity2.getID() == entity1.getID() && ((CollisionEvent) obj).entity1.getID() == entity2.getID();
        }
        return false;
    }
}