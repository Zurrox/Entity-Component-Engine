package uk.ac.brighton.uni.ch629.ecengine.event;

import java.util.UUID;

public class CollisionEvent implements IEvent
{
    public UUID entity1, entity2;

    public CollisionEvent(UUID entity1, UUID entity2)
    {
        this.entity1 = entity1;
        this.entity2 = entity2;
    }

    public Class<? extends IEvent> getType()
    {
        return CollisionEvent.class;
    }
}