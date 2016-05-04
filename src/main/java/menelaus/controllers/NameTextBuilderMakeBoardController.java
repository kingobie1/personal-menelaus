package menelaus.controllers;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import menelaus.model.BuilderManager;

/**
 * Controller handles the name of the board in the Builder Make board screen.
 * @author Sanjay.
 *
 */
public class NameTextBuilderMakeBoardController implements DocumentListener{

	BuilderManager manager;
	JTextField nameTextView;
	
	/**
	 * constructor.
	 * @param manager
	 * @param nameTextView
	 */
	public NameTextBuilderMakeBoardController(BuilderManager manager, JTextField nameTextView) {
		this.manager = manager;
		this.nameTextView = nameTextView;
	}
	
	private void handleChangedText() {
		String newText = nameTextView.getText();
		this.manager.setLevelName(newText);
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		handleChangedText();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		handleChangedText();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		handleChangedText();
	}

}
