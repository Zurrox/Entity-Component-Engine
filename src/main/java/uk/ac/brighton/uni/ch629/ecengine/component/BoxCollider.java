package uk.ac.brighton.uni.ch629.ecengine.component;

import uk.ac.brighton.uni.ch629.ecengine.entity.Entity;
import uk.ac.brighton.uni.ch629.ecengine.types.Vector2d;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

@ComponentDetails(dependencies = TransformComponent.class, type = ComponentDetails.ComponentType.COLLISION)
public class BoxCollider extends CollisionComponent {
    private double width, height;

    public BoxCollider(Entity parent) {
        this(parent, 1, 1);
        initialize();
    }

    public BoxCollider(Entity parent, double width, double height) {
        super(parent);
        initialize();
        this.width = width;
        this.height = height;
    }

    public void initialize() {
    }

    public void setSize(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public Rectangle2D getRectangle() {
        Vector2d pos = parent.getTransform().getPos();
        return new Rectangle2D.Double(pos.x + offset.x, pos.y + offset.y, width, height);
    }

    protected boolean intersects(Rectangle2D otherRectangle) {
        return getRectangle().intersects(otherRectangle);
    }

    protected boolean intersects(Ellipse2D ellipse) {
        return ellipse.intersects(getRectangle());
    }
}