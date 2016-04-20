package uk.ac.brighton.uni.ch629.ecengine.entity;

import uk.ac.brighton.uni.ch629.ecengine.component.Component;
import uk.ac.brighton.uni.ch629.ecengine.logic.World;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Entity {
    final World WORLD;
    final UUID ID;
    final Set<Component> COMPONENTS;

    public Entity(World world) {
        this(world, UUID.randomUUID());
    }

    public Entity(World world, Component... comps) {
        this(world);
        COMPONENTS.addAll(Arrays.asList(comps));
    }

    public Entity(World world, Class<? extends Component>... comps) {
        this(world);
        for (Class<? extends Component> comp : comps) {
            try {
                COMPONENTS.add(comp.getDeclaredConstructor(Entity.class).newInstance(this));
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    public Entity(final World world, final UUID id) {
        this.WORLD = world;
        this.ID = id;
        COMPONENTS = new HashSet<Component>();
    }

    public void addComponent(Component component) {
        if (!hasComponent(component.getClass())) COMPONENTS.add(component);
    }

    public void addComponent(Class<? extends Component> component) {
        if (!hasComponent(component)) try {
            COMPONENTS.add(component.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public <T extends Component> T getComponent(Class<? extends T> component) {
        for (Component comp : COMPONENTS) if (comp.getClass() == component) return (T) comp;
        return null;
    }

    public boolean removeComponent(Class<? extends Component> component) {
        Iterator<Component> itr = COMPONENTS.iterator();
        while (itr.hasNext()) {
            if (itr.next().getClass() == component) {
                itr.remove();
                return true;
            }
        }
        return false;
    }

    public boolean hasComponent(Class<? extends Component> component) {
        for (Component comp : COMPONENTS) if (comp.getClass() == component) return true;
        return false;
    }

    public Entity blueprint() {
        Entity newEntity = new Entity(WORLD);
        newEntity.COMPONENTS.addAll(COMPONENTS);
        return newEntity;
    }

    public Set<Component> getComponents() {
        return COMPONENTS;
    }

    public Set<Component> getComponentsImplementing(Class clazz) {
        return null; //TODO: Implement
    }

    public void update(int deltaTime) {
        for (Component component : COMPONENTS) component.update(deltaTime);
    }

    public void render(Graphics graphics) {
        for(Component component : COMPONENTS) component.render(graphics);
    }

    public UUID getID() {
        return ID;
    }

    public World getWorld() {
        return WORLD;
    }
}