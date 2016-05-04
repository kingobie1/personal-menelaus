package menelaus.view.game;

import menelaus.controllers.ButtonLoadLevelController;
import menelaus.controllers.ButtonMainMenuController;
import menelaus.view.KabasujiPanel;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 * Extra screen where you can load levels.
 * @author Obatola Seward-Evans
 *
 */
public class ExtraScreen extends KabasujiPanel {

	/**
	 * Create the panel.
	 */
	public ExtraScreen() {
		
		/* Buttons */
		JButton btnLoadLevel = new JButton("Load Level");
		btnLoadLevel.addActionListener(new ButtonLoadLevelController());
		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.addActionListener(new ButtonMainMenuController());
		
		/* CONNECT BUTTON TO CONTROLLERS */
		btnMainMenu.addActionListener(new ButtonMainMenuController());
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(444)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnMainMenu)
								.addComponent(btnLoadLevel))
						.addContainerGap(444, Short.MAX_VALUE))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(343)
						.addComponent(btnLoadLevel)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnMainMenu)
						.addContainerGap(343, Short.MAX_VALUE))
				);
		setLayout(groupLayout);

	}

}
