/*
ID: 212054480
ID: 322991563
 */

package Sprites;

import Game.Game;
import Geometry.Line;
import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Velocity;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.List;

/**
 * The Sprites.Paddle class represents the player-controlled paddle in the game.
 * The paddle is both a sprite and a collidable object, controlled by the keyboard.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Block block;
    private List<Ball> balls;
    private final double epsilon = 0.000001d;

    /**
     * Constructs a Sprites.Paddle with the specified keyboard sensor and block.
     *
     * @param keyboard the keyboard sensor to control the paddle
     * @param block    the block representing the paddle
     */
    public Paddle(biuoop.KeyboardSensor keyboard, Block block) {
        this.keyboard = keyboard;
        this.block = block;
    }

    /**
     * Moves the paddle to the left.
     * The paddle wraps around to the right edge if it moves past the left edge.
     */
    public void moveLeft() {
        double x = block.getCollisionRectangle().getUpperLeft().getX();
        if (Math.abs(x - 0) < epsilon || x < 0) {
            this.block.getCollisionRectangle().getUpperLeft().setX(800 - this.block.getCollisionRectangle().getWidth());
        } else if (Math.abs(x - 8) < epsilon) {
            this.block.getCollisionRectangle().getUpperLeft().setX(0);
        } else {
            this.block.getCollisionRectangle().getUpperLeft().setX(x - 8);
        }
    }

    /**
     * Moves the paddle to the right.
     * The paddle wraps around to the left edge if it moves past the right edge.
     */
    public void moveRight() {
        double x = this.block.getCollisionRectangle().getUpperLeft().getX();
        double width = this.block.getCollisionRectangle().getWidth();
        if (Math.abs(x - (800 - width)) < epsilon || x > 800 - width) {
            this.block.getCollisionRectangle().getUpperLeft().setX(0);
        } else if (Math.abs(x + width - 792) < epsilon) {
            this.block.getCollisionRectangle().getUpperLeft().setX(800 - width);
        } else {
            this.block.getCollisionRectangle().getUpperLeft().setX(x + 8);
        }
    }

    /**
     * Sets the array of balls associated with the paddle.
     *
     * @param balls the array of balls
     */
    public void setBalls(List<Ball> balls) {
        this.balls = balls;
    }

    /**
     * Checks if any ball is inside the paddle after a specified movement.
     *
     * @param movement the movement to check
     * @return true if any ball is inside the paddle, false otherwise
     */
    public boolean isInsidePaddle(int movement) {
        double paddleStartX = this.block.getCollisionRectangle().getUpperLeft().getX() + movement;
        double paddleStartY = this.block.getCollisionRectangle().getUpperLeft().getY();
        double paddleWidth = this.block.getCollisionRectangle().getWidth();
        for (Ball ball : this.balls) {
            Point ballAfterVelocity = new Point(ball.getX(), ball.getY());
            if ((ballAfterVelocity.getY() > paddleStartY
                    || Math.abs(ballAfterVelocity.getY() - paddleStartY) < epsilon)
                    && (ballAfterVelocity.getX() > paddleStartX
                    || Math.abs(ballAfterVelocity.getX() - paddleStartX) < epsilon)
                    && (ballAfterVelocity.getX() < paddleStartX + paddleWidth
                    || Math.abs(ballAfterVelocity.getX() - (paddleStartX + paddleWidth)) < epsilon)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Updates the paddle's position based on keyboard input.
     * Moves the paddle left or right if the corresponding key is pressed.
     */
    public void timePassed() {
        if (keyboard.isPressed(this.keyboard.LEFT_KEY)) {
            if (!isInsidePaddle(-8)) {
                moveLeft();
            }
        } else if (keyboard.isPressed(this.keyboard.RIGHT_KEY)) {
            if (!isInsidePaddle(8)) {
                moveRight();
            }
        }
    }

    /**
     * Draws the paddle on the given DrawSurface.
     *
     * @param d the DrawSurface to draw on
     */
    public void drawOn(DrawSurface d) {
        int x1 = (int) this.block.getCollisionRectangle().getUpperLeft().getX();
        int y1 = (int) this.block.getCollisionRectangle().getUpperLeft().getY();
        d.setColor(Color.BLACK);
        d.drawRectangle(x1, y1, (int) this.block.getCollisionRectangle().getWidth(),
                (int) this.block.getCollisionRectangle().getHeight());
        d.setColor(this.block.getColor());
        d.fillRectangle(x1, y1, (int) this.block.getCollisionRectangle().getWidth(),
                (int) this.block.getCollisionRectangle().getHeight());
    }

    /**
     * Gets the collision rectangle of the paddle.
     *
     * @return the collision rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.block.getCollisionRectangle();
    }

    /**
     * Adjusts the vertical velocity component to be negative if it's positive.
     *
     * @param velocity the current velocity
     * @return the adjusted velocity
     */
    public Velocity yFixer(Velocity velocity) {
        if (velocity.getDy() > 0) {
            velocity.setDy(-velocity.getDy());
        }
        return velocity;
    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter) {
        int region = numRegion(collisionPoint);
        double currentDx = currentVelocity.getDx();
        double currentDy = currentVelocity.getDy();
        double speed = Math.sqrt(currentDx * currentDx + currentDy * currentDy);
        switch (region) {
            case 1:
                return yFixer(Velocity.fromAngleAndSpeed(300, speed));
            case 2:
                return yFixer(Velocity.fromAngleAndSpeed(330, speed));
            case 3:
                return new Velocity(currentDx, -currentDy);
            case 4:
                return yFixer(Velocity.fromAngleAndSpeed(30, speed));
            case 5:
                return yFixer(Velocity.fromAngleAndSpeed(60, speed));
            default:
                double newDx = currentVelocity.getDx();
                Line[] vertLines = this.block.getCollisionRectangle().verticalLines();
                for (Line line : vertLines) {
                    if (line.isPointOnLine(collisionPoint)) {
                        newDx = -newDx;
                        break;
                    }
                }
                return new Velocity(newDx, currentDy);
        }
    }

    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Checks if the collision point is on the top edge of the paddle.
     *
     * @param collisionPoint the collision point
     * @return true if the collision point is on the top edge, false otherwise
     */
    public boolean isOnLine(Point collisionPoint) {
        double startX = this.block.getCollisionRectangle().getUpperLeft().getX();
        double endX = startX + this.block.getCollisionRectangle().getWidth();
        double y = this.block.getCollisionRectangle().getUpperLeft().getY();
        Line line = new Line(startX, y, endX, y);
        return line.isPointOnLine(collisionPoint);
    }

    /**
     * Determines which region of the paddle was hit by the ball.
     *
     * @param collisionPoint the collision point
     * @return the region number (1-5) if the collision point is on the top edge, 0 otherwise
     */
    public int numRegion(Point collisionPoint) {
        if (isOnLine(collisionPoint)) {
            double startX = this.block.getCollisionRectangle().getUpperLeft().getX();
            double endX = startX + this.block.getCollisionRectangle().getWidth();
            double region = (endX - startX) / 5;
            return (int) ((collisionPoint.getX() - startX) / region) + 1;
        }
        return 0;
    }
}