package uk.ac.brighton.uni.ch629.ecengine.component;

import uk.ac.brighton.uni.ch629.ecengine.colliders.ICollider;
import uk.ac.brighton.uni.ch629.ecengine.entity.Entity;
import uk.ac.brighton.uni.ch629.ecengine.logic.World;
import uk.ac.brighton.uni.ch629.ecengine.types.Box2i;
import uk.ac.brighton.uni.ch629.ecengine.types.Circle2i;

import java.util.UUID;

public class CircleCollider extends CollisionComponent {
    public Circle2i circle;

    public CircleCollider(Entity parent) {
        this(parent, 0, 0, 1);
    }

    public CircleCollider(Entity parent, int x, int y, int radius) {
        super(parent);
        circle = new Circle2i(x, y, radius);
    }

    public boolean intersects(ICollider other) { //FIXME: What can I do with this duplicate code?
        if (other instanceof BoxCollider) return intersects(((BoxCollider) other).box);
        else if (other instanceof CircleCollider) return intersects(((CircleCollider) other).circle);
        else return other.intersects(this);
    }

    private boolean intersects(final Box2i otherBox) { //TODO: This is a broad search
        Box2i box = new Box2i(circle.x - circle.radius, circle.y - circle.radius, circle.radius * 2, circle.radius * 2);
        return otherBox.intersects(box);
    }

    private boolean intersects(final Circle2i otherCircle) {
        return circle.getDistance(otherCircle) < circle.radius + otherCircle.radius;
    }
}