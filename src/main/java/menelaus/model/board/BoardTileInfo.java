package menelaus.model.board;

import java.io.Serializable;

/**
 * Holds extra information about "virtual tiles" on the board.
 * @author vouldjeff
 */
public class BoardTileInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * The colored number for release levels.
	 */
	private ColoredSetItem coloredSetItem;
	
	/**
	 * Is the Tile chopped out.
	 */
	private boolean isTileChopped;
	
	/**
	 * A piece placed there.
	 */
	private Piece piecePlaced;
	
	/**
	 * Returns the Colored Set item.
	 * @return The object.
	 */
	public ColoredSetItem getColoredSetItem() {
		return coloredSetItem;
	}
	
	/**
	 * Sets a colored number.
	 * @param coloredSetItem The new object.
	 */
	public void setColoredSetItem(ColoredSetItem coloredSetItem) {
		this.coloredSetItem = coloredSetItem;
	}
	
	/**
	 * Tells if the tile on the board is actually there.
	 * @return True if chopped/missing.
	 */
	public boolean isTileChopped() {
		return isTileChopped;
	}
	
	/**
	 * Sets the chopped state of the tile.
	 * @param isTileChopped What should it be changed to.
	 */
	public void setTileChopped(boolean isTileChopped) {
		this.isTileChopped = isTileChopped;
	}
	
	/**
	 * Returns the piece placed. Null if nothing.
	 * @return The Piece.
	 */
	public Piece getPiecePlaced() {
		return piecePlaced;
	}
	
	/**
	 * Sets a new piece that covers this tile.
	 * @param piecePlaced The new Piece.
	 */
	public void setPiecePlaced(Piece piecePlaced) {
		this.piecePlaced = piecePlaced;
	}

	/**
	 * Creates a new BoardTileInfo object.
	 * @param isTileChopped Is the tile chopped?
	 */
	public BoardTileInfo(boolean isTileChopped) {
		super();
		this.isTileChopped = isTileChopped;
	}
}
