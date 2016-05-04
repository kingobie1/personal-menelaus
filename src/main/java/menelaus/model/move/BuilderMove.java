package menelaus.model.move;

import menelaus.model.BuilderManager;
import menelaus.model.Level;
/**
 * An abstract class used for all builder moves
 * All classes must be able to validate, complete and undo moves
 *
 *
 */
public abstract class BuilderMove {
	BuilderManager manager;
	/**
	 * constructor
	 * @param manager
	 */
	public BuilderMove(BuilderManager manager) {
		this.manager = manager;
	}
	/**
	 * Determines whether a move is valid within the given level
	 * @param level
	 * @return true if the move is valid, false otherwise
	 */
	public abstract boolean valid(Level level);
	/**
	 * Attempts to complete the move with the underlying logic within the given level
	 * @param level
	 * @return true if move was successfully completed, false otherwise
	 */
	public abstract boolean doMove(Level level);
	/**
	 * Attempts to undo the move most recently completed with the underlying logic within the given level
	 * @param level
	 * @return true if move was successfully undone, false otherwise
	 */
	public abstract boolean undo(Level level);

}
