package menelaus.model.move;

import menelaus.model.BuilderManager;
import menelaus.model.Level;
import menelaus.model.basic.Point;
/**
 * Contains the necessary information to deselect a square from the builder
 * 
 *
 */
public class DeselectSquareBuilderMove extends BuilderMove {

	Point selectedPoint;
	/**
	 * Constructor
	 * @param manager
	 * @param xToDeselect
	 * @param yToDeselect
	 */
	public DeselectSquareBuilderMove(BuilderManager manager, int xToDeselect, int yToDeselect) {
		super(manager);
		this.manager = manager;
		this.selectedPoint = new Point(xToDeselect, yToDeselect);
	}
	
	@Override
	/**
	 * Attempts to complete this move within the given level
	 * @return true if move is successfully undone, false otherwise
	 */
	public boolean undo(Level level) {
		this.manager.selectPoint(selectedPoint); //deselect the point
		return true;
	}

	@Override
	/**
	 * Attempts to complete this move within the given level
	 * @return true if move is successfully completed, false otherwise
	 */
	public boolean doMove(Level level) {
		//Don't do the move if invalid
		if(!valid(level)) return false;
		return this.manager.deselectPoint(selectedPoint); //If it's valid, select the point
	}

	@Override
	/**
	 * Determines whether this move is valid within the given level
	 * @return true if move is valid, false otherwise
	 */
	public boolean valid(Level level) {
		if(this.manager.getSelectedPoints().contains(selectedPoint)) {
			return true;
		}
		return false;
	}

}
