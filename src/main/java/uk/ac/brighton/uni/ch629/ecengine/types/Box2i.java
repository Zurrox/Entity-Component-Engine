package uk.ac.brighton.uni.ch629.ecengine.types;

public class Box2i extends Vector2i {
    public int width, height; //TODO: Might have to redo the intersection code. Or just be limited with shapes.

    public Box2i(final int x, final int y, final int width, final int height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }

    public boolean intersects(final Vector2i other) {
        return (other.x >= this.x || other.x <= this.x + this.width) &&
                (other.y >= this.y || other.y <= this.y + this.height);
    }

    public boolean intersects(final Box2i other) {
        return (other.x + other.width >= this.x || other.x <= this.x + this.width) &&
                (other.y + other.height >= this.y || other.y <= this.y + this.height);
    }
}

//Collision Detection
//TODO: Try Bounding box to check for possible collisions then use more intricate checks for collisions.