package menelaus.model.move;

import menelaus.model.BuilderManager;
import menelaus.model.Level;
import menelaus.model.basic.Point;
import menelaus.model.board.InvalidPiecePlacementException;
import menelaus.model.board.Piece;
import menelaus.model.board.Tile;

import java.util.ArrayList;

/**
 * This is the move which makes a piece in the builder.
 * @author sanjay
 */
public class MakePieceBuilderMove extends BuilderMove {

    Piece pieceToPlace = null;
    Piece pieceToPlaceInBullpen = null;
    ArrayList<Point> allSelected;
/**
 * Constructor
 * @param manager
 */
    public MakePieceBuilderMove(BuilderManager manager) {
        super(manager);
        this.manager = manager;
        allSelected = new ArrayList<>();
        for (int i = 0; i < manager.getSelectedPoints().size(); i++) {

            Point p = manager.getSelectedPoints().get(i); //Makes a safe duplicate
            allSelected.add(new Point(p.getX(), p.getY())); //Deep copy
        }
    }

    @Override
    /**
	 * Determines whether this move is valid within the given level
	 * @return true if move is valid, false otherwise
	 */
    public boolean valid(Level level) {
        // There are exactly 6 selections
        // All of the selections are adjacent to at least one other selection.
        // All 6 selections are valid
        // A piece can be placed there

        if (allSelected.size() != 6) return false;

        for (int i = 0; i < allSelected.size(); i++) {
            boolean valid = false;
            for (int j = 0; j < allSelected.size(); j++) {
                if (allSelected.get(i).adjacentTo(allSelected.get(j))) {
                    valid = true;
                    break;
                }
            }
            if (!valid) return false;
        }

        Piece temp = new Piece(allSelected.get(0));
        for (int i = 1; i < allSelected.size(); i++) {
            temp.addTile(new Tile(allSelected.get(i).subtract(allSelected.get(0))));
        }
        if (!level.getBoard().isPlacementValid(temp)) return false;
        return true;
    }

    boolean redoValid(Level level) {
        return pieceToPlace != null && level.getBoard().isPlacementValid(pieceToPlace);

    }

    @Override
    /**
	 * Attempts to complete this move within the given level
	 * @return true if move is successfully completed, false otherwise
	 */
    public boolean doMove(Level level) {
        if (pieceToPlace == null) {
            if (!valid(level)) return false;
            pieceToPlace = new Piece(allSelected.get(0));
            pieceToPlaceInBullpen = new Piece(allSelected.get(0));
            for (int i = 1; i < allSelected.size(); i++) {
                pieceToPlace.addTile(new Tile(allSelected.get(i).subtract(allSelected.get(0))));
                pieceToPlaceInBullpen.addTile(new Tile(allSelected.get(i).subtract(allSelected.get(0))));
            }
        } else if (!redoValid(level)) return false;
        try {
            level.getBoard().placePiece(pieceToPlace);
            level.getBullpen().addPiece(pieceToPlaceInBullpen);
            manager.clearSelectedPoints();
        } catch (InvalidPiecePlacementException e) {
            System.err.println("MakePieceBuilderMove:: Making a piece failed");
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    /**
	 * Attempts to complete this move within the given level
	 * @return true if move is successfully undone, false otherwise
	 */
    public boolean undo(Level level) {
        level.getBoard().removePiece(pieceToPlace);
        level.getBullpen().removePiece(pieceToPlaceInBullpen);
        manager.setSelectedPoints(allSelected);
        return true;
    }
}
