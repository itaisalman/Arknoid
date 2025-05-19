package Game;
/*
ID: 212054480
ID: 322991563
 */

import Geometry.Line;
import Geometry.Point;
import Sprites.Collidable;
import Sprites.CollisionInfo;
import java.util.List;

/**
 * The Game.Game.GameEnvironment class represents the game environment that includes all collidable objects.
 * It provides methods to add collidables and to find the closest collision point with an object
 * moving along a trajectory.
 */
public class GameEnvironment {
    private List<Collidable> collection;

    /**
     * Constructs a Game.Game.GameEnvironment with the specified list of collidables.
     *
     * @param c the list of collidables
     */
    public GameEnvironment(List<Collidable> c) {
        this.collection = c;
    }


    /**
     * Returns the list of collidable objects in the game environment.
     *
     * @return a List of Collidable objects representing the collidable objects in the game environment
     */
    public List<Collidable> getCollection() {
        return collection;
    }

    /**
     * Adds the given collidable to the environment.
     *
     * @param c the collidable to be added
     */
    public void addCollidable(Collidable c) {
        collection.add(c);
    }

    /**
     * Determines the closest collision point for an object moving along a trajectory.
     * If there is no collision, returns null. Otherwise, returns information about the closest collision.
     *
     * @param trajectory the trajectory of the moving object
     * @return the Sprites.CollisionInfo object containing details about the closest collision,
     * or null if there is no collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point closestPoint = null;
        Collidable closestObject = null;
        for (Collidable c : collection) {
            Point collisionPoint = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (collisionPoint != null) {
                if (closestPoint == null) {
                    closestPoint = collisionPoint;
                    closestObject = c;
                } else {
                    if (collisionPoint.distance(trajectory.start()) < closestPoint.distance(trajectory.start())) {
                        closestPoint = collisionPoint;
                        closestObject = c;
                    }
                }
            }
        }
        if (closestPoint == null) {
            return null;
        } else {
            return new CollisionInfo(closestPoint, closestObject);
        }
    }
}
