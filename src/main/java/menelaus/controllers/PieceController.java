package menelaus.controllers;

import menelaus.model.GameManager;
import menelaus.model.Level;
import menelaus.model.basic.LevelType;
import menelaus.model.basic.Point;
import menelaus.model.board.InvalidPiecePlacementException;
import menelaus.model.board.Piece;
import menelaus.model.move.*;
import menelaus.util.SoundManager;
import menelaus.util.SoundType;
import menelaus.view.BoardView;
import menelaus.view.BullpenView;
import menelaus.view.game.LevelPlayScreen;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by @author frankegan on 4/21/16.
 */
public class PieceController extends MouseAdapter {
    BoardView boardView;
    BullpenView bullpenView;
    GameManager gameManager;
    Level level;

    /**
     * The piece that is being dragged around after it is placed on the board
     */
    public Piece draggingPiece;
    
    /**
     * The point we are dragging from
     */
    private Point draggingAnchor;
    /**
     * The offset we are using to calculate dragging pieces
     */
    public Point dragOffset;
    /**
     * Was teh dragging piece actually moved or just selected
     */
    public boolean dragMoved = false;

    /**
     * constructor.
     * @param app
     * @param gameManager
     */
    public PieceController(LevelPlayScreen app, GameManager gameManager) {
        this.boardView = app.getBoardView();
        this.bullpenView = app.getBullpenView();
        this.gameManager = gameManager;
        this.level = gameManager.getLevel();
    }

    /**
     * Determine which piece was selected in the PiecesView.
     */
    @Override
    public void mousePressed(MouseEvent me) {
    	SoundManager.getInstance().playSound(SoundType.PRESSTILE);
    	
        _handleMousePressed(new Point(me.getX(), me.getY()));
    }
    
    @Override
    public void mouseMoved(MouseEvent me) {
    	Point clickedPoint = new Point(me.getX(), me.getY());
    	
    	_handleMouseMoved(clickedPoint);
    }
    
    @Override
    public void mouseDragged(MouseEvent me) {
    	Point clickedPoint = new Point(me.getX(), me.getY());
    	
        _handleMouseDragged(clickedPoint);
    }
    
    /**
     * Once released, no more dragging.
     */
    @Override
    public void mouseReleased(MouseEvent me) {
    	Point clickedPoint = new Point(me.getX(), me.getY());
    	
        _handleMouseReleased(clickedPoint);
    }
    
    @Override
    public void mouseExited(MouseEvent me) {
    	_handleMouseExited();
    }
    
    /**
     * Isolated method for easy testing that handles mouse press.
     * @param clickedPoint The coordinate on the board.
     */
    public void _handleMousePressed(Point clickedPoint) {
        Point gridPoint = clickedPoint.divide(boardView.calculateGridUnitSize());
    	
    	Piece pp = level.getActive();
        if (pp == null) {
            // perhaps we are pressing inside one of the existing pieces?
            Piece found = boardView.findPiece(clickedPoint);
            if (found != null) {
                draggingPiece = found;
                dragOffset = boardView.pointUnder(clickedPoint);
            }
            return;
        }

        level.setActive(null);  // no longer being dragged around
        level.setSelected(null);
        Move pieceMove = (level.getType() == LevelType.LIGHTNING) ?
                new ToBoardCoverMove(pp, gridPoint) : new ToBoardMove(pp, gridPoint);
        gameManager.performNewMove(pieceMove);

        boardView.repaint();
        bullpenView.repaint();   // has also changed state since piece no longer selected.
    }
    
    /**
     * Isolated method for easy testing for mouse moved.
     * @param clickedPoint Coordinate on the board.
     */
    public void _handleMouseMoved(Point clickedPoint) {
    	Piece selected = level.getSelected();
        if (selected == null) {
            return;
        }

        Piece pp = level.getSelected();
        pp.setPosition(clickedPoint.divide(boardView.calculateGridUnitSize()));
        level.setActive(pp);
        boardView.repaint();
    }
    
    /**
     * Isolated method for easy testing for mouse dragged.
     * @param clickedPoint Coordinate on the board.
     */
    public void _handleMouseDragged(Point clickedPoint) {
    	// if nothing being dragged, leave
        if (draggingPiece == null) {
            return;
        }
        Point anchor = draggingPiece.getPosition();
        Point clicked = boardView.pointUnder(clickedPoint);

        Point adjusted = anchor.add(clicked.subtract(dragOffset));

        level.getBoard().removePiece(draggingPiece);
        draggingPiece.setPosition(adjusted);
        try {
            if (level.getBoard().isPlacementValid(draggingPiece)) {
                level.getBoard().placePiece(draggingPiece);
                if (!dragMoved)
                    dragMoved = !(anchor.equals(adjusted));
            } else {
                draggingPiece.setPosition(anchor);
            }
        } catch (InvalidPiecePlacementException e) {
            e.printStackTrace();
        }

        dragOffset = clicked;
        boardView.repaint();
    }
    
    /**
     * Isolated method for mouse release on the board.
     * @param clickedPoint Coordinate.
     */
    public void _handleMouseReleased(Point clickedPoint) {
    	if (draggingPiece != null) {
            Point anchor = draggingPiece.getPosition();
            Point clicked = boardView.pointUnder(clickedPoint);
            
            Point adjusted = anchor.add(clicked.subtract(dragOffset));
            
            if (dragMoved) {
                gameManager.performNewMove(new AroundBoardMove(draggingPiece, adjusted));
                dragMoved = false;
            }
        }
        draggingPiece = null;
        draggingAnchor = null;
        dragOffset = null;
    }
    
    /**
     * Isolated method for easy testing for mouse exit.
     */
    public void _handleMouseExited() {
        if (draggingPiece != null) {
            //piece is no longer on the board so move it back to bullpen
            gameManager.performNewMove(new ToBullpenMove(draggingPiece));
            draggingPiece = null;
            draggingAnchor = null;
            dragOffset = null;
        }

        // clear the view of partial drawings once mouse exits region
        level.setActive(null);
        bullpenView.repaint();
        boardView.repaint();
    }
}
