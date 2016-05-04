package menelaus.model;

import org.junit.Ignore;

import junit.framework.TestCase;
import menelaus.model.basic.LevelType;
/**
 * Tests the capabilities of the Builder Manager
 * @author sanjay
 *
 */
public class TestBuilderManager extends TestCase {
	public void testCreation() {
		BuilderManager bManager = new BuilderManager();
		assertEquals(bManager.getType(),bManager.DEFAULT_LEVEL);
		assertEquals(bManager.getWidth(),bManager.DEFAULT_WIDTH);
		assertEquals(bManager.getHeight(),bManager.DEFAULT_HEIGHT);
	}
	
	public void testSetSize() {
		BuilderManager bManager = new BuilderManager();
		bManager.setSize(8, 8);
		assertEquals(bManager.getWidth(),8);
		assertEquals(bManager.getHeight(),8);
	}
	
	public void testCreateLevel(){
		BuilderManager bManager = new BuilderManager();
		bManager.createLevel("Test Level", LevelType.PUZZLE, 8, 8);
		assertEquals(bManager.getName(),"Test Level");
		assertEquals(bManager.getType(),LevelType.PUZZLE);
		assertEquals(bManager.getWidth(),8);
		assertEquals(bManager.getHeight(),8);
	}
	
	public void testSetName() {
		BuilderManager bManager = new BuilderManager();
		bManager.createLevel("Test Level", LevelType.PUZZLE, 8, 8);
		assertEquals(bManager.getName(),"Test Level");
		bManager.setLevelName("Test Level Changed");
		assertEquals(bManager.getName(),"Test Level Changed");
	}
	
	@Ignore
	public void testSetType() {
		BuilderManager bManager = new BuilderManager();
		bManager.createLevel("Test Level", LevelType.PUZZLE, 8, 8);
		assertEquals(bManager.getType(),LevelType.PUZZLE);
		bManager.setLevelType(LevelType.LIGHTNING);
		assertEquals(bManager.getType(),LevelType.LIGHTNING);
		
		bManager.getNumSelectedPoints();
		bManager.setToBoardMode();
	}
	
	
}
