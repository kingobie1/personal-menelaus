package menelaus.view.builder;

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
		
		JLabel lblMenelausKabasujiBuilder = new JLabel("Menelaus KabaSuji Builder");
		lblMenelausKabasujiBuilder.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		
		JLabel lblObaDimitarFrank = new JLabel("Oba, Dimitar, Frank, Sanjay & Matt");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(246)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblMenelausKabasujiBuilder)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(167)
							.addComponent(lblObaDimitarFrank)))
					.addContainerGap(245, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(334)
					.addComponent(lblMenelausKabasujiBuilder)
					.addGap(18)
					.addComponent(lblObaDimitarFrank)
					.addContainerGap(334, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}

}
