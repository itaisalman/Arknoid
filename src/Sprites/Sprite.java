package Sprites;
/*
ID: 212054480
ID: 322991563
 */

import Game.Game;
import biuoop.DrawSurface;

/**
 * The Sprites.Sprite interface represents an object that can be drawn on a DrawSurface and updated over time.
 */
public interface Sprite {

    /**
     * Draws the sprite onto the specified DrawSurface.
     * @param d the DrawSurface onto which to draw the sprite
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite that a unit of time has passed.
     * This method is typically called once per frame or time step in the game loop.
     */
    void timePassed();

    /**
     * Adds the sprite to the specified game.
     * This method registers the sprite with the game, allowing it to participate in the game's logic.
     * @param game the game to which the sprite should be added
     */
    void addToGame(Game game);
}
