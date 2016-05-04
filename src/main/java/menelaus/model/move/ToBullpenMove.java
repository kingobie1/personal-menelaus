package menelaus.model.move;

import menelaus.model.Level;
import menelaus.model.board.Piece;

/**
 * Moves back a Piece from the Board to the Bullpen.
 * @author vouldjeff
 */
public class ToBullpenMove extends Move {
	/**
	 * Constructor
	 * @param piece
	 */
    public ToBullpenMove(Piece piece) {
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

        level.getBullpen().addPiece(piece);
        level.getBoard().removePiece(piece);
        return true;
    }

    @Override
    public boolean valid(Level level) {
		return piece != null && level.getBoard().getPieces().contains(piece);
    }
}
