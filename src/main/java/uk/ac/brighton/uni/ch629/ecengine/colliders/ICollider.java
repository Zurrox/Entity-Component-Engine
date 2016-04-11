package uk.ac.brighton.uni.ch629.ecengine.colliders;

import uk.ac.brighton.uni.ch629.ecengine.types.Vector2i;

public interface ICollider
{
    public boolean isPointIn(Vector2i pos);
}