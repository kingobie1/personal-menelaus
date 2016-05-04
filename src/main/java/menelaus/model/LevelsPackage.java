package menelaus.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Holds a collection of levels that could be loaded into the game and played in a sequence. 
 * Class is serializable.
 * @author vouldjeff
 *
 */
public class LevelsPackage implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * The collection with all levels.
	 */
	private ArrayList<Level> levels;

	/**
	 * Returns all Levels in the package.
	 * @return A List with all levels.
	 */
	public ArrayList<Level> getLevels() {
		return levels;
	}

	/**
	 * Creates a new empty LevelsPackage.
	 */
	public LevelsPackage() {
		super();
		this.levels = new ArrayList<Level>();
	}
	
	/**
	 * Adds a level to the package.
	 * @param level The new level.
	 */
	public void addLevel(Level level) {
		levels.add(level);
	}
}
