package Geometry;
/*
ID: 212054480
ID: 322991563
 */

/**
 * The {@code Geometry.Point} class represents a point in a 2D space.
 * It includes methods for calculating the distance to another point,
 * checking for equality with another point, and accessing the x and y coordinates.
 */
public class Point {
    private double x;
    private double y;
    private final double epsilon = 0.000001d;

    /**
     * Constructs a {@code Geometry.Point} with the specified x and y coordinates.
     *
     * @param x the x coordinate of the point
     * @param y the y coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @param other the other point
     * @return the distance between this point and the other point
     */
    public double distance(Point other) {
        double distanceX = this.getX() - other.getX();
        double distanceY = this.getY() - other.getY();
        return Math.sqrt((distanceX * distanceX) + (distanceY * distanceY));
    }

    /**
     * Checks if this point is equal to another point.
     * Two points are considered equal if their x and y coordinates are both within
     * a small epsilon value of each other.
     *
     * @param other the other point to compare with
     * @return {@code true} if the points are equal, {@code false} otherwise
     */
    public boolean equals(Point other) {
        return (Math.abs(this.getX() - other.getX())) < epsilon && (Math.abs(this.getY() - other.getY())) < epsilon;
    }

    /**
     * @return the x coordinate of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return the y coordinate of this point
     */
    public double getY() {
        return this.y;
    }

    /**
     * Sets the x-coordinate of this point.
     *
     * @param x the new x-coordinate value
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Sets the y-coordinate of this point.
     *
     * @param y the new y-coordinate value
     */
    public void setY(double y) {
        this.y = y;
    }
}
