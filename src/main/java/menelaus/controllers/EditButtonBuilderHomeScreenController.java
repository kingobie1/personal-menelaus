package menelaus.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import menelaus.model.LevelsPackage;
import menelaus.util.LevelsPackagePersistenceUtil;
import menelaus.util.SoundManager;
import menelaus.util.SoundType;
import menelaus.view.builder.BuilderLevelBuilderScreen;
import menelaus.view.builder.BuilderSelectScreen;
import menelaus.view.builder.BuilderWindowFrame;

/**
 * Controller that handles going to the builder select screen.
 * @author Sanjay
 *
 */
public class EditButtonBuilderHomeScreenController implements ActionListener{


	@Override
	public void actionPerformed(ActionEvent e) {
		SoundManager.getInstance().playSound(SoundType.BUTTONSOUND);
		
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new File (System.getProperty("user.home")));
		
		fc.showOpenDialog(null);

		File selectedFile = fc.getSelectedFile();
		
		if (selectedFile == null) {
			return;
		}
		
		try {
			LevelsPackage levelsPackage = LevelsPackagePersistenceUtil.fromFile(selectedFile);
			BuilderWindowFrame.getInstance().setCurrentPackage(levelsPackage);
			
			BuilderWindowFrame.getInstance().swapPanel(new BuilderSelectScreen());
		} catch (ClassNotFoundException e1) {
			JOptionPane.showMessageDialog(null, "Alert! Selected file is not valid.");
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Alert! Selected file is not valid.");
		}
	}
	
}
