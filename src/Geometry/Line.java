package Geometry;
/*
ID: 212054480
ID: 322991563
 */

import java.util.List;

/**
 * The {@code Geometry.Line} class represents a line segment in 2D space.
 * It includes methods for calculating the length of the line,
 * finding the middle point, checking for intersections with other lines,
 * and determining if a point lies on the line.
 */
public class Line {
    private Point start;
    private Point end;
    private final double epsilon = 0.000001d;

    /**
     * Constructs a {@code Geometry.Line} with the specified start and end points.
     *
     * @param start the start point of the line
     * @param end   the end point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs a {@code Geometry.Line} with the specified coordinates for the start and end points.
     *
     * @param x1 the x coordinate of the start point
     * @param y1 the y coordinate of the start point
     * @param x2 the x coordinate of the end point
     * @param y2 the y coordinate of the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * Checks if a given point lies on this line.
     *
     * @param p the point to check
     * @return {@code true} if the point lies on the line, {@code false} otherwise
     */
    public boolean isPointOnLine(Point p) {
        if (this.isVertOrHorizon() != -1) {
            if (this.isVertOrHorizon() == 1) {
                if (Math.abs(this.start.getX() - p.getX()) < epsilon) {
                    double minY = Math.min(this.start.getY(), this.end.getY());
                    double maxY = Math.max(this.start.getY(), this.end.getY());
                    return (p.getY() > minY) || (Math.abs(p.getY() - minY) < epsilon)
                            && ((p.getY() < maxY) || (Math.abs(maxY - p.getY()) < epsilon));
                }
            }
            if (this.isVertOrHorizon() == 0) {
                if (Math.abs(this.start.getY() - p.getY()) < epsilon) {
                    double minX = Math.min(this.start.getX(), this.end.getX());
                    double maxX = Math.max(this.start.getX(), this.end.getX());
                    return ((p.getX() > minX) || (Math.abs(p.getX() - maxX) < epsilon))
                            && (p.getX() < maxX) || (Math.abs(maxX - p.getX()) < epsilon);
                }
            }
        }
        double m2 = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
        double b2 = this.start.getY() - m2 * this.start.getX();
        if (Math.abs(p.getY() - ((m2 * p.getX()) + b2)) < epsilon) {
            return (Math.abs(p.getY() - Math.min(this.start.getY(), this.end.getY())) < epsilon
                    || p.getY() > Math.min(this.start.getY(), this.end.getY()))
                    && (Math.abs(p.getY() - Math.max(this.start.getY(), this.end.getY())) < epsilon
                    || p.getY() < Math.max(this.start.getY(), this.end.getY()));
        }
        return false;
    }

    /**
     * Checks if this line is parallel to either the X or Y axis.
     *
     * @return {@code 1} if parallel to Y axis, {@code 0} if parallel to X axis, {@code -1} if not parallel
     */
    private int isVertOrHorizon() {
        if (Math.abs(this.start.getX() - this.end.getX()) < epsilon) {
            return 1;
        }
        if (Math.abs(this.start.getY() - this.end.getY()) < epsilon) {
            return 0;
        }
        return -1;
    }

    /**
     * Checks if there is a mutual edge between this line and another line.
     *
     * @param other the other line to check for mutual edge
     * @return {@code true} if there is a mutual edge, {@code false} otherwise
     */
    private boolean isThereMutualEdge(Line other) {
        return this.start.equals(other.start) || this.end.equals(other.end)
                || this.start.equals(other.end) || this.end.equals(other.start);
    }

    /**
     * Two lines are considered equal if their start and end points are the same,
     * regardless of order.
     *
     * @param other the other line to compare with
     * @return {@code true} if the lines are equal, {@code false} otherwise
     */
    public boolean equals(Line other) {
        return (this.start.equals(other.start) && this.end.equals(other.end))
                || (this.start.equals(other.end) && this.end.equals(other.start));
    }

