package menelaus.view.game;

import menelaus.controllers.ButtonContinueController;
import menelaus.controllers.ButtonHomeExitController;
import menelaus.controllers.ButtonHomeExtraController;
import menelaus.controllers.ButtonLevelsController;
import menelaus.util.SoundManager;
import menelaus.util.SoundType;
import menelaus.view.KabasujiPanel;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This was the home screen for the builder.
 * @author vouldjeff
 * @author fegan
 */
public class HomeScreen extends KabasujiPanel {
	JLabel lblMute;
	
	/**
	 * Create the frame.
	 */
	public HomeScreen() {
		JButton btnContinue = new JButton("Continue");

        JButton btnLevels = new JButton("Levels");

        JButton btnExtra = new JButton("Extra");

        JButton btnExit = new JButton("Exit");

		btnContinue.addActionListener(new ButtonContinueController());
		btnLevels.addActionListener(new ButtonLevelsController(null));
		btnExit.addActionListener(new ButtonHomeExitController(this)); // Exit.      
		btnExtra.addActionListener(new ButtonHomeExtraController()); // Extra.

		JLabel lblKabasuji = new JLabel("KabaSuji");
		lblKabasuji.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		
		/** mute button. */
		lblMute = new JLabel("");
		lblMute.addMouseListener(new MouseAdapter() {			
			@Override
			public void mouseClicked(MouseEvent e) {
				updateMute();
			}
		});

        try {
            lblMute.setIcon(new ImageIcon(HomeScreen.class.getResource("/com/sun/javafx/webkit/prism/resources/mediaMute.png")));
        } catch (Exception e) {
            //do nothing
        }

        if (SoundManager.getInstance().isMute()) {
            try {
                lblMute.setIcon(new ImageIcon(HomeScreen.class.getResource("/com/sun/javafx/webkit/prism/resources/mediaUnmute.png")));
            } catch (Exception e) {
                //do nothing
            }
        } else {
            try {
                lblMute.setIcon(new ImageIcon(HomeScreen.class.getResource("/com/sun/javafx/webkit/prism/resources/mediaMute.png")));
            } catch (Exception e) {
                //do nothing
            }
        }
		
		GroupLayout gl_contentPane = new GroupLayout(this);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(428, Short.MAX_VALUE)
					.addComponent(lblKabasuji)
					.addGap(436))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(441)
					.addComponent(btnContinue, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(448))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(441)
					.addComponent(btnLevels, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
					.addGap(448))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(441)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnExit, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
						.addComponent(btnExtra, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
					.addGap(448))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblMute)
					.addContainerGap(964, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(289, Short.MAX_VALUE)
					.addComponent(lblKabasuji)
					.addGap(12)
					.addComponent(btnContinue)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnLevels)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnExtra)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnExit)
					.addGap(243)
					.addComponent(lblMute)
					.addContainerGap())
		);
		this.setLayout(gl_contentPane);
	}
	
	private void updateMute(){
		SoundManager.getInstance().changeMute();
		if (SoundManager.getInstance().isMute()) {
			lblMute.setIcon(new ImageIcon(HomeScreen.class.getResource("/com/sun/javafx/webkit/prism/resources/mediaUnmute.png")));
		} else {
			lblMute.setIcon(new ImageIcon(HomeScreen.class.getResource("/com/sun/javafx/webkit/prism/resources/mediaMute.png")));
		}
		SoundManager.getInstance().playSound(SoundType.BUTTONSOUND);
	}
}
