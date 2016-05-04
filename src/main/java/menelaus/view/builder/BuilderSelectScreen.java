package menelaus.view.builder;

import menelaus.controllers.*;
import menelaus.model.BuilderManager;
import menelaus.model.basic.LevelType;
import menelaus.view.BoardView;
import menelaus.view.KabasujiPanel;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 * Contains the information necessary to display the builder select screen
 *
 */
public class BuilderSelectScreen extends KabasujiPanel {

    private JTextField txtInsertName;
    private JTextField txtWidth;
    private JTextField txtHeight;

    ButtonGroup levelTypeButtons;
    JToggleButton btnPuzzle;
    JToggleButton btnLightning;
    JToggleButton btnRelease;

    BuilderManager manager;
    BoardView boardPanel;

    void initializeManager() {
        manager = new BuilderManager();
    }

    void initializeControllers() {
        boardPanel.addMouseListener(new BoardControllerBuilderMakeBoard(manager, boardPanel));
        btnPuzzle.addActionListener(new LevelTypeButtonBuilderMakeBoardController(this.manager, LevelType.PUZZLE, this));
        btnLightning.addActionListener(new LevelTypeButtonBuilderMakeBoardController(this.manager, LevelType.LIGHTNING, this));
        btnRelease.addActionListener(new LevelTypeButtonBuilderMakeBoardController(this.manager, LevelType.RELEASE, this));
        txtInsertName.getDocument().addDocumentListener(new NameTextBuilderMakeBoardController(manager, txtInsertName));
        txtWidth.getDocument().addDocumentListener(new WidthTextBuilderMakeBoardController(this.boardPanel, this.manager, this.txtWidth));
        txtHeight.getDocument().addDocumentListener(new HeightTextBuilderMakeBoardController(this.boardPanel, this.manager, this.txtHeight));
    }

    void resetViewToLevel() {
        txtInsertName.setText(this.manager.getName());
        txtWidth.setText(this.manager.getWidth() + "");
        txtHeight.setText(this.manager.getHeight() + "");
        this.repaint();
    }

    /**
     * Gets the current builder manager for this view
     * @return Builder Manager
     */
    public BuilderManager getManager() {
        return this.manager;
    }

    /**
     * Create the panel.
     */
    public BuilderSelectScreen() {
        initializeManager();

        levelTypeButtons = new ButtonGroup();

        btnPuzzle = new JToggleButton("Puzzle");

        btnLightning = new JToggleButton("Lightning");

        btnRelease = new JToggleButton("Release");

        levelTypeButtons.add(btnPuzzle);
        levelTypeButtons.add(btnLightning);
        levelTypeButtons.add(btnRelease);

        txtInsertName = new JTextField();
        txtInsertName.setText("Insert Name");
        txtInsertName.setColumns(10);

        JButton btnStart = new JButton("Complete");
        btnStart.addActionListener(new ButtonBuilderStartController(getManager()));

        txtWidth = new JTextField();
        txtWidth.setText("Width");
        txtWidth.setColumns(10);

        txtHeight = new JTextField();
        txtHeight.setText("Height");
        txtHeight.setColumns(10);

        JLabel lblX = new JLabel("x");

        JLabel lblBoardSize = new JLabel("Board Size");

        boardPanel = new BoardView(manager.getLevel().getBoard(), manager.getLevel());

        JButton btnMainMenu = new JButton("Main Menu");
        btnMainMenu.addActionListener(new ButtonBuilderMainMenuController());

        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(btnMainMenu)
                                                        .addComponent(txtInsertName, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE))
                                                .addGroup(groupLayout.createSequentialGroup()
                                                        .addGap(70)
                                                        .addComponent(btnStart)))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addComponent(lblBoardSize)
                                                .addGap(18)
                                                .addComponent(txtWidth, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(lblX)
                                                .addGap(12)
                                                .addComponent(txtHeight, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
                                        .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                                .addComponent(btnRelease, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                                                .addComponent(btnLightning, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                                                .addComponent(btnPuzzle, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(boardPanel, GroupLayout.PREFERRED_SIZE, 700, GroupLayout.PREFERRED_SIZE)
                                .addGap(789))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap(25, Short.MAX_VALUE)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
                                                .addComponent(boardPanel, GroupLayout.PREFERRED_SIZE, 700, GroupLayout.PREFERRED_SIZE)
                                                .addGap(15))
                                        .addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
                                                .addComponent(btnMainMenu)
                                                .addGap(18)
                                                .addComponent(txtInsertName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                                        .addComponent(txtWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txtHeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblBoardSize)
                                                        .addComponent(lblX))
                                                .addGap(18)
                                                .addComponent(btnPuzzle, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18)
                                                .addComponent(btnLightning, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18)
                                                .addComponent(btnRelease, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
                                                .addGap(69)
                                                .addComponent(btnStart)
                                                .addGap(140))))
        );
        GroupLayout gl_boardPanel = new GroupLayout(boardPanel);
        gl_boardPanel.setHorizontalGroup(
                gl_boardPanel.createParallelGroup(Alignment.LEADING)
                        .addGap(0, 613, Short.MAX_VALUE)
        );
        gl_boardPanel.setVerticalGroup(
                gl_boardPanel.createParallelGroup(Alignment.LEADING)
                        .addGap(0, 598, Short.MAX_VALUE)
        );
        boardPanel.setLayout(gl_boardPanel);
        setLayout(groupLayout);
        resetViewToLevel();
        initializeControllers();

    }
}