    /**
     * Determines the type of the line.
     * <ul>
     * <li>1: Vertical line</li>
     * <li>2: Horizontal line</li>
     * <li>3: Geometry.Line is a single point</li>
     * <li>4: Regular line</li>
     * </ul>
     *
     * @return an integer representing the type of the line
     */
    private int lineType() {
        if (Math.abs(this.start.getX() - this.end.getX()) < epsilon) {
            if (Math.abs(this.start.getY() - this.end.getY()) < epsilon) {
                return 3;
            }
            return 1;
        }
        if (Math.abs(this.start.getY() - this.end.getY()) < epsilon) {
            if (Math.abs(this.start.getX() - this.end.getX()) < epsilon) {
                return 3;
            }
            return 2;
        }
        if (this.start.equals(this.end)) {
            return 3;
        }
        return 4;
    }

    /**
     * Checks if two vertical lines overlap.
     *
     * @param other the other line to compare with
     * @return {@code true} if the two vertical lines overlap, {@code false} otherwise
     */
    private boolean twoVerticals(Line other) {
        if (Math.abs(this.start.getX() - other.start.getX()) < epsilon) {
            double thisMinY = Math.min(this.start.getY(), this.end.getY());
            double otherMinY = Math.min(other.start.getY(), other.end.getY());
            double otherMaxY = Math.max(other.start.getY(), other.end.getY());
            if ((thisMinY > otherMinY || Math.abs(thisMinY - otherMinY) < epsilon)
                    && (thisMinY < otherMaxY || Math.abs(thisMinY - otherMaxY) < epsilon)) {
                return true;
            }
            double thisMaxY = Math.max(this.start.getY(), this.end.getY());
            return (otherMinY > thisMinY || Math.abs(otherMinY - thisMinY) < epsilon)
                    && (otherMinY < thisMaxY || Math.abs(otherMinY - thisMaxY) < epsilon);
        }
        return false;
    }

    /**
     * Finds the intersection point of two vertical or horizontal lines if they overlap.
     *
     * @param other the other vertical or horizontal line to check for intersection
     * @return the intersection point if the lines overlap, {@code null} otherwise
     */
    private Point twoVerticalsOrHorizontalsPoint(Line other) {
        if (this.isThereMutualEdge(other)) {
            if (this.start.equals(other.start) || this.start.equals(other.end)) {
                return this.start;
            }
            return this.end;
        }
        return null;
    }

    /**
     * Checks if two horizontal lines overlap.
     *
     * @param other the other line to compare with
     * @return {@code true} if the two horizontal lines overlap, {@code false} otherwise
     */
    private boolean twoHorizontals(Line other) {
        if (Math.abs(this.start.getY() - other.start.getY()) < epsilon) {
            double thisMinX = Math.min(this.start.getX(), this.end.getX());
            double otherMinX = Math.min(other.start.getX(), other.end.getX());
            double otherMaxX = Math.max(other.start.getX(), other.end.getX());
            if ((thisMinX > otherMinX || Math.abs(thisMinX - otherMinX) < epsilon)
                    && (thisMinX < otherMaxX || Math.abs(thisMinX - otherMaxX) < epsilon)) {
                return true;
            }
            double thisMaxX = Math.max(this.start.getX(), this.end.getX());

            return (otherMinX > thisMinX || Math.abs(otherMinX - thisMinX) < epsilon)
                    && (otherMinX < thisMaxX || Math.abs(otherMinX - thisMaxX) < epsilon);
        }
        return false;
    }

    /**
     * Checks if a vertical line intersects with a horizontal line.
     *
     * @param other the other line to compare with
     * @return {@code true} if the vertical line intersects with the horizontal line, {@code false} otherwise
     */
    private boolean vertAndHorizon(Line other) {
        if (Math.max(this.start.getY(), this.end.getY()) < other.start.getY()
                || Math.min(this.start.getY(), this.end.getY()) > other.start.getY()) {
            return false;
        }
        return !(Math.max(other.start.getX(), other.end.getX()) < this.start.getX())
                && !(Math.min(other.start.getX(), other.end.getX()) > this.start.getX());
    }

    /**
     * Finds the intersection point of a vertical line and a horizontal line.
     *
     * @param other the other horizontal line to check for intersection
     * @return the intersection point if the lines intersect, {@code null} otherwise
     */
    private Point vertAndHorizonPoint(Line other) {
        Point point = new Point(this.start.getX(), this.start.getY());
        if (this.isPointOnLine(point) && other.isPointOnLine(point)) {
            return point;
        }
        return null;
    }

