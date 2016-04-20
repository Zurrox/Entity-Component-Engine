package uk.ac.brighton.uni.ch629.ecengine.colliders;

public interface ICollider {
    /**
     * Whether the current collider intersects with another collider
     *
     * @param other Collider to check intersection
     * @return Whether the two collider's intersect
     */
    boolean intersects(ICollider other);
}