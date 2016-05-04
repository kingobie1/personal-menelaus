package menelaus.model.move;

import java.util.Hashtable;

import menelaus.model.BuilderManager;
import menelaus.model.Level;
import menelaus.model.basic.Point;
import menelaus.model.board.BoardTileInfo;
import menelaus.model.board.ColoredSetItem;
/**
 * A builder move which contains information necessary for deleting a number from a release level
 * 
 *
 */
public class DeleteReleaseNumberBuilderMove extends BuilderMove {

	ColoredSetItem oldValue;
	Point pointToDeleteAt;
	/**
	 * constructor
	 * @param manager
	 * @param x
	 * @param y
	 */
	public DeleteReleaseNumberBuilderMove(BuilderManager manager, int x, int y) {
		super(manager);
		this.manager = manager;
		this.pointToDeleteAt = new Point(x, y);
		Hashtable<Point, BoardTileInfo> tileInfo = manager.getLevel().getBoard().getTileInfo();
		if(tileInfo.containsKey(this.pointToDeleteAt)) {
			ColoredSetItem temp = tileInfo.get(this.pointToDeleteAt).getColoredSetItem();
			if(temp == null) {
				this.oldValue = null;
			}
			else {
				this.oldValue = temp.duplicate();
			}
		}
		else {
			this.oldValue = null;
		}
	}

	@Override
	/**
	 * Determines whether this move is valid within the given level
	 * @return true if move is valid, false otherwise
	 */
	public boolean valid(Level level) {
		Hashtable<Point, BoardTileInfo> tileInfo = level.getBoard().getTileInfo();
		if(!tileInfo.containsKey(this.pointToDeleteAt)) return false;
		else {
			return tileInfo.get(this.pointToDeleteAt).getColoredSetItem() != null;
		}
	}

	@Override
	/**
	 * Attempts to complete this move within the given level
	 * @return true if move is successfully completed, false otherwise
	 */
	public boolean doMove(Level level) {
		if(!valid(level)) return false;
		Hashtable<Point, BoardTileInfo> tileInfo = level.getBoard().getTileInfo();
		BoardTileInfo currTileInfo;
		if(tileInfo.containsKey(this.pointToDeleteAt)) {
			currTileInfo = tileInfo.get(this.pointToDeleteAt);
		}
		else {
			currTileInfo = new BoardTileInfo(false);
		}
		currTileInfo.setColoredSetItem(null);
		tileInfo.put(this.pointToDeleteAt, currTileInfo);
		return true;
	}

	@Override
	/**
	 * Attempts to complete this move within the given level
	 * @return true if move is successfully undone, false otherwise
	 */
	public boolean undo(Level level) {
		Hashtable<Point, BoardTileInfo> tileInfo = level.getBoard().getTileInfo();
		BoardTileInfo currTileInfo;
		if(tileInfo.containsKey(this.pointToDeleteAt)) {
			currTileInfo = tileInfo.get(this.pointToDeleteAt);
		}
		else {
			currTileInfo = new BoardTileInfo(false);
		}
		currTileInfo.setColoredSetItem(this.oldValue);
		tileInfo.put(this.pointToDeleteAt, currTileInfo);
		return true;
	}
}
