/*
ID: 212054480
ID: 322991563
 */

package Sprites;

import Game.Counter;
import Game.Game;
import Geometry.Line;
import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Velocity;
import Listeners.HitListener;
import Listeners.HitNotifier;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The Sprites.Block class represents a block in the game, which is both a collidable object and a sprite.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle collisionRectangle;
    private Color color;
    private List<HitListener> hitListeners;
    private Counter levelCounter;

    /**
     * Constructs a block with the specified collision rectangle, color, and level counter.
     *
     * @param collisionRectangle the collision rectangle of the block
     * @param color              the color of the block
     * @param levelCounter       the level counter associated with the block
     */
    public Block(Rectangle collisionRectangle, Color color, Counter levelCounter) {
        this.collisionRectangle = collisionRectangle;
        this.color = color;
        this.hitListeners = new ArrayList<>();
        this.levelCounter = levelCounter;
    }

    /**
     * Returns the level counter associated with the block.
     *
     * @return the level counter of the block
     */
    public Counter getLevelCounter() {
        return levelCounter;
    }


    @Override
    public void drawOn(DrawSurface d) {
        int x1 = (int) this.collisionRectangle.getUpperLeft().getX();
        int y1 = (int) this.collisionRectangle.getUpperLeft().getY();
        d.setColor(this.color);
        d.fillRectangle(x1, y1, (int) this.collisionRectangle.getWidth(), (int) this.collisionRectangle.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle(x1, y1, (int) this.collisionRectangle.getWidth(), (int) this.collisionRectangle.getHeight());
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(Game game) {
        game.addCollidable(this);
        game.addSprite(this);
    }

    /**
     * Gets the collision rectangle of the block.
     *
     * @return the collision rectangle of the block
     */
    public Rectangle getCollisionRectangle() {
        return this.collisionRectangle;
    }

    /**
     * Gets the color of the block.
     *
     * @return the color of the block
     */
    public Color getColor() {
        return this.color;
    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter) {
        double newDx = currentVelocity.getDx();
        double newDy = currentVelocity.getDy();
        Line[] horizLines = this.collisionRectangle.horizontalLines();
        Line[] vertLines = this.collisionRectangle.verticalLines();
        for (Line line : horizLines) {
            if (line.isPointOnLine(collisionPoint)) {
                newDy = -newDy;
                break;
            }
        }
        for (Line line : vertLines) {
            if (line.isPointOnLine(collisionPoint)) {
                newDx = -newDx;
                break;
            }
        }
        if (!ballColorMatch(hitter)) {
            this.notifyHit(hitter);
        }
        return new Velocity(newDx, newDy);
    }

    /**
     * Notifies all hit listeners that a hit event has occurred with a specific ball.
     *
     * @param hitter the ball that hit the block
     */
    private void notifyHit(Ball hitter) {
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Checks if the color of a given ball matches the color of this block.
     *
     * @param ball the ball to check against the block's color
     * @return true if the ball's color matches this block's color, false otherwise
     */
    public boolean ballColorMatch(Ball ball) {
        return ball.getColor() == this.color;
    }

    /**
     * Removes the block from the specified game by removing it as both a collidable and a sprite.
     *
     * @param game the game from which the block should be removed
     */
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

}
