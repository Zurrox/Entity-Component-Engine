package uk.ac.brighton.uni.ch629.ecengine.component;

import uk.ac.brighton.uni.ch629.ecengine.entity.Entity;
import uk.ac.brighton.uni.ch629.ecengine.types.Vector2d;

import java.awt.*;

@ComponentDetails(dependencies = TransformComponent.class)
public class VelocityComponent extends Component {
    Vector2d vel = Vector2d.zero;
    int divideAmount = 0; //Slows down the movement

    public VelocityComponent(Entity parent) {
        super(parent);
    }

    public void update(double deltaTime) {
        parent.getTransform().move((int) Math.round(vel.x * deltaTime), (int) Math.round(vel.y * deltaTime));
        wait(divideAmount);
    }

    public void setVelocity(double x, double y) {
        setVelocity(new Vector2d(x, y));
    }

    public void setDivideAmount(int amount) {
        divideAmount = amount;
    }

    public void render(Graphics2D graphics) {

    }

    public void invert() {
        vel = new Vector2d(vel.x * (-1), vel.y * (-1));
    }

    public void invertX() {
        vel = new Vector2d(-1 * vel.x, vel.y);
    }

    public void invertY() {
        vel = new Vector2d(vel.x, -1 * vel.y);
    }

    public Vector2d getVelocity() {
        return vel;
    }

    public void setVelocity(Vector2d vel) {
        this.vel = vel;
    }
}