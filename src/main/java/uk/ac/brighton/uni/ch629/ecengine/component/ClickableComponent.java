package uk.ac.brighton.uni.ch629.ecengine.component;

import uk.ac.brighton.uni.ch629.ecengine.logic.World;
import uk.ac.brighton.uni.ch629.ecengine.rendering.Graphics;

import java.util.UUID;

public class ClickableComponent extends Component {
    public ClickableComponent(World world, UUID parentID) {
        super(world, parentID);
    }

    public void update(Graphics g, int deltaTime) {

    }
}