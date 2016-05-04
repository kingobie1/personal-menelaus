package menelaus.model.move;

import java.util.ArrayList;
import java.util.Hashtable;

import menelaus.model.BuilderManager;
import menelaus.model.Level;
import menelaus.model.basic.Point;
import menelaus.model.board.BoardTileInfo;
/**
 * This move is for selecting squares.
 * @author sanjay
 *
 */
public class SelectSquareMove extends BuilderMove {

	Point selectedPoint;
	/**
	 * Constructor
	 * @param manager
	 * @param xToSelect
	 * @param yToSelect
	 */
	public SelectSquareMove(BuilderManager manager, int xToSelect, int yToSelect) {
		super(manager);
		this.manager = manager;
		this.selectedPoint = new Point(xToSelect, yToSelect);
	}
	
	@Override
	public boolean undo(Level level) {
		this.manager.deselectPoint(selectedPoint); //deselect the point
		return true;
	}

	@Override
	public boolean doMove(Level level) {
		//Don't do the move if invalid
		if(!valid(level)) return false;
		return this.manager.selectPoint(selectedPoint); //If it's valid, select the point
	}

	@Override
	public boolean valid(Level level) {
		//Is not valid if you've already selected 6 tiles, or if there's a piece on top of selection.
		
		ArrayList<Point> allSelected = this.manager.getSelectedPoints();
		if (allSelected.size() >= 6) return false; //You can't select more than 6 pieces
		
		Hashtable<Point, BoardTileInfo> info = level.getBoard().getTileInfo();
		if(info.containsKey(selectedPoint)) {
			if (!(info.get(selectedPoint).getPiecePlaced() == null))
				return false;
		}
		
		if(allSelected.contains(this.selectedPoint)) return false;
		
		return true;
		/*
		if (allSelected.size() == 0) return true;
		
		boolean isValid = false;
		for(int i = 0; i < allSelected.size(); i++) {
			if (allSelected.get(i).adjacentTo(selectedPoint))
			{
				isValid = true;
				break;
			}
		}
		return isValid;*/
	}
	
	 

}
