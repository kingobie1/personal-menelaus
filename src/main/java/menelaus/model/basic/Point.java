package menelaus.model.basic;

import java.io.Serializable;

/**
 * The fundamental reference unit.
 *
 * @author vouldjeff
 */
public class Point implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The X Coordinate of the point.
     */
    private int x;

    /**
     * The Y Coordinate of the point.
     */
    private int y;

    /**
     * Returns the X coordinate.
     *
     * @return An int.
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the X coordinate.
     *
     * @param x The new value.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Returns the Y coordinate.
     *
     * @return An int.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the Y coordinate.
     *
     * @param y The new value.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Creates a new Point.
     *
     * @param x The X coordinate.
     * @param y The Y coordinate.
     */
    public Point(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    /**
     * Adds two points together.
     *
     * @param otherPoint The other option to add.
     * @return The result new Point where components where summed.
     */
    public Point add(Point otherPoint) {
        return new Point(this.x + otherPoint.getX(), this.y + otherPoint.getY());
    }

    /**
     * Subtracts two points.
     *
     * @param otherPoint The point to subtract from this object.
     * @return The result new Point.
     */
    public Point subtract(Point otherPoint) {
        return new Point(this.x - otherPoint.getX(), this.y - otherPoint.getY());
    }

    /**
     * Multiplies the components of the Point.
     *
     * @param diff The delta.
     * @return The new Point.
     */
    public Point multiply(int diff) {
        return new Point(this.x * diff, this.y * diff);
    }
    
    /**
     * Divides the components of the Point.
     *
     * @param diff The delta.
     * @return The new Point.
     */
    public Point divide(int diff) {
        return new Point(this.x / diff, this.y / diff);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Point other = (Point) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }

    @Override
    /**
     * Converts a point to a string representation
     */
    public String toString() {
        return "X = " + x + ", Y = " + y;
    }
    /**
     * Determines whether this points is adjacent to the given point on the baord
     * @param other
     * @return true if they are adjacent, false otherwise
     */
    public boolean adjacentTo(Point other) {
        //The other one is adjacent if one of the coordinates is 1 block away, and the other is zero. That is...
        //Both are within 1 block.
        //Exactly 1 is 0 blocks away.
        return (
                (Math.abs(other.x - this.x) <= 1 && Math.abs(other.y - this.y) <= 1)
                        &&
                        (Math.abs(other.x - this.x) == 0 ^ Math.abs(other.y - this.y) == 0)
        );
    }

}
