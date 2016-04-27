package uk.ac.brighton.uni.ch629.ecengine.component;

import uk.ac.brighton.uni.ch629.ecengine.entity.Entity;
import uk.ac.brighton.uni.ch629.ecengine.types.Vector2d;

import java.awt.*;

public class TransformComponent extends Component { //TODO: Maybe make all entities have a TransformComponent, which can be used to render & deal with collisions
    private Vector2d pos;

    public TransformComponent(Entity parent) {
        this(parent, Vector2d.zero);
    }

    public TransformComponent(Entity parent, Vector2d pos) {
        super(parent);
        this.pos = pos;
    }

    public TransformComponent(Entity parent, double x, double y) {
        super(parent);
        pos = new Vector2d(x, y);
    }

    public void update(double deltaTime) {

    }

    public void render(Graphics2D graphics) {

    }

    public Vector2d getPos() {
        return pos;
    }

    public void setPos(Vector2d pos) {
        this.pos = pos;
        notifyObservers();
    }

    public void setPos(double x, double y) {
        setPos(new Vector2d(x, y));
    }

    public void move(Vector2d pos) {
        pos.move(pos);
    }

    public void move(double x, double y) {
        pos.move(x, y);
    }
}