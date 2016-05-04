package menelaus.controllers;

import menelaus.model.BuilderManager;
import menelaus.model.Level;
import menelaus.util.SoundManager;
import menelaus.util.SoundType;
import menelaus.view.BullpenView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by @author frankegan on 4/30/16.
 */
public class AddPieceToBullpenController extends MouseAdapter {

    BullpenView allPieceBullpenView;
    BullpenView bullpenView;
    BuilderManager buildManager;
    Level level;

    /**
     * Add pieces to bullpen controller.
     * @param allPieceBullpenView
     * @param bullpenView
     * @param buildManager
     */
    public AddPieceToBullpenController(BullpenView allPieceBullpenView, BullpenView bullpenView, BuilderManager buildManager) {
        this.allPieceBullpenView = allPieceBullpenView;
        this.bullpenView = bullpenView;
        this.buildManager = buildManager;
        this.level = buildManager.getLevel();
    }

    /**
     * Determine which piece was selected in the PiecesView.
     */
    @Override
    public void mousePressed(MouseEvent me) {
        //get the piece that was clicked
		SoundManager.getInstance().playSound(SoundType.PRESSPIECE);
        level.getBullpen().addPiece(allPieceBullpenView.findPiece(me.getX(), me.getY()));
        bullpenView.repaint();
    }
}
