package uk.ac.brighton.uni.ch629.ecengine.entity;

import uk.ac.brighton.uni.ch629.ecengine.component.Component;
import uk.ac.brighton.uni.ch629.ecengine.component.ComponentDetails;
import uk.ac.brighton.uni.ch629.ecengine.component.TransformComponent;
import uk.ac.brighton.uni.ch629.ecengine.logic.World;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Entity {
    final World WORLD;
    final UUID ID;
    final Set<Component> COMPONENTS;
    protected TransformComponent transform = new TransformComponent(this); //All Entities should have some sort of positional data, if not they could just be zero.
    String tag = "GameObject";

    public Entity(World world) {
        this(world, UUID.randomUUID());
    }

    public Entity(World world, Component... comps) {
        this(world);
        COMPONENTS.add(transform);
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
        COMPONENTS = new HashSet<>();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void addComponent(Component component) {
        if (!hasComponent(component.getClass())) COMPONENTS.add(component);
    }

    public void addComponent(Class<? extends Component> component) {
        if (component.isAnnotationPresent(ComponentDetails.class)) {
            ComponentDetails details = component.getAnnotation(ComponentDetails.class);
            Class<? extends Component>[] dependencies = details.dependencies();
            if (dependencies.length > 0) {
                for (Class clazz : dependencies) {
                    if (!hasComponent(clazz))
                        return; //TODO: Throw Dependency Exception -- Doesn't have the dependent component
                }
            }
        }

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
        for (Component comp : COMPONENTS) {
            if (comp.getClass().isAnnotationPresent(ComponentDetails.class)) {
                ComponentDetails details = comp.getClass().getAnnotation(ComponentDetails.class);
                if (details.dependencies().length > 0) {
                    for (Class<? extends Component> clazz : details.dependencies()) {
                        if (clazz == component)
                            return false; //TODO: Another Component depends on this one. -> Could have another parameter which says to remove dependant Components
                    }
                }
            }
        }

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

    public void update(double deltaTime) {
        for (Component component : COMPONENTS) {
            if (component.getWaitFrames() == 0) component.update(deltaTime);
            else component.decreaseWaitFrames();
        }
    }

    public void render(Graphics2D graphics) {
        for(Component component : COMPONENTS) component.render(graphics);
    }

    public UUID getID() {
        return ID;
    }

    public World getWorld() {
        return WORLD;
    }

    public TransformComponent getTransform() {
        return transform;
    }

    public void kill() {
        for (Component component : COMPONENTS) component.kill();
        COMPONENTS.clear();
        WORLD.removeEntity(ID);
    }
}