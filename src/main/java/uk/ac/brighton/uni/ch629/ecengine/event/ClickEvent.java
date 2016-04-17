package uk.ac.brighton.uni.ch629.ecengine.event;

import uk.ac.brighton.uni.ch629.ecengine.types.Vector2i;

import java.util.UUID;

public class ClickEvent implements IEvent {
    public UUID entityClicked;
    public Vector2i relativePointClicked;

    public ClickEvent(final UUID entityClicked, final Vector2i point) {
        this.entityClicked = entityClicked;
        this.relativePointClicked = point;
    }
}