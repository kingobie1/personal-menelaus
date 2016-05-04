package menelaus.model.move;

import java.util.Random;

import menelaus.model.Level;
import menelaus.model.basic.LevelType;
import menelaus.model.basic.Point;
import menelaus.model.board.InvalidPiecePlacementException;
import menelaus.model.board.Piece;
import menelaus.util.PieceBank;

/**
 * Covers part of the Board during a Lightning level.
 * @author vouldjeff
 */
public class ToBoardCoverMove extends Move {
	Point location;
	/**
	 * Constructor
	 * @param piece
	 * @param location
	 */
	public ToBoardCoverMove(Piece piece, Point location) {
		super(piece);
		this.location = location;
		this.alterMoveCount = true;
	}

	/**
	 * Perform the Move. Always let the GameManager call this method.
	 */
	@Override
	public boolean doMove(Level level) {
		if (!valid(level)) {
			return false;
		}
		
		try {
			level.getBoard().coverWithPiece(piece);
			level.getBullpen().removePiece(piece);
			level.getBullpen().addPiece(new Piece(PieceBank.getPiece(new Random().nextInt(35) + 1)));
			return true;
		} catch (InvalidPiecePlacementException e) {
			return false;
		}
	}

	@Override
	public boolean valid(Level level) {
		return level.getType() == LevelType.LIGHTNING && level.getBoard().isPointWithinBoundary(location) && level.getBullpen().getPieces().contains(piece);
	}
}
