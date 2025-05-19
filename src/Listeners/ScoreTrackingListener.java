/*
ID: 212054480
ID: 322991563
 */

package Listeners;

import Game.Counter;
import Sprites.Ball;
import Sprites.Block;

/**
 * A class that tracks the score in the game.
 * This class implements the HitListener interface.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructs a ScoreTrackingListener.
     *
     * @param scoreCounter the counter for the current score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
        beingHit.getLevelCounter().decrease(1);
        if (beingHit.getLevelCounter().getValue() == 0) {
            this.currentScore.increase(100);
        }
    }
}
