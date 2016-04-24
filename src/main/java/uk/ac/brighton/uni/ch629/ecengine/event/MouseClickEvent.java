package uk.ac.brighton.uni.ch629.ecengine.event;

import java.awt.*;

public class MouseClickEvent implements IEvent {
    public Point pointClicked;
    public int button;

    public MouseClickEvent(final Point point, final int button) {
        this.pointClicked = point;
        this.button = button;
    }
}