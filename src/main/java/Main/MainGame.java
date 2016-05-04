package Main;
import menelaus.view.game.GameWindowFrame;

/** Used to launch the KabaSuji Application. */
public class MainGame {
	
	/**
	 * create and open the Main Game window frame.
	 * @param args
	 */
	public static void main (String[] args){
		//Model m = new Model();

		
		// if using 'app' within the anonymous class generated below, must be marked final.
		final GameWindowFrame app = GameWindowFrame.getInstance();

		// state how to deal with leaving
//		app.addWindowListener(new WindowAdapter() {
//			public void windowClosing(WindowEvent e) {
//				app.dispose();
//			}
//		});

		app.setVisible(true);
	}
}
