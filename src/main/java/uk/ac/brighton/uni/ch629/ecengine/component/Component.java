package uk.ac.brighton.uni.ch629.ecengine.component;

import uk.ac.brighton.uni.ch629.ecengine.logic.World;

import java.awt.*;
import java.util.UUID;

/**
 * A Component class for Entities to hold in order to do anything in the Engine
 */
public abstract class Component {
    protected final World world;
    protected final UUID parentID;

    public Component(final World world, final UUID parentID) {
        this.world = world;
        this.parentID = parentID;
    }

    /**
     * Get the Parent of the Component
     * @return The ID of the Entity holding this Component
     */
    public UUID getParentID() {
        return parentID;
    }

    /**
     * Get the World that the current Component is contained within
     * @return The World which the Component is held by
     */
    public World getWorld() {
        return world;
    }

    /**
     * Update the Component
     * @param g Instance of Graphics to draw to the Game Window
     * @param deltaTime The Delta Time since the last frame.
     */
    public abstract void update(Graphics g, int deltaTime); //TODO: Split to Render & Update (Update doesn't require Graphics
}