package uk.ac.brighton.uni.ch629.ecengine.event;

import uk.ac.brighton.uni.ch629.ecengine.entity.Entity;

public class EntityKillEvent implements IEvent {
    public Entity entity;

    public EntityKillEvent(Entity entity) {
        this.entity = entity;
    }
}