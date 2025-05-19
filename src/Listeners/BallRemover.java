/*
ID: 212054480
ID: 322991563
 */

package Listeners;

import Game.Game;
import Game.Counter;
import Sprites.Ball;
import Sprites.Block;

/**
 * A class that handles the removal of balls from the game when they hit blocks.
 * This class implements the HitListener interface.
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;

    /**
     * Constructs a BallRemover.
     *
     * @param game the game from which balls will be removed
     * @param remainingBalls a counter for the remaining balls in the game
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
        this.game.getBalls().remove(hitter);
    }
}
