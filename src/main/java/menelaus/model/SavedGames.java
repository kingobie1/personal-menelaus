package menelaus.model;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.UUID;

/**
 * Holds and manages aquired stars for all levels.
 * @author vouldjeff
 *
 */
public class SavedGames implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * The collection with all achieved stars.
	 */
	Hashtable<UUID, LevelStars> stars;

	/**
	 * Returns the Hashtable with all stars.
	 * @return A key-value collection that has LevelStars objects for each unique level id.
	 */
	public Hashtable<UUID, LevelStars> getStars() {
		return stars;
	}
	
	/**
	 * Builds a new empty SavedGames object.
	 */
	public SavedGames() {
		super();
		this.stars = new Hashtable<UUID, LevelStars>();
	}
	
	/**
	 * Adds new Stars for a particular level.
	 * @param levelStars The object to add.
	 */
	public void addLevelStars(LevelStars levelStars) {
		LevelStars old = stars.get(levelStars.getLevelId());
		if (old != null && old.getStarsCount() >= levelStars.getStarsCount()) {
			return;
		}
		
		stars.put(levelStars.getLevelId(), levelStars);
	}
	
	/**
	 * Searches for LevelStars for a particular level.
	 * @param level The level in search.
	 * @return The LevelStars object or null.
	 */
	public LevelStars getStarsForLevel(Level level) {
		return stars.get(level.getUuid());
	}
}
