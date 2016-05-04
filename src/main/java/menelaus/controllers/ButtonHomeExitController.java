package menelaus.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import menelaus.util.SoundManager;
import menelaus.util.SoundType;
import menelaus.view.game.GameWindowFrame;
import menelaus.view.game.HomeScreen;

/**
 * Controller that handles quitting the application.
 * @author Obatola Seward-Evans
 *
 */
public class ButtonHomeExitController implements ActionListener {
	
	// The home screen JFrame that contains the levels button:
	HomeScreen home;
	
	/**
	 * constructor.
	 * @param home
	 */
	public ButtonHomeExitController(HomeScreen home) {
		this.home = home;
	}
	
	/**
	 * constructor.
	 */
	public ButtonHomeExitController() {
	}
	
	public void actionPerformed(ActionEvent e) {
		SoundManager.getInstance().playSound(SoundType.EXITSOUND);
		GameWindowFrame.getInstance().close();
	}

}
