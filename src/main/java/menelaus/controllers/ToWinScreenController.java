package menelaus.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import menelaus.model.Level;
import menelaus.model.LevelStars;
import menelaus.model.events.GameEndReason;
import menelaus.view.game.GameWindowFrame;
import menelaus.view.game.WinScreen;

/**
 * Brings player to win screen.
 * @author Obatola Seward-Evans
 */
public class ToWinScreenController implements ActionListener{

	Level level;
	LevelStars stars;
	GameEndReason reason;
	
	
	/**
	 * Constructor for ToWinScreenController.
	 * @param stars
	 * @param reason
	 * @param inputLevel
	 */
	public ToWinScreenController(LevelStars stars, GameEndReason reason, Level inputLevel){
		this.stars = stars;
		this.reason = reason;
		level = inputLevel;
	}
	
	public void actionPerformed(ActionEvent e) {
		GameWindowFrame frame = GameWindowFrame.getInstance();
		
		frame.swapPanel(new WinScreen(stars, reason, level));
	}

}
