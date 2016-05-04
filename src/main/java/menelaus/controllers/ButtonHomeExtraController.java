package menelaus.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import menelaus.util.SoundManager;
import menelaus.util.SoundType;
import menelaus.view.game.ExtraScreen;
import menelaus.view.game.GameWindowFrame;

/**
 * Controller that handles going to the extra screen.
 * @author Obatola Seward-Evans
 *
 */
public class ButtonHomeExtraController implements ActionListener {

	// The home screen JFrame that contains the levels button:

	public void actionPerformed(ActionEvent e) {
		SoundManager.getInstance().playSound(SoundType.BUTTONSOUND);
		GameWindowFrame.getInstance().swapPanel(new ExtraScreen());
	}
}
