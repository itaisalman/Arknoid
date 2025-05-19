/*
ID: 212054480
ID: 322991563
 */

package Sprites;

import Game.Game;
import Geometry.Line;
import Geometry.Point;
import Geometry.Velocity;
import biuoop.DrawSurface;
import Game.GameEnvironment;

import java.awt.Color;

/**
 * The Sprites.Ball class represents a ball in the game.
 * It implements the Sprites.Sprite interface and is responsible for drawing itself
 * and updating its position based on its velocity.
 */
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;

    /**
     * Constructs a Sprites.Ball instance with a specified center, radius, and color.
     * The initial velocity is set to zero.
     *
     * @param center the center point of the ball
     * @param r      the radius of the ball
     * @param color  the color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
    }

    /**
     * Constructs a Sprites.Ball instance with specified x and y coordinates, radius, and color.
     * The initial velocity is set to zero.
     *
     * @param x     the x-coordinate of the ball's center
     * @param y     the y-coordinate of the ball's center
     * @param r     the radius of the ball
     * @param color the color of the ball
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
    }

    /**
     * Sets the color of the sprite.
     *
     * @param color the new color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Gets the current color of the sprite.
     *
     * @return the current color of the sprite
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * @return the x-coordinate
     */
    public int getX() {
        return (int) Math.round(this.center.getX());
    }

    /**
     * @return the y-coordinate
     */
    public int getY() {
        return (int) Math.round(this.center.getY());
    }

    /**
     * @return the radius
     */
    public int getSize() {
        return this.radius;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawCircle(getX(), getY(), getSize());
        d.setColor(this.color);
        d.fillCircle(getX(), getY(), getSize());
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    @Override
    public void addToGame(Game game) {
        game.addSprite(this);
    }

    /**
     * Sets the velocity of the ball using dx and dy values.
     *
     * @param dx the change in x-direction
     * @param dy the change in y-direction
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Sets the game environment for the ball.
     *
     * @param ge the game environment to be set
     */
    public void setGameEnvironment(GameEnvironment ge) {
        this.gameEnvironment = ge;
    }

    /**
     * Removes the sprite from the specified game.
     *
     * @param game the game from which the sprite should be removed
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
    }

    /**
     * Handles a horizontal collision by adjusting the ball's Y-coordinate.
     * If the ball is moving downwards, it sets the Y-coordinate to just above the collision point.
     * If the ball is moving upwards, it sets the Y-coordinate to just below the collision point.
     *
     * @param ci the Sprites.CollisionInfo object containing details about the collision
     */
    public void horizCollision(CollisionInfo ci) {
        if (this.velocity.getDy() > 0) {
            this.center.setY(ci.collisionPoint().getY() - 1);
        } else {
            this.center.setY(ci.collisionPoint().getY() + 1);
        }
    }

    /**
     * Handles a vertical collision by adjusting the ball's X-coordinate.
     * If the ball is moving to the right, it sets the X-coordinate to just left of the collision point.
     * If the ball is moving to the left, it sets the X-coordinate to just right of the collision point.
     *
     * @param ci the Sprites.CollisionInfo object containing details about the collision
     */
    public void vertCollision(CollisionInfo ci) {
        if (this.velocity.getDx() > 0) {
            this.center.setX(ci.collisionPoint().getX() - 1);
        } else {
            this.center.setX(ci.collisionPoint().getX() + 1);
        }
    }

    /**
     * Moves the ball one step according to its velocity.
     * If a collision with a collidable object is detected,
     * it adjusts the velocity accordingly.
     */
    public void moveOneStep() {
        Point end = new Point(this.center.getX() + this.velocity.getDx(),
                this.center.getY() + this.velocity.getDy());
        Line line = new Line(this.center, end);
        CollisionInfo ci = gameEnvironment.getClosestCollision(line);
        if (ci == null) {
            this.center = this.velocity.applyToPoint(this.center);
        } else {
            Point collisionPoint1 = ci.collisionPoint();
            Line[] horizLines = ci.collisionObject().getCollisionRectangle().horizontalLines();
            Line[] vertLines = ci.collisionObject().getCollisionRectangle().verticalLines();
            for (Line horizLine : horizLines) {
                if (horizLine.isIntersecting(new Line(this.center, ci.collisionPoint()))) {
                    horizCollision(ci);
                }
            }
            for (Line vertLine : vertLines) {
                if (vertLine.isIntersecting(new Line(this.center, ci.collisionPoint()))) {
                    vertCollision(ci);
                }
            }
            this.velocity = ci.collisionObject().hit(collisionPoint1, this.velocity, this);
        }
    }

}