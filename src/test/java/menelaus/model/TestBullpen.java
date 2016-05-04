package menelaus.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import menelaus.model.basic.Point;
import menelaus.model.board.Piece;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author mtmccarthy
 *
 */

public class TestBullpen {
	
	Bullpen bp;
	Piece p1;
	Point pt1;
	Piece p2;
	Point pt2;
	Piece p3;
	Point pt3;
	Piece p4;
	Point pt4;
	ArrayList<Piece> pieces;
	
	@Before
	public void setUp(){
		bp = new Bullpen();
		pt1 = new Point(0,0);
		p1 = new Piece(pt1);
		pt2 = new Point(1,1);
		p2 = new Piece(pt2);
		pt3 = new Point(2,5);
		p3 = new Piece(pt3);
		pt4 = new Point(10,10);
		p4 = new Piece(pt4);
	}
	
	public void testRemoveAndAddPiece(){
		bp.addPiece(p1);
		bp.addPiece(p2);
		bp.addPiece(p3);
		
		bp.removePiece(p1);
		bp.removePiece(p2);
		bp.removePiece(p3);
		bp.addPiece(p4);
		bp.removePiece(p4);
		
		assertEquals(bp.getPieces(), new ArrayList<Piece>());
	}
	@Test
	public void testFindPiece(){
		bp.addPiece(p1);
		bp.addPiece(p2);
		bp.addPiece(p3);
		
		assertEquals(p1, bp.findPiece(p1.getPosition()));
		assertEquals(p2, bp.findPiece(1, 1));
	}
}
