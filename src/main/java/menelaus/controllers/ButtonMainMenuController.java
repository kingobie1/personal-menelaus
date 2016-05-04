package menelaus.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import menelaus.util.SoundManager;
import menelaus.util.SoundType;
import menelaus.view.game.GameWindowFrame;
import menelaus.view.game.HomeScreen;

/**
 * Controller that handles going to the home screen.
 * @author Obatola Seward-Evans
 *
 */
public class ButtonMainMenuController implements ActionListener {
	
	public void actionPerformed(ActionEvent e) {
		SoundManager.getInstance().playSound(SoundType.BUTTONSOUND);
		GameWindowFrame.getInstance().swapPanel(new HomeScreen());
	}
}
