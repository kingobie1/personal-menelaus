package menelaus.controllers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

import menelaus.model.BuilderManager;
import menelaus.model.dataholders.ReleasePaneData;
import menelaus.util.SoundManager;
import menelaus.util.SoundType;
import menelaus.view.builder.BuilderReleasePane;

/**
 * Handles the Release Pane in the builder.
 * @author Sanjay.
 *
 */
public class ReleasePaneBuilderMakeLevelController implements MouseListener {

	
	BuilderReleasePane pane;
	ReleasePaneData data;
	BuilderManager manager;
	
	/**
	 * constructor.
	 * @param manager
	 * @param data
	 * @param pane
	 */
	public ReleasePaneBuilderMakeLevelController(BuilderManager manager, ReleasePaneData data, BuilderReleasePane pane){
		this.manager = manager;
		this.data = data;
		this.pane = pane;
	}

	@Override
	/**
	 * Handles the mouse click
	 */
	public void mouseClicked(MouseEvent e) {
		SoundManager.getInstance().playSound(SoundType.BUTTONSOUND);
		if(SwingUtilities.isLeftMouseButton(e)) {
			handleIncrementNumber();
		}
		else if(SwingUtilities.isRightMouseButton(e)){
			handleChangeColor();
		}
		refreshView();
	}
	/**
	 * Increments the number in the release Pane data
	 */
	public void handleIncrementNumber() {
		this.data.incrementNumber();
	}
	/**
	 * Changes the number in the release Pane data
	 */
	public void handleChangeColor() {
		this.data.changeColor();
	}
	/**
	 * Refreshes the Boundary class and repaints the screen
	 */
	public void refreshView() {
		manager.setReleaseItem(this.data.generateSetItem());
		this.pane.setItem(this.data.generateSetItem());
		this.pane.repaint();
	}

	@Override
	/**
	 * Unimplemented method required for extension
	 */
	public void mousePressed(MouseEvent e) {
		// Do Nothing
		
	}

	@Override
	/**
	 * Unimplemented method required for extension
	 */
	public void mouseReleased(MouseEvent e) {
		// Do Nothing
		
	}

	@Override
	/**
	 * Unimplemented method required for extension
	 */
	public void mouseEntered(MouseEvent e) {
		// Do Nothing
		
	}

	@Override
	/**
	 * Unimplemented method required for extension
	 */
	public void mouseExited(MouseEvent e) {
		// Do Nothing
		
	}
	
	
	

}
