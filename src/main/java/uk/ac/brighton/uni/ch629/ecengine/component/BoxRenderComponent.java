package uk.ac.brighton.uni.ch629.ecengine.component;

import uk.ac.brighton.uni.ch629.ecengine.entity.Entity;
import uk.ac.brighton.uni.ch629.ecengine.types.Box2i;

import java.awt.*;

public class BoxRenderComponent extends Component {
    public Box2i box;

    public BoxRenderComponent(Entity parent) {
        super(parent);
        box = new Box2i(0, 0, 1, 1);
    }

    public void update(double deltaTime) {

    }

    public void render(Graphics graphics) {
        graphics.fillRect(box.x, box.y, box.width, box.height);
    }
}