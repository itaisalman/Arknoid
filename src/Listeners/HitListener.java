/*
ID: 212054480
ID: 322991563
 */

package Listeners;

import Sprites.Ball;
import Sprites.Block;

/**
 * An interface that should be implemented by any class that wants to be notified when a block is hit.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit the block that is hit
     * @param hitter the Ball that's doing the hitting
     */
    void hitEvent(Block beingHit, Ball hitter);
}
