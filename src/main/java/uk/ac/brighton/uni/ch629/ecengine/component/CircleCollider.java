package uk.ac.brighton.uni.ch629.ecengine.component;

import uk.ac.brighton.uni.ch629.ecengine.entity.Entity;
import uk.ac.brighton.uni.ch629.ecengine.types.Vector2d;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;


@ComponentDetails(dependencies = TransformComponent.class, type = ComponentDetails.ComponentType.COLLISION)
public class CircleCollider extends CollisionComponent {
    private int radius;

    public CircleCollider(Entity parent) {
        this(parent, 1);
    }

    public CircleCollider(Entity parent, int radius) {
        super(parent);
        this.radius = radius;
    }

    public CircleCollider(Entity parent, boolean trigger, int radius) {
        this(parent, radius);
        setSolid(trigger);
    }

    public Ellipse2D getEllipse() {
        Vector2d pos = parent.getTransform().getPos();
        return new Ellipse2D.Double(pos.x + offset.x, pos.y + offset.y, radius * 2, radius * 2);
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    protected boolean intersects(Rectangle2D rectangle) {
        return getEllipse().intersects(rectangle);
    }

    protected boolean intersects(Ellipse2D otherCircle) {
        return false; //TODO: IMPLEMENT
    }

    @Override
    public void render(Graphics2D graphics) {
    }
}