    /**
     * Checks if a vertical line intersects with a dot.
     *
     * @param other the other line to compare with
     * @return {@code true} if the vertical line intersects with the dot, {@code false} otherwise
     */
    private boolean verAndDot(Line other) {
        if (Math.abs(other.start.getX() - this.end.getX()) < epsilon) {
            double thisMinY = Math.min(this.start.getY(), this.end.getY());
            double thisMaxY = Math.max(this.start.getY(), this.end.getY());
            double otherStartY = other.start.getY();
            return (thisMinY < otherStartY || Math.abs(thisMinY - otherStartY) < epsilon)
                    && (thisMaxY > otherStartY || Math.abs(thisMaxY - otherStartY) < epsilon);
        }
        return false;
    }

    /**
     * Checks if a vertical line intersects with a regular line.
     *
     * @param other the other line to compare with
     * @return {@code true} if the vertical line intersects with the regular line, {@code false} otherwise
     */
    private boolean verAndRegular(Line other) {
        if (this.isThereMutualEdge(other)) {
            return true;
        }
        double otherM = (other.start.getY() - other.end.getY()) / (other.start.getX() - other.end.getX());
        double otherB = other.start.getY() - (other.start.getX() * otherM);
        double intersectionY = otherM * this.start.getX() + otherB;
        if (Math.abs(Math.round(intersectionY) - intersectionY) < epsilon
                || Math.abs(intersectionY - Math.round(intersectionY)) < epsilon) {
            intersectionY = Math.round(intersectionY);
        }
        if (intersectionY > Math.max(this.start.getY(), this.end.getY())
                || intersectionY < Math.min(this.start.getY(), this.end.getY())) {
            return false;
        }
        return other.isPointOnLine(new Point(this.start.getX(), intersectionY));
    }

    /**
     * Finds the intersection point of a vertical line and a regular line.
     *
     * @param other the other regular line to check for intersection
     * @return the intersection point if the lines intersect
     */
    private Point vertAndRegularPoint(Line other) {
        double otherM = (other.start.getY() - other.end.getY()) / (other.start.getX() - other.end.getX());
        double otherB = other.start.getY() - (other.start.getX() * otherM);
        double intersectionY = otherM * this.start.getX() + otherB;
        return new Point(this.start.getX(), intersectionY);
    }

    /**
     * Checks if a horizontal line intersects with a dot.
     *
     * @param other the other line to compare with
     * @return {@code true} if the horizontal line intersects with the dot, {@code false} otherwise
     */
    private boolean horizonAndDot(Line other) {
        if (Math.abs(other.start.getY() - this.end.getY()) < epsilon) {
            double thisMinX = Math.min(this.start.getX(), this.end.getX());
            double thisMaxX = Math.max(this.start.getX(), this.end.getX());
            double otherStartX = other.start.getX();
            return (thisMinX < otherStartX || Math.abs(otherStartX - thisMinX) < epsilon)
                    && (thisMaxX > otherStartX || Math.abs(thisMaxX - otherStartX) < epsilon);
        }
        return false;
    }

    /**
     * Checks if a horizontal line intersects with a regular line.
     *
     * @param other the other line to compare with
     * @return {@code true} if the horizontal line intersects with the regular line, {@code false} otherwise
     */
    private boolean horizonAndRegular(Line other) {
        if (this.isThereMutualEdge(other)) {
            return true;
        }
        double otherM = (other.start.getY() - other.end.getY()) / (other.start.getX() - other.end.getX());
        double otherB = other.start.getY() - (other.start.getX() * otherM);
        double intersectionX = (this.start.getY() - otherB) / otherM;
        if ((Math.abs(intersectionX - Math.min(this.start.getX(), this.end.getX())) < epsilon
                || intersectionX > Math.min(this.start.getX(), this.end.getX()))
                && (Math.abs(Math.max(this.start.getX(), this.end.getX()) - intersectionX) < epsilon
                || Math.max(this.start.getX(), this.end.getX()) > intersectionX)) {
            return other.isPointOnLine(new Point(intersectionX, this.start.getY()));
        }
        return false;
    }

