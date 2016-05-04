package menelaus.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import menelaus.model.basic.*;

/**
 * 
 * @author mtmccarthy
 *
 */

public class TestLevel {
	LevelType puzzle;
	LevelType lightning;
	LevelType release;
	Level l1;
	Level l2;
	
	
	@Before
	public void setUp() {
		puzzle = LevelType.PUZZLE;
		lightning = LevelType.LIGHTNING;
		release = LevelType.RELEASE;
		
		l1 = new Level(puzzle, 100, 100);
		
	}
	@Test
	public void testGettersAndSetters(){
		int tl = 100;
		l1.setTimeLimit(tl);
		int ml = 5;
		l1.setMoveLimit(ml);
		String name = "Level 1";
		l1.setName(name);
		
		assertEquals(l1.getTimeLimit(), tl);
		assertEquals(l1.getMoveLimit(), ml);
		assertEquals(l1.getType(), puzzle);
		assertEquals(l1.getName(), name);
		
	}
}
