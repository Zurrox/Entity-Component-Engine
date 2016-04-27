package uk.ac.brighton.uni.ch629.ecengine.event;

import java.util.UUID;

public class EntityKillEvent implements IEvent {
    public UUID entity;

    public EntityKillEvent(UUID entity) {
        this.entity = entity;
    }
}