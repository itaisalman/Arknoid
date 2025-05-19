package Sprites;
/*
ID: 212054480
ID: 322991563
 */

import Iterators.ListIterator;
import biuoop.DrawSurface;

import java.util.List;

/**
 * The Sprites.SpriteCollection class represents a collection of Sprites.Sprite objects.
 * It provides methods to add sprites, notify them that time has passed,
 * and draw them on a given DrawSurface.
 */
public class SpriteCollection {
    private List<Sprite> collection;

    /**
     * Constructs a Sprites.SpriteCollection with the specified list of sprites.
     *
     * @param list the list of sprites
     */
    public SpriteCollection(List<Sprite> list) {
        this.collection = list;
    }

    /**
     * Returns the collection of sprites managed by this object.
     *
     * @return the list of sprites managed by this object
     */
    public List<Sprite> getCollection() {
        return collection;
    }

    /**
     * Adds a sprite to the collection.
     *
     * @param s the sprite to be added
     */
    public void addSprite(Sprite s) {
        collection.add(s);
    }

    /**
     * Calls the timePassed method on all sprites in the collection.
     */
    public void notifyAllTimePassed() {
        ListIterator<Sprite> listIterator = new ListIterator<>(this.collection);
        while (listIterator.hasNext()) {
            listIterator.next().timePassed();
        }
    }

    /**
     * Calls the drawOn method on all sprites in the collection,
     * drawing them on the given DrawSurface.
     *
     * @param d the DrawSurface to draw on
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : collection) {
            s.drawOn(d);
        }
    }
}