    /**
     * Finds the intersection point of a horizontal line and a regular line.
     *
     * @param other the other regular line to check for intersection
     * @return the intersection point if the lines intersect
     */
    private Point horizonAndRegularPoint(Line other) {
        double otherM = (other.start.getY() - other.end.getY()) / (other.start.getX() - other.end.getX());
        double otherB = other.start.getY() - (other.start.getX() * otherM);
        double intersectionX = (this.start.getY() - otherB) / otherM;
        return new Point(intersectionX, this.start.getY());
    }

    /**
     * Checks if two regular (non-vertical, non-horizontal) lines intersect.
     *
     * @param other the other line to compare with
     * @return {@code true} if the two regular lines intersect, {@code false} otherwise
     */
    private boolean twoRegulars(Line other) {
        double thisM = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
        double thisB = this.start.getY() - ((this.start.getX()) * thisM);
        double otherM = (other.start.getY() - other.end.getY()) / (other.start.getX() - other.end.getX());
        double otherB = other.start.getY() - ((other.start.getX()) * otherM);
        if (Math.abs(thisM - otherM) < epsilon) {
            if (Math.abs(thisB - otherB) < epsilon) {
                return !(Math.min(this.start.getX(), this.end.getX()) > Math.max(other.start.getX(), other.end.getX()))
                        && !(Math.max(this.start.getX(), this.end.getX())
                        < Math.min(other.start.getX(), other.end.getX()));
            }
            return false;
        }
        double intersectionX = (otherB - thisB) / (thisM - otherM);
        double intersectionY = thisM * intersectionX + thisB;
        return this.isPointOnLine(new Point(intersectionX, intersectionY))
                && other.isPointOnLine(new Point(intersectionX, intersectionY));
    }

    /**
     * Finds the intersection point of two regular (non-vertical, non-horizontal) lines.
     *
     * @param other the other regular line to check for intersection
     * @return the intersection point if the lines intersect, or the mutual edge point if they overlap,
     * {@code null} otherwise
     */
    private Point twoRegularsPoint(Line other) {
        double thisM = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
        double thisB = this.start.getY() - ((this.start.getX()) * thisM);
        double otherM = (other.start.getY() - other.end.getY()) / (other.start.getX() - other.end.getX());
        double otherB = other.start.getY() - ((other.start.getX()) * otherM);
        if (Math.abs(thisM - otherM) < epsilon) {
            if (Math.abs(thisB - otherB) < epsilon) {
                if (this.isThereMutualEdge(other)) {
                    if (this.start.equals(other.start) || this.start.equals(other.end)) {
                        return this.start;
                    }
                    return this.end;
                }
                return null;
            }
            return null;
        }
        double intersectionX = (otherB - thisB) / (thisM - otherM);
        double intersectionY = thisM * intersectionX + thisB;
        return new Point(intersectionX, intersectionY);
    }

