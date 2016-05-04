package menelaus.model;

import menelaus.model.basic.LevelType;
import menelaus.model.board.Board;
import menelaus.model.board.Piece;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Represents a Level that could be later played -- it has a Board, Bullpen and game constraints.
 * However, a game in progress is always managed by the GameManager and that is where the progress state is kept.
 * @author vouldjeff
 *
 */
public class Level implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * The unique id of the level.
	 */
	private UUID uuid;
	
	/**
	 * The name of the level.
	 */
	private String name;
	
	/**
	 * The Level type.
	 */
	private LevelType type;
	
	/**
	 * The maximum number of moves.
	 */
	private int moveLimit;
	
	/**
	 * The maximum time allowed to complete the move.
	 */
	private int timeLimit;
	
	/**
	 * The Board with chopped pieces and numbers.
	 */
	private Board board;
	
	/**
	 * The bullpen with all possible pieces.
	 */
	private Bullpen bullpen;
	
	/**
	 * A selected piece.
	 */
	private Piece selected;
	
	/**
	 * An active piece.
	 */
    private Piece active;
	
    /**
     * Gets the unique LevelID.
     * @return A UUID object
     */
	public UUID getUuid() {
		return uuid;
	}

	/**
	 * Returns the name of the level.
	 * @return The name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name of the level.
	 * @param name The new name.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the level type.
	 * @return A LevelType object.
	 */
	public LevelType getType() {
		return type;
	}
	
	/**
	 * Returns the max number of moves allowed in for the level.
	 * @return The number.
	 */
	public int getMoveLimit() {
		return moveLimit;
	}
	
	/**
	 * Sets a maximum number of moves.
	 * @param moveLimit The new limit.
	 */
	public void setMoveLimit(int moveLimit) {
		this.moveLimit = moveLimit;
	}
	
	/**
	 * Returns the maximum time to complete the level.
	 * @return The seconds.
	 */
	public int getTimeLimit() {
		return timeLimit;
	}
	
	/**
	 * Sets a max time allowed to complete the level.
	 * @param timeLimit The new limit.
	 */
	public void setTimeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
	}
	
	/**
	 * Returns the board information.
	 * @return The Board object.
	 */
	public Board getBoard() {
		return board;
	}
	
	/**
	 * Returns the bullpen information.
	 * @return The Bullpen object.
	 */
	public Bullpen getBullpen() {
		return bullpen;
	}
	
	/**
	 * Creates a new Level with a new Board with given dimensions and LevelType.
	 * @param type The type of the level.
	 * @param boardHeight The Board height.
	 * @param boardWidth The Board width.
	 */
	public Level(LevelType type, int boardHeight, int boardWidth) {
		super();
		this.moveLimit = 0;
		this.timeLimit = 0;
		this.type = type;
		this.uuid = UUID.randomUUID();
		this.bullpen = new Bullpen();
		this.board = new Board(boardHeight, boardWidth);
	}
	
	/**
	 * Returns all pieces back from board to bullpen.
	 */
	public void resetLevel() {
		if (type != LevelType.LIGHTNING) {
			for (Piece p : this.board.getPieces()){
				this.getBullpen().addPiece(p);
			}
		}
		
		this.getBoard().getPieces().clear();
		this.getBoard().resetBoard();
		this.selected = null;
		this.active = null;
	}

	/**
	 * Sets a selected Piece for drag and other functionality.
	 * @param p The piece.
	 */
	public void setSelected(Piece p) {
		selected = p;
	}

	/**
	 * Currently selected piece (or null if none). When a shape is
	 * selected, it is no longer part of the Model.
	 * @return The current selected piece
	 */
	public Piece getSelected() {
		return selected;
	}

	/**
	 * Sets the active piece.
	 * @param active The piece.
	 */
    public void setActive(Piece active) {
        this.active = active;
    }

    /**
     * Currently actively dragged piece (or null if none). When a shape is
     * selected, it is no longer part of the Model.
     * @return The current active piece
     */
    public Piece getActive() {
        return active;
    }
    
    /**
     * Changes the type of level. Should be removed.
     * @param type
     */
    public void setType(LevelType type) {
    	this.type = type;
    }
}
