/*
ID: 212054480
ID: 322991563
 */

package Iterators;

import java.util.Iterator;
import java.util.List;

/**
 * A generic list iterator that implements the Iterator interface to iterate over a list of elements.
 *
 * @param <T> the type of elements in the list
 */
public class ListIterator<T> implements Iterator<T> {
    private List<T> list;
    private int currentIndex;

    /**
     * Constructs a ListIterator for the specified list.
     *
     * @param list the list to be iterated over
     */
    public ListIterator(List<T> list) {
        this.list = list;
        this.currentIndex = 0;
    }

    /**
     * Returns {@code true} if the iteration has more elements.
     *
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext() {
        return this.currentIndex < this.list.size();
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws java.util.NoSuchElementException if the iteration has no more elements
     */
    @Override
    public T next() {
        T result = this.list.get(this.currentIndex);
        this.currentIndex++;
        return result;
    }
}
