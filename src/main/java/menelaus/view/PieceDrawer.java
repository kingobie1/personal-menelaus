package menelaus.view;

import menelaus.model.basic.Point;
import menelaus.model.board.Piece;
import menelaus.model.board.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.Hashtable;


/**
 * This handles all of the piece drawing. 
 * @author fegan
 * @author vouldjeff
 */
public class PieceDrawer {
    static final String TILE_COLOR = "#3399ff";
    static final String LIGHTNING_COLOR = "#7b1fa2";
    static final String HINT_COLOR = "#ff8133";
    static final String SELECT_COLOR = "#aade10";

    static final int BORDER = 4;

    /**
     * Draws the piece.
     * <p/>
     * Draw a bunch of rectangles and hope to it works.
     *
     * @param graphics The graphics object we're drawing to.
     * @param piece The piece we want drawn.
     * @param spot point to draw a piece.
     * @param tileSize the given tile size in relation to the board.
     */
    public static void drawPiece(Graphics graphics, Piece piece, Point spot, int tileSize) {
        Point massDelta = new Point((6 - piece.getWidth()) / 2, (6 - piece.getHeight()) / 2);
        Point origin = piece.getOrigin().add(massDelta);

        for (Tile t : piece.getTiles()) {
            Point actualPosition = origin.add(t.getRelativePosition()).multiply(tileSize).add(spot);

            //draw each tile
            graphics.setColor(Color.decode(TILE_COLOR));
            graphics.fillRect(
                    actualPosition.getX(),
                    actualPosition.getY(),
                    tileSize,
                    tileSize);
            //draw border
            graphics.setColor(Color.GRAY);
            graphics.drawRect(
                    actualPosition.getX(),
                    actualPosition.getY(),
                    tileSize,
                    tileSize);
        }
    }

    /**
     * Draws the piece.
     * <p/>
     * Draw a bunch of rectangles, here the piece can have a position in terms of teh actual grid units, instead of pixels.
     *
     * @param graphics The graphics object we're drawing to.
     * @param piece    The piece we want drawn
     * @param tileSize the given tile size in relation to the board.
     */
    public static void drawPieceToGrid(Graphics graphics, Piece piece, int tileSize) {
        _draw(graphics, piece, tileSize, Color.decode(TILE_COLOR), false);
    }
    /**
     * Draws the piece.
     * <p/>
     * Draw a bunch of rectangles, here the piece can have a position in terms of teh actual grid units, instead of pixels.
     *
     * @param graphics The graphics object we're drawing to.
     * @param piece    The piece we want drawn
     * @param tileSize 
     */
    public static void drawCoverPieceToGrid(Graphics graphics, Piece piece, int tileSize) {
        _draw(graphics, piece, tileSize, Color.decode(LIGHTNING_COLOR), true);
    }

    /**
     * Draws the hint.
     * <p/>
     * Draw a bunch of rectangles, here the piece can have a position in terms of teh actual grid units, instead of pixels.
     *
     * @param graphics The graphics object we're drawing to.
     * @param piece    The piece we want drawn
     * @param tileSize the given tile size in relation to the board.
     */
    public static void drawHintToGrid(Graphics graphics, Piece piece, int tileSize) {
        _draw(graphics, piece, tileSize, Color.decode(HINT_COLOR), true);
    }

    private static void _draw(Graphics graphics, Piece piece, int tileSize, Color c, boolean skipBorders) {
        Point origin = piece.getOrigin();

        Hashtable<Integer, Integer> minVertical = new Hashtable<Integer, Integer>();
        Hashtable<Integer, Integer> minHorizontal = new Hashtable<Integer, Integer>();
        Hashtable<Integer, Integer> maxVertical = new Hashtable<Integer, Integer>();
        Hashtable<Integer, Integer> maxHorizontal = new Hashtable<Integer, Integer>();

        if (!skipBorders) {
	        for (Tile tile : piece.getTiles()) {
	            Integer searchMinVertical = minVertical.get(tile.getRelativePosition().getX());
	            Integer searchMaxVertical = maxVertical.get(tile.getRelativePosition().getX());
	            Integer searchMinHorizontal = minHorizontal.get(tile.getRelativePosition().getY());
	            Integer searchMaxHorizontal = maxHorizontal.get(tile.getRelativePosition().getY());
	
	            if (searchMinVertical == null || searchMinVertical.intValue() > tile.getRelativePosition().getY()) {
	                minVertical.put(tile.getRelativePosition().getX(), tile.getRelativePosition().getY());
	            }
	
	            if (searchMaxVertical == null || searchMaxVertical.intValue() < tile.getRelativePosition().getY()) {
	                maxVertical.put(tile.getRelativePosition().getX(), tile.getRelativePosition().getY());
	            }
	
	            if (searchMinHorizontal == null || searchMinHorizontal.intValue() > tile.getRelativePosition().getX()) {
	                minHorizontal.put(tile.getRelativePosition().getY(), tile.getRelativePosition().getX());
	            }
	
	            if (searchMaxHorizontal == null || searchMaxHorizontal.intValue() < tile.getRelativePosition().getX()) {
	                maxHorizontal.put(tile.getRelativePosition().getY(), tile.getRelativePosition().getX());
	            }
	        }
        }

        for (Tile t : piece.getTiles()) {
            Point actualPosition = piece.getPosition().add(t.getRelativePosition()).multiply(tileSize);

            //draw tiles
            graphics.setColor(c);
            graphics.fillRect(
                    actualPosition.getX(),
                    actualPosition.getY(),
                    tileSize,
                    tileSize);

            if (skipBorders) {
            	continue;
            }
            
            Integer v = minVertical.get(t.getRelativePosition().getX());
            if (v != null && t.getRelativePosition().getY() == v.intValue()) {
                _drawLine(graphics, actualPosition.subtract(new Point(0, BORDER / 2)), tileSize, true);
            }

            v = minHorizontal.get(t.getRelativePosition().getY());
            if (v != null && t.getRelativePosition().getX() == v.intValue()) {
                _drawLine(graphics, actualPosition.subtract(new Point(BORDER / 2, 0)), tileSize, false);
            }

            v = maxVertical.get(t.getRelativePosition().getX());
            if (v != null && t.getRelativePosition().getY() == v.intValue()) {
                _drawLine(graphics, actualPosition.add(new Point(0, tileSize - BORDER / 2)), tileSize, true);
            }

            v = maxHorizontal.get(t.getRelativePosition().getY());
            if (v != null && t.getRelativePosition().getX() == v.intValue()) {
                _drawLine(graphics, actualPosition.add(new Point(tileSize - BORDER / 2, 0)), tileSize, false);
            }
        }
    }

    private static void _drawLine(Graphics graphics, Point actualPosition, int tileSize, boolean isY) {
        graphics.setColor(Color.GRAY);
        graphics.fillRect(
                actualPosition.getX(),
                actualPosition.getY(),
                isY ? tileSize : BORDER,
                isY ? BORDER : tileSize);
    }

    /**
     * Draw for each selected points.
     * @param graphics the given graphic.
     * @param selectedPoints
     * @param tileSize the given tile size in relation to the board.
     */
    public static void drawSelection(Graphics graphics, ArrayList<Point> selectedPoints, int tileSize) {
        for (Point p : selectedPoints) {
            Point actualPoint = p.multiply(tileSize);

            //draw tiles
            graphics.setColor(Color.decode(SELECT_COLOR));
            graphics.fillRect(
                    actualPoint.getX(),
                    actualPoint.getY(),
                    tileSize,
                    tileSize);
        }
    }
    
}
