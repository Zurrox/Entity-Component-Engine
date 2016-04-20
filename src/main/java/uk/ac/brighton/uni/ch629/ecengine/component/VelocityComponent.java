package uk.ac.brighton.uni.ch629.ecengine.component;

import uk.ac.brighton.uni.ch629.ecengine.entity.Entity;
import uk.ac.brighton.uni.ch629.ecengine.types.Vector2i;

import java.awt.*;

public class VelocityComponent extends Component {
    Vector2i vel = Vector2i.zero;

    public VelocityComponent(Entity parent) {
        super(parent);
    }

    public void update(double deltaTime) {
        TransformComponent transformComponent = parent.getComponent(TransformComponent.class);
        if(transformComponent != null) transformComponent.pos.move(vel.x * deltaTime, vel.y * deltaTime);
    }

    public void render(Graphics graphics) {

    }
}