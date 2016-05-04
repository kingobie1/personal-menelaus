package menelaus.model.board;

import java.io.Serializable;

import menelaus.model.basic.Point;

/**
 * Tiles are the building blocks of Pieces.
 * @author vouldjeff
 *
 */
public class Tile implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * The relative position to the position of the Piece.
	 */
	private Point relativePosition;

	/**
	 * Gets the relative position.
	 * @return The object.
	 */
	public Point getRelativePosition() {
		return relativePosition;
	}

	/**
	 * Sets the relative position
	 * @param relativePosition The new object.
	 */
	public void setRelativePosition(Point relativePosition) {
		this.relativePosition = relativePosition;
	}

	/**
	 * Builds a new Tile.
	 * @param relativePosition The place to put it.
	 */
	public Tile(Point relativePosition) {
		super();
		this.relativePosition = relativePosition;
	}
	
	/**
	 * Builds a new Tile.
	 * @param x The x relative position.
	 * @param y The y relative position.
	 */
	public Tile(int x, int y) {
		super();
		this.relativePosition = new Point(x, y);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((relativePosition == null) ? 0 : relativePosition.hashCode());
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
		Tile other = (Tile) obj;
		if (relativePosition == null) {
			if (other.relativePosition != null)
				return false;
		} else if (!relativePosition.equals(other.relativePosition))
			return false;
		return true;
	}
}
