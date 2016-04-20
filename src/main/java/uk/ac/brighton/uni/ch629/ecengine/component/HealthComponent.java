package uk.ac.brighton.uni.ch629.ecengine.component;

import uk.ac.brighton.uni.ch629.ecengine.entity.Entity;
import uk.ac.brighton.uni.ch629.ecengine.event.CollisionEvent;
import uk.ac.brighton.uni.ch629.ecengine.event.SubscribeEvent;
import uk.ac.brighton.uni.ch629.ecengine.event.SubscriptionClass;

import java.awt.*;
import java.util.UUID;

public class HealthComponent extends Component {
    private int health, maxHealth;
    private SubscriptionClass<HealthComponent> subClass;

    public HealthComponent(Entity parent) {
        this(parent, 100);
    }

    public void update(int deltaTime) {

    }

    public void render(Graphics graphics) {

    }

    public HealthComponent(Entity parent, int maxHealth) {
        this(parent, maxHealth, maxHealth);
    }

    public HealthComponent(Entity parent, int health, int maxHealth) {
        super(parent);
        this.health = health;
        this.maxHealth = maxHealth;
        if (subClass == null) subClass = getWorld().EVENT_BUS.registerListenerClass(HealthComponent.class);
        subClass.subscribe(this);
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    @SubscribeEvent
    public void onHit(CollisionEvent collisionEvent) {
        UUID other;
        if (collisionEvent.entity1.equals(parent.getID())) other = collisionEvent.entity2;
        else other = collisionEvent.entity1;
        if (getWorld().hasComponent(other, DamageComponent.class)) {
            DamageComponent dc = getWorld().getComponent(other, DamageComponent.class);
            health -= dc.getDamage();
        }
    }
}