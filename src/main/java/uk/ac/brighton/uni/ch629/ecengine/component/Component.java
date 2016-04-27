package uk.ac.brighton.uni.ch629.ecengine.component;

import uk.ac.brighton.uni.ch629.ecengine.entity.Entity;
import uk.ac.brighton.uni.ch629.ecengine.logic.World;

import java.awt.*;
import java.util.Observable;

/**
 * A Component class for Entities to hold in order to do anything in the Engine
 */
public abstract class Component extends Observable {
    protected final Entity parent;
    private int waitFrames = 0;

    public Component(Entity parent) {
        this.parent = parent;
    }

    /**
     * Get the Parent of the Component
     *
     * @return The Entity holding this Component
     */
    public Entity getParent() {
        return parent;
    }

    /**
     * Gets a Component in the current Entity.
     *
     * @param compClass The class of the Component to retrieve
     * @param <T>       The type of Component
     * @return The Component if it exists within the Entity
     */
    public <T extends Component> T getComponent(Class<T> compClass) {
        return parent.getComponent(compClass);
    }

    /**
     * Get the World that the current Component is contained within
     *
     * @return The World which the Component is held by
     */
    public World getWorld() {
        return parent.getWorld();
    }

    public void wait(int frames) {
        waitFrames += frames;
    }

    public int getWaitFrames() {
        return waitFrames;
    }

    public void decreaseWaitFrames() {
        if (waitFrames > 0) waitFrames -= 1;
    }

    /**
     * Update the Component
     *
     * @param deltaTime The Delta Time since the last frame.
     */
    public abstract void update(double deltaTime);

    public abstract void render(Graphics2D graphics);

    public void kill() {
    }
}