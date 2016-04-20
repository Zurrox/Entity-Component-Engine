package uk.ac.brighton.uni.ch629.ecengine.component;

import uk.ac.brighton.uni.ch629.ecengine.colliders.ICollider;
import uk.ac.brighton.uni.ch629.ecengine.entity.Entity;
import uk.ac.brighton.uni.ch629.ecengine.event.CollisionEvent;
import uk.ac.brighton.uni.ch629.ecengine.logic.World;

import java.awt.*;

public abstract class CollisionComponent extends Component implements ICollider {
    public CollisionComponent(Entity parent) {
        super(parent);
    }

    public void update(Graphics g, int deltaTime) { //FIXME: Need to make sure each CollisionEvent only gets called once, as both components will be updated meaning that the event is sent for each collision.
        for (CollisionComponent collider : getWorld().COLLISION_HANDLER.colliders) {
            if (collider != this && intersects(collider))
                getWorld().EVENT_BUS.send(new CollisionEvent(parent.getID(), collider.getParent().getID())); //FIXME: Event is not being called correctly...
        }
    }
}