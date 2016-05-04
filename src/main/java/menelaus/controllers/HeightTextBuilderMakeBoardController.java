package menelaus.controllers;

import menelaus.model.BuilderManager;
import menelaus.view.BoardView;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Controller handle the height of the text in the builder make board.
 * @author Sanjay.
 *
 */
public class HeightTextBuilderMakeBoardController implements DocumentListener {
    BoardView boardView;
    BuilderManager manager;
    JTextField heightField;

    /**
     * constructor.
     * @param boardView
     * @param manager
     * @param heightField
     */
    public HeightTextBuilderMakeBoardController(BoardView boardView, BuilderManager manager, JTextField heightField) {
        this.boardView = boardView;
        this.manager = manager;
        this.heightField = heightField;
    }

    /**
     * Resizes the board dimensions to math the input number. Chops out any pieces that aren't in the specified dimensions.
     * @param newText The new dimension
     */
    void handleTextChanged(String newText) {
        int newHeight;
        try {
            newHeight = Integer.parseInt(newText);

        } catch (NumberFormatException e) {
            return;
        }
        this.manager.setSize(this.manager.getWidth(), newHeight);

        this.boardView.repaint();
        this.heightField.repaint();
    }

    /**
     * This method is used to determine if the new dimension we're setting makes this dimension the longest.
     * @param newSideLen the new side length.
     * @return Is this now the longest side?
     */
    private boolean newMajorSide(int newSideLen) {
        return newSideLen > manager.getWidth();
    }

	@Override
	public void insertUpdate(DocumentEvent e) {
		handleTextChanged(this.heightField.getText());
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		handleTextChanged(this.heightField.getText());
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		handleTextChanged(this.heightField.getText());
	}
}
