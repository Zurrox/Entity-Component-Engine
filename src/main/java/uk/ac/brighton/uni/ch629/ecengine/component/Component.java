package uk.ac.brighton.uni.ch629.ecengine.component;

import uk.ac.brighton.uni.ch629.ecengine.entity.Entity;
import uk.ac.brighton.uni.ch629.ecengine.logic.World;
import uk.ac.brighton.uni.ch629.ecengine.rendering.*;

import java.awt.*;
import java.awt.Color;
import java.util.Observable;

/**
 * A Component class for Entities to hold in order to do anything in the Engine
 */
public abstract class Component extends Observable {
    protected final Entity parent;
    private int waitFrames = 0;
    protected Color color = Color.BLACK;

    public Component(Entity parent) { //TODO: I could juse have it set the parent when you add it to the Entity...
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

    public void setColor(Color color) {
        this.color = color;
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

    /**
     * Stops the Entity from updating for a set amount of frames
     * @param frames The amount of frames to skip
     */
    public void wait(int frames) {
        waitFrames += frames;
    }

    /**
     * Get how many frames should be skipped
     * @return The amount of frames to skip
     */
    public int getWaitFrames() {
        return waitFrames;
    }

    /**
     * Decrements the wait frames by one.
     */
    public void decreaseWaitFrames() {
        if (waitFrames > 0) waitFrames -= 1;
    }

    /**
     * Update the Component
     *
     * @param deltaTime The Delta Time since the last frame.
     */
    public abstract void update(double deltaTime);

    /**
     * Render the component
     * @param graphics The Graphics to draw on screen.
     */
    public abstract void render(Graphics2D graphics);

    public void kill() {
    }
}