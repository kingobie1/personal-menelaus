package menelaus.controllers;

import menelaus.model.BuilderManager;
import menelaus.model.move.MakePieceBuilderMove;
import menelaus.util.SoundManager;
import menelaus.util.SoundType;
import menelaus.view.BoardView;
import menelaus.view.BullpenView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Controller handles making a piece.
 * @author sanjay
 *
 */
public class MakePieceButtonController implements ActionListener{

	BuilderManager manager;
	BoardView boardView;
	BullpenView bullpenView;
	
	/**
	 * constructor.
	 * 
	 * @param manager
	 * @param view
	 * @param penView
	 */
	public MakePieceButtonController(BuilderManager manager, BoardView view, BullpenView penView) {
		this.manager = manager;
		this.boardView = view;
		this.bullpenView = penView;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		SoundManager.getInstance().playSound(SoundType.BUTTONSOUND);
		handleButtonClicked();
	}
	
	void handleButtonClicked() {
		MakePieceBuilderMove move = new MakePieceBuilderMove(manager);
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
