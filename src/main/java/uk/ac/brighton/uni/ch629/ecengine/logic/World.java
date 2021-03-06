package uk.ac.brighton.uni.ch629.ecengine.logic;

import uk.ac.brighton.uni.ch629.ecengine.component.Component;
import uk.ac.brighton.uni.ch629.ecengine.entity.Entity;
import uk.ac.brighton.uni.ch629.ecengine.event.EventBus;
import uk.ac.brighton.uni.ch629.ecengine.exceptions.DuplicateComponentException;
import uk.ac.brighton.uni.ch629.ecengine.game.GameWindow;
import uk.ac.brighton.uni.ch629.ecengine.physics.CollisionHandler;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A class to hold all Entities for the current instance. This World runs all Events &amp; Collisions for the set of
 * Entities. The World also updates all Entities.
 */
public class World {
    public final EventBus EVENT_BUS;
    public final CollisionHandler COLLISION_HANDLER;
    public final GameWindow WINDOW;
    //TODO: Maybe use an Entity class to hold components, can make blueprints through that. Can also have Tags, ie to know which Entity is the Player
    public final HashMap<UUID, Entity> entities;
    public Set<Entity> entitiesToAdd = new HashSet<>();
    UUID id;

    public World(final GameWindow window) {
        this.WINDOW = window;
        EVENT_BUS = new EventBus();
        COLLISION_HANDLER = new CollisionHandler();
        entities = new HashMap<>();
        id = UUID.randomUUID();
    }

    /**
     * Updates all Entities(The Components)
     *
     * @param deltaTime The Delta Time since the last update frame
     */
    public void updateAll(double deltaTime) {
        //TODO: Could use an instance of Time within the GameWindow which can then be used to get the deltaTime value
        for (Entity entity : entities.values()) if(!entity.dead) entity.update(deltaTime);
//        Iterator<Entity> itr = entities.values().iterator();
//        while(itr.hasNext()) if(itr.next().dead) itr.remove();
    }

    public boolean hasEntity(UUID entityID) {
        for(Entity entity : entities.values()) if(entity.getID().equals(entityID)) return true;
        return false;
    }

    public void renderAll(Graphics2D graphics) {
        for(Entity entity : entities.values()) entity.render(graphics);
    }

    public void render(UUID entityID, Graphics2D graphics) {
        Entity entity = entities.get(entityID);
        if(entity != null) entity.render(graphics);
    }

    /**
     * Updates a specific Entity
     *
     * @param entityID  The ID of the entity to update
     * @param deltaTime The Delta Time since the last update frame
     */
    public void update(UUID entityID, int deltaTime) {
        Entity entity = entities.get(entityID);
        if(entity != null) entity.update(deltaTime);
    }

    public void addEntity(Entity entity) {
        if (!entities.containsKey(entity.getID()) && !entitiesToAdd.contains(entity)) entitiesToAdd.add(entity);
    }

    public void addAllEntities() {
        for(Entity entity : entitiesToAdd) entities.put(entity.getID(), entity);
        entitiesToAdd.clear();
    }

    /**
     * Get's all the Components within a specific Entity
     *
     * @param entity The ID of the entity to get Components from
     * @return A list of Components held by the set Entity
     */
    public Set<Component> getComponents(UUID entity) {
        return entities.containsKey(entity) ? entities.get(entity).getComponents() : null;
    }

    /**
     * Checks whether an Entity has a specific Component
     *
     * @param entityID  The ID of the Entity to be checked
     * @param compClass The class of the Component to be checked
     * @return Whether the specific Entity has a Component of a set type
     */
    public boolean hasComponent(UUID entityID, Class<? extends Component> compClass) {
        if (entities.containsKey(entityID)) return entities.get(entityID).hasComponent(compClass);
        return false;
    }

    /**
     * Gets an instance of a Component held by the specific Entity
     *
     * @param entityID  The Entity to check
     * @param compClass The class of the Component to get
     * @param <T>       The type of Component
     * @return The instance of the Component held by the specific Entity
     */
    public <T extends Component> T getComponent(UUID entityID, Class<T> compClass) {
        if (entities.containsKey(entityID)) return entities.get(entityID).getComponent(compClass);
        return null;
    }

    /**
     * Get a Component of a specific Class within a list of Components
     *
     * @param comps     A list of Components
     * @param compClass The class of the Component to receive
     * @param <T>       The type of Component
     * @return The instanced Component within the List
     */
    public <T extends Component> T getComponent(List<Component> comps, Class<T> compClass) {
        for (Component comp : comps) if (comp.getClass().equals(compClass)) return compClass.cast(comp);
        return null;
    }

    /**
     * Get all Components which are instances of a specific class
     *
     * @param clazz The class which the components are implementing
     * @return A list of components implementing the class
     */
    public List<Component> getComponentsImplementing(Class clazz) {
        List<Component> out = new ArrayList<Component>();
        for (Entity entity : entities.values()) out.addAll(entity.getComponentsImplementing(clazz));
        return out;
    }

    /**
     * Check whether a specific Component type is contained within a list of Components
     *
     * @param comps     The list of Components
     * @param compClass The class of the Component to find
     * @return Whether the Component exists within the list
     */
    public boolean containsComponent(List<Component> comps, Class<? extends Component> compClass) {
        return getComponent(comps, compClass) != null;
    }

    /**
     * Adds one or more Components to an Entity
     *
     * @param entityID  The Entity to add the Components to
     * @param component Component class to be added
     * @throws DuplicateComponentException If the Entity already has a Component of the added type, this is thrown
     */
    public void addComponent(UUID entityID, Class<? extends Component> component) throws DuplicateComponentException {
        Entity entity = entities.get(entityID);
        if (entity != null) entity.addComponent(component);
    }

    /**
     * Removes a Component within an Entity
     *
     * @param entityID  The Entity for the Component to be removed from
     * @param compClass The class of the Component to be removed
     */
    public boolean removeComponent(UUID entityID, Class<? extends Component> compClass) { //TODO: Maybe NoComponentException or something
        Entity entity = entities.get(entityID);
        return entity != null && entity.removeComponent(compClass);
    }

    public void removeEntity(UUID entity) {
        entities.remove(entity);
    }

    public List<Entity> getEntitiesWithTag(String tag) {
        return entities.values().stream().filter(e -> e.getTag().equalsIgnoreCase(tag)).collect(Collectors.toList());
    }

    /**
     * Get the ID of this World
     *
     * @return The ID of the World
     */
    public UUID getID() {
        return id;
    }
}