package menelaus.model.move;

import menelaus.model.BuilderManager;
import menelaus.model.Level;
import menelaus.model.basic.Color;
import menelaus.model.basic.LevelType;
import menelaus.model.basic.Point;
import menelaus.model.board.BoardTileInfo;
import menelaus.model.board.ColoredSetItem;
import menelaus.model.board.Piece;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Hashtable;

import static org.junit.Assert.assertTrue;

/**
 * Created by frankegan on 4/18/16.
 */
public class TestMove{
    Piece piece;
    Level lightning;
    Level puzzle; 
    BuilderManager manager;

    @Before
    public void setUp() {
        piece = new Piece(new Point(1, 1));
        lightning = new Level(LevelType.LIGHTNING, 8, 6);
        lightning.getBullpen().addPiece(piece);
        puzzle = new Level(LevelType.PUZZLE, 8, 6);
        puzzle.getBullpen().addPiece(piece);
        manager = new BuilderManager();
        ArrayList<Point> selectedPoints = new ArrayList<Point>();
        selectedPoints.add(new Point(0, 0));
        selectedPoints.add(new Point(1, 0));
        selectedPoints.add(new Point(2, 0));
        selectedPoints.add(new Point(3, 0));
        selectedPoints.add(new Point(4, 0));
        selectedPoints.add(new Point(5, 0));
        manager.setSelectedPoints(selectedPoints);
    }

    @Test
    public void testAroundBoardMove() {
        Move abm = new AroundBoardMove(piece, new Point(3, 3));
        assertTrue(!abm.valid(lightning)); //cannot move around in lighting level
        assertTrue(abm.valid(puzzle));
        assertTrue(abm.doMove(puzzle));
    }

    @Test
    public void testFlipPieceMove() {
        Move flip = new FlipPieceMove(piece);
        assertTrue(flip.valid(lightning));
        assertTrue(flip.doMove(lightning));
    }

    @Test
    public void testRotatePieceMove() {
        Move rot = new RotatePieceMove(piece);
        assertTrue(rot.valid(lightning));
        assertTrue(rot.doMove(lightning));
    }

    @Test
    public void testToBoardCoverMove() {
        Move cov = new ToBoardCoverMove(piece, new Point(3, 3));
        assertTrue(cov.valid(lightning));
        assertTrue(cov.doMove(lightning));
    }

    @Test
    public void testToBoardMove() {
        Move tbm = new ToBoardMove(piece, new Point(1, 1));
        lightning.getBullpen().addPiece(piece);
        assertTrue(tbm.valid(puzzle));
        assertTrue(tbm.doMove(puzzle));
    }

    @Test
    public void testBullpenMove() {
        Move tbp = new RotatePieceMove(piece);
        assertTrue(tbp.valid(lightning));
        assertTrue(tbp.doMove(lightning));
    }
    @Test
    public void testMakePieceBuilderMove(){
    	MakePieceBuilderMove tmpbMove = new MakePieceBuilderMove(manager);
    	assertTrue(tmpbMove.valid(lightning));
    	assertTrue(tmpbMove.valid(puzzle));
    	assertTrue(tmpbMove.doMove(lightning));
    	assertTrue(tmpbMove.doMove(puzzle));
    	manager.makeMove(tmpbMove);
    	manager.undo();
    	manager.redo();
    	manager.deselectPointByIndex(0);
    	manager.setReleaseItem(manager.getReleaseItem());
    	manager.setToReleaseMode(manager.getReleaseItem());
    	manager.makeMoveAndClear(tmpbMove);
    }
    @Test
    public void testSelectSquareMove(){
    	DeselectSquareBuilderMove dSquareBuilderMove = new DeselectSquareBuilderMove(manager, 0, 0);
    	assertTrue(dSquareBuilderMove.valid(lightning));
    	assertTrue(dSquareBuilderMove.doMove(lightning));
    	DeselectSquareBuilderMove dSquareBuilderMove2 = new DeselectSquareBuilderMove(manager, 1, 0);
    	assertTrue(dSquareBuilderMove2.valid(puzzle));
    	assertTrue(dSquareBuilderMove2.doMove(puzzle));
    	SelectSquareMove ssmMove = new SelectSquareMove(manager, 0, 0);
    	assertTrue(ssmMove.valid(lightning));
    	assertTrue(ssmMove.doMove(lightning));
    	SelectSquareMove ssmMove2 = new SelectSquareMove(manager, 1, 0);
    	assertTrue(ssmMove2.valid(puzzle));
    	assertTrue(ssmMove2.doMove(puzzle));
    	
    }
   
    @Test
    public void testToBullpenMove(){
    	ToBullpenMove toBullpenMove = new ToBullpenMove(piece);
    	lightning.getBoard().getPieces().add(piece);
		puzzle.getBoard().addColoredSetItem(new ColoredSetItem(Color.BLUE, 1), piece.getPosition());
    	puzzle.getBoard().getPieces().add(piece);
    	assertTrue(toBullpenMove.valid(lightning));
    	assertTrue(toBullpenMove.valid(puzzle));
    	//assertTrue(toBullpenMove.doMove(lightning)); 
    	assertTrue(toBullpenMove.doMove(puzzle)); 
    }
    @Test
    public void testMakeHintBuilderMove(){
    	BuilderMove makeHintBuilderMove = new MakeHintBuilderMove(manager);
    	assertTrue(makeHintBuilderMove.valid(lightning));
    	assertTrue(makeHintBuilderMove.valid(puzzle));
    	assertTrue(makeHintBuilderMove.doMove(lightning));
    	
    }
    @Test
    public void testPlaceReleaseNumberBuilderMove(){
    	ColoredSetItem csi = new ColoredSetItem(Color.BLUE, 1);
    	Level level = new Level(LevelType.RELEASE, 6, 8);
    	PlaceReleaseNumberBuilderMove prnbm = new PlaceReleaseNumberBuilderMove(manager, 0, 0, csi);
    	prnbm.valid(level);
    	prnbm.doMove(level);
    	prnbm.undo(level);
    }
    
}
