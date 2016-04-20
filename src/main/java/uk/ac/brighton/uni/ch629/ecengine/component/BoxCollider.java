package uk.ac.brighton.uni.ch629.ecengine.component;

import uk.ac.brighton.uni.ch629.ecengine.colliders.ICollider;
import uk.ac.brighton.uni.ch629.ecengine.entity.Entity;
import uk.ac.brighton.uni.ch629.ecengine.logic.World;
import uk.ac.brighton.uni.ch629.ecengine.types.Box2i;
import uk.ac.brighton.uni.ch629.ecengine.types.Circle2i;

import java.util.UUID;

public class BoxCollider extends CollisionComponent {
    protected Box2i box;

    public BoxCollider(Entity parent) {
        this(parent, 0, 0, 1, 1);
    }

    public BoxCollider(Entity parent, int x, int y, int width, int height) {
        super(parent);
        box = new Box2i(x, y, width, height);
    }

    public boolean intersects(ICollider other) {
        if (other instanceof BoxCollider) return intersects(((BoxCollider) other).box);
        else if (other instanceof CircleCollider) return intersects(((CircleCollider) other).circle);
        else return other.intersects(this); //TODO: Be careful with this, can easily cause a StackOverflowException
    }

    private boolean intersects(Box2i otherBox) {
        return box.x <= otherBox.x + otherBox.width &&
                box.x + box.width >= otherBox.x &&
                box.y <= otherBox.y + otherBox.height &&
                box.height + box.y >= otherBox.y;
    }

    private boolean intersects(Circle2i otherCircle) { //TODO: This is a Broad Search
        Box2i box = new Box2i(otherCircle.x - otherCircle.radius, otherCircle.y - otherCircle.radius, otherCircle.radius * 2, otherCircle.radius * 2);
        return intersects(box);
    }
}