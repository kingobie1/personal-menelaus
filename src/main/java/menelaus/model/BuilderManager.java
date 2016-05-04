package menelaus.model;

import menelaus.model.basic.LevelType;
import menelaus.model.basic.Point;
import menelaus.model.board.Board;
import menelaus.model.board.ColoredSetItem;
import menelaus.model.move.BuilderMove;
import menelaus.util.LevelsPackagePersistenceUtil;
import menelaus.view.builder.BuilderLevelBuilderScreen;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

/** 
 * Manages a level builder.
 * @author sanjay
 *
 */
public class BuilderManager {
	Level currentProject;
	Stack<BuilderMove> moves;
	Stack<BuilderMove> redoMoves; 
	ArrayList<Point> selectedPoints;
	boolean isPlacingRelease;
	ColoredSetItem releaseItem;
	/**
	 * The Default Level Type to be chosen if LevelType is null
	 */
	public final LevelType DEFAULT_LEVEL = LevelType.PUZZLE;
	/**
	 * The Default width to be chosen if width is null
	 */
	public final int DEFAULT_WIDTH = 6;
	/**
	 * The Default height to be chosen if height is null
	 */
	public final int DEFAULT_HEIGHT = 6;
	/**
	 * Constructor
	 */
	public BuilderManager() {
		this.currentProject = new Level(DEFAULT_LEVEL, DEFAULT_HEIGHT, DEFAULT_WIDTH);
		this.selectedPoints = new ArrayList<Point>();
		this.moves = new Stack<BuilderMove>();
		this.redoMoves = new Stack<BuilderMove>();
	}
	/**
	 * Gets the name of the current level
	 * @return Name of current level
	 */
	public String getName() {
		return this.currentProject.getName();
	}
	/**
	 * Gets the current level
	 * @return Current level
	 */
	public Level getLevel() {
		return this.currentProject;
	}
	/**
	 * Gets the type of the current level
	 * @return Type of Level
	 */
	public LevelType getType() {
		return this.currentProject.getType();
	}
	/**
	 * Gets the width of the current level
	 * @return width of level
	 */
	public int getWidth() {
		return this.currentProject.getBoard().getWidth();
	}
	/**
	 * Gets the height of the current level
	 * @return height of current level
	 */
	public int getHeight() {
		return this.currentProject.getBoard().getHeight();
	}
	/**
	 * Sets the name of the current level to the given name
	 * @param name
	 */
	public void setLevelName(String name) {
		currentProject.setName(name);
	}
	/**
	 * @param type
	 */
	/**
	 * @param type
	 */
	/**
	 * Sets the LevelType of the current level to the given LevelType
	 * @param type
	 * 
	 */
	public void setLevelType(LevelType type) {
		this.currentProject.setType(type);
	}
	
	/**
	 * Sets this Builder Manager's current level to a new level with given name type width and height
	 * @param name
	 * @param type
	 * @param w
	 * @param h
	 */
	public void createLevel(String name, LevelType type, int w, int h) {
		currentProject = new Level(type, h, w);
		setLevelName(name);
	}
	
	/**
	 * Sets the size of the board of the current level to the given width and height
	 * @param w
	 * @param h
	 */
	public void setSize(int w, int h) {
		this.currentProject.getBoard().setWidth(w);
		this.currentProject.getBoard().setHeight(h);
	}
	/**
	 * Attempts to make a move on this manager with the given Builder Move
	 * @param m
	 * @return true if move was successfully created, false otherwise
	 */
	public boolean makeMove(BuilderMove m) {
		if (m.valid(currentProject)) {
			m.doMove(currentProject);
			moves.push(m);
			return true;
		}
		return false;
	}
	/**
	 * Attempts to make the given move on the builder the clears the redoMoves field so that no moves can be redone
	 * @param m
	 * @return true if move successfully created and redoMoves is cleared, false otherwise
	 */
	public boolean makeMoveAndClear(BuilderMove m) {
		if (makeMove(m)) {
			redoMoves.clear(); 
			//Just made a move, changing the board. So, any former redos are invalid.
			return true;
		}
		return false;
	}
	/**
	 * Attempts to undo the most recent move
	 * @return true if successfully undone, false otherwise
	 */
	public boolean undo() {
		if(moves.isEmpty()) return false;
		BuilderMove move = moves.pop();
		if (move.undo(currentProject)) {
			redoMoves.push(move);
			return true; //Just pass on the return if the undo works.
		}
		else {
			moves.push(move); //Push it back onto the stack because it was unsuccessful.
			return false;
		}
	}
	
