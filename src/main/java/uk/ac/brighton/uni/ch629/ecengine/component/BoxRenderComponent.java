package uk.ac.brighton.uni.ch629.ecengine.component;

import uk.ac.brighton.uni.ch629.ecengine.entity.Entity;
import uk.ac.brighton.uni.ch629.ecengine.types.Vector2d;

import java.awt.*;
import java.awt.geom.Rectangle2D;

@ComponentDetails(dependencies = TransformComponent.class, type = ComponentDetails.ComponentType.RENDER)
public class BoxRenderComponent extends Component {
    private Vector2d offset = Vector2d.zero;
    private double width = 1, height = 1;

    public BoxRenderComponent(Entity parent) {
        super(parent);
    }

    public BoxRenderComponent(Entity parent, double width, double height) {
        this(parent);
        this.width = width;
        this.height = height;
    }

    public void update(double deltaTime) {

    }

    public Rectangle2D getRectangle() {
        Vector2d pos = parent.getTransform().getPos();
        return new Rectangle2D.Double(pos.x + offset.x, pos.y + offset.y, width, height);
    }

    public void setSize(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public Vector2d getSize() {
        return new Vector2d(width, height);
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void render(Graphics2D graphics) {
        graphics.setColor(color);
        graphics.fill(getRectangle());
    }
}