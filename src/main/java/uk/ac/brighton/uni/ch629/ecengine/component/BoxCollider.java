package uk.ac.brighton.uni.ch629.ecengine.component;

import uk.ac.brighton.uni.ch629.ecengine.entity.Entity;
import uk.ac.brighton.uni.ch629.ecengine.types.Box2i;
import uk.ac.brighton.uni.ch629.ecengine.types.Circle2i;
import uk.ac.brighton.uni.ch629.ecengine.types.Vector2i;

import java.awt.*;

@ComponentDetails(dependencies = TransformComponent.class, type = ComponentDetails.ComponentType.COLLISION)
public class BoxCollider extends CollisionComponent {
    private int width, height;

    public BoxCollider(Entity parent) {
        this(parent, 1, 1);
        initialize();
    }

    public BoxCollider(Entity parent, int width, int height) {
        super(parent);
        initialize();
        this.width = width;
        this.height = height;
    }

    public void initialize() {
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Box2i getBox() {
        return new Box2i(parent.getTransform().getPos(), width, height);
    }

    public Rectangle getRectangle() {
        Vector2i pos = parent.getTransform().getPos();
        return new Rectangle(pos.x, pos.y, width, height);
    }

    protected boolean intersects(Rectangle otherRectangle) {
        return getRectangle().intersects(otherRectangle);
    }

    protected boolean intersects(Circle2i otherCircle) { //TODO: This is a Broad Search
        Rectangle rect = new Rectangle(otherCircle.x - otherCircle.radius, otherCircle.y - otherCircle.radius, otherCircle.radius * 2, otherCircle.radius * 2);
        return intersects(rect);
    }
}