	/**
	 * Attempts to redo the most recently undone move
	 * @return true if successfully redone, false otherwise
	 */
	public boolean redo() {
		if (!redoMoves.empty()) {
			BuilderMove move = redoMoves.pop();
			if ( makeMove(move)) {
				return true;
			}
			else {
				redoMoves.push(move);
			}
		}
		return false;
	}
	/**
	 * Selects given point by adding the given point to the selectedPoints field
	 * @param x
	 * @return true if successfully added, false otherwise
	 */
	public boolean selectPoint(Point x) {
		return this.selectedPoints.add(x);
	}
	/**
	 * Deselects given point by removing the given point to the selectedPoints field
	 * @param x
	 * @return true if successfully removed, false otherwise
	 */
	public boolean deselectPoint(Point x) {
		return this.selectedPoints.remove(x);
	}
	/**
	 * Deselects point at given by removing the given point at the index within selectedPoints
	 * @param index
	 * @return true if successully removed, false otherwise
	 */
	public boolean deselectPointByIndex(int index) {
		if (index < this.selectedPoints.size()) {
			this.selectedPoints.remove(index);
			return true;
		}
		return false;
	}
	/**
	 * Determines whether this buildermanager is placing a released tile
	 * @return true if placing a released tile, false otherwise
	 */
	public boolean getIsReleaseMode() {
		return this.isPlacingRelease;
	}
	/**
	 * Returns the released item held
	 * @return the ColoredSetItem being used for relase
	 */
	public ColoredSetItem getReleaseItem() {
		return this.releaseItem;
	}
	/**
	 * Sets the released item held to the given ColoredSet item
	 * @param item
	 */
	public void setReleaseItem(ColoredSetItem item) {
		this.releaseItem = item;
	}
	/**
	 * Sets the release item and trigger PlacingReleased flag to indicate that it is currently attempting to place a released tile
	 * @param s
	 */
	public void setToReleaseMode(ColoredSetItem s) {
		this.releaseItem = s;
		this.isPlacingRelease = true;
	}
	/**
	 * Sets the isPlacingReleased trigger to false and empty's the current relase item indicating that it is no longer attmpeting to place a relased item
	 */
	public void setToBoardMode() {
		this.releaseItem = null;
		this.isPlacingRelease = false;
	}
	/**
	 * Returns the number of selected points stored in the field
	 * @return the number of selected points
	 */
	public int getNumSelectedPoints() {
		return this.selectedPoints.size();
	}
	/**
	 * Gets the current selected points 
	 * @return current selected points
	 */
	public ArrayList<Point> getSelectedPoints() {
		return this.selectedPoints;
	}
	/**
	 * Clears the selected points in this manager so that no points are selected
	 */
	public void clearSelectedPoints() {
		this.selectedPoints.clear();
	}
	/**
	 * Sets selected points by clearing current selected points and refilling points with the given points
	 * @param backup
	 */
	public void setSelectedPoints(ArrayList<Point> backup) {
		clearSelectedPoints();
		for(int i = 0; i < backup.size(); i++) {
			this.selectedPoints.add(new Point(backup.get(i).getX(), backup.get(i).getY()));
		}
	}
	
	/**
	 * Clean the level up before saving it to make it playable.
	 * 
	 */
	public void cleanUpLevel() {
		Board theBoard = this.currentProject.getBoard();
		while(theBoard.getPieces().size() > 0) {
			theBoard.removePiece(theBoard.getPieces().get(0)); //Remove all the pieces from the board. 
			// They're still in the bullpen.
		}
	}
}
