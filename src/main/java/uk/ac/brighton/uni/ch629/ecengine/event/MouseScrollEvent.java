package uk.ac.brighton.uni.ch629.ecengine.event;

import java.awt.*;

public class MouseScrollEvent implements IEvent {
    public Point position;
    public int scroll;

    public MouseScrollEvent(Point pos, int scroll) {
        this.position = pos;
        this.scroll = scroll;
    }
}