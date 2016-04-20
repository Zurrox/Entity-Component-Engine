package uk.ac.brighton.uni.ch629.ecengine.component;

import uk.ac.brighton.uni.ch629.ecengine.entity.Entity;
import uk.ac.brighton.uni.ch629.ecengine.logic.World;
import uk.ac.brighton.uni.ch629.ecengine.types.Vector2i;

import java.awt.*;

public class TransformComponent extends Component {
    public Vector2i pos;

    public TransformComponent(Entity parent) {
        this(parent, Vector2i.zero());
    }

    public TransformComponent(Entity parent, final Vector2i pos) {
        super(parent);
        this.pos = pos;
    }

    public void update(Graphics g, int deltaTime) {

    }
}