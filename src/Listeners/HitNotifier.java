/*
ID: 212054480
ID: 322991563
 */

package Listeners;

/**
 * An interface that should be implemented by any class that can register and notify HitListeners.
 */
public interface HitNotifier {
    /**
     * Adds a HitListener to the list of listeners for hit events.
     *
     * @param hl the HitListener to add
     */
    void addHitListener(HitListener hl);

    /**
     * Removes a HitListener from the list of listeners for hit events.
     *
     * @param hl the HitListener to remove
     */
    void removeHitListener(HitListener hl);
}
