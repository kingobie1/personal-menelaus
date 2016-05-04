package menelaus.view;

import menelaus.model.Level;
import menelaus.model.basic.LevelType;
import menelaus.model.basic.Point;
import menelaus.model.board.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Here is where the pieces are to be played (in 700x700 size).
 *
 * @author Obatola Seward-Evans
 */
public class BoardView extends JPanel {

    /**
     * border color for BoardView;
     */
    Color borderColor = Color.black;

    /**
     * background color for boardView.
     */
    Color backgroundColor = Color.white;

    /**
     * height of a grid square.
     */
    int gridSquareHeight = 0;

    /**
     * width of a grid square.
     */
    int gridSquareWidth = 0;

    /**
     * size of the board.
     */
    int drawingSize = 0;

    /**
     * width/height of grid by grid squares.
     */
    int subdivisions = 0;

    /**
     * Board tile info map for release level.
     */
    Hashtable<Point, BoardTileInfo> boardTileInfoMap;

    /**
     * Board tile info for each point.
     */
    BoardTileInfo boardTileInfo;

    /**
     * Core board.
     */
    Board board;
    Level level;

    /**
     * around edges.
     */
    int offset = 32;

    /**
     * Base size of board.
     */
    public final int N = 700;

    /**
     * Whether or not board tiles can be selected.
     */
    boolean hasSelection;

    /**
     * Only applicable in builder, which tiles are selected.
     */
    ArrayList<Point> selection = new ArrayList<Point>();

    /**
     * Off-screen image for drawing (and Graphics object).
     */
    Image offScreenImage = null;
    Graphics offScreenGraphics = null;

    /**
     * Only here so we can safely open within WindowBuilder.
     */
    public BoardView() {
        this.setBorder(BorderFactory.createLineBorder(borderColor));
        this.setBackground(backgroundColor);
        autoChopOut();
    }

    /**
     * Given a set of KabaSuji pieces, draw them in this panel.
     * @param board the given board.
     * @param level the given level.
     */
    public BoardView(Board board, Level level) {
        this.board = board;
        this.level = level;
        this.hasSelection = false;
        this.setBorder(BorderFactory.createLineBorder(borderColor));
        this.setBackground(backgroundColor);
        autoChopOut();
    }


    /**
     * Constructor for boardView with hasSelection.
     * @param board the given board.
     * @param level the given level.
     * @param hasSelection the selected pieces when making a new piece/hint in Builder.
     */
    public BoardView(Board board, Level level, boolean hasSelection) {
        this.board = board;
        this.level = level;
        this.hasSelection = hasSelection;
        this.setBorder(BorderFactory.createLineBorder(borderColor));
        this.setBackground(backgroundColor);
        autoChopOut();
    }

    /**
     * Swing thing. We must be large enough to draw all KabaSuji pieces.
     */
    public Dimension getMinimumSize() {
        int width = 2 * N + 2 * offset;
        int height = 2 * N + 2 * offset;

        return new Dimension(width, height);
    }

    /**
     * Draw background puzzle and all active pieces.
     */
    @Override
    public void paintComponent(Graphics g) {
        initDemensions();

        super.paintComponent(g);

        // Draw Background Color
        BufferedImage backgroundIMG = null;
        try {
            backgroundIMG = ImageIO.read(this.getClass().getResource("/assets/secondary_back.png"));
            if (backgroundIMG != null) {
                g.drawImage(backgroundIMG.getScaledInstance(1000, 750, Image.SCALE_DEFAULT), 0, 0, null);
            }
        } catch (Exception e) {
            System.out.println("BoardView: the image isn't read");
        }

        drawHints(g);

        // Draw Pieces:
        for (Piece p : board.getPieces()) {
            if (level.getType() == LevelType.PUZZLE || level.getType() == LevelType.RELEASE)
                PieceDrawer.drawPieceToGrid(g, p, calculateGridUnitSize());
            else if (level.getType() == LevelType.LIGHTNING)
                PieceDrawer.drawCoverPieceToGrid(g, p, calculateGridUnitSize());
        }

        // draw active piece.
        Piece active = level.getActive();
        if (active != null) {
            PieceDrawer.drawPieceToGrid(g, active, calculateGridUnitSize());
        }

        if (this.hasSelection) {
            if (this.selection != null) {
                PieceDrawer.drawSelection(g, selection, calculateGridUnitSize());
            }
        }

        drawGrid(g);
        drawUnavailableTiles(g);
        if (level.getType() == LevelType.RELEASE) {
            drawReleaseColorSets(g);
        }

    }

