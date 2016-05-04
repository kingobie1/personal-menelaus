package menelaus.model.board;

import menelaus.model.basic.Point;

/**
 * Represent hints on the board.
 * @author vouldjeff
 *
 */
public class HintPiece extends Piece {
	private static final long serialVersionUID = 1L;

	/**
	 * Build a new hint with particular position.
	 * @param position The position.
	 */
	public HintPiece(Point position) {
		super(position);
	}

}
