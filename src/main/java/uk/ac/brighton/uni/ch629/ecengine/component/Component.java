package uk.ac.brighton.uni.ch629.ecengine.component;

import uk.ac.brighton.uni.ch629.ecengine.logic.World;
import uk.ac.brighton.uni.ch629.ecengine.rendering.Graphics;

import java.util.UUID;

public abstract class Component
{
    protected final World world;
    protected final UUID parentID;
    //TODO: Maybe ComponentType which will be Collider, Renderer?... or just have ICollider and implement it into the BoxCollider and etc, then have some check to see if something is instanceof ICollider

    public Component(final World world, final UUID parentID)
    {
        this.world = world;
        this.parentID = parentID;
    }

    public UUID getParentID()
    {
        return parentID;
    }

    public World getWorld()
    {
        return world;
    }

    public abstract void update(Graphics g, int deltaTime);
}