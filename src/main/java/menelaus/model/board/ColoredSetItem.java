package menelaus.model.board;

import java.io.Serializable;
import java.util.DuplicateFormatFlagsException;

import menelaus.model.basic.Color;

/**
 * The groups of numbers scattered around of the Board on a Release level.
 * @author vouldjeff
 *
 */
public class ColoredSetItem implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * The color of the number.
	 */
	private Color color;
	
	/**
	 * The actual digit.
	 */
	private int number;
	
	/**
	 * Returns the color.
	 * @return Color object.
	 */
	public Color getColor() {
		return color;
	}
	
	/** return the Java Color of the java,awt package 
	 * @return The associated Java color to this ColoredSetItem's color*/
	public java.awt.Color getJavaColor(){
		if (color == Color.BLUE){
			return java.awt.Color.blue;
		} else if (color == Color.GREEN){
			return java.awt.Color.green;
		} else if (color == Color.RED) {
			return java.awt.Color.red;
		} else {
			return java.awt.Color.black;
		}
	}
	
	/**
	 * Sets the color.
	 * @param color The new value.
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
	/**
	 * Returns the number.
	 * @return The int.
	 */
	public int getNumber() {
		return number;
	}
	
	/**
	 * Changes the value of the digit.
	 * @param number The new digit.
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * Creates a new ColoredSetItem.
	 * @param color The Color.
	 * @param number The digit.
	 */
	public ColoredSetItem(Color color, int number) {
		super();
		this.color = color;
		this.number = number;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + number;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ColoredSetItem other = (ColoredSetItem) obj;
		if (color != other.color)
			return false;
		if (number != other.number)
			return false;
		return true;
	}
	
	/**
	 * Duplicated the object.
	 * @return New deep copy.
	 */
	public ColoredSetItem duplicate() {
		return new ColoredSetItem(this.getColor(), this.getNumber());
	}
}
