package menelaus.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import menelaus.util.SoundManager;
import menelaus.util.SoundType;
import menelaus.view.builder.BuilderWindowFrame;

/**
 * Controller that handles quiting the window for the builder.
 * @author Obatola Seward-Evans
 *
 */
public class ButtonBuilderExitController implements ActionListener {

	public void actionPerformed(ActionEvent e) {
		SoundManager.getInstance().playSound(SoundType.EXITSOUND);
		BuilderWindowFrame.getInstance().close();		
	}

}
