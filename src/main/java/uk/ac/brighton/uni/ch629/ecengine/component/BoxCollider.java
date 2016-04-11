package uk.ac.brighton.uni.ch629.ecengine.component;

import uk.ac.brighton.uni.ch629.ecengine.event.CollisionEvent;
import uk.ac.brighton.uni.ch629.ecengine.logic.World;
import uk.ac.brighton.uni.ch629.ecengine.rendering.Graphics;
import uk.ac.brighton.uni.ch629.ecengine.types.Box2i;

import java.util.UUID;

public class BoxCollider extends Component
{
    Box2i box;

    public BoxCollider(final World world, final UUID parentID, final int x, final int y, final int width, final int height)
    {
        super(world, parentID);
        box = new Box2i(x, y, width, height);
    }

    public void update(Graphics g, int deltaTime)
    { //TODO: This only needs to be called when the entity actually moves to save memory.
        if (false)
        { //When this entity touches another //TODO: Loop through all Collider Components in the world?
            world.EVENT_BUS.send(new CollisionEvent(parentID, UUID.randomUUID())); //TODO: Maybe have a hasComponentType(Collider) or something like that?
        }
    }
}