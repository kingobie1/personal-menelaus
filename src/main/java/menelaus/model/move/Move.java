package menelaus.model.move;

import menelaus.model.Level;
import menelaus.model.board.Piece;

/**
 * Base class for Moves.
 * @author vouldjeff
 */
public abstract class Move {
	
	/**
	 * The Piece that is involved.
	 */
    protected Piece piece;
    
    /**
     * Does this move influence the number of moves left.
     */
    protected boolean alterMoveCount;

    /**
     * Returns the Piece.
     * @return The object.
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * Sets the Piece.
     * @param piece The new object.
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    /**
     * Returns if the move should change the number of moves left.
     * @return baby boolean.
     */
    public boolean shouldAlterMoveCount() {
        return alterMoveCount;
    }

    /**
     * Execute the move. Always call through the GameManager.
     * @param level The level to perform the move on.
     * @return True if the move was valid.
     */
    public abstract boolean doMove(Level level);

    /**
     * Returns if the move is valid.
     * @param level The level to perform the move on.
     * @return True if valid.
     */
    public abstract boolean valid(Level level);

    /**
     * Constructs a new move.
     * @param piece The piece.
     */
    public Move(Piece piece) {
        this.piece = piece;
        this.alterMoveCount = false;
    }
}
