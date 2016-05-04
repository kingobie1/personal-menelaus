package menelaus.model.board;

import static org.junit.Assert.*;
import menelaus.model.basic.Color;

import org.junit.Before;
import org.junit.Test;

public class TestColoredSetItem {
	Color blue = Color.BLUE;
	Color green = Color.GREEN;
	Color red = Color.RED;
	Color nullColor = null;
	
	ColoredSetItem blueone;
	ColoredSetItem greenone;
	ColoredSetItem redone;
	ColoredSetItem nullone;
	
	@Before
	public void setUp() throws Exception {
		blueone = new ColoredSetItem(blue, 1);
		greenone = new ColoredSetItem(green, 1);
		redone = new ColoredSetItem(red, 1);
		nullone = new ColoredSetItem(nullColor, 1);
		
	}

	@Test
	public void testJavaColors() {
		assertEquals(blueone.getJavaColor(), java.awt.Color.blue);
		assertEquals(greenone.getJavaColor(), java.awt.Color.green);
		assertEquals(redone.getJavaColor(), java.awt.Color.red);
		assertEquals(nullone.getJavaColor(), java.awt.Color.black);
	}

}
