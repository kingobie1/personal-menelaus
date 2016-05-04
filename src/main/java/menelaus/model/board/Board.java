package menelaus.model.board;

import menelaus.model.basic.Point;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

/**
 * Represents a Board with specific dimensions. Holds pieces, hints and coloredSetItems.
 * @author vouldjeff
 * @author sanjay
 */
public class Board implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The height of the Board.
     */
    private int height;
    
    /**
     * The width of the board.
     */
    private int width;
    
    /**
     * For each virtual tile of the board an object with BoardTileInfo. If no object, then regular tile.
     */
    private Hashtable<Point, BoardTileInfo> tileInfo;
    
    /**
     * A collection with all hints.
     */
    private ArrayList<HintPiece> hints;
    
    /**
     * A collection with all placed pieces.
     */
    private ArrayList<Piece> pieces;

    /**
     * Gets the Board height.
     * @return The height.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the Board height.
     * @param height New height.
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Gets the Board width.
     * @return The width.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets the Board width.
     * @param width New width.
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Gets a Hashtable with info for each tile in the Board. Useful for drawing.
     * @return The hashtable.
     */
    public Hashtable<Point, BoardTileInfo> getTileInfo() {
        return tileInfo;
    }

    /**
     * Gets all hints.
     * @return All hints.
     */
    public ArrayList<HintPiece> getHints() {
        return hints;
    }

    /**
     * Adds a hint.
     * @param hint object to be added
     */
    public void addHintPiece(HintPiece hint) {
    	hints.add(hint);
    }
    
    /**
     * Adds a colored set item for a Release level.
     * @param item the object
     * @param point location at which to place it
     */
    public void addColoredSetItem(ColoredSetItem item, Point point) {
        BoardTileInfo info = tileInfo.get(point);
        if (info == null) {
            info = new BoardTileInfo(false);
            tileInfo.put(point, info);
        }

        info.setColoredSetItem(item);
    }

    /**
     * Gets all pieces.
     * @return The Collection.
     */
    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    /**
     * Constructs a new board with given dimensions.
     * @param height Board height.
     * @param width Board width.
     */
    public Board(int height, int width) {
        super();
        this.height = height;
        this.width = width;
        this.pieces = new ArrayList<Piece>();
        this.hints = new ArrayList<HintPiece>();
        this.tileInfo = new Hashtable<Point, BoardTileInfo>();
    }

    /**
     * Removes a tile from the board. This allows to create weirdly shaped boards.
     * @param point piece to be chopped out
     */
    public void chopTileOut(Point point) {
        BoardTileInfo info = tileInfo.get(point);
        if (info == null) {
            info = new BoardTileInfo(true);
            tileInfo.put(point, info);
        } else {
            info.setTileChopped(true);
        }
    }
    
    /**
     * Checks is tile is chopped out.
     * @param point at which location
     * @return Whether the tile is chopped out.
     */
    public boolean isChoppedOut(Point point) {
    	BoardTileInfo info = tileInfo.get(point);
        if (info == null) {
            return false;
        } else return (info.isTileChopped());
    }
    
    /**
     * Returns a chopped tile.
     * @param point Point at which to check.
     */
    public void unchopTile(Point point) {
    	BoardTileInfo info = tileInfo.get(point);
        if (info == null) {
        	return;
        } else if (info.isTileChopped()) {
        	info.setTileChopped(false);
        };
    }
    
    /**
     * Places a piece on the Board. Used for non-lightning levels.
     * @param piece New piece.
     * @throws InvalidPiecePlacementException If placement is over another piece or outside of board.
     */
    public void placePiece(Piece piece) throws InvalidPiecePlacementException {
        _placePiece(piece, false);
    }
    
    /**
     * Covers board with a Piece for a lightning level.
     * @param piece New piece.
     * @throws InvalidPiecePlacementException If placement is over another piece or outside of board.
     */
    public void coverWithPiece(Piece piece) throws InvalidPiecePlacementException {
    	_placePiece(piece, true);
    }
    
    /**
     * Tried to place a piece on board.
     * @param piece The piece to be added.
     * @param canPlaceOver If a lightning level that allows overlap.
     * @throws InvalidPiecePlacementException If piece if placed outside of board.
     */
    private void _placePiece(Piece piece, boolean canPlaceOver) throws InvalidPiecePlacementException {
    	if (!isPlacementValid(piece, canPlaceOver)) {
            throw new InvalidPiecePlacementException();
        }

        pieces.add(piece);

        Iterator<Tile> iterator = piece.getTiles().iterator();
        while (iterator.hasNext()) {
            Tile tile = iterator.next();
            Point point = piece.getPosition().add(tile.getRelativePosition());

            BoardTileInfo info = tileInfo.get(point);
            if (info == null) {
                info = new BoardTileInfo(false);
                tileInfo.put(point, info);
            }

            info.setPiecePlaced(piece);
        }
    }
    
    /**
     * Says if a piece could be placed.
     * @param piece object in question
     * @param canPlaceOver is this a lightning level
     * @return Can it be placed?
     */
    public boolean isPlacementValid(Piece piece, boolean canPlaceOver) {
    	try {
			return isPointWithinBoundary(piece.getPosition()) && isBoardFreeForPiece(piece, canPlaceOver);
		} catch (InvalidPiecePlacementException e) {
			return false;
		}
    }
    
    /**
     * Says if a piece could be placed.
     * @param piece object in question
     * @return Can it be placed?
     */
    public boolean isPlacementValid(Piece piece) {
    	return isPlacementValid(piece, false);
    }

    /**
     * Removes a piece from the board.
     * @param piece Object to be removed.
     */
    public void removePiece(Piece piece) {
        if (!pieces.remove(piece)) {
            return;
        }

        Iterator<Tile> iterator = piece.getTiles().iterator();
        while (iterator.hasNext()) {
            Tile tile = iterator.next();
            Point point = piece.getPosition().add(tile.getRelativePosition());

            BoardTileInfo info = tileInfo.get(point);
            info.setPiecePlaced(null);
        }
    }

    /**
     * Says if a piece can be placed a the particular position.
     * @param piece The new piece to be placed.
     * @param canPlaceOver Is it a lightning level that allows overlap?
     * @return True is can be placed.
     * @throws InvalidPiecePlacementException Exception if outside of board.
     */
    private boolean isBoardFreeForPiece(Piece piece, boolean canPlaceOver) throws InvalidPiecePlacementException {
        Iterator<Tile> iterator = piece.getTiles().iterator();
        while (iterator.hasNext()) {
            Tile tile = iterator.next();
            Point point = piece.getPosition().add(tile.getRelativePosition());

            if (!isPointWithinBoundary(point)) {
                throw new InvalidPiecePlacementException();
            }

            BoardTileInfo info = tileInfo.get(point);
            if (info == null) {
                continue;
            }

            if (info.isTileChopped()) {
            	return false;
            }
            
            if (!canPlaceOver && (info.getPiecePlaced() != null) && !info.getPiecePlaced().equals(piece)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Is this point within the board
     * @param point Object in question.
     * @return Is it within board?
     */
    public boolean isPointWithinBoundary(Point point) {
        return point.getX() < width && point.getY() < height && point.getX() >= 0 && point.getY() >= 0;
    }
    
    /**
     * Says if all the non-chopped tiles are filled with pieces.
     * @return Is board full?
     */
    public boolean isFull() {
    	return getNumberOfEmptyTiles() == 0;
    }
    
    /**
     * Returns the number of empty tiles.
     * @return Number of empty tiles
     */
    public int getNumberOfEmptyTiles() {
    	int count = 0;
    	
    	for (int i = 0; i < width; i++) {
    		for (int j = 0; j < height; j++) {
    			BoardTileInfo info = tileInfo.get(new Point(i, j));
    			if (info == null || (info.getPiecePlaced() == null && !info.isTileChopped())) {
    				count++;
    			}
    		}
    	}
    	
    	return count;
    }
    
    /**
     * Places a given piece at an X,Y location. Undoes the move if invalid.
     * @param p The piece to place.
     * @param x The x coordinate to place at.
     * @param y The y coordinate to place at.
     * @return Whether the placing was successful.
     */
    public boolean placePieceAtRowAndColumn(Piece p, int x, int y) {
    	Point newLocation = new Point(x, y);
    	Point oldLocation = new Point(p.getPosition().getX(), p.getPosition().getX());
    	
    	p.setPosition(newLocation);
    	if (isPlacementValid(p)) {
    		try {
				placePiece(p);
				return true;
			} catch (InvalidPiecePlacementException e) {
				p.setPosition(oldLocation);
				return false;
			}
    	}
    	else {
    		//go back
    		p.setPosition(oldLocation);
    		return false;
    	}
    	
    	//newLocation;
    	
    }

    /**
     * Resets the Board -- removing all pieces and placement data.
     */
	public void resetBoard() {
		for(BoardTileInfo btInfo : this.tileInfo.values()){
			btInfo.setPiecePlaced(null);
		}
		
	}
}
