package menelaus.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;

import menelaus.model.BuilderManager;
import menelaus.model.LevelsPackage;
import menelaus.util.LevelsPackagePersistenceUtil;
import menelaus.util.SoundManager;
import menelaus.util.SoundType;
import menelaus.view.builder.BuilderWindowFrame;
import menelaus.view.builder.HomeScreen;

/**
 * Controller that handles saving a level in the builder.
 * @author Sanjay.
 *
 */
public class SaveLevelButtonBuilderMakeLevelController implements ActionListener{
	BuilderManager manager;
	
	/**
	 * constructor.
	 * @param manager
	 */
	public SaveLevelButtonBuilderMakeLevelController(BuilderManager manager) {
		this.manager = manager;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		SoundManager.getInstance().playSound(SoundType.COMPLETESOUND);
		
		manager.cleanUpLevel();
		LevelsPackage thePackage = BuilderWindowFrame.getInstance().getCurrentPackage();
		if (thePackage == null) {
			thePackage = new LevelsPackage();
		}
		
		thePackage.addLevel(manager.getLevel());
		
		JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File (System.getProperty("user.home")));

        try {
            if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
        		try {
        			LevelsPackagePersistenceUtil.toFile(thePackage, chooser.getSelectedFile());
        		} catch (IOException e1) {
        		}	
            }
        } catch (Exception e2) {
        }

		BuilderWindowFrame.getInstance().swapPanel(new HomeScreen());
	}
}
