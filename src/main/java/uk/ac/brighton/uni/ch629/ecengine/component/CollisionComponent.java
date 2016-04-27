package uk.ac.brighton.uni.ch629.ecengine.component;

import uk.ac.brighton.uni.ch629.ecengine.colliders.ICollider;
import uk.ac.brighton.uni.ch629.ecengine.entity.Entity;
import uk.ac.brighton.uni.ch629.ecengine.event.CollisionEvent;
import uk.ac.brighton.uni.ch629.ecengine.types.Vector2d;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

@ComponentDetails(dependencies = TransformComponent.class, type = ComponentDetails.ComponentType.COLLISION)
public abstract class CollisionComponent extends Component implements ICollider {
    protected Vector2d offset = Vector2d.zero; //TODO: Move to Vector2f or Point

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
        else if (other instanceof CircleCollider) return intersects(((CircleCollider) other).getEllipse());
        else return other.intersects(this); //TODO: Be careful with this, can easily cause a StackOverflowException
    }

    @Override
    public void render(Graphics2D graphics) {

    }

    public void setOffset(double x, double y) {
        offset.setPos(x, y);
    }

    public void setOffset(Vector2d offset) {
        this.offset = offset;
    }

    protected abstract boolean intersects(Rectangle2D otherRectangle);

    protected abstract boolean intersects(Ellipse2D otherCircle);

    @Override
    public void kill() {
        parent.getWorld().COLLISION_HANDLER.removeCollider(this);
    }
}