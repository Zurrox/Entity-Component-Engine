package uk.ac.brighton.uni.ch629.ecengine.component;

import uk.ac.brighton.uni.ch629.ecengine.logic.World;
import uk.ac.brighton.uni.ch629.ecengine.types.Vector2i;

import java.awt.*;
import java.util.UUID;

public class TransformComponent extends Component {
    Vector2i pos;

    public TransformComponent(World world, UUID parentID, final Vector2i pos) {
        super(world, parentID);
        this.pos = pos;
    }

    public void update(Graphics g, int deltaTime) {

    }
}