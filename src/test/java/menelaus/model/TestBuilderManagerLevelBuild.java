package menelaus.model;

import junit.framework.TestCase;
import menelaus.util.LevelsPackagePersistenceUtil;

import java.io.File;
import java.io.IOException;

public class TestBuilderManagerLevelBuild extends TestCase {
	public void testFull1() {
		/*
		BuilderManager manager = new BuilderManager();
		manager.createLevel("Test1", LevelType.PUZZLE, 6, 6);
		SelectSquareMove s1 = new SelectSquareMove(manager, 1, 1);
		SelectSquareMove s2 = new SelectSquareMove(manager, 1, 2);
		SelectSquareMove s3 = new SelectSquareMove(manager, 1, 3);
		SelectSquareMove s4 = new SelectSquareMove(manager, 2, 1);
		SelectSquareMove s5 = new SelectSquareMove(manager, 2, 2);
		SelectSquareMove s6 = new SelectSquareMove(manager, 2, 3);
		assertTrue(s1.valid(manager.currentProject));
		
		assertTrue(manager.makeMove(s1));
		assertTrue(s2.valid(manager.currentProject));
		assertTrue(manager.makeMove(s2));
		assertTrue(s3.valid(manager.currentProject));
		assertTrue(manager.makeMove(s3));
		assertTrue(s4.valid(manager.currentProject));
		assertTrue(manager.makeMove(s4));
		assertTrue(s5.valid(manager.currentProject));
		assertTrue(manager.makeMove(s5));
		assertTrue(s6.valid(manager.currentProject));
		assertTrue(manager.makeMove(s6));
		MakePieceBuilderMove mp1 = new MakePieceBuilderMove(manager);
		assertTrue(mp1.valid(manager.currentProject));
		assertTrue(manager.makeMove(mp1));
		manager.saveLevel();
		System.out.println();*/
		
	}
	
	public void testLoad() {

		BuilderManager manager = new BuilderManager();
		try {
			LevelsPackage pack = LevelsPackagePersistenceUtil.fromFile(new File("default-levels.boba"));
			manager.currentProject = pack.getLevels().get(0);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			fail();
		}
		System.out.println();
	}
}
