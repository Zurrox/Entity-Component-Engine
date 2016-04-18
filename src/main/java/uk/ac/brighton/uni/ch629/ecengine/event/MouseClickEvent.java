package uk.ac.brighton.uni.ch629.ecengine.event;

import java.awt.*;

public class MouseClickEvent implements IEvent {
    public Point pointClicked;

    public MouseClickEvent(final Point point) {
        this.pointClicked = point;
    }
}