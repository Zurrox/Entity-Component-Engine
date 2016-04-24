package uk.ac.brighton.uni.ch629.ecengine.component;

import uk.ac.brighton.uni.ch629.ecengine.entity.Entity;
import uk.ac.brighton.uni.ch629.ecengine.types.Vector2i;

import java.awt.*;

public class TransformComponent extends Component { //TODO: Maybe make all entities have a TransformComponent, which can be used to render & deal with collisions
    private Vector2i pos;

    public TransformComponent(Entity parent) {
        this(parent, Vector2i.zero);
    }

    public TransformComponent(Entity parent, Vector2i pos) {
        super(parent);
        this.pos = pos;
    }

    public TransformComponent(Entity parent, int x, int y) {
        super(parent);
        pos = new Vector2i(x, y);
    }

    public void update(double deltaTime) {

    }

    public void render(Graphics graphics) {

    }

    public Vector2i getPos() {
        return pos;
    }

    public void setPos(Vector2i pos) {
        this.pos = pos;
        notifyObservers();
    }

    public void setPos(int x, int y) {
        setPos(new Vector2i(x, y));
    }

    public void move(Vector2i pos) {
        pos.move(pos);
    }

    public void move(int x, int y) {
        pos.move(x, y);
    }
}