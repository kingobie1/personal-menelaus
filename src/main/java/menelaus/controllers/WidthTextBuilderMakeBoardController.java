package menelaus.controllers;

import menelaus.model.BuilderManager;
import menelaus.view.BoardView;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Controller handles the width of the text.
 * @author Sanjay.
 *
 */
public class WidthTextBuilderMakeBoardController implements DocumentListener{
	BoardView boardView;
	BuilderManager manager;
	JTextField widthField;
	
	/**
	 * constructor.
	 * @param boardView
	 * @param manager
	 * @param widthField
	 */
	public WidthTextBuilderMakeBoardController(BoardView boardView, BuilderManager manager, JTextField widthField) {
		this.boardView = boardView;
		this.manager = manager;
		this.widthField = widthField;
	}

	void handleTextChanged(String newText) {
		int newWidth;
		try {
			newWidth = Integer.parseInt(newText);
			
		} catch (NumberFormatException e) {
			return;
		}
		this.manager.setSize(newWidth, this.manager.getHeight());

		this.boardView.repaint();
		this.widthField.repaint();
	}

	/**
	 * This method is used to determine if the new dimension we're setting makes this dimension the longest.
	 * @param newSideLen the new side length.
	 * @return Is this now the longest side?
	 */
	private boolean newMajorSide(int newSideLen) {
		return newSideLen > manager.getHeight();
	}
	@Override
	public void insertUpdate(DocumentEvent e) {
		handleTextChanged(this.widthField.getText());
	}
	@Override
	public void removeUpdate(DocumentEvent e) {
		handleTextChanged(this.widthField.getText());
	}
	@Override
	public void changedUpdate(DocumentEvent e) {
		handleTextChanged(this.widthField.getText());
	}
}
