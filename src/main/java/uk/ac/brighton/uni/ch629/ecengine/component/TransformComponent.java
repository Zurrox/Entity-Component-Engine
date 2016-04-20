package uk.ac.brighton.uni.ch629.ecengine.component;

import uk.ac.brighton.uni.ch629.ecengine.entity.Entity;
import uk.ac.brighton.uni.ch629.ecengine.types.Vector2i;

import java.awt.*;

public class TransformComponent extends Component {
    public Vector2i pos;

    public TransformComponent(Entity parent) {
        this(parent, Vector2i.zero);
    }

    public void update(double deltaTime) {

    }

    public void render(Graphics graphics) {

    }

    public TransformComponent(Entity parent, Vector2i pos) {
        super(parent);
        this.pos = pos;
    }

    public TransformComponent(Entity parent, int x, int y) {
        super(parent);
        pos = new Vector2i(x, y);
    }
}