package menelaus.model;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mtmccarthy
 *
 */

public class TestLevelStars {

	LevelStars ls1;
	LevelStars ls2;
	UUID uuid;
	
	@Before
	public void setUp() {
		uuid = UUID.randomUUID();
		ls1 = new LevelStars(2, UUID.randomUUID());
		ls2 = new LevelStars(1, uuid);
	}

	@Test
	public void testGetterAndSetters(){
		assertEquals(ls1.getStarsCount(), 2);
		assertEquals(ls2.getLevelId(), uuid);
	}
}
