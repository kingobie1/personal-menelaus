package menelaus.util;

import menelaus.model.Level;
import menelaus.model.LevelsPackage;
import menelaus.model.basic.Color;
import menelaus.model.basic.LevelType;
import menelaus.model.basic.Point;
import menelaus.model.board.ColoredSetItem;
import menelaus.model.board.HintPiece;
import menelaus.model.board.Piece;
import menelaus.model.board.Tile;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * 
 * @author vouldjeff
 *
 */
public class LevelsPackagePersistenceUtilTest {
	LevelsPackage levelsPackage;
	
	@Rule
    public TemporaryFolder folder = new TemporaryFolder();
	
	
	@Before
	public void setUp() throws Exception {
		levelsPackage = new LevelsPackage();
		
		Piece piece1 = new Piece(new Point(0, 0));
		piece1.addTile(new Tile(0, 0));
		piece1.addTile(new Tile(0, 1));
		piece1.addTile(new Tile(1, 1));
		
		HintPiece hint1 = new HintPiece(new Point(0, 0));
		hint1.addTile(new Tile(0, 0));
		hint1.addTile(new Tile(0, 1));
		hint1.addTile(new Tile(1, 1));
		
		ColoredSetItem coloredSetItem = new ColoredSetItem(Color.BLUE, 1);
		
		Level level1 = new Level(LevelType.PUZZLE, 6, 6);
		level1.getBullpen().addPiece(piece1);
		level1.getBoard().addHintPiece(hint1);
		
		Level level2 = new Level(LevelType.RELEASE, 8, 8);
		level2.getBoard().addColoredSetItem(coloredSetItem, new Point(1, 1));
		
		levelsPackage.addLevel(level1);
		levelsPackage.addLevel(new Level(LevelType.PUZZLE, 6, 6));
		levelsPackage.addLevel(new Level(LevelType.LIGHTNING, 7, 7));
		levelsPackage.addLevel(level2);
	}

	@Test
	public void testToAndFromFile() throws ClassNotFoundException, IOException {
		LevelsPackage loaded;
		
		File createdFile = folder.newFile("testFile.dat");
		
		LevelsPackagePersistenceUtil.toFile(levelsPackage, createdFile);
		loaded = LevelsPackagePersistenceUtil.fromFile(createdFile);
		
		assertEquals(levelsPackage.getLevels().size(), loaded.getLevels().size());
	}
	
	@Test
	public void testIfThereAreDefaultLevelsPackage() {
		LevelsPackage loaded;
		
		try {
			loaded = LevelsPackagePersistenceUtil.fromFile(new File("default-levels.boba"));
			assertNotNull(loaded);
		} catch (ClassNotFoundException e) {
			fail("Default levels package is corrupted.");
			e.printStackTrace();
		} catch (IOException e) {
			fail("Default levels package is missing.");
			e.printStackTrace();
		}
	}
	
	/**
	 * This is not a test. It is used to create a sample default LevelsPackage, so that we can test code.
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@Test
	@Ignore
	public void testCreateDefaultLevels() throws ClassNotFoundException, IOException {
		LevelsPackage loaded;
		
		LevelsPackage defaultLevelsPackage = new LevelsPackage();
		
		Piece piece1 = new Piece(new Point(0, 0));
		piece1.addTile(new Tile(0, 0));
		piece1.addTile(new Tile(0, 1));
		piece1.addTile(new Tile(1, 1));
		
		HintPiece hint1 = new HintPiece(new Point(0, 0));
		hint1.addTile(new Tile(0, 0));
		hint1.addTile(new Tile(0, 1));
		hint1.addTile(new Tile(1, 1));
		
		ColoredSetItem coloredSetItem1 = new ColoredSetItem(Color.BLUE, 1);
		ColoredSetItem coloredSetItem2 = new ColoredSetItem(Color.BLUE, 2);
		ColoredSetItem coloredSetItem3 = new ColoredSetItem(Color.RED, 3);
		ColoredSetItem coloredSetItem4 = new ColoredSetItem(Color.RED, 4);
		
		Piece piece2 = new Piece(new Point(1, 0));
		piece2.addTile(new Tile(0, 0));
		piece2.addTile(new Tile(1, 0));
		piece2.addTile(new Tile(1, 1));
		piece2.addTile(new Tile(1, 2));
		
		Piece piece3 = new Piece(new Point(0, 2));
		piece3.addTile(new Tile(0, 0));
		piece3.addTile(new Tile(1, 0));
		
		Level level1 = new Level(LevelType.RELEASE, 12, 12);
		level1.getBoard().addHintPiece(hint1);
		level1.getBoard().addColoredSetItem(coloredSetItem1, new Point(0, 0));
		level1.getBoard().addColoredSetItem(coloredSetItem2, new Point(0, 1));
		level1.getBoard().addColoredSetItem(coloredSetItem3, new Point(1, 0));
		level1.getBoard().addColoredSetItem(coloredSetItem4, new Point(1, 1));
		
		level1.getBullpen().addPiece(piece1);
		level1.getBullpen().addPiece(piece2);
		level1.getBullpen().addPiece(piece3);
		level1.setMoveLimit(4);
		
		defaultLevelsPackage.addLevel(level1);
		
		File createdFile = new File("default-levels.boba");
		
		LevelsPackagePersistenceUtil.toFile(defaultLevelsPackage, createdFile);
		loaded = LevelsPackagePersistenceUtil.fromFile(createdFile);
		
		assertNotNull(loaded);
	}
}
