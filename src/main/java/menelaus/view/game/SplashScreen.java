package menelaus.view.game;

import menelaus.view.KabasujiPanel;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.*;

/**
 * Contains the information necessary to display the splashscreen on load time
 *
 */
public class SplashScreen extends KabasujiPanel {

	/**
	 * Create the panel.
	 */
	public SplashScreen() {
		
		JLabel lblMenelausKabasuji = new JLabel("Menelaus KabaSuji");
		lblMenelausKabasuji.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		
		JLabel lblObaDimitarFrank = new JLabel("Oba, Dimitar, Frank, Sanjay & Matt");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(320)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblMenelausKabasuji)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(73)
							.addComponent(lblObaDimitarFrank)))
					.addContainerGap(319, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(334)
					.addComponent(lblMenelausKabasuji)
					.addGap(18)
					.addComponent(lblObaDimitarFrank)
					.addContainerGap(334, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}

}
