package uk.ac.brighton.uni.ch629.ecengine.component;

import uk.ac.brighton.uni.ch629.ecengine.entity.Entity;
import uk.ac.brighton.uni.ch629.ecengine.types.Vector2d;

import java.awt.*;
import java.awt.geom.Ellipse2D;

@ComponentDetails(dependencies = TransformComponent.class, type = ComponentDetails.ComponentType.RENDER)
public class CircleRenderComponent extends Component {
    private Vector2d offset = Vector2d.zero;
    private float radius = 1;

    public CircleRenderComponent(Entity parent) {
        super(parent);
    }

    public void setOffset(int x, int y) {
        this.offset = new Vector2d(x, y);
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public Ellipse2D getEllipse() {
        Vector2d pos = parent.getTransform().getPos();
        return new Ellipse2D.Double(pos.x + offset.x, pos.y + offset.y, radius * 2, radius * 2);
    }

    public void update(double deltaTime) {

    }

    public void render(Graphics2D graphics) {
        graphics.fill(getEllipse());
    }
}
