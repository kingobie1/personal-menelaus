package menelaus.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import menelaus.model.BuilderManager;
import menelaus.util.SoundManager;
import menelaus.util.SoundType;
import menelaus.view.BoardView;
import menelaus.view.BullpenView;

/**
 * Controller handles the undoing in the builder.
 * @author Sanjay.
 *
 */
public class UndoBuilderMakeLevelController implements ActionListener{

	BuilderManager manager;
	BoardView boardView;
	BullpenView bullpenView;
	
	/**
	 * constructor.
	 * @param manager
	 * @param boardView
	 * @param bullpenView
	 */
	public UndoBuilderMakeLevelController(BuilderManager manager, BoardView boardView, BullpenView bullpenView) {
		this.manager = manager;
		this.boardView = boardView;
		this.bullpenView = bullpenView;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		SoundManager.getInstance().playSound(SoundType.BUTTONSOUND);
		doUndo();
	}
	
	void doUndo() {
		manager.undo();
		boardView.repaint();
		bullpenView.repaint();
	}
	
	
}
