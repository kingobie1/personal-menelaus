package menelaus.controllers;

import menelaus.model.GameManager;
import menelaus.model.Level;
import menelaus.model.board.Piece;
import menelaus.model.move.ToBullpenMove;
import menelaus.util.SoundManager;
import menelaus.util.SoundType;
import menelaus.view.BullpenView;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Controller that handles piece selection.
 * Created by @author frankegan on 4/21/16.
 */
public class PieceSelectionController extends MouseAdapter {

    BullpenView bullpenView;
    GameManager gameManager;
    Level level;

    /**
     * constructor.
     * @param bullpenView
     * @param gameManager
     */
    public PieceSelectionController(BullpenView bullpenView, GameManager gameManager) {
        this.bullpenView = bullpenView;
        this.gameManager = gameManager;
        this.level = gameManager.getLevel();
    }
    
    /**
     * Constructs a new controller.
     * @param bullpenView Baby param
     * @param levelParam Baby obvious.
     */
    public PieceSelectionController(BullpenView bullpenView, Level levelParam) {
        this.bullpenView = bullpenView;
        this.gameManager = null;
        this.level = levelParam;
    }

    /**
     * Determine which piece was selected in the PiecesView.
     */
    @Override
    public void mousePressed(MouseEvent me) {
    	SoundManager.getInstance().playSound(SoundType.PRESSPIECE);
        //get the piece that was clicked
        Piece selected = bullpenView.findPiece(me.getX(), me.getY());
        if (selected == null) return;
        
        //select, rotate, or flip based on mouse event
        if(me.getClickCount() == 1 && SwingUtilities.isLeftMouseButton(me)){
            level.setSelected(selected);
        }
        else if (me.getClickCount() == 2){
            selected.rotate();
            bullpenView.repaint();
            level.setSelected(selected);
        }
        else if (SwingUtilities.isRightMouseButton(me) || me.getButton() == MouseEvent.BUTTON3){
            selected.flip();
            bullpenView.repaint();
            level.setSelected(selected);
        }
    }

    /**
     * Determine which piece is being dragged back to the bullpen.
     * @param e the mouse event.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (gameManager != null && level.getActive() != null){
            gameManager.performNewMove(new ToBullpenMove(level.getActive()));
        }
    }
}
