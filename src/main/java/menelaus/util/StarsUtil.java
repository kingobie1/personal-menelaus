package menelaus.util;

import java.util.Hashtable;

import menelaus.model.GameManager;
import menelaus.model.LevelStars;
import menelaus.model.basic.Color;
import menelaus.model.board.Board;
import menelaus.model.board.BoardTileInfo;
import menelaus.model.board.ColoredSetItem;

/**
 * Grades a GameManager in progress and awards LevelStars depending on the Level and its progress.
 * @author vouldjeff
 *
 */
public class StarsUtil {
	/**
	 * Returns the stars for a particular level in progress.
	 * @param gameManager The Game
	 * @return Awarded stars 0-3 with the level id
	 */
	public static LevelStars getStars(GameManager gameManager) {
		switch (gameManager.getLevel().getType()) {
		case LIGHTNING:
			return gradeLightning(gameManager);
		case RELEASE:
			return gradeRelease(gameManager);
		default:
			return gradePuzzle(gameManager);
		}
	}
	
	/**
	 * Grades a PuzzleLevel.
	 * @param gameManager The manager.
	 * @return The object with LevelStars.
	 */
	static LevelStars gradePuzzle(GameManager gameManager){
		int stars = 0;
		int spacesLeft = gameManager.getLevel().getBoard().getNumberOfEmptyTiles();
		
		if (spacesLeft == 0) {
			stars = 3;
		} else if (spacesLeft <= 6) {
			stars = 2;
		} else if (spacesLeft <= 12) {
			stars = 1;
		}
		
		return new LevelStars(stars, gameManager.getLevel().getUuid());
	}
	
	/**
	 * Grades a Release Level.
	 * @param gameManager The manager.
	 * @return The object with LevelStars.
	 */
	static LevelStars gradeRelease(GameManager gameManager) {
		int completeSets = 0;
		for (Color color : Color.values()) {
			if (isSetComplete(color, gameManager.getLevel().getBoard())) {
				completeSets++;
			}
		}
		
		if (completeSets > 3) {
			completeSets = 3;
		}
		
		return new LevelStars(completeSets, gameManager.getLevel().getUuid());
	}
	
	/**
	 * Checks if colored set is complete.
	 * @param color For what color.
	 * @param board For which board.
	 * @return Is the set complete?
	 */
	static boolean isSetComplete(Color color, Board board) {
		Hashtable<Integer, Boolean> filledIn = new Hashtable<Integer, Boolean>();
		
		for (BoardTileInfo info : board.getTileInfo().values()) {
			ColoredSetItem item = info.getColoredSetItem();
			if (item == null || item.getColor() != color || info.getPiecePlaced() == null) {
				continue;
			}
			
			filledIn.put(item.getNumber(), true);
		}
		
		for (int i = 1; i <= 6; i++) {
			if (!filledIn.containsKey(i)) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Grades a Lightning level.
	 * @param gameManager The manager.
	 * @return The LevelStars object.
	 */
	static LevelStars gradeLightning(GameManager gameManager) {
		int emptyTiles = gameManager.getLevel().getBoard().getNumberOfEmptyTiles();
		int stars = 0;
		
		if (emptyTiles == 0) {
			stars = 3;
		} else if (emptyTiles <= 6) {
			stars = 2;
		} else if (emptyTiles <= 12) {
			stars = 1;
		}
		
		return new LevelStars(stars, gameManager.getLevel().getUuid());
	}
}
