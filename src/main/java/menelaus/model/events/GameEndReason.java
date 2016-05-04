package menelaus.model.events;

/**
 * Reasons for ending a game
 * @author vouldjeff
 *
 */
public enum GameEndReason {
	/**
	 * User has one the game
	 */
	WON, 
	/**
	 * User has reached the maximum amount of moves and has no moves left
	 */
	NO_MOVES, 
	/**
	 * User has reached the maximum time limit on a lighting level
	 */
	NO_TIME, 
	/**
	 * User has volunteered to end the game
	 */
	USER, 
	/**
	 * User has requested to restart the game
	 */
	RESTART
}
