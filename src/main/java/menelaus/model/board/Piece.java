package menelaus.model.board;

import menelaus.model.basic.Point;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * A piece that could be placed on the Board or on the Bullpen; knows its dimensions and center of mass.
 * @author vouldjeff
 */
public class Piece implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * The position of the piece origin.
	 */
	private Point position;
	
	/**
	 * The tiles which build the piece.
	 */
	private ArrayList<Tile> tiles;
	
	/**
	 * Is the piece selected?
	 */
	private boolean selected;
	
	/**
	 * Returns the position of the origin of the piece.
	 * @return The origin point.
	 */
	public Point getPosition() {
		return position;
	}
	
	/**
	 * Sets the position of the piece.
	 * @param position The new origin location.
	 */
	public void setPosition(Point position) {
		this.position = position;
	}
	
	/**
	 * Returns all the tiles that build the piece.
	 * @return The tiles.
	 */
	public ArrayList<Tile> getTiles() {
		return tiles;
	}
	
	/**
	 * Is the piece selected?
	 * @return baby boolean
	 */
	public boolean getSelected() {
		return this.selected;
	}
	
	/**
	 * Says that this piece is selected/unselected.
	 * @param selected The new value.
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	/**
	 * Rotates the Piece clockwise.
	 */
	public void rotate() {
		Iterator<Tile> iterator = tiles.iterator();
		
		while (iterator.hasNext()) {
			Tile tile = (Tile) iterator.next();
			Point newLocation = new Point(tile.getRelativePosition().getY(), 
					-tile.getRelativePosition().getX());
			
			tile.setRelativePosition(newLocation);
		}
	}
	
	/**
	 * Flips the piece along the Y axis.
	 */
	public void flip() {
		Iterator<Tile> iterator = tiles.iterator();
		int maxX = 0;
		
		while (iterator.hasNext()) {
			Tile tile = (Tile) iterator.next();
			
			if (maxX < tile.getRelativePosition().getX()) {
				maxX = tile.getRelativePosition().getX();
			}
		}
		
		iterator = tiles.iterator();
		while (iterator.hasNext()) {
			Tile tile = (Tile) iterator.next();
			Point newLocation = new Point(maxX - tile.getRelativePosition().getX(), 
					tile.getRelativePosition().getY());
			
			tile.setRelativePosition(newLocation);
		}
	}
	
	/**
	 * Adds a new tile to the Piece.
	 * @param tile The new tile.
	 */
	public void addTile(Tile tile) {
		tiles.add(tile);
	}
	
	/**
	 * Creates a new Piece at a particular location and adds one tile.
	 * @param position The origin.
	 */
	public Piece(Point position) {
		super();
		this.position = position;
		this.tiles = new ArrayList<Tile>();
		tiles.add(new Tile(new Point(0,0)));
	}
	
	/**
	 * Duplicates the Piece.
	 * @param oldPiece Object to be copied.
	 */
	public Piece(Piece oldPiece) {
		super();
		this.position = new Point(oldPiece.getPosition().getX(), oldPiece.getPosition().getY());
		this.tiles = new ArrayList<Tile>();
		for (Tile tile : oldPiece.getTiles()) {
			tiles.add(new Tile(tile.getRelativePosition().getX(), tile.getRelativePosition().getY()));
		}
	}

	/**
	 * Calculates the height of the piece.
	 * @return The height.
	 */
    public int getHeight() {
        int max = 0, min = 0;
        for (Tile t : tiles) {
            int y = t.getRelativePosition().getY();
            if (y > max)
                max = y;
            else if (y < min)
                min = y;
        }
        return max - min;
    }

    /**
     * Calculates the width of the piece.
     * @return The width.
     */
    public int getWidth() {
        int max = 0, min = 0;
        for (Tile t : tiles) {
            int x = t.getRelativePosition().getX();
            if (x > max)
                max = x;
            else if (x < min)
                min = x;
        }
        return max - min;
    }
    
    /**
     * Gets the actual mathematical origin -- top left point.
     * @return The Point object.
     */
    public Point getOrigin() {
    	int minX = 0, minY = 0;
    	for (Tile t : tiles) {
            if (t.getRelativePosition().getX() < minX) {
            	minX = t.getRelativePosition().getX();
            }
            
            if (t.getRelativePosition().getY() < minY) {
            	minY = t.getRelativePosition().getY();
            }
        }
    	
    	
    	return new Point(minX, minY).multiply(-1);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((position == null) ? 0 : position.hashCode());
        result = prime * result + ((tiles == null) ? 0 : tiles.hashCode());
        return result;
    }
}
