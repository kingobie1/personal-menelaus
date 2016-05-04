package menelaus.controllers;

import menelaus.model.Level;
import menelaus.util.SoundManager;
import menelaus.util.SoundType;
import menelaus.view.game.GameWindowFrame;
import menelaus.view.game.LevelPlayScreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

/**
 * Controller that handles going to the level play screen of the next level.
 * @author fegan
 * @author vouldjeff
 */
public class ButtonContinueController implements ActionListener{

    public void actionPerformed(ActionEvent actionEvent) {
    	SoundManager.getInstance().playSound(SoundType.BUTTONSOUND);
		GameWindowFrame frame = GameWindowFrame.getInstance();
		Level nextLevel;
		nextLevel = frame.getSavedGamesUtil().getNextPlayableLevelInPackage(frame.getRecentlyPLayedLevelsPackage());
		
    	
    	if (nextLevel == null) {
    		JOptionPane.showMessageDialog(null, "You've played all levels!");
    		return;
    	}
    	
        try {
			GameWindowFrame.getInstance().swapPanel(new LevelPlayScreen(nextLevel));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
