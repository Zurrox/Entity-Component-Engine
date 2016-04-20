package uk.ac.brighton.uni.ch629.ecengine.component;

import uk.ac.brighton.uni.ch629.ecengine.entity.Entity;

import java.awt.*;

public class DamageComponent extends Component {
    private int damage;

    public DamageComponent(Entity parent) {
        this(parent, 1);
    }

    public void update(int deltaTime) {

    }

    public void render(Graphics graphics) {

    }

    public DamageComponent(Entity parent, int damage) {
        super(parent);
        setDamage(damage);
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(final int damage) {
        this.damage = damage;
    }
}