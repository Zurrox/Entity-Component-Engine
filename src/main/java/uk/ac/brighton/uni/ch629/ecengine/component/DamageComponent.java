package uk.ac.brighton.uni.ch629.ecengine.component;

import uk.ac.brighton.uni.ch629.ecengine.logic.World;

import java.awt.*;
import java.util.UUID;

public class DamageComponent extends Component {
    private int damage;

    public DamageComponent(final World world, final UUID parentID) {
        this(world, parentID, 1);
    }

    public DamageComponent(final World world, final UUID parentID, final int damage) {
        super(world, parentID);
        setDamage(damage);
    }

    public void update(Graphics g, int deltaTime) {

    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(final int damage) {
        this.damage = damage;
    }
}