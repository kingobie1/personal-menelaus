package menelaus.model;

import menelaus.model.events.GameEndListener;
import menelaus.model.events.GameEndReason;
import menelaus.model.events.GameTickListener;
import menelaus.model.move.Move;
import menelaus.util.StarsUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
 * This class manages a Level which is played. Tracks time, executes moves and notifies
 * whenever a game has ended. Use this class to modify the game environment.
 * @author vouldjeff
 *
 */
public class GameManager {
	private static final int TICK_TIME = 1000;
	
	/**
	 * Use to track time passed during game.
	 */
	private Timer timer;
	
	/**
	 * Holds the Level that this GameManager operates on.
	 */
	private Level level;
	
	/**
	 * This collection holds all moves of pieces. If undo functionality is needed just revert objects inside.
	 */
	private Deque<Move> moves;
	
	/**
	 * A count of the moves made during the game.
	 */
	private int movesMade;
	
	/**
	 * Keeps time of seconds passed.
	 */
	private int timePassed;
	
	/**
	 * The current state of the game and stars achieved by player.
	 */
	private LevelStars levelStars;

	private ArrayList<GameTickListener> tickListeners = new ArrayList<GameTickListener>();
	private ArrayList<GameEndListener> endListeners = new ArrayList<GameEndListener>();
	
	/**
	 * Tells whether the game is in progress.
	 */
	private boolean isRunning;
	
	/**
	 * The Level which is played.
	 * @return The current level being played
	 */
	public Level getLevel() {
		return level;
	}
	
	/**
	 * Returns the moves made during the game.
	 * @return Number of moves.
	 */
	public int getMovesMade() {
		return movesMade;
	}
	
	/**
	 * Returns the achieved stars.
	 * @return The LevelStars object.
	 */
	public LevelStars getLevelStars() {
		return levelStars;
	}

	/**
	 * Initializes a new object with timer setup.
	 * @param levelParam The level which is going to be played.
	 */
	public GameManager(Level levelParam) {
		super();
		this.level = levelParam;
		this.isRunning = false;
		this.levelStars = new LevelStars(0, levelParam.getUuid());
		final GameManager _this = this;
		
		timer = new Timer(TICK_TIME, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timePassed++;
				levelStars = StarsUtil.getStars(_this);
				
				if (level.getTimeLimit() > 0 && timePassed >= level.getTimeLimit()) {
					stopGame();
					notifyEndListeners(GameEndReason.NO_TIME);
				} else {
					notifyTickListeners();
				}
			}
		});
	}
	
	/**
	 * Returns the number of seconds since the start of the game.
	 * @return Seconds elapsed.
	 */
	public int getTimePassed() {
		return timePassed;
	}
	
	/**
	 * Register a listener for whenever the Game is won/finished.
	 * @param listener
	 */
	public void addGameEndListener(GameEndListener listener) {
        endListeners.add(listener);
    }
	
	/**
	 * Register a listener for the Timer ticking.
	 * @param listener
	 */
	public void addGameTickListener(GameTickListener listener) {
        tickListeners.add(listener);
    }
	
	/**
	 * Always allow GameManager to perform the moves. This method runs the move, checks the progress
	 * on the actual level, generates Stars and notifies if game is won.
	 * @param move The move to be performed.
	 */
	public void performNewMove(Move move) {
		if (!isRunning) {
			throw new IllegalStateException("game is not running");
		}
		
		moves.add(move);
		boolean successfulMove = move.doMove(this.level);
		if (!successfulMove) {
			return;
		}
		
		if (move.shouldAlterMoveCount()) {
			movesMade++;
		}
		
		levelStars = StarsUtil.getStars(this);
		
		if (levelStars.getStarsCount() == 3 || level.getBoard().isFull()) {
			stopGame();
			notifyEndListeners(GameEndReason.WON);
			return;
		}
		
		if (level.getMoveLimit() > 0 && movesMade >= level.getMoveLimit()) {
			stopGame();
			notifyEndListeners(GameEndReason.NO_MOVES);
		}
	}
	
	/**
	 * Call whenever the game starts. Starts timer.
	 */
	public void startGame() {
		this.level.resetLevel();
		this.movesMade = 0;
		this.timePassed = 0;
		this.moves = new ArrayDeque<Move>();
		this.isRunning = true;
	
		timer.start();
	}
	
	/**
	 * Call this method if Player wants to end game earlier. It stops timer.
	 */
	public void userEndsGame() {
		stopGame();
		notifyEndListeners(GameEndReason.USER);
	}
	
	/**
	 * Call this method if Player wants to end game earlier. It stops timer.
	 */
	public void userRestartsGame() {
		stopGame();
		notifyEndListeners(GameEndReason.RESTART);
	}
	
	private void notifyTickListeners() {
		for (GameTickListener listener : tickListeners) {
			listener.tick();
		}
	}
	
	private void notifyEndListeners(GameEndReason reason) {
		for (GameEndListener listener : endListeners) {
			listener.end(reason);
		}
	}

	private void stopGame() {
		this.isRunning = false;
		timer.stop();
	}
}
