package menelaus.view.game;

import menelaus.model.LevelsPackage;
import menelaus.util.LevelsPackagePersistenceUtil;
import menelaus.util.SavedGamesUtil;
import menelaus.util.SoundManager;
import menelaus.view.KabasujiPanel;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.URL;
import java.nio.file.Path;

/**
 * The window frame that holds all the panels.
 * @author vouldjeff
 * @author Obatola Seward-Evans
 *
 */
public class GameWindowFrame extends JFrame {
    private final static String DEFAULT_PACKAGE_NAME = "default-levels.boba";
    private final static String SAVED_GAMES_NAME = "saved-games.boba";

    private KabasujiPanel contentPane;
    private static GameWindowFrame instance = new GameWindowFrame();

    private LevelsPackage levelsPackage;
    private LevelsPackage recentlyPLayedLevelsPackage;
    private SavedGamesUtil savedGamesUtil;

    /**
     * Create the frame.
     */
    private GameWindowFrame() {
        // initialize sound manager.
        SoundManager.getInstance();

        setBounds(100, 100, GameViewConfigurations.panelWidth, GameViewConfigurations.panelHeight);

        // Run the splash screen for 2 seconds then swap to main menu:
        contentPane = new SplashScreen();
        this.add(contentPane);
        setVisible(true);
        setResizable(false);
        setName("KabaSuji");
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
//        	String dlPath = GameWindowFrame.getInstance().getClass().getResource(DEFAULT_PACKAGE_NAME).getFile();
//        	String dlPath = this.getClass().getResource(DEFAULT_PACKAGE_NAME).getFile();

    		File f = new File("default-levels.boba");
        	
    		levelsPackage = LevelsPackagePersistenceUtil.fromFile(f);
//	    	levelsPackage = LevelsPackagePersistenceUtil.fromFile(new File(dlPath));
	        savedGamesUtil = new SavedGamesUtil(new File(SAVED_GAMES_NAME));
	        recentlyPLayedLevelsPackage = levelsPackage;


            contentPane = new HomeScreen();
//			Thread.sleep(2000);
			// TODO: change back
			Thread.sleep(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.swapPanel(contentPane);
	}

	/**
	 * Gives access to the gameWindow
	 * @return instance  the game window
	 */
	public static GameWindowFrame getInstance(){
		return instance;
	}
	
	/**
	 * sets loaded recently played levels package.
	 * @return boolean - true if level was set
	 */
	public LevelsPackage getLevelsPackage() {
		return levelsPackage;
	}
	
	/**
	 * sets recently played levels package.
	 * @param inputLevelsPackage the current levels package.
	 * @return boolean - true if level was set
	 */
	public boolean setRecentlyPLayedLevelsPackage(LevelsPackage inputLevelsPackage){
		recentlyPLayedLevelsPackage = inputLevelsPackage;
		return true;
	}
	
	/**
	 * gets loaded recently played levels package.
	 * @return boolean - true if level was set
	 */
	public LevelsPackage getRecentlyPLayedLevelsPackage() {
		return recentlyPLayedLevelsPackage;
	}
	
	/**
	 * Returns the SavedGamesUtil.
	 * @return savedGamesUtil
	 */
	public SavedGamesUtil getSavedGamesUtil() {
		return savedGamesUtil;
	}

	/**
	 * Swaps panel in GameWindow to the given panel
	 * @param panel  the panel you want to switch to.
	 */
	public void swapPanel(KabasujiPanel panel) {
        contentPane = panel;
        this.getContentPane().removeAll();
        this.getContentPane().add(panel);
        //setVisible(true);
        validate();
    }

    /**
     * Close game
     */
    public void close() {
        System.exit(0);
    }

    /**
     * Gets the panel that is being displayed.
     * @return contentPane
     */
    public KabasujiPanel getPanel() {
        return contentPane;
    }
}
