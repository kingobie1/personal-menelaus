package menelaus.controllers;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JToggleButton;

import menelaus.model.BuilderManager;
import menelaus.model.dataholders.ReleasePaneData;


/**
 * Enables Release Mode for Builder Make Level Screen.
 * @author Sanjay
 *
 */
public class EnableReleaseModeBuilderMakeLevelController implements ActionListener{

	BuilderManager manager;
	JToggleButton enableButton;
	ReleasePaneData data;
	
	/**
	 * constructor.
	 * @param manager
	 * @param enableButton
	 * @param data
	 */
	public EnableReleaseModeBuilderMakeLevelController(BuilderManager manager, JToggleButton enableButton, ReleasePaneData data) {
		this.manager = manager;
		this.enableButton = enableButton;
		this.data = data;
	}
	
	public void actionPerformed(java.awt.event.ActionEvent e) {
		if(this.enableButton.isSelected()) {
			//It was changed to enabled
			this.manager.setToReleaseMode(this.data.generateSetItem());
		}
		else {
			//It was changed to disabled
			this.manager.setToBoardMode();
		}
	}
	
}
