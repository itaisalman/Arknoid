/*
ID: 212054480
ID: 322991563
 */

package Sprites;

import Game.Game;
import Geometry.Rectangle;
import biuoop.DrawSurface;
import Game.Counter;
import java.awt.Color;

/**
 * Represents a score indicator that displays the current score on a rectangle.
 */
public class ScoreIndicator implements Sprite {
    private Rectangle rect;
    private Counter score;
    private Color color;

    /**
     * Constructs a score indicator with the specified rectangle and score counter.
     *
     * @param rect the rectangle representing the score indicator's position and size
     * @param score the counter for keeping track of the score to display
     */
    public ScoreIndicator(Rectangle rect, Counter score) {
        this.rect = rect;
        this.score = score;
        this.color = Color.WHITE;
    }

    @Override
    public void drawOn(DrawSurface d) {
        int x1 = (int) this.rect.getUpperLeft().getX();
        int y1 = (int) this.rect.getUpperLeft().getY();
        d.setColor(this.color);
        d.fillRectangle(x1, y1, (int) this.rect.getWidth(), (int) this.rect.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle(x1, y1, (int) this.rect.getWidth(), (int) this.rect.getHeight());
        d.drawText(390, 15, "score: " + this.score.getValue(), 15);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(Game game) {
        game.addSprite(this);
    }
}


