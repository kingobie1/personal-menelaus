package menelaus.view.builder;

import menelaus.controllers.*;
import menelaus.model.AllPieceBullpen;
import menelaus.model.BuilderManager;
import menelaus.model.basic.LevelType;
import menelaus.model.dataholders.ReleasePaneData;
import menelaus.view.BoardView;
import menelaus.view.BullpenView;
import menelaus.view.KabasujiPanel;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.*;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;

/**
 * This is the screen where you build a level.
 * @author sanjay
 */
public class BuilderLevelBuilderScreen extends KabasujiPanel {
    /**
     * Keep eclipse happy with Cereal (Serial).
     */
    private static final long serialVersionUID = -5145620117800309808L;
    private JTextField txtMaxMoves;
    private JTextField txtInsertName;

    BuilderManager manager = new BuilderManager();
    
    
    BoardView panelBoardView;
    //the view for the corresponding bullpen
    BullpenView panelBullpenView;
    //the AllPieceBullpen
    BullpenView panelAllBullpenView;
    
    //JPanel panelBoardView;
    //JPanel panelBullpenView;
    //JPanel panelAllBullpenView;

    JButton btnMakePiece;
    JButton btnMakeHint;
    JButton btnComplete;
    JButton btnUndo;
    JLabel lblMaxMoves;
    JButton btnRedo;
    
    JToggleButton btnEnableReleaseMode;
    BuilderReleasePane releasePanel;
    ReleasePaneData releasePaneData;

    void initializeControllers() {
    	
    	ReleasePaneBuilderMakeLevelController releaseController = new ReleasePaneBuilderMakeLevelController(this.manager,this.releasePaneData,this.releasePanel);
    	
        this.panelBoardView.addMouseListener(new BoardBuilderMakeLevelController(this.manager, this.panelBoardView, releaseController));
        this.btnMakePiece.addActionListener(new MakePieceButtonController(this.manager, this.panelBoardView, this.panelBullpenView));
        this.btnMakeHint.addActionListener(new MakeHintButtonController(this.manager, this.panelBoardView, this.panelBullpenView));
        this.txtMaxMoves.getDocument().addDocumentListener(new TextNumRestrictionsBuilderMakeLevelController(this.manager, this.txtMaxMoves));
        this.btnComplete.addActionListener(new SaveLevelButtonBuilderMakeLevelController(manager));
        this.btnUndo.addActionListener(new UndoBuilderMakeLevelController(this.manager, this.panelBoardView, this.panelBullpenView));
        this.btnRedo.addActionListener(new RedoBuilderMakeLevelController(this.manager, this.panelBoardView, this.panelBullpenView));
        txtInsertName.getDocument().addDocumentListener(new NameTextBuilderMakeBoardController(manager, txtInsertName));
        if(this.manager.getType() == LevelType.RELEASE) {
        	this.btnEnableReleaseMode.addActionListener(new EnableReleaseModeBuilderMakeLevelController(this.manager, this.btnEnableReleaseMode, this.releasePaneData));
        	releasePanel.addMouseListener(releaseController);
        }
    }
    
    void refreshComponentsByGame() {
        switch (this.manager.getType()) {
            case LIGHTNING:
                this.lblMaxMoves.setText("Max Time:");
                this.txtMaxMoves.setText(this.manager.getLevel().getTimeLimit() + "");
                break;
            default:
                this.lblMaxMoves.setText("Max Moves:");
                this.txtMaxMoves.setText(this.manager.getLevel().getMoveLimit() + "");
                break;
        }
        this.txtInsertName.setText(this.manager.getName());
        this.repaint();
    }

    /**
     * Create the panel.
     * @param manager 
     *
     * @throws Exception
     */
    public BuilderLevelBuilderScreen(BuilderManager manager) throws Exception {
        if (manager != null) {
            this.manager = manager;
        } else {
            throw new Exception("cannot pass null manager into BuilderLevelBuilderScreen constructor");
        }

        JButton btnExit = new JButton("Exit");
        btnExit.addActionListener(new ButtonBuilderMainMenuController());

        btnComplete = new JButton("Complete");

        btnUndo = new JButton("Undo");

        btnMakePiece = new JButton("Make Piece");

        btnMakeHint = new JButton("Make Hint");
        
        /*Release Code*/
        releasePaneData = new ReleasePaneData();
        btnEnableReleaseMode = new JToggleButton("Place #");
        releasePanel = new BuilderReleasePane(releasePaneData.generateSetItem(), 30, 30); 
        releasePanel.setPreferredSize(new Dimension(50, 50));

        txtMaxMoves = new JTextField();
        txtMaxMoves.setText("Max Moves");
        txtMaxMoves.setColumns(10);

        txtInsertName = new JTextField();
        txtInsertName.setText("Insert Name");
        txtInsertName.setColumns(10);

        lblMaxMoves = new JLabel("Max Moves:");
        
        if (this.manager.getType() != LevelType.RELEASE) {
        	 btnEnableReleaseMode.setVisible(false);
        	 releasePanel.setVisible(false);
		}

        panelBoardView = new BoardView(this.manager.getLevel().getBoard(), this.manager.getLevel(), true);
        panelBoardView.setSelection(this.manager.getSelectedPoints());

        btnRedo = new JButton("Redo");

        JScrollPane scrollPane = new JScrollPane();

        JScrollPane scrollPane_1 = new JScrollPane();

        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
        			.addGap(22)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(panelBoardView, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addComponent(btnMakePiece)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(btnMakeHint)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(btnEnableReleaseMode)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(releasePanel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addComponent(btnUndo)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(btnRedo)
        					.addPreferredGap(ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
        					.addComponent(btnExit)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(btnComplete))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addComponent(txtInsertName, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
        					.addGap(18)
        					.addComponent(lblMaxMoves)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(txtMaxMoves, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)))
        			.addContainerGap())
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 728, Short.MAX_VALUE)
        					.addContainerGap())
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(txtMaxMoves, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(txtInsertName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(lblMaxMoves))
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(btnUndo)
        						.addComponent(btnRedo)
        						.addComponent(btnComplete)
        						.addComponent(btnExit))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        							.addComponent(btnEnableReleaseMode)
        							.addComponent(btnMakePiece)
        							.addComponent(btnMakeHint))
        						.addComponent(releasePanel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
        					.addGap(18)
        					.addComponent(panelBoardView, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE)
        					.addGap(77))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 728, Short.MAX_VALUE)
        					.addContainerGap())))
        );

        panelBullpenView = new BullpenView(this.manager.getLevel().getBullpen());
        panelBullpenView.setPreferredSize(new Dimension(240, 2200));
        scrollPane_1.setViewportView(panelBullpenView);
        scrollPane_1.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane_1.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
        
        PieceSelectionController psc = new PieceSelectionController(panelBullpenView, this.manager.getLevel());
        panelBullpenView.addMouseListener(psc);

        panelAllBullpenView = new BullpenView(new AllPieceBullpen());
        panelAllBullpenView.setPreferredSize(new Dimension(240, 2200));
        panelAllBullpenView.addMouseListener(new AddPieceToBullpenController(panelAllBullpenView, panelBullpenView, manager));

        scrollPane.setViewportView(panelAllBullpenView);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);

        setLayout(groupLayout);
        initializeControllers();
        refreshComponentsByGame();
    }
}
