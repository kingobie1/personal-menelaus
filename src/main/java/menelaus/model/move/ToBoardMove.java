package menelaus.model.move;

import menelaus.model.Level;
import menelaus.model.basic.LevelType;
import menelaus.model.basic.Point;
import menelaus.model.board.InvalidPiecePlacementException;
import menelaus.model.board.Piece;

/**
 * Places a Piece from the Bullpen to the Board.
 * @author vouldjeff
 */
public class ToBoardMove extends Move {
	Point location;
	/**
	 * Constructor
	 * @param piece
	 * @param location
	 */
	public ToBoardMove(Piece piece, Point location) {
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
			level.getBoard().placePiece(piece);
			level.getBullpen().removePiece(piece);
			return true;
		} catch (InvalidPiecePlacementException e) {
			return false;
		}
	}

	@Override
	public boolean valid(Level level) {
		return level.getType() != LevelType.LIGHTNING && level.getBoard().isPointWithinBoundary(location) && level.getBullpen().getPieces().contains(piece);
	}
}
