package menelaus.controllers;

import menelaus.model.Level;
import menelaus.model.basic.Point;
import menelaus.model.board.InvalidPiecePlacementException;
import menelaus.model.board.Piece;
import menelaus.model.move.ToBoardMove;
import menelaus.view.KabasujiPanel;
import menelaus.view.builder.BuilderWindowFrame;
import menelaus.view.game.GameWindowFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Controller handles the Piece Dragging.
 * Created by @frankegan on 4/19/16.
 */
public class PieceDragController extends MouseAdapter {

    /**
     * Needed for controller behavior.
     */
    Level level;
    KabasujiPanel panel;

    /**
     * Original x,y where shape was before move.
     */
    int originalx;
    int originaly;

    /**
     * Anchor point where first grabbed and delta from that location.
     */
    Point anchor;
    int deltaX;
    int deltaY;

    /**
     * Button that started off.
     */
    int buttonType;

    /**
     * Constructor holds onto key manager objects.
     * @param level
     * @param frame
     */
    public PieceDragController(Level level, BuilderWindowFrame frame) {
        this.level = level;
        this.panel = frame.getPanel();
    }

    /**
     * Constructor holds onto key manager objects.
     * @param level
     * @param frame
     */
    public PieceDragController(Level level, GameWindowFrame frame) {
        this.level = level;
        this.panel = frame.getPanel();
    }

    /**
     * Set up press events but no motion events.
     */
    public void register() {
        panel.setActiveListener(this);
        panel.setActiveMotionListener(this);
    }

    /**
     * Whenever mouse is pressed (left button), attempt to select object.
     * This is a GUI controller.
     */
    @Override
    public void mousePressed(MouseEvent me) {		
        buttonType = me.getButton();
        select(me.getX(), me.getY());
    }

    /**
     * Whenever mouse is dragged, attempt to start object.
     * This is a GUI controller.
     */
    @Override
    public void mouseDragged(MouseEvent me) {
        drag(me.getX(), me.getY());
    }

    /**
     * Whenever mouse is released, complete the move.
     * This is a GUI controller.
     */
    @Override
    public void mouseReleased(MouseEvent me) {
        release(me.getX(), me.getY());
    }

    /**
     * Separate out this function for testing purposes.
     */
    protected boolean select(int x, int y) {
        anchor = new Point(x, y);

        // pieces are returned in order of Z coordinate
        Piece p = level.getBullpen().findPiece(anchor.getX(), anchor.getY());
        if (p == null) {
            return false;
        }

        // no longer in the board since we are moving it around...
//        level.getBullpen().removePiece(p);
        level.setSelected(p);
        originalx = p.getPosition().getX();
        originaly = p.getPosition().getY();

        // set drawer to highlight
        p.setSelected(true);
        
        // set anchor for smooth moving
        deltaX = anchor.getX() - originalx;
        deltaY = anchor.getY() - originaly;

        panel.repaint();
        return true;
    }

    /**
     * Separate out this function for testing purposes.
     */
    protected boolean drag(int x, int y) {
        // no board? no behavior! No dragging of right-mouse buttons...
        if (buttonType == MouseEvent.BUTTON3) {
            return false;
        }
        Piece selected = level.getSelected();

        if (selected == null) {
            return false;
        }

        int oldx = selected.getPosition().getX();
        int oldy = selected.getPosition().getY();

        selected.setPosition(new Point(x - deltaX, y - deltaY));

        boolean ok = true;

        // must still be visible!
        if (selected.getPosition().getX() < 0) {
            ok = false;
        }
        if (selected.getPosition().getY() < 0) {
            ok = false;
        }
        if (!ok) {
            selected.setPosition(new Point(oldx, oldy));
        } else {
            panel.repaint();
        }

        return true;
    }

    /**
     * Separate out this function for testing purposes.
     */
    protected boolean release(int x, int y) {
        Piece selected = level.getSelected();
        if (selected == null) {
            return false;
        }

        // now released we can create Move
        try {
            level.getBoard().placePiece(selected);
        } catch (InvalidPiecePlacementException e) {
            e.printStackTrace();
        }
        ToBoardMove move = new ToBoardMove(selected, new Point(originalx, originaly));
        if (move.doMove(level)) {
            // TODO: 4/19/16 start tarcking moves
//            level.recordMove(move);
        }

        // return to original drawer
        selected.setSelected(false);

        // no longer selected
        level.setSelected(null);

        panel.repaint();
        return true;
    }
}