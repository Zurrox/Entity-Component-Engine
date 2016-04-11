package uk.ac.brighton.uni.ch629.ecengine.physics;

import uk.ac.brighton.uni.ch629.ecengine.types.Box2i;
import uk.ac.brighton.uni.ch629.ecengine.types.Circle2i;

public class CollisionHandler
{
    public static boolean intersects(Box2i box1, Box2i box2)
    {
        return (box1.x + box1.width > box2.x || box1.x < box2.x + box2.width) &&
                (box1.y + box1.height > box2.y || box1.y < box2.y + box2.height);
    }

    public static boolean intersects(Circle2i circle1, Circle2i circle2)
    {
        return false;
    }

    public static boolean intersects(Box2i box, Circle2i circle)
    {
        return false;
    }
}
