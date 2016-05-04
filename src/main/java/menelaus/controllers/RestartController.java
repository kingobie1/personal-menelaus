package menelaus.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import menelaus.model.Level;
import menelaus.util.SoundManager;
import menelaus.util.SoundType;
import menelaus.view.game.GameWindowFrame;
import menelaus.view.game.LevelPlayScreen;


/** 
 * handles restarts.
 * @author Obatola Seward-Evans
 *
 */
public class RestartController implements ActionListener {
	/** the level you plan to restart to */
	Level previousLevel;
	
	/**
	 * constructor.
	 * @param previousLevel
	 */
	public RestartController(Level previousLevel){
		this.previousLevel = previousLevel;
	}
	
	public void actionPerformed(ActionEvent e) {
		SoundManager.getInstance().playSound(SoundType.RESTARTSOUND);		
		
		try {
			GameWindowFrame.getInstance().swapPanel(new LevelPlayScreen(previousLevel));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
	}

}
