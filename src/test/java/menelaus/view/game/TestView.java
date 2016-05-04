package menelaus.view.game;

import menelaus.controllers.ButtonBuilderLevelSelectController;
import menelaus.controllers.ButtonLevelSelectController;
import menelaus.controllers.ButtonLevelsController;
import menelaus.controllers.PieceController;
import menelaus.controllers.ToWinScreenController;
import menelaus.model.Level;
import menelaus.model.LevelStars;
import menelaus.model.LevelsPackage;
import menelaus.model.basic.LevelType;
import menelaus.model.basic.Point;
import menelaus.model.board.Piece;
import menelaus.model.board.Tile;
import menelaus.model.events.GameEndReason;
import menelaus.view.BoardView;
import menelaus.view.builder.BuilderWindowFrame;
import menelaus.view.builder.HomeScreen;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

public class TestView {

	GameWindowFrame frame;
	BuilderWindowFrame builderFrame;
	LevelPlayScreen screen;
	Level level;
	
	//Game Simple Views
	BoardView boardView;
	
	@Before
	public void setUp() throws Exception {
		if (_stopTest()) { return; }
		
		frame = GameWindowFrame.getInstance();
		builderFrame = BuilderWindowFrame.getInstance();
		
		Level level = new Level(LevelType.PUZZLE, 6, 6);
		Piece piece = new Piece(new Point(0, 0));
		piece.addTile(new Tile(1, 0));
		level.getBullpen().addPiece(piece);
	
		ButtonLevelSelectController controller = new ButtonLevelSelectController(level);
		controller.actionPerformed(null);
		screen = (LevelPlayScreen) frame.getContentPane().getComponent(0);
	}
	
	@Test
	public void testEverything() {
		HomeScreen homeScreen = new HomeScreen();
		SplashScreen splashScreen = new menelaus.view.game.SplashScreen();
		menelaus.view.builder.SplashScreen splashScreen2 = new menelaus.view.builder.SplashScreen();
		ExtraScreen extraScreen = new ExtraScreen();
	}

	@Test
	public void testWinScreen() {
		if (_stopTest()) { return; }
		
		Level level = new Level(LevelType.PUZZLE, 12, 12);
		LevelStars stars = new LevelStars(3, level.getUuid());
		
		ToWinScreenController controller = new ToWinScreenController(stars, GameEndReason.WON, level);
		controller.actionPerformed(null);
	}
	
	@Test
	public void testPlayScreen() {
		if (_stopTest()) { return; }
		
		screen.btnRestart.doClick();
		screen.btnExitButton.doClick();
	}
	
	@Test
	public void testMousePressed() throws Exception {
		if (_stopTest()) { return; }
		
		PieceController ctr = new PieceController(screen, screen.gameManager);
		ctr._handleMousePressed(new Point(20, 20));
		screen.gameManager.getLevel().setActive(screen.gameManager.getLevel().getBullpen().getPieces().get(0));
		ctr._handleMousePressed(new Point(20, 20));
	}
	
	@Test
	public void testMouseMoved() throws Exception {
		if (_stopTest()) { return; }
		
		PieceController ctr = new PieceController(screen, screen.gameManager);
		ctr._handleMouseMoved(new Point(20, 20));
		screen.gameManager.getLevel().setSelected(screen.gameManager.getLevel().getBullpen().getPieces().get(0));
		ctr._handleMouseMoved(new Point(20, 20));
	}
	
	@Test
	public void testMouseDraggedReleased() throws Exception {
		if (_stopTest()) { return; }
		
		PieceController ctr = new PieceController(screen, screen.gameManager);
		Level l = screen.gameManager.getLevel();
		
		Piece piece = new Piece(new Point(1, 1));
		piece.addTile(new Tile(0, 1));
		piece.addTile(new Tile(1, 0));
		piece.addTile(new Tile(1, 1));
		piece.addTile(new Tile(-1, 0));
		piece.addTile(new Tile(-1, -1));
		piece.addTile(new Tile(-1, 1));
		
		l.getBoard().placePiece(piece);
		ctr.draggingPiece = piece;
		ctr.dragOffset = new Point(0, 0);
		ctr.dragMoved = true;
		
		ctr._handleMouseDragged(new Point(20, 20));
		ctr._handleMouseReleased(new Point(25, 25));
	}
	
	@Test
	public void testMouseExited() throws Exception {
		if (_stopTest()) { return; }
		
		PieceController ctr = new PieceController(screen, screen.gameManager);
		Level l = screen.gameManager.getLevel();
		
		Piece piece = new Piece(new Point(1, 1));
		piece.addTile(new Tile(0, 1));
		piece.addTile(new Tile(1, 0));
		piece.addTile(new Tile(1, 1));
		piece.addTile(new Tile(-1, 0));
		piece.addTile(new Tile(-1, -1));
		piece.addTile(new Tile(-1, 1));
		
		l.getBoard().placePiece(piece);
		ctr.draggingPiece = piece;
		ctr.dragOffset = new Point(0, 0);
		ctr.dragMoved = true;
		
		ctr._handleMouseExited();
	}
	
	@Test
	public void testLevelSelect() {
		if (_stopTest()) { return; }
		
		ButtonLevelsController controller = new ButtonLevelsController();
		controller.actionPerformed(null);
	}
	
	@Test
	public void testStartLevel() {
		if (_stopTest()) { return; }
		
		ButtonBuilderLevelSelectController controller = new ButtonBuilderLevelSelectController();
		controller.actionPerformed(null);
	}
	
	private boolean _stopTest() {
		return GraphicsEnvironment.getLocalGraphicsEnvironment().isHeadless();
	}
}
