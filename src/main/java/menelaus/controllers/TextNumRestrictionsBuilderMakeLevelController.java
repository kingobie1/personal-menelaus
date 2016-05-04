package menelaus.controllers;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import menelaus.model.BuilderManager;

/**
 * Controller handles the number restricitons in the builder.
 * @author sanjay
 *
 */
public class TextNumRestrictionsBuilderMakeLevelController implements DocumentListener{
	BuilderManager manager;
	JTextField view;
	
	/**
	 * consructor.
	 * @param manager
	 * @param view
	 */
	public TextNumRestrictionsBuilderMakeLevelController(BuilderManager manager, JTextField view) {
		this.manager = manager;
		this.view = view;
	}
	
	private void handleTextChanged() {
		String newText = view.getText();
		
		int newNumRestrictions;
		try {
			newNumRestrictions = Integer.parseInt(newText);
		} catch (NumberFormatException e) {
			System.err.println("Number of restrictions is Not a Number");
			return;
		}
		if (newNumRestrictions > 0) {
			switch(manager.getType()) {
			case LIGHTNING:
				//Set the max time
				manager.getLevel().setTimeLimit(newNumRestrictions);
				break;
			case PUZZLE:
				manager.getLevel().setMoveLimit(newNumRestrictions);
				break;
			case RELEASE:
				manager.getLevel().setMoveLimit(newNumRestrictions);
				break;
			}
		}
		else {
			System.err.println("Restriction should be above 0");
		}
		
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		handleTextChanged();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		handleTextChanged();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		handleTextChanged();
	}
}
