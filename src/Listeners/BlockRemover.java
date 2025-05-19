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
 * A class that handles the removal of blocks from the game when they are hit.
 * This class implements the HitListener interface.
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * Constructs a BlockRemover.
     *
     * @param game the game from which blocks will be removed
     * @param remainingBlocks a counter for the remaining blocks in the game
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.game);
        this.remainingBlocks.decrease(1);
        hitter.setColor(beingHit.getColor());
    }
}
