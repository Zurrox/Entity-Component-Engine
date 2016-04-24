package uk.ac.brighton.uni.ch629.ecengine.component;

import uk.ac.brighton.uni.ch629.ecengine.entity.Entity;
import uk.ac.brighton.uni.ch629.ecengine.types.Box2i;
import uk.ac.brighton.uni.ch629.ecengine.types.Vector2i;

import java.awt.*;

@ComponentDetails(dependencies = TransformComponent.class, type = ComponentDetails.ComponentType.RENDER)
public class BoxRenderComponent extends Component {
    private Vector2i offset = Vector2i.zero;
    private int width = 1, height = 1;

    public BoxRenderComponent(Entity parent) {
        super(parent);
    }

    public BoxRenderComponent(Entity parent, int width, int height) {
        this(parent);
        this.width = width;
        this.height = height;
    }

    public void update(double deltaTime) {

    }

    public Box2i getBox() {
        return new Box2i(parent.getTransform().getPos(), width, height);
    }

    public Rectangle getRectangle() {
        Vector2i pos = parent.getTransform().getPos();
        return new Rectangle(pos.x, pos.y, width, height);
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Vector2i getSize() {
        return new Vector2i(width, height);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void render(Graphics graphics) {
        Box2i box = getBox();
        graphics.fillRect(box.x + offset.x, box.y + offset.y, box.width, box.height);
    }
}