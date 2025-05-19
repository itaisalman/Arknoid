package Geometry;
/*
ID: 212054480
ID: 322991563
 */

/**
 * The {@code Geometry.Velocity} class represents the velocity of an object in a 2D space.
 * It includes methods for creating a velocity instance using its components or
 * from an angle and speed, and for applying this velocity to a given point.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructs a {@code Geometry.Velocity} with the specified change in x and y.
     *
     * @param dx the change in the x direction
     * @param dy the change in the y direction
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Creates a {@code Geometry.Velocity} instance from the specified angle and speed.
     *
     * @param angle the direction of the velocity in degrees
     * @param speed the magnitude of the velocity
     * @return a new {@code Geometry.Velocity} instance with the specified angle and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radians = Math.toRadians(angle);
        double dx = speed * Math.sin(radians);
        double dy = speed * Math.cos(radians);
        return new Velocity(dx, dy);
    }

    /**
     * Returns the change in the x direction.
     *
     * @return the change in the x direction
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Returns the change in the y direction.
     *
     * @return the change in the y direction
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Sets the value of dy (change in the y-direction).
     *
     * @param dy the new value for dy
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * Applies this velocity to the given point, returning a new point with the
     * updated position.
     *
     * @param p the point to which the velocity will be applied
     * @return a new {@code Geometry.Point} with the updated position
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }
}