package menelaus.model.move;

import java.util.Hashtable;

import menelaus.model.BuilderManager;
import menelaus.model.Level;
import menelaus.model.basic.Point;
import menelaus.model.board.BoardTileInfo;
import menelaus.model.board.ColoredSetItem;
/**
 * Contains the necessary information for placing a number tile in a released level
 * 
 *
 */
public class PlaceReleaseNumberBuilderMove extends BuilderMove {

	ColoredSetItem newValue;
	ColoredSetItem oldValue;
	Point pointToPlaceAt;
	/**
	 * Constructor
	 * @param manager
	 * @param x
	 * @param y
	 * @param number
	 */
	public PlaceReleaseNumberBuilderMove(BuilderManager manager, int x, int y, ColoredSetItem number) {
		super(manager);
		this.manager = manager;
		this.pointToPlaceAt = new Point(x, y);
		Hashtable<Point, BoardTileInfo> tileInfo = manager.getLevel().getBoard().getTileInfo();
		if(tileInfo.containsKey(this.pointToPlaceAt)) {
			ColoredSetItem temp = tileInfo.get(this.pointToPlaceAt).getColoredSetItem();
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
		this.newValue = number;
	}

	@Override
	public boolean valid(Level level) {
		Hashtable<Point, BoardTileInfo> tileInfo = level.getBoard().getTileInfo();
		if(!tileInfo.containsKey(this.pointToPlaceAt)) return true;
		return !tileInfo.get(this.pointToPlaceAt).isTileChopped();
	}

	@Override
	public boolean doMove(Level level) {
		if(!valid(level)) return false;
		Hashtable<Point, BoardTileInfo> tileInfo = level.getBoard().getTileInfo();
		BoardTileInfo currTileInfo;
		if(tileInfo.containsKey(this.pointToPlaceAt)) {
			currTileInfo = tileInfo.get(this.pointToPlaceAt);
		}
		else {
			currTileInfo = new BoardTileInfo(false);
		}
		currTileInfo.setColoredSetItem(this.newValue);
		tileInfo.put(this.pointToPlaceAt, currTileInfo);
		return true;
	}

	@Override
	public boolean undo(Level level) {
		Hashtable<Point, BoardTileInfo> tileInfo = level.getBoard().getTileInfo();
		BoardTileInfo currTileInfo;
		if(tileInfo.containsKey(this.pointToPlaceAt)) {
			currTileInfo = tileInfo.get(this.pointToPlaceAt);
		}
		else {
			currTileInfo = new BoardTileInfo(false);
		}
		currTileInfo.setColoredSetItem(this.oldValue);
		tileInfo.put(this.pointToPlaceAt, currTileInfo);
		return true;
	}

}
