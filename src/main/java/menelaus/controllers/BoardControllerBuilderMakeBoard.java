package menelaus.controllers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import menelaus.model.BuilderManager;
import menelaus.model.basic.Point;
import menelaus.util.SoundManager;
import menelaus.util.SoundType;
import menelaus.view.BoardView;

/**
 * Controller that handles making a board for the builder.
 * @author sanjay
 *
 */
public class BoardControllerBuilderMakeBoard implements MouseListener{

	BuilderManager manager;
	BoardView view;
	
	/**
	 * constructor.
	 * @param manager
	 * @param view
	 */
	public BoardControllerBuilderMakeBoard(BuilderManager manager, BoardView view) {
		this.manager = manager;
		this.view = view;
	}
	
	public void mouseClicked(MouseEvent e) {
		Point clickedPoint = new Point(e.getX(), e.getY());
		handleMouseClick(view.pointUnder(clickedPoint));
	}

	/**
	 * chop and unchoping and choping tile pieces when the mouse is clicked.
	 * @param pointOnBoard
	 */
	public void handleMouseClick(Point pointOnBoard) {
		if(manager.getLevel().getBoard().isChoppedOut(pointOnBoard))
			manager.getLevel().getBoard().unchopTile(pointOnBoard);
		else
			manager.getLevel().getBoard().chopTileOut(pointOnBoard);
		refreshBoard();
	}
	
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		SoundManager.getInstance().playSound(SoundType.PRESSPIECE);
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	void refreshBoard() {
		view.repaint();
	}
}
