package uk.ac.brighton.uni.ch629.ecengine.logic;

import uk.ac.brighton.uni.ch629.ecengine.component.Component;
import uk.ac.brighton.uni.ch629.ecengine.component.DamageComponent;
import uk.ac.brighton.uni.ch629.ecengine.event.EventBus;
import uk.ac.brighton.uni.ch629.ecengine.exceptions.DuplicateComponentException;
import uk.ac.brighton.uni.ch629.ecengine.exceptions.DuplicateEntityException;
import uk.ac.brighton.uni.ch629.ecengine.physics.CollisionHandler;
import uk.ac.brighton.uni.ch629.ecengine.rendering.Graphics;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class World {
    public final EventBus EVENT_BUS;
    UUID id;
    HashMap<UUID, List<Component>> entities;
    public final CollisionHandler COLLISION_HANDLER;

    public World() {
        EVENT_BUS = new EventBus();
        COLLISION_HANDLER = new CollisionHandler();
        entities = new HashMap<UUID, List<Component>>();
        id = UUID.randomUUID();
    }

    public void updateAll(Graphics g, int deltaTime) {
        for (UUID id : entities.keySet()) {
            List<Component> comps = entities.get(id);
            for (Component comp : comps) comp.update(g, deltaTime);
        }
    }

    public void update(UUID entityID, Graphics g, int deltaTime) {
        if (entities.containsKey(entityID)) {
            for (Component comp : entities.get(entityID)) {
                comp.update(g, deltaTime);
            }
        }
    }

    public void addEntity(UUID entity, Component... comps) throws DuplicateEntityException {
        if (entities.containsKey(entity)) entities.put(entity, Arrays.asList(comps));
        else throw new DuplicateEntityException(this, entity);
    }

    public List<Component> getComponents(UUID entity) {
        if (entities.containsKey(entity)) return entities.get(entity);
        else return null;
    }

    public boolean hasComponent(UUID entityID, Class<? extends Component> compClass) {
        return getComponent(entityID, compClass) != null;
    }

    public <T extends Component> T getComponent(UUID entityID, Class<T> compClass) {
        return getComponent(getComponents(entityID), compClass);
    }

    public <T extends Component> T getComponent(List<Component> comps, Class<T> compClass) {
        for (Component comp : comps) if (comp.getClass().equals(compClass)) return compClass.cast(comp);
        return null;
    }

    public List<Component> getComponentsImplementing(Class clazz) {
        List<Component> components = new ArrayList<Component>();
        DamageComponent dc = new DamageComponent(this, null);
        if (dc.getClass().isAssignableFrom(clazz)) {
            components.add(dc); //TODO: Properly implement this.
        }
        return components;
    }

    public boolean containsComponent(List<Component> comps, Class<? extends Component> compClass) {
        return getComponent(comps, compClass) != null;
    }

    public void addComponent(UUID entityID, Class<? extends Component>... compClasses) throws DuplicateComponentException {
        for (Class<? extends Component> compClass : compClasses) {
            List<Component> components = getComponents(entityID);
            try {
                if (containsComponent(components, compClass))
                    throw new DuplicateComponentException(this, entityID, compClass);
                components.add(compClass.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeComponent(UUID entityID, Class<? extends Component> compClass) { //TODO: Maybe NoComponentException or something
        List<Component> components = getComponents(entityID);
        if (components != null) {
            Iterator<Component> itr = components.iterator();
            while (itr.hasNext()) {
                if (itr.next().getClass().equals(compClass)) {
                    itr.remove();
                    entities.put(entityID, components); //Should replace.
                    return;
                }
            }
        }
    }

    public void createEmptyEntity() {
        entities.put(UUID.randomUUID(), new ArrayList<Component>());
    }

    public void createEntity(Class<? extends Component>... compClasses) throws DuplicateComponentException {
        List<Component> components = new ArrayList<Component>();
        for (Class<? extends Component> compClass : compClasses)
            try {
                UUID entityID = UUID.randomUUID();
                if (containsComponent(components, compClass))
                    throw new DuplicateComponentException(this, entityID, compClass);
                components.add(compClass.getDeclaredConstructor(World.class, UUID.class).newInstance(this, entityID)); //TODO: Might break, idk
                entities.put(entityID, components);
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

    public UUID getID() {
        return id;
    }
}