package menelaus.controllers;

import menelaus.model.BuilderManager;
import menelaus.util.SoundManager;
import menelaus.util.SoundType;
import menelaus.view.builder.BuilderLevelBuilderScreen;
import menelaus.view.builder.BuilderWindowFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller that handles going to the BuilderLevelBuilderScreen.
 * @author Obatola Seward-Evans
 *
 */
public class ButtonBuilderStartController implements ActionListener {

    BuilderManager manager;

    /**
     * constructor.
     * @param manager manager of the builder.
     */
    public ButtonBuilderStartController(BuilderManager manager) {
        this.manager = manager;
    }

    @Override
	public void actionPerformed(ActionEvent e) {
		SoundManager.getInstance().playSound(SoundType.BUTTONSOUND);

        if(!isSizeValid(manager.getWidth(), manager.getHeight())){
            JOptionPane.showOptionDialog(null, "Please check board dimensions.","Invalid Dimensions", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);
            return;
        }

		try {
			BuilderWindowFrame.getInstance().swapPanel(new BuilderLevelBuilderScreen(manager));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

    /**
     * Figures out if board dimensions are valid or against  regulation.
     * @param x The x dimension
     * @param y the y dimension
     * @return whether the board is valid
     */
    boolean isSizeValid(int x, int y){
        return (x <= 12) && (x >= 0) && (y <= 12) && (y >= 0);
    }
}