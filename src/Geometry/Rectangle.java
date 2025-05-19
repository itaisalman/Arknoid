package Geometry;
/*
ID: 212054480
ID: 322991563
 */

import java.util.ArrayList;

/**
 * The {@code Geometry.Rectangle} class represents a rectangle in 2D space.
 * It includes methods for calculating intersection points with a line,
 * dividing the rectangle into its boundary lines, and retrieving various
 * properties of the rectangle such as width, height, and upper-left corner.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Creates a new rectangle with the specified location and width/height.
     *
     * @param upperLeft the upper-left point of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Returns a (possibly empty) list of intersection points with the specified line.
     *
     * @param line the line to check for intersections with
     * @return a list of intersection points with the specified line
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> intersectionPoints = new ArrayList<>();
        Line[] rectLines = divideRectToLines();
        for (Line rectLine : rectLines) {
            if (rectLine.intersectionWith(line) != null) {
                intersectionPoints.add(rectLine.intersectionWith(line));
            }
        }
        return intersectionPoints;
    }

    /**
     * Divides the rectangle into its four boundary lines.
     *
     * @return an array of four lines representing the boundaries of the rectangle
     */
    public Line[] divideRectToLines() {
        Line[] rectLines = new Line[4];
        rectLines[0] = new Line(upperLeft.getX(), upperLeft.getY(),
                upperLeft.getX(), upperLeft.getY() + this.height);
        rectLines[1] = new Line(upperLeft.getX() + this.width, upperLeft.getY(),
                upperLeft.getX() + this.width, upperLeft.getY() + this.height);
        rectLines[2] = new Line(upperLeft.getX(), upperLeft.getY() + this.height,
                upperLeft.getX() + this.width, upperLeft.getY() + this.height);
        rectLines[3] = new Line(upperLeft.getX(), upperLeft.getY(),
                upperLeft.getX() + this.width, upperLeft.getY());
        return rectLines;
    }

    /**
     * Returns the horizontal lines of the rectangle.
     *
     * @return an array of two lines representing the horizontal boundaries of the rectangle
     */
    public Line[] horizontalLines() {
        Line[] horizontalLines = new Line[2];
        horizontalLines[0] = new Line(upperLeft.getX(), upperLeft.getY() + this.height,
                upperLeft.getX() + this.width, upperLeft.getY() + this.height);
        horizontalLines[1] = new Line(upperLeft.getX(), upperLeft.getY(),
                upperLeft.getX() + this.width, upperLeft.getY());
        return horizontalLines;
    }

    /**
     * Returns the vertical lines of the rectangle.
     *
     * @return an array of two lines representing the vertical boundaries of the rectangle
     */
    public Line[] verticalLines() {
        Line[] verticalLines = new Line[2];
        verticalLines[0] = new Line(upperLeft.getX(), upperLeft.getY(),
                upperLeft.getX(), upperLeft.getY() + this.height);
        verticalLines[1] = new Line(upperLeft.getX() + this.width, upperLeft.getY(),
                upperLeft.getX() + this.width, upperLeft.getY() + this.height);
        return verticalLines;
    }

    /**
     * Returns the width of the rectangle.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Returns the height of the rectangle.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     *
     * @return the upper-left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
}