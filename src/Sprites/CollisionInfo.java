/*
ID: 212054480
ID: 322991563
 */

package Sprites;

import Geometry.Point;

/**
 * The {@code Sprites.CollisionInfo} class represents information about a collision.
 * It includes the point at which the collision occurs and the collidable object involved in the collision.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Constructs a {@code Sprites.CollisionInfo} object with the specified collision point and collidable object.
     *
     * @param collisionPoint the point at which the collision occurs
     * @param collisionObject the collidable object involved in the collision
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * Returns the point at which the collision occurs.
     *
     * @return the collision point as a {@code Geometry.Point}
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Returns the collidable object involved in the collision.
     *
     * @return the collidable object as a {@code Sprites.Collidable}
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }

}
