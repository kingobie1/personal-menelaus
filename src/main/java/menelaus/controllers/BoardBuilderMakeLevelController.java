package menelaus.controllers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

import menelaus.model.BuilderManager;
import menelaus.model.basic.Point;
import menelaus.model.move.DeleteReleaseNumberBuilderMove;
import menelaus.model.move.DeselectSquareBuilderMove;
import menelaus.model.move.PlaceReleaseNumberBuilderMove;
import menelaus.model.move.SelectSquareMove;
import menelaus.util.SoundManager;
import menelaus.util.SoundType;
import menelaus.view.BoardView;
/**
 * Controller that handles making a level for the builder.
 * @author sanjay
 *
 */
public class BoardBuilderMakeLevelController implements MouseListener{
	BuilderManager manager;
	BoardView view;
	ReleasePaneBuilderMakeLevelController releaseController;
	
	/**
	 * constructor.
	 * @param manager
	 * @param view
	 * @param releaseController
	 */
	public BoardBuilderMakeLevelController(BuilderManager manager, BoardView view, ReleasePaneBuilderMakeLevelController releaseController) {
		this.manager = manager;
		this.view = view;
		this.releaseController = releaseController;
	}
	
	/**
	 * Method which contains the logic for when the mouse is clicked while making a level
	 * @param e
	 */
	public void mouseClicked(MouseEvent e) {
		Point clickedPoint = new Point(e.getX(), e.getY());
		
		if(manager.getIsReleaseMode()) {
			if(SwingUtilities.isLeftMouseButton(e))
				_handleMouseClickReleaseMode(view.pointUnder(clickedPoint));
			else if (SwingUtilities.isRightMouseButton(e))
				_handleRightClickReleaseMode(view.pointUnder(clickedPoint));
		} else {
			_handleMouseClickBoardMode(view.pointUnder(clickedPoint));	
		}
	}

	/**
	 * Helper function which contains the logic for a right click on the button
	 * @param pointOnBoard
	 */
	public void _handleRightClickReleaseMode(Point pointOnBoard) {

		DeleteReleaseNumberBuilderMove mv = new DeleteReleaseNumberBuilderMove(this.manager, pointOnBoard.getX(), pointOnBoard.getY());
		if(manager.makeMove(mv)) {
			System.out.println("Move made successfully");
			//releaseController.handleIncrementNumber();
			//releaseController.refreshView();
			SoundManager.getInstance().playSound(SoundType.PRESSPIECE); //CHANGE THE SOUND
		}
		else {
			System.out.println("Move failed!");
			SoundManager.getInstance().playSound(SoundType.PRESSTILE);
		}
		refreshBoard();
		
	}

	/**
	 *  Helper function which contains the logic for a left click on the button
	 * @param pointOnBoard
	 */
	public void _handleMouseClickReleaseMode(Point pointOnBoard) {
		PlaceReleaseNumberBuilderMove mv = new PlaceReleaseNumberBuilderMove(this.manager, pointOnBoard.getX(), pointOnBoard.getY(), this.manager.getReleaseItem());
		if(manager.makeMove(mv)) {
			System.out.println("Move made successfully");
			releaseController.handleIncrementNumber();
			releaseController.refreshView();
			SoundManager.getInstance().playSound(SoundType.PRESSPIECE); //CHANGE THE SOUND
		}
		else {
			System.out.println("Move failed!");
			SoundManager.getInstance().playSound(SoundType.PRESSTILE);
		}
		refreshBoard();
		
	}

	/**
	 * handle the mouse click on the board.
	 * @param pointOnBoard
	 */
	public void _handleMouseClickBoardMode(Point pointOnBoard) {
		if(manager.getSelectedPoints().contains(pointOnBoard)) {
			DeselectSquareBuilderMove mv = new DeselectSquareBuilderMove(this.manager, pointOnBoard.getX(), pointOnBoard.getY());
			if(manager.makeMove(mv)) {
				System.out.println("Move made successfully");
				SoundManager.getInstance().playSound(SoundType.PRESSPIECE);
			}
			else {
				System.out.println("Move failed!");
				SoundManager.getInstance().playSound(SoundType.PRESSTILE);
			}
		} else {
			SelectSquareMove mv = new SelectSquareMove(this.manager, pointOnBoard.getX(), pointOnBoard.getY());
			if(manager.makeMove(mv)) {
				System.out.println("Move successful.");
				SoundManager.getInstance().playSound(SoundType.PRESSPIECE);
			}
			else {
				System.out.println("Move failed!");
				SoundManager.getInstance().playSound(SoundType.PRESSTILE);
			}
			
		}
		refreshBoard();
	}
	
	/**
	 * Unimplemented method required for extension
	 */
	public void mousePressed(MouseEvent e) {
		
	}

	/**
	 * Unimplemented method required for extension
	 */
	public void mouseReleased(MouseEvent e) {
		
	}
	/**
	 * Unimplemented method required for extension
	 */
	public void mouseEntered(MouseEvent e) {
		
	}
	/**
	 * Unimplemented method required for extension
	 */
	public void mouseExited(MouseEvent e) {
		
	}
	/**
	 * Repaints the board view
	 */
	void refreshBoard() {
		view.repaint();
	}

}
