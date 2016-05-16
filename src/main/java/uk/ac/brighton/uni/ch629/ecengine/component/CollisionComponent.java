package uk.ac.brighton.uni.ch629.ecengine.component;

import uk.ac.brighton.uni.ch629.ecengine.colliders.ICollider;
import uk.ac.brighton.uni.ch629.ecengine.entity.Entity;
import uk.ac.brighton.uni.ch629.ecengine.event.CollisionEvent;
import uk.ac.brighton.uni.ch629.ecengine.types.Vector2d;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

@ComponentDetails(dependencies = TransformComponent.class, type = ComponentDetails.ComponentType.COLLISION)
public abstract class CollisionComponent extends Component implements ICollider {
    protected Vector2d offset = Vector2d.zero; //TODO: Move to Vector2f or Point
    protected boolean isSolid = true;
    private ActionListener triggerListener = null;

    public CollisionComponent(Entity parent) {
        super(parent);
        parent.getWorld().COLLISION_HANDLER.addCollider(this);
    }

    public CollisionComponent(Entity parent, boolean solid) {
        this(parent);
        isSolid = solid;
    }

    public void setTrigger(ActionListener listener) {
        triggerListener = listener;
        isSolid = false;
    }

    public void onHit(Entity entity) {
        if(triggerListener != null) triggerListener.actionPerformed(new ActionEvent(entity, -1, null));
    }

    @Override
    public void update(double deltaTime) {
        getWorld().COLLISION_HANDLER.colliders.stream().filter(collider -> collider != this && intersects(collider)).forEach(collider -> {
            getWorld().EVENT_BUS.sendToQueue(new CollisionEvent(parent, collider.getParent()));
            onHit(collider.getParent());
            collider.onHit(parent);
        });
    }

    public boolean intersects(ICollider other) {
        if (other instanceof BoxCollider) return intersects(((BoxCollider) other).getRectangle());
        else if (other instanceof CircleCollider) return intersects(((CircleCollider) other).getEllipse());
        else return other.intersects(this); //TODO: Be careful with this, can easily cause a StackOverflowException
    }

    @Override
    public void render(Graphics2D graphics) {

    }

    public void setSolid(boolean solid) {
        isSolid = solid;
    }

    public boolean isSolid() {
        return isSolid;
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
        synchronized (parent.getWorld().COLLISION_HANDLER) {
            parent.getWorld().COLLISION_HANDLER.removeCollider(this);
        }
    }
}