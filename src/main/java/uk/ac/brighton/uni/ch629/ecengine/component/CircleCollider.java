package uk.ac.brighton.uni.ch629.ecengine.component;

import uk.ac.brighton.uni.ch629.ecengine.entity.Entity;
import uk.ac.brighton.uni.ch629.ecengine.types.Circle2i;
import uk.ac.brighton.uni.ch629.ecengine.types.Vector2i;

import java.awt.*;


@ComponentDetails(dependencies = TransformComponent.class, type = ComponentDetails.ComponentType.COLLISION)
public class CircleCollider extends CollisionComponent {
    private int radius;

    public CircleCollider(Entity parent) {
        this(parent, 1);
    }

    public CircleCollider(Entity parent, int radius) {
        super(parent);
        this.radius = radius;
    }

    public Circle2i getCircle() {
        return new Circle2i(parent.getTransform().getPos(), radius);
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    protected boolean intersects(Rectangle rectangle) { //TODO: This is a Broad Search
        Vector2i pos = parent.getTransform().getPos();
        Rectangle rect = new Rectangle(pos.x - radius, pos.y - radius, radius * 2, radius * 2);
        return rect.intersects(rectangle);
    }

    protected boolean intersects(final Circle2i otherCircle) {
        return getCircle().getDistance(otherCircle) < radius + otherCircle.radius;
    }

    @Override
    public void render(Graphics graphics) {
    }
}