    /**
     * Draw hint piece on board.
     *
     * @param g
     * @author Obatola Seward-Evans
     */
    private void drawHints(Graphics g) {
        for (HintPiece hintPiece : board.getHints()) {
            PieceDrawer.drawHintToGrid(g, hintPiece, calculateGridUnitSize());
        }
    }

    private void initDemensions() {
        drawingSize = this.getWidth();

        // if get board height is the largest:
        if (board.getHeight() > board.getWidth()) {
            // set the amount of subdivisions to be board height:
            subdivisions = board.getHeight();
        } else {
            // if not, set amount of subdivisions to be board width:
            subdivisions = board.getWidth();
        }

        gridSquareHeight = drawingSize / subdivisions;

        gridSquareWidth = drawingSize / subdivisions;

    }

    /**
     * Calculates.
     *
     * @return The size of a grid unit in pixels
     */
    public int calculateGridUnitSize() {
        if (board.getHeight() > board.getWidth())
            return this.getHeight() / board.getHeight();
        else
            return this.getWidth() / board.getWidth();
    }

    /**
     * Draws all the color set numbers on the board.
     *
     * @param g
     */
    public void drawReleaseColorSets(Graphics g) {
        // get tile info.
        boardTileInfoMap = board.getTileInfo();

        // iterate through hashmap of point, boardTileInfo
        if (boardTileInfoMap.keySet() != null) {
            for (Point point : boardTileInfoMap.keySet()) {
                ColoredSetItem colorInfo = boardTileInfoMap.get(point).getColoredSetItem();

                // skip to next loop iteration if null
                if (colorInfo == null) {
                    continue;
                }

                String number = colorInfo.getNumber() + "";

                int x = point.getX() * gridSquareWidth + gridSquareWidth / 2 - 5;
                int y = point.getY() * gridSquareHeight + gridSquareHeight / 2 + 5;

                int fontSize = gridSquareHeight / 5;
                g.setColor(colorInfo.getJavaColor());
                g.setFont(new Font(Font.MONOSPACED, Font.BOLD, fontSize));
                g.drawString(number, x, y);
            }
        }
    }

    /**
     * Draws all unavailable tiles on the board.
     *
     * @param g
     * @author Obatola Seward-Evans
     */
    public void drawUnavailableTiles(Graphics g) {

        for (menelaus.model.basic.Point point : board.getTileInfo().keySet()) {
            if (board.getTileInfo().get(point).isTileChopped()) { // This tile should not be on the board.
                g.setColor(Color.LIGHT_GRAY);

                // TODO: make sure rectangle point is correct.
                g.fillRect(point.getX() * calculateGridUnitSize(), point.getY() * calculateGridUnitSize(), gridSquareHeight, gridSquareWidth);
            }
        }
    }

    /**
     * Draws a grid on the board.
     *
     * @param g Graphics
     * @author Obatola Seward-Evans
     */
    public void drawGrid(Graphics g) {
        int subdivisionSize = drawingSize / subdivisions;

        // Draw Grid:
        Graphics2D grid = (Graphics2D) g;
        grid.setPaint(Color.GRAY);
        for (int i = 1; i < subdivisions; i++) {
            int x = i * subdivisionSize;
            grid.drawLine(x, 0, x, getSize().height);
        }
        for (int i = 1; i < subdivisions; i++) {
            int y = i * subdivisionSize;
            grid.drawLine(0, y, getSize().width, y);
        }
    }

    /**
     * Finds teh piece clciked on.
     * @param point To coordinate of the click.
     * @return the piece that was clicked on.
     */
    public Piece findPiece(Point point) {
    	BoardTileInfo info = board.getTileInfo().get(pointUnder(point));
    	if (info == null) {
    		return null;
    	}
    	
        return info.getPiecePlaced();
    }

    /**
     * Converts pixels to grid units.
     * @param point Coordinate of the search.
     * @return A point that represents the the grid unit clicked.
     */
    public Point pointUnder(Point point) {
        return point.divide(calculateGridUnitSize());
    }

    /**
     * Set the selection.
     * @param newSel
     */
    public void setSelection(ArrayList<Point> newSel) {
        this.selection = newSel;
    }

    /**
     * Automatically chops out pieces for rectangular boards.
     */
    public void autoChopOut() {
        if(board == null) return;

        int height = board.getHeight();
        int width = board.getWidth();

        if (height > width) {
            //auto chop out pieces not available
            for (int x = width; x < height; x++) {
                for (int y = 0; y < height; y++) {
                    level.getBoard().chopTileOut(new Point(x, y));
                }
            }
        } else {
            for (int x = 0; x < width; x++) {
                for (int y = height; y < width; y++) {
                    level.getBoard().chopTileOut(new Point(x, y));
                }
            }
        }
    }

}
