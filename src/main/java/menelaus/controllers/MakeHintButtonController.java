package menelaus.controllers;

import menelaus.model.BuilderManager;
import menelaus.model.move.MakeHintBuilderMove;
import menelaus.view.BoardView;
import menelaus.view.BullpenView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller that handles making a hint.
 * Created by @author frankegan on 5/2/16.
 */
public class MakeHintButtonController implements ActionListener {
    private BullpenView bullpenView;
    private BoardView boardView;
    private BuilderManager manager;

    /**
     * constructor. 
     * 
     * @param manager
     * @param view
     * @param penView
     */
    public MakeHintButtonController(BuilderManager manager, BoardView view, BullpenView penView) {
        this.manager = manager;
        this.boardView = view;
        this.bullpenView = penView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MakeHintBuilderMove move = new MakeHintBuilderMove(manager);
        if(move.valid(manager.getLevel())) {
            manager.makeMove(move);
            System.out.println("Move successful!");
            boardView.repaint();
            bullpenView.repaint();
        }
        else {
            System.out.println("Move failed!");
        }
    }
}
