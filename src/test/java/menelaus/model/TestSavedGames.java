package menelaus.model;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import menelaus.model.basic.LevelType;

/**
 * 
 * @author mtmccarthy
 *
 */
public class TestSavedGames {

	Level level;
	LevelStars star1;
	LevelStars star2;
	LevelStars star3;
	
	SavedGames sgs;
	
	
	@Before
	public void setUp() throws Exception {
		level = new Level(LevelType.PUZZLE, 10, 10);
		star1 = new LevelStars(2, UUID.randomUUID());
		star2 = new LevelStars(1, UUID.randomUUID());
		star3 = new LevelStars(3, level.getUuid());
		
		sgs = new SavedGames();
		sgs.addLevelStars(star1);
	}

	@Test
	public void testGettersAndSetters() {
		sgs.addLevelStars(star2);
		assertEquals(2, sgs.getStars().size());
	}
	
	@Test
	public void testAddLevelStars(){
		sgs.addLevelStars(star3);
		assertEquals(star3, sgs.getStarsForLevel(level));
	}
}