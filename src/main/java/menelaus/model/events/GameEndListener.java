package menelaus.model.events;

/**
 * Interface used for ending games
 * @author vouldjeff
 *
 */
public interface GameEndListener {
	/**
	 * Ends the game with the given reason
	 * @param reason
	 * 
	 */
	void end(GameEndReason reason);
}
