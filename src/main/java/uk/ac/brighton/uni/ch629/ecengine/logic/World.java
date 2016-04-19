package uk.ac.brighton.uni.ch629.ecengine.logic;

import uk.ac.brighton.uni.ch629.ecengine.component.Component;
import uk.ac.brighton.uni.ch629.ecengine.component.DamageComponent;
import uk.ac.brighton.uni.ch629.ecengine.event.EventBus;
import uk.ac.brighton.uni.ch629.ecengine.exceptions.DuplicateComponentException;
import uk.ac.brighton.uni.ch629.ecengine.exceptions.DuplicateEntityException;
import uk.ac.brighton.uni.ch629.ecengine.physics.CollisionHandler;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.List;

/**
 * A class to hold all Entities for the current instance. This World runs all Events &amp; Collisions for the set of
 * Entities. The World also updates all Entities.
 */
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

    /**
     * Updates all Entities(The Components)
     * @param g The instance of Graphics to draw onto the Game Window
     * @param deltaTime The Delta Time since the last update frame
     */
    public void updateAll(Graphics g, int deltaTime) { //TODO: Split into Render & Update
        //TODO: Graphics could be held in the GameWindow which the World can hold.
        //TODO: Could use an instance of time within the GameWindow which can then be used to get the deltaTime value
        for (UUID id : entities.keySet()) {
            List<Component> comps = entities.get(id);
            for (Component comp : comps) comp.update(g, deltaTime);
        }
    }

    /**
     * Updates a specific Entity
     * @param entityID The ID of the entity to update
     * @param g The instance of Graphics to draw onto the Game Window
     * @param deltaTime The Delta Time since the last update frame
     */
    public void update(UUID entityID, Graphics g, int deltaTime) {
        if (entities.containsKey(entityID)) {
            for (Component comp : entities.get(entityID)) {
                comp.update(g, deltaTime);
            }
        }
    }

    /**
     * Adds an Entity containing set components.
     * @param entity The ID of the new Entity
     * @param comps A list of Components to be contained within the Entity
     * @throws DuplicateEntityException If there is already an Entity with the set ID, this is thrown
     */
    public void addEntity(UUID entity, Component... comps) throws DuplicateEntityException {
        //TODO: Should make another method not including the ID, which just generates a new ID
        if (entities.containsKey(entity)) entities.put(entity, Arrays.asList(comps));
        else throw new DuplicateEntityException(this, entity);
    }

    /**
     * Get's all the Components within a specific Entity
     * @param entity The ID of the entity to get Components from
     * @return A list of Components held by the set Entity
     */
    public List<Component> getComponents(UUID entity) {
        if (entities.containsKey(entity)) return entities.get(entity);
        else return null;
    }

    /**
     * Checks whether an Entity has a specific Component
     * @param entityID The ID of the Entity to be checked
     * @param compClass The class of the Component to be checked
     * @return Whether the specific Entity has a Component of a set type
     */
    public boolean hasComponent(UUID entityID, Class<? extends Component> compClass) {
        return getComponent(entityID, compClass) != null;
    }

    /**
     * Gets an instance of a Component held by the specific Entity
     * @param entityID The Entity to check
     * @param compClass The class of the Component to get
     * @param <T> The type of Component
     * @return The instance of the Component held by the specific Entity
     */
    public <T extends Component> T getComponent(UUID entityID, Class<T> compClass) {
        return getComponent(getComponents(entityID), compClass);
    }

    /**
     * Get a Component of a specific Class within a list of Components
     * @param comps A list of Components
     * @param compClass The class of the Component to receive
     * @param <T> The type of Component
     * @return The instanced Component within the List
     */
    public <T extends Component> T getComponent(List<Component> comps, Class<T> compClass) {
        for (Component comp : comps) if (comp.getClass().equals(compClass)) return compClass.cast(comp);
        return null;
    }

    /**
     * Get all Components which are instances of a specific class
     * @param clazz The class which the components are implementing
     * @return A list of components implementing the class
     */
    public List<Component> getComponentsImplementing(Class clazz) {
        List<Component> components = new ArrayList<Component>();
        DamageComponent dc = new DamageComponent(this, null);
        if (dc.getClass().isAssignableFrom(clazz)) {
            components.add(dc); //TODO: Properly implement this. -> Maybe use EntityID then Class (Mainly for CollisionComponent to find colliders)
        }
        return components;
    }

    /**
     * Check whether a specific Component type is contained within a list of Components
     * @param comps The list of Components
     * @param compClass The class of the Component to find
     * @return Whether the Component exists within the list
     */
    public boolean containsComponent(List<Component> comps, Class<? extends Component> compClass) {
        return getComponent(comps, compClass) != null;
    }

    /**
     * Adds one or more Components to an Entity
     * @param entityID The Entity to add the Components to
     * @param compClasses One or more Component classes to be added
     * @throws DuplicateComponentException If the Entity already has a Component of the added type, this is thrown
     */
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

    /**
     * Removes a Component within an Entity
     * @param entityID The Entity for the Component to be removed from
     * @param compClass The class of the Component to be removed
     */
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

    /**
     * Creates an Entity with no Components attached
     */
    public void createEmptyEntity() {
        entities.put(UUID.randomUUID(), new ArrayList<Component>());
    }

    /**
     * Creates an Entity with the specified Components
     * @param compClasses One or more Component classes to be initialized within the new Entity
     * @throws DuplicateComponentException If multiple of the same Component are added to the new Entity, this is thrown
     */
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

    /**
     * Get the ID of this World
     * @return The ID of the World
     */
    public UUID getID() {
        return id;
    }
}