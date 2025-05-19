/*
ID: 212054480
ID: 322991563
 */

package Sprites;

import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Velocity;

/**
 * The {@code Sprites.Collidable} interface should be implemented by any class
 * whose instances are intended to represent objects that can be collided with.
 * It includes methods for retrieving the collision shape and handling the collision.
 */
public interface Collidable {

    /**
     * Returns the "collision shape" of the object.
     * This method should be implemented to provide the specific shape
     * of the collidable object, usually a rectangle.
     *
     * @return the collision shape of the object as a {@code Geometry.Rectangle}
     */
    Rectangle getCollisionRectangle();

    /**
     * Calculates and returns the new velocity of a ball after a hit, based on the collision point and current velocity.
     *
     * @param collisionPoint the point of collision between the ball and another object
     * @param currentVelocity the current velocity of the ball before the hit
     * @param hitter the ball that hits another object
     * @return the new velocity of the ball after the hit
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter);
}
