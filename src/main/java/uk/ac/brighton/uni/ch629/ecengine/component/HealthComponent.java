package uk.ac.brighton.uni.ch629.ecengine.component;

import uk.ac.brighton.uni.ch629.ecengine.event.CollisionEvent;
import uk.ac.brighton.uni.ch629.ecengine.event.SubscribeEvent;
import uk.ac.brighton.uni.ch629.ecengine.event.SubscriptionClass;
import uk.ac.brighton.uni.ch629.ecengine.logic.World;

import java.awt.*;
import java.util.UUID;

public class HealthComponent extends Component {
    private int health, maxHealth;
    private SubscriptionClass<HealthComponent> subClass;

    public HealthComponent(final World world, final UUID parentID) {
        this(world, parentID, 100);
    }

    public HealthComponent(final World world, final UUID parentID, final int maxHealth) {
        this(world, parentID, maxHealth, maxHealth);
    }

    public HealthComponent(final World world, final UUID parentID, final int health, final int maxHealth) {
        super(world, parentID);
        this.health = health;
        this.maxHealth = maxHealth;
        if(subClass == null) subClass = world.EVENT_BUS.registerListenerClass(HealthComponent.class);
        subClass.subscribe(this);
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void update(Graphics g, int deltaTime) {

    }

    @SubscribeEvent
    public void onHit(CollisionEvent collisionEvent) {
        UUID other;
        if (collisionEvent.entity1.equals(parentID)) other = collisionEvent.entity2;
        else other = collisionEvent.entity1;
        if (world.hasComponent(other, DamageComponent.class)) {
            DamageComponent dc = world.getComponent(other, DamageComponent.class);
            health -= dc.getDamage();
        }
    }
}