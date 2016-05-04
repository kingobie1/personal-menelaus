package menelaus.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import menelaus.model.basic.*;

/**
 * 
 * @author mtmccarthy
 *
 */
public class TestLevelPackage {
	
	Level l1;
	Level l2;
	Level l3;
	LevelsPackage levels;
	LevelsPackage pack;

	@Before
	public void setUp() throws Exception {
		l1 = new Level(LevelType.PUZZLE, 100, 100);
		l2 = new Level(LevelType.LIGHTNING, 100, 100);
		l3 = new Level(LevelType.RELEASE, 100, 100);
		levels = new LevelsPackage();
		levels.addLevel(l1);
		levels.addLevel(l2);
		levels.addLevel(l3);
	}

	@Test
	public void testSetLevels() {
		assertEquals(l1, levels.getLevels().get(0));
		assertEquals(3, levels.getLevels().size());
	}

}
