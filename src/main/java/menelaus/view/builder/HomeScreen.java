package menelaus.view.builder;

import menelaus.controllers.ButtonBuilderLevelSelectController;
import menelaus.controllers.ButtonHomeExitController;
import menelaus.controllers.EditButtonBuilderHomeScreenController;
import menelaus.view.KabasujiPanel;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.*;

/**
 * Contains information necessary to display the homescreen of the builder
 *
 */
public class HomeScreen extends KabasujiPanel {
	/**
	 * Create the panel.
	 */
	public HomeScreen() {

		JLabel lblKabasujiBuilder = new JLabel("KabaSuji Builder");
		lblKabasujiBuilder.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		
		JButton btnNewButton = new JButton("New Package");
		btnNewButton.addActionListener(new ButtonBuilderLevelSelectController());
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ButtonHomeExitController());
		
		JButton btnEditLevel = new JButton("Add Level to Package");
		btnEditLevel.addActionListener(new EditButtonBuilderHomeScreenController());
		
		GroupLayout gl_contentPane = new GroupLayout(this);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnExit, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(406)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblKabasujiBuilder)
								.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
								.addComponent(btnEditLevel, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE))))
					.addGap(396))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(290, Short.MAX_VALUE)
					.addComponent(lblKabasujiBuilder)
					.addGap(24)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnEditLevel, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
					.addGap(8)
					.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
					.addGap(191))
		);
		gl_contentPane.linkSize(SwingConstants.VERTICAL, new Component[] {lblKabasujiBuilder, btnNewButton, btnExit});
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {lblKabasujiBuilder, btnNewButton, btnExit});
		this.setLayout(gl_contentPane);
	}
}
