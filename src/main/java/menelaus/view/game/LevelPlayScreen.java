package menelaus.view.game;

import menelaus.controllers.PieceController;
import menelaus.controllers.PieceSelectionController;
import menelaus.controllers.RestartController;
import menelaus.controllers.ToWinScreenController;
import menelaus.model.GameManager;
import menelaus.model.Level;
import menelaus.model.basic.LevelType;
import menelaus.model.events.GameEndListener;
import menelaus.model.events.GameEndReason;
import menelaus.model.events.GameTickListener;
import menelaus.util.SavedGamesUtil;
import menelaus.view.BoardView;
import menelaus.view.BullpenView;
import menelaus.view.KabasujiPanel;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * The level play screen.
 * @author fegan
 * @author vouldjeff
 * @author Obatola Seward-Evans
 */
public class LevelPlayScreen extends KabasujiPanel {

	GameManager gameManager;
	BoardView boardView;
	BullpenView bullpenView;
	JButton btnRestart;
	JButton btnExitButton;

	JLabel labelCountDown;
	
	private void gameEnded(GameEndReason reason) {
		SavedGamesUtil savedGamesUtil = GameWindowFrame.getInstance().getSavedGamesUtil();
		try {
			savedGamesUtil.addLevelStars(gameManager.getLevelStars());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Cannot write your progress to disk.");
		}
		
		// If restart do not go to winscreen
		if (reason != GameEndReason.RESTART) {
			ToWinScreenController controller = new ToWinScreenController(gameManager.getLevelStars(), reason, gameManager.getLevel());
			controller.actionPerformed(null);
		}
	}

	private void initGameManager(Level level) {
		gameManager = new GameManager(level);
		gameManager.addGameEndListener(new GameEndListener() {
			public void end(GameEndReason reason) {
				gameEnded(reason);
			}
		});

		gameManager.addGameTickListener(new GameTickListener() {
			public void tick() {
				if (gameManager.getLevel().getType() == LevelType.LIGHTNING) {
					labelCountDown.setText("Time left: " + (gameManager.getLevel().getTimeLimit() - gameManager.getTimePassed()));
				} else {
					labelCountDown.setText("Moves left: " + (gameManager.getLevel().getMoveLimit() - gameManager.getMovesMade()));
				}
			}
		});
	}

	/**
	 * get bullpen view.
	 * @return The current BullpenView
	 */
	public BullpenView getBullpenView(){
		return bullpenView;
	}

	/**
	 * get bullpen view.
	 * @return the current boardView
	 */
	public BoardView getBoardView(){
		return boardView;
	}

	/**
	 * Create the panel.
	 * @param inputLevel 
	 * @throws Exception 
	 */
	public LevelPlayScreen(Level inputLevel) throws Exception {
		
		if (inputLevel == null) {
			throw new Exception("cannot pass null manager into LevelPlayScreen constructor");
		}
		
		initGameManager(inputLevel);

		setBounds(100, 100, GameViewConfigurations.panelWidth, GameViewConfigurations.panelHeight);

		JScrollPane scrollPane = new JScrollPane();

		JLabel labelLevelName = new JLabel(gameManager.getLevel().getType().toString() + " LEVEL: " + gameManager.getLevel().getName());
		labelLevelName.setMaximumSize(new Dimension(248, 16));
		
		if (gameManager.getLevel().getType() == LevelType.LIGHTNING) {
			labelCountDown = new JLabel("Time left: " + gameManager.getLevel().getTimeLimit());
		} else {
			labelCountDown = new JLabel("Moves left: " + gameManager.getLevel().getMoveLimit());
		}

        /* BUTTONS */
		
		/** Restart Button. */
		btnRestart = new JButton("RESTART");
		btnRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameManager.userRestartsGame();
				RestartController restartController = new RestartController(gameManager.getLevel());
				restartController.actionPerformed(e);
			}
		});
				
				
		
		/** Exit Button. */
		btnExitButton = new JButton("EXIT");
		btnExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameManager.userEndsGame();
				
				// TODO: find way to add reason.
				ToWinScreenController controller = new ToWinScreenController(gameManager.getLevelStars(), null, gameManager.getLevel());
				controller.actionPerformed(e);
			}
		});

		/** Create Board View */
		boardView = new BoardView(gameManager.getLevel().getBoard(), gameManager.getLevel());

		/** Create BullpenView */
		bullpenView = new BullpenView(gameManager.getLevel().getBullpen());

		GroupLayout gl_contentPane = new GroupLayout(this);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(17)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(labelCountDown)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnExitButton)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnRestart))
								.addComponent(labelLevelName, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
							.addGap(18)
							.addComponent(boardView, GroupLayout.PREFERRED_SIZE, 700, GroupLayout.PREFERRED_SIZE)
							.addGap(7))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(725, Short.MAX_VALUE))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(34, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(boardView, GroupLayout.PREFERRED_SIZE, 700, GroupLayout.PREFERRED_SIZE)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addComponent(labelLevelName)
							.addGap(18)
							.addComponent(labelCountDown)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnRestart)
								.addComponent(btnExitButton))
							.addGap(18)
							.addComponent(scrollPane)))
					.addContainerGap())
		);

		scrollPane.setViewportView(bullpenView);
		this.setLayout(gl_contentPane);

		PieceController pc = new PieceController(this, gameManager);
		boardView.addMouseListener(pc);
		boardView.addMouseMotionListener(pc);
		boardView.addMouseWheelListener(pc);

		PieceSelectionController psc = new PieceSelectionController(bullpenView, gameManager);
		bullpenView.addMouseListener(psc);

		gameManager.startGame();
	}
}