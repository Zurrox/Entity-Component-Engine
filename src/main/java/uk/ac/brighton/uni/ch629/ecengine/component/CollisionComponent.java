package uk.ac.brighton.uni.ch629.ecengine.component;

import uk.ac.brighton.uni.ch629.ecengine.colliders.ICollider;
import uk.ac.brighton.uni.ch629.ecengine.event.CollisionEvent;
import uk.ac.brighton.uni.ch629.ecengine.logic.World;
import uk.ac.brighton.uni.ch629.ecengine.rendering.Graphics;

import java.util.UUID;

public abstract class CollisionComponent extends Component implements ICollider {
    public CollisionComponent(World world, UUID parentID) {
        super(world, parentID);
    }

    public void update(Graphics g, int deltaTime) { //FIXME: Need to make sure each CollisionEvent only gets called once, as both components will be updated meaning that the event is sent for each collision.
        for(CollisionComponent collider : world.COLLISION_HANDLER.colliders) {
            if(collider != this && intersects(collider)) world.EVENT_BUS.send(new CollisionEvent(parentID, collider.parentID)); //FIXME: Event is not being called correctly...
        }
    }
}