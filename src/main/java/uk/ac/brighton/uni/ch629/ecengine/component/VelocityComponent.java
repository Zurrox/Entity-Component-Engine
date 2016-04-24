package uk.ac.brighton.uni.ch629.ecengine.component;

import uk.ac.brighton.uni.ch629.ecengine.entity.Entity;
import uk.ac.brighton.uni.ch629.ecengine.types.Vector2i;

import java.awt.*;

@ComponentDetails(dependencies = TransformComponent.class)
public class VelocityComponent extends Component {
    Vector2i vel = Vector2i.zero;

    public VelocityComponent(Entity parent) {
        super(parent);
    }

    public void update(double deltaTime) {
        parent.getTransform().move((int) Math.round(vel.x * deltaTime), (int) Math.round(vel.y * deltaTime));
    }

    public void setVelocity(int x, int y) {
        setVelocity(new Vector2i(x, y));
    }

    public void render(Graphics graphics) {

    }

    public void inverse() {
        vel = new Vector2i(vel.x * (-1), vel.y * (-1));
    }

    public Vector2i getVelocity() {
        return vel;
    }

    public void setVelocity(Vector2i vel) {
        this.vel = vel;
    }
}