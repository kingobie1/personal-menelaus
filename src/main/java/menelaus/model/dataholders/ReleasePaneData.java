package menelaus.model.dataholders;

import menelaus.model.basic.Color;
import menelaus.model.board.ColoredSetItem;

/**
 * Contains the information necessary to create a colored set item in released levels
 *
 */
public class ReleasePaneData {

	Color color;
	int number;
	/**
	 * The maximum possible number for released levels
	 */
	public final int MAX_POSSIBLE = 6;
	/**
	 * The minimum possible number for released levels
	 */
	public final int MIN_POSSIBLE = 1;
	
	/**
	 * Constructor
	 */
	public ReleasePaneData() {
		this.color = Color.BLUE;
		this.number = this.MIN_POSSIBLE;
	}
	/**
	 * Increments the stored number and checks within it is greater than the maximum amount
	 */
	public void incrementNumber() {
		this.number += 1;
		if(this.number > this.MAX_POSSIBLE) {
			this.number = this.MIN_POSSIBLE;
		}
	}
	/**
	 * Changes the color to the next color in the cycle
	 * Blue goes to green, green to red, red to blue
	 */
	public void changeColor() {
		switch (this.color) {
		case BLUE:
			this.color = Color.GREEN;
			break;

		case GREEN:
			this.color = Color.RED;
			break;
			
		case RED:
			this.color = Color.BLUE;
			break;
			
		default:
			break;
		}
	}
	/**
	 * Creates a new Colored set item with the information stored in the fields color and number
	 * @return a new Colored set item
	 */
	public ColoredSetItem generateSetItem() {
		return new ColoredSetItem(this.color, this.number);
	}

}
