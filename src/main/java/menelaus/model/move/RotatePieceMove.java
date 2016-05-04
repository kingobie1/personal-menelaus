package menelaus.model.move;

import menelaus.model.Level;
import menelaus.model.board.Piece;

/**
 * Rotates a Piece which is in the Bullpen.
 * @author vouldjeff
 */
public class RotatePieceMove extends Move {
	/**
	 * Constructor
	 * @param piece
	 */
	public RotatePieceMove(Piece piece) {
		super(piece);
	}

	/**
	 * Perform the Move. Always let the GameManager call this method.
	 */
	@Override
	public boolean doMove(Level level) {
		if (!valid(level)) {
			return false;
		}
		
		piece.rotate();
		return true;
	}

	@Override
	public boolean valid(Level level) {
		return piece != null && level.getBullpen().getPieces().contains(piece);
	}
}