    /**
     * Checks if this line intersects with another line.
     *
     * @param other the other line to check for intersection
     * @return {@code true} if the lines intersect, {@code false} otherwise
     */
    public boolean isIntersecting(Line other) {
        if (this.equals(other)) {
            return true;
        }
        int thisLineType = this.lineType();
        int otherLineType = other.lineType();
        if (thisLineType == 1 && otherLineType == 1) {
            return this.twoVerticals(other);
        }
        if ((thisLineType == 1 && otherLineType == 2) || (thisLineType == 2 && otherLineType == 1)) {
            if (thisLineType == 1) {
                return this.vertAndHorizon(other);
            }
            return other.vertAndHorizon(this);
        }
        if ((thisLineType == 1 && otherLineType == 3) || (thisLineType == 3 && otherLineType == 1)) {
            if (thisLineType == 1) {
                return this.verAndDot(other);
            }
            return other.verAndDot(this);
        }
        if ((thisLineType == 1 && otherLineType == 4) || (thisLineType == 4 && otherLineType == 1)) {
            if (thisLineType == 1) {
                return this.verAndRegular(other);
            }
            return other.verAndRegular(this);
        }
        if (thisLineType == 2 && otherLineType == 2) {
            return this.twoHorizontals(other);
        }
        if ((thisLineType == 2 && otherLineType == 3) || (thisLineType == 3 && otherLineType == 2)) {
            if (thisLineType == 2) {
                return this.horizonAndDot(other);
            }
            return other.horizonAndDot(this);
        }
        if ((thisLineType == 2 && otherLineType == 4) || (thisLineType == 4 && otherLineType == 2)) {
            if (thisLineType == 2) {
                return this.horizonAndRegular(other);
            }
            return other.horizonAndRegular(this);
        }
        if (thisLineType == 3 && otherLineType == 3) {
            return this.start.equals(other.start);
        }
        if ((thisLineType == 3 && otherLineType == 4) || (thisLineType == 4 && otherLineType == 3)) {
            if (thisLineType == 3) {
                return other.isPointOnLine(this.start);
            }
            return this.isPointOnLine(other.start);
        }
        if (thisLineType == 4 && otherLineType == 4) {
            return this.twoRegulars(other);
        }
        return false;
    }

    /**
     * Determines the intersection point of this line with another line.
     *
     * @param other the other line to check for intersection
     * @return the intersection point if the lines intersect, {@code null} otherwise
     */
    public Point intersectionWith(Line other) {
        if (this.equals(other)) {
            return null;
        }
        if (!this.isIntersecting(other)) {
            return null;
        }
        int thisLineType = this.lineType();
        int otherLineType = other.lineType();
        if (thisLineType == 1 && otherLineType == 1) {
            return this.twoVerticalsOrHorizontalsPoint(other);
        }
        if ((thisLineType == 1 && otherLineType == 2) || (thisLineType == 2 && otherLineType == 1)) {
            if (thisLineType == 1) {
                return this.vertAndHorizonPoint(other);
            }
            return other.vertAndHorizonPoint(this);
        }
        if ((thisLineType == 1 && otherLineType == 3) || (thisLineType == 3 && otherLineType == 1)) {
            if (thisLineType == 1) {
                return other.start;
            }
            return this.start;
        }
        if ((thisLineType == 1 && otherLineType == 4) || (thisLineType == 4 && otherLineType == 1)) {
            if (thisLineType == 1) {
                return vertAndRegularPoint(other);
            }
            return other.vertAndRegularPoint(this);
        }
        if (thisLineType == 2 && otherLineType == 2) {
            return this.twoVerticalsOrHorizontalsPoint(other);
        }
        if ((thisLineType == 2 && otherLineType == 3) || (thisLineType == 3 && otherLineType == 2)) {
            if (thisLineType == 2) {
                return other.start;
            }
            return this.start;
        }
        if ((thisLineType == 2 && otherLineType == 4) || (thisLineType == 4 && otherLineType == 2)) {
            if (thisLineType == 2) {
                return this.horizonAndRegularPoint(other);
            }
            return other.horizonAndRegularPoint(this);
        }
        if (thisLineType == 3 && otherLineType == 3) {
            if (this.start.equals(other.start)) {
                return this.start;
            }
            return null;
        }
        if ((thisLineType == 3 && otherLineType == 4) || (thisLineType == 4 && otherLineType == 3)) {
            if (thisLineType == 3) {
                return this.start;
            }
            return other.start;
        }
        if (thisLineType == 4 && otherLineType == 4) {
            return this.twoRegularsPoint(other);
        }
        return null;
    }

    /**
     * Returns the closest intersection point between the line and the given rectangle
     * to the start of the line.
     *
     * @param rect the rectangle to check for intersections with the line
     * @return the closest intersection point to the start of the line, or null if there are no intersections
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectionPoints = rect.intersectionPoints(this);
        if (intersectionPoints.isEmpty()) {
            return null;
        }
        Point minDistance = intersectionPoints.get(0);
        for (Point intersectionPoint : intersectionPoints) {
            if (this.start.distance(intersectionPoint) < this.start.distance(minDistance)) {
                minDistance = intersectionPoint;
            }
        }
        return minDistance;
    }
}