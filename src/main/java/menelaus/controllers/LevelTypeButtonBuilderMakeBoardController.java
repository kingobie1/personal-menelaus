package menelaus.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import menelaus.model.BuilderManager;
import menelaus.model.basic.LevelType;
import menelaus.util.SoundManager;
import menelaus.util.SoundType;
import menelaus.view.builder.BuilderSelectScreen;

/**
 * Controller handles the level type button response.
 * @author Sanjay
 *
 */
public class LevelTypeButtonBuilderMakeBoardController implements ActionListener{

	BuilderManager manager;
	LevelType type;
	BuilderSelectScreen screen;
	
	/**
	 * constructor.
	 * 
	 * @param manager
	 * @param type
	 * @param screen
	 */
	public LevelTypeButtonBuilderMakeBoardController(BuilderManager manager, LevelType type, BuilderSelectScreen screen) {
		this.manager = manager;
		this.type = type;
		this.screen = screen;
	}
	
	public void actionPerformed(ActionEvent e) {
		SoundManager.getInstance().playSound(SoundType.BUTTONSOUND);
		handleClick();
	}
	
	void handleClick() {
		manager.setLevelType(this.type);
		System.out.println(manager.getLevel().getType().name());
	}

}
