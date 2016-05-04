package menelaus.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import menelaus.util.SoundManager;
import menelaus.util.SoundType;
import menelaus.view.builder.BuilderWindowFrame;
import menelaus.view.builder.HomeScreen;

/**
 * Controller that handles going to the main menu for the builder.
 * @author Obatola Seward-Evans
 *
 */
public class ButtonBuilderMainMenuController implements ActionListener {

	public void actionPerformed(ActionEvent e) {
		SoundManager.getInstance().playSound(SoundType.BUTTONSOUND);
		BuilderWindowFrame.getInstance().swapPanel(new HomeScreen());
	}
}
