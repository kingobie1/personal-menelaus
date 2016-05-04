package menelaus.util;

import menelaus.model.GameManager;
import menelaus.model.Level;
import menelaus.model.LevelStars;
import menelaus.model.basic.Color;
import menelaus.model.basic.LevelType;
import menelaus.model.basic.Point;
import menelaus.model.board.ColoredSetItem;
import menelaus.model.board.InvalidPiecePlacementException;
import menelaus.model.board.Piece;
import menelaus.model.board.Tile;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StarsUtilTest {
	private Level level;

	@Before
	public void setUp() throws Exception {
		Piece piece1 = new Piece(new Point(0, 0));
		piece1.addTile(new Tile(0, 0));
		piece1.addTile(new Tile(0, 1));
		piece1.addTile(new Tile(1, 1));
		
		Piece piece2 = new Piece(new Point(1, 0));
		piece2.addTile(new Tile(0, 0));
		piece2.addTile(new Tile(1, 0));
		piece2.addTile(new Tile(1, 1));
		piece2.addTile(new Tile(1, 2));
		
		level = new Level(LevelType.PUZZLE, 3, 3);
		level.getBoard().placePiece(piece1);
		level.getBoard().placePiece(piece2);
		
	}

	@Test
	public void testGradePuzzle() throws Exception {
		Piece piece3 = new Piece(new Point(0, 2));
		piece3.addTile(new Tile(0, 0));
		piece3.addTile(new Tile(1, 0));
		
		GameManager gameManager = new GameManager(level);
		LevelStars stars = StarsUtil.getStars(gameManager);
		
		assertEquals(2, stars.getStarsCount());
		
		level.getBoard().placePiece(piece3);
		stars = StarsUtil.getStars(gameManager);
		
		assertEquals(3, stars.getStarsCount());
	}

	@Test
	public void testGradeLightning() throws InvalidPiecePlacementException {
		Level emptyLevel = new Level(LevelType.LIGHTNING, 4, 4);
		GameManager gameManager = new GameManager(emptyLevel);
		LevelStars stars = StarsUtil.getStars(gameManager);
		
		assertEquals(0, stars.getStarsCount());
		
		for (Piece piece : level.getBoard().getPieces()) {
			emptyLevel.getBoard().placePiece(piece);
		}
		
		Piece piece3 = new Piece(new Point(0, 2));
		piece3.addTile(new Tile(0, 0));
		piece3.addTile(new Tile(1, 0));
		emptyLevel.getBoard().placePiece(piece3);
		stars = StarsUtil.getStars(gameManager);
		
		assertEquals(1, stars.getStarsCount());
		
		Piece piece4 = new Piece(new Point(3, 0));
		piece4.addTile(new Tile(0, 0));
		piece4.addTile(new Tile(0, 1));
		piece4.addTile(new Tile(0, 2));
		emptyLevel.getBoard().placePiece(piece4);
		stars = StarsUtil.getStars(gameManager);
		
		assertEquals(2, stars.getStarsCount());
		
		Piece piece5 = new Piece(new Point(0, 3));
		piece5.addTile(new Tile(0, 0));
		piece5.addTile(new Tile(1, 0));
		piece5.addTile(new Tile(2, 0));
		piece5.addTile(new Tile(3, 0));
		emptyLevel.getBoard().placePiece(piece5);
		stars = StarsUtil.getStars(gameManager);
		
		assertEquals(3, stars.getStarsCount());
	}
	
	@Test
	public void testIsSetComplete() throws InvalidPiecePlacementException {
		LevelStars stars;
		Level newLevel = new Level(LevelType.RELEASE, 3, 3);
		
		for (Piece piece : level.getBoard().getPieces()) {
			newLevel.getBoard().placePiece(piece);
		}
		
		newLevel.getBoard().addColoredSetItem(new ColoredSetItem(Color.BLUE, 1), new Point(0, 0));
		newLevel.getBoard().addColoredSetItem(new ColoredSetItem(Color.BLUE, 2), new Point(1, 0));
		newLevel.getBoard().addColoredSetItem(new ColoredSetItem(Color.BLUE, 3), new Point(2, 0));
		newLevel.getBoard().addColoredSetItem(new ColoredSetItem(Color.BLUE, 4), new Point(0, 2));
		newLevel.getBoard().addColoredSetItem(new ColoredSetItem(Color.BLUE, 5), new Point(1, 2));
		newLevel.getBoard().addColoredSetItem(new ColoredSetItem(Color.BLUE, 6), new Point(2, 2));
		
		assertEquals(false, StarsUtil.isSetComplete(Color.BLUE, newLevel.getBoard()));
		
		Piece piece3 = new Piece(new Point(0, 2));
		piece3.addTile(new Tile(0, 0));
		piece3.addTile(new Tile(1, 0));
		newLevel.getBoard().placePiece(piece3);
		
		assertEquals(true, StarsUtil.isSetComplete(Color.BLUE, newLevel.getBoard()));
	}
}
