package menelaus.model.move;

import menelaus.model.Level;
import menelaus.model.board.Piece;

/**
 * Flips a Piece which is in the Bullpen.
 * @author vouldjeff
 */
public class FlipPieceMove extends Move {
	/**
	 * Constructor
	 * @param piece
	 */
	public FlipPieceMove(Piece piece) {
		super(piece);
	}

	@Override
	/**
	 * Attempts to complete this move within the given level
	 * @return true if move is successfully completed, false otherwise
	 */
	public boolean doMove(Level level) {
		if (!valid(level)) {
			return false;
		}
		
		piece.flip();
		return true;
	}

	@Override
	/**
	 * Determines whether this move is valid within the given level
	 * @return true if move is valid, false otherwise
	 */
	public boolean valid(Level level) {
		return piece != null && level.getBullpen().getPieces().contains(piece);
	}
}
