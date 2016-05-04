package menelaus.view.builder;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import menelaus.model.board.ColoredSetItem;

/**
 * Contains the information necessary to display a released tile
 *
 */
public class BuilderReleasePane extends JPanel {

	/**
	 * Cereal to keep Eclipse Happy :)
	 */
	private static final long serialVersionUID = 8728359376463283367L;
	ColoredSetItem item;
	int width;
	int height;
	
	/**
	 * Create the panel.
	 * @param item 
	 * @param width 
	 * @param height 
	 */
	public BuilderReleasePane(ColoredSetItem item, int width, int height) {
		this.item = item;
		this.width = width;
		this.height = height;
		
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}

	@Override
	protected void paintComponent(Graphics g) {
		int fontSize = 14;
		super.paintComponent(g);
		g.setColor(this.item.getJavaColor());
		g.setFont(new Font(Font.MONOSPACED, Font.BOLD, fontSize));
		g.drawChars((this.item.getNumber() + "").toCharArray(),0,1,(this.width/2 - 5),(this.height/2 + 5));
	}
	
	/**
	 * Sets the item stored in the release pane to the given ColoredSetItem
	 * @param s
	 */
	public void setItem(ColoredSetItem s) {
		this.item = s;
	}
}
