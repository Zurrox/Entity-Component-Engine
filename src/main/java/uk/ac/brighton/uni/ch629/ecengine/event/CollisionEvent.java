package uk.ac.brighton.uni.ch629.ecengine.event;

import java.util.UUID;

public class CollisionEvent implements IEvent {
    public UUID entity1, entity2;

    public CollisionEvent(UUID entity1, UUID entity2) {
        this.entity1 = entity1;
        this.entity2 = entity2;
    }

    public Class<? extends IEvent> getType() {
        return CollisionEvent.class;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof  CollisionEvent) {
            return ((CollisionEvent) obj).entity1 == entity1 && ((CollisionEvent) obj).entity2 == entity2 ||
                    ((CollisionEvent) obj).entity2 == entity1 && ((CollisionEvent) obj).entity1 == entity2;
        }
        return false;
    }
}