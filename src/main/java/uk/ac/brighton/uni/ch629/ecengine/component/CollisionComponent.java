package uk.ac.brighton.uni.ch629.ecengine.component;

import uk.ac.brighton.uni.ch629.ecengine.colliders.ICollider;
import uk.ac.brighton.uni.ch629.ecengine.entity.Entity;
import uk.ac.brighton.uni.ch629.ecengine.event.CollisionEvent;
import uk.ac.brighton.uni.ch629.ecengine.types.Circle2i;
import uk.ac.brighton.uni.ch629.ecengine.types.Vector2i;

import java.awt.*;

@ComponentDetails(dependencies = TransformComponent.class, type = ComponentDetails.ComponentType.COLLISION)
public abstract class CollisionComponent extends Component implements ICollider {
    protected Vector2i shape;
    protected Vector2i offset = Vector2i.zero;

    public CollisionComponent(Entity parent) {
        super(parent);
        parent.getWorld().COLLISION_HANDLER.addCollider(this);
    }

    @Override
    public void update(double deltaTime) {
        for (CollisionComponent collider : getWorld().COLLISION_HANDLER.colliders) {
            if (collider != this && intersects(collider))
                getWorld().EVENT_BUS.sendToQueue(new CollisionEvent(parent.getID(), collider.getParent().getID()));
        }
    }

    public boolean intersects(ICollider other) {
        if (other instanceof BoxCollider) return intersects(((BoxCollider) other).getRectangle());
        else if (other instanceof CircleCollider) return intersects(((CircleCollider) other).getCircle());
        else return other.intersects(this); //TODO: Be careful with this, can easily cause a StackOverflowException
    }

    @Override
    public void render(Graphics graphics) {

    }

    public void setOffset(int x, int y) {
        offset.setPos(x, y);
    }

    public void setOffset(Vector2i offset) {
        this.offset = offset;
    }

    protected abstract boolean intersects(Rectangle otherRectangle);

    protected abstract boolean intersects(Circle2i otherCircle);
}