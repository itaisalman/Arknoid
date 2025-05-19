/*
ID: 212054480
ID: 322991563
 */

package Game;

/**
 * The Counter class is a simple utility class that keeps track of a count value.
 * It allows incrementing and decrementing the count by a specified number.
 */
public class Counter {
    private int count;

    /**
     * Constructs a Counter with the specified initial count.
     *
     * @param count the initial count value
     */
    public Counter(int count) {
        this.count = count;
    }

    /**
     * Adds the specified number to the current count.
     *
     * @param number the number to add to the count
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * Subtracts the specified number from the current count.
     *
     * @param number the number to subtract from the count
     */
    public void decrease(int number) {
        this.count -= number;
    }

    /**
     * Returns the current count value.
     *
     * @return the current count value
     */
    public int getValue() {
        return this.count;
    }
}
