package uk.ac.brighton.uni.ch629.ecengine.component;

import uk.ac.brighton.uni.ch629.ecengine.entity.Entity;
import uk.ac.brighton.uni.ch629.ecengine.types.Vector2i;

import java.awt.*;

@ComponentDetails(dependencies = TransformComponent.class, type = ComponentDetails.ComponentType.RENDER)
public class CircleRenderComponent extends Component {
    private Vector2i offset = Vector2i.zero;
    private int radius = 1;

    public CircleRenderComponent(Entity parent) {
        super(parent);
    }

    public void setOffset(int x, int y) {
        this.offset = new Vector2i(x, y);
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void update(double deltaTime) {

    }

    public void render(Graphics graphics) {
        Vector2i pos = parent.getTransform().getPos();

        //- radius so the pos is in the middle of the circle, not the top right
        graphics.fillOval(pos.x + offset.x - radius, pos.y + offset.y - radius, radius * 2, radius * 2);
    }
}
