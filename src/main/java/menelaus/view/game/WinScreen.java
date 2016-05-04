package menelaus.view.game;

import menelaus.controllers.ButtonContinueController;
import menelaus.controllers.ButtonLevelsController;
import menelaus.controllers.RestartController;
import menelaus.model.Level;
import menelaus.model.LevelStars;
import menelaus.model.events.GameEndReason;
import menelaus.util.SoundManager;
import menelaus.util.SoundType;
import menelaus.view.KabasujiPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Contains information necessary to display a win screen once a game has ended 
 * Displays where game was completed successfully or ended for any other reason
 *
 */
public class WinScreen extends KabasujiPanel  {
	LevelStars stars;
	GameEndReason reason;
	
	/** the string in which the lblScore presents. */
	String scoreLabel;
	
	/** the string in which the lblCongratulations presents. */
	String congratsLabel;

	
	/** the count of stars for the current level. */
	int starCount;
	
	private KabasujiPanel contentPane;
	
	BufferedImage starIMG;
	BufferedImage emptyStarIMG;
	ImageIcon starIcon;
	ImageIcon emptyStarIcon;
	String starString = "str";
	
	JLabel lblStar1;
	JLabel lblStar2;
	JLabel lblStar3;

	/**
	 * Create the frame.
	 * @param starsParams
	 * @param reason
	 * @param inputLevel
	 * @author Obatola Seward-Evans
	 */
	public WinScreen(LevelStars starsParams, GameEndReason reason, Level inputLevel) {
		this.stars = starsParams;
		this.reason = reason;
		contentPane = this;
		int starDemension = 50;
		
		// Set star count
		if ( stars == null ){
			starCount = 0;
		} else {
			starCount = this.stars.getStarsCount();	
		}
		
		try {
			starIMG = ImageIO.read(this.getClass().getResource("/assets/star.png"));
			emptyStarIMG = ImageIO.read(this.getClass().getResource("/assets/empty_star.png"));
			starIcon = new ImageIcon(starIMG.getScaledInstance(starDemension, starDemension, Image.SCALE_DEFAULT));
			emptyStarIcon = new ImageIcon(emptyStarIMG.getScaledInstance(starDemension, starDemension, Image.SCALE_DEFAULT));
			starString = null;
		} catch (Exception e) {
			System.out.println("WinScreen: the image isn't read");
		}
		
		Level restartLevel = inputLevel;
	
		if ( starCount < 1 ) {
			congratsLabel = "You Lose!";
			SoundManager.getInstance().playSound(SoundType.LOSESOUND);
		} else if (starCount == 1) {
			congratsLabel = "Almost there!";
			SoundManager.getInstance().playSound(SoundType.WINSOUND);
		} else {
			congratsLabel = "Congratulations!!!";
			SoundManager.getInstance().playSound(SoundType.WINSOUND);
		}
		
		// Create the string in which the lblScore presents.
		scoreLabel = new StringBuilder().append("Score: ")
				.append(String.valueOf( starCount )).toString();
		
		setBounds(KabasujiPanel.START_X, KabasujiPanel.START_Y, KabasujiPanel.WIDTH, KabasujiPanel.HEIGHT);
		
		/** Continue Button. */
		JButton btnContinue = new JButton("Continue");
		btnContinue.setBounds(415, 338, 170, 57);
		btnContinue.addActionListener(new ButtonContinueController());
		
		/** Restart Button. */
		JButton btnRestart = new JButton("Play Again");
		btnRestart.setBounds(415, 407, 170, 59);
		if (restartLevel != null) {
			btnRestart.addActionListener(new RestartController(restartLevel));
		}
		
		/** Exit Button. */
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(415, 478, 170, 58);
		btnExit.addActionListener(new ButtonLevelsController());
		
		/** Congratulations label. */
		JLabel lblCongratulations = new JLabel( congratsLabel );
		lblCongratulations.setBounds(354, 208, 291, 36);
		lblCongratulations.setHorizontalAlignment(SwingConstants.CENTER);
		lblCongratulations.setFont(new Font("Lucida Grande", Font.PLAIN, 32));
		
		lblStar1 = new JLabel(starString);
		lblStar1.setBounds(415, 268, starDemension, starDemension);
		lblStar2 = new JLabel(starString);
		lblStar2.setBounds(475, 268, starDemension, starDemension);
		lblStar3 = new JLabel(starString);
		lblStar3.setBounds(535, 268, starDemension, starDemension);

		
		setStarIcons();
		setLayout(null);
		add(lblCongratulations);
		add(btnRestart);
		add(btnExit);
		add(btnContinue);
		add(lblStar1);
		add(lblStar2);
		add(lblStar3);
	}
	
	private void setStarIcons() {
		
		switch (starCount) {
		case 1:
			lblStar1.setIcon(starIcon);
			lblStar2.setIcon(emptyStarIcon);
			lblStar3.setIcon(emptyStarIcon);
			break;
			
		case 2:
			lblStar1.setIcon(starIcon);
			lblStar2.setIcon(starIcon);
			lblStar3.setIcon(emptyStarIcon);
			break;
			
		case 3:
			lblStar1.setIcon(starIcon);
			lblStar2.setIcon(starIcon);
			lblStar3.setIcon(starIcon);
			break;
			
		default: // case 0
			lblStar1.setIcon(emptyStarIcon);
			lblStar2.setIcon(emptyStarIcon);
			lblStar3.setIcon(emptyStarIcon);
			break;
		}
	}
	
}
