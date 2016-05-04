package menelaus.view.builder;

import menelaus.model.LevelsPackage;
import menelaus.util.SoundManager;
import menelaus.view.KabasujiPanel;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Main application window for the builder
 *
 */
public class BuilderWindowFrame extends JFrame {

	private KabasujiPanel contentPane;
	private static BuilderWindowFrame instance = new BuilderWindowFrame();
	
	private LevelsPackage currentPackage;
	/**
	 * Create the frame.
	 */
	private BuilderWindowFrame() {
		
		// initialize sound manager.
		SoundManager.getInstance();

		setBounds(KabasujiPanel.START_X, KabasujiPanel.START_Y, KabasujiPanel.WIDTH, KabasujiPanel.HEIGHT);
		
		// Run the splash screen for 2 seconds then swap to main menu:
		contentPane = new SplashScreen();
		this.add(contentPane);
		setVisible(true);
		setResizable(false);
		setName("KabaSuji Builder");

		//set close confirmation dialog
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				String ObjButtons[] = {"Yes", "No"};
				int promptResult = JOptionPane.showOptionDialog(null, "Are you sure you want to exit?", "KabaSuji", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, ObjButtons, ObjButtons[1]);
				if (promptResult == JOptionPane.YES_OPTION) {
					dispose();
				} else System.out.println("Not closing");
			}
		});
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		contentPane = new HomeScreen();
		this.swapPanel(contentPane);

	}

	/**
	 * Gives access to the gameWindow
	 * @return instance  the game window
	 */
	public static BuilderWindowFrame getInstance(){
		return instance;
	}

	/**
	 * Swaps panel in BuilderWindow
	 *  to the given panel
	 * @param panel  the panel you want to switch to.
	 * 
	 */
	public void swapPanel(KabasujiPanel panel) {
        contentPane = panel;
		this.getContentPane().removeAll();
		this.getContentPane().add( panel );

		validate();
	}
	
	/**
	 * Close game
	 */
	public void close(){
		System.exit(0);
	}

	/**
	 * Get panel in BuilderWindow
	 * @return the current panel
	 */
	public KabasujiPanel getPanel() {
		return contentPane;
	}
	
	/**
	 * Gets the current LevelsPackage loaded in the builder
	 * @return current LevelsPackage
	 */
	public LevelsPackage getCurrentPackage() {
		return this.currentPackage;
	}
	
	/**
	 * Sets the current LevelsPackage loaded in the builder to the given LevelsPackage
	 * @param newPackage
	 */
	public void setCurrentPackage(LevelsPackage newPackage) {
		this.currentPackage = newPackage;
	}
}
