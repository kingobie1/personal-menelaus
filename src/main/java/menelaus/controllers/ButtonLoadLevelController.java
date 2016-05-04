package menelaus.controllers;

import menelaus.model.LevelsPackage;
import menelaus.util.LevelsPackagePersistenceUtil;
import menelaus.view.game.ExtraScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Controller that handles loading a level.
 * @author Obatola Seward-Evans
 *
 */
public class ButtonLoadLevelController implements ActionListener{

	public void actionPerformed(ActionEvent e) {
		
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new File (System.getProperty("user.home")));
		
		fc.showOpenDialog(new ExtraScreen());

		File selectedFile = fc.getSelectedFile();
		
		if (selectedFile == null) {
			return;
		}
		
		try {
			LevelsPackage levelsPackage = LevelsPackagePersistenceUtil.fromFile(selectedFile);
			
			ButtonLevelsController blc = new ButtonLevelsController(levelsPackage);
			blc.actionPerformed(null);
		} catch (ClassNotFoundException e1) {
			JOptionPane.showMessageDialog(null, "Alert! Selected file is not valid.");
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Alert! Selected file is not valid.");
		}
		
	}

}
