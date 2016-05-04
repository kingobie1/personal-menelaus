package menelaus.model;

import java.io.Serializable;
import java.util.UUID;

/**
 * Holds the number of achieved stars for a particular level UUID.
 * @author vouldjeff
 *
 */
public class LevelStars implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * The number of stars.
	 */
	private int starsCount;
	
	/**
	 * The level id.
	 */
	private UUID levelId;
	
	/**
	 * Returns the number of stars.
	 * @return Stars.
	 */
	public int getStarsCount() {
		return starsCount;
	}
	
	/**
	 * Returns the level unique id.
	 * @return UUID object.
	 */
	public UUID getLevelId() {
		return levelId;
	}

	/**
	 * Constructs a new LevelStars object.
	 * @param starsCount The stars to be awarded.
	 * @param levelId The level for which stars are awarded.
	 */
	public LevelStars(int starsCount, UUID levelId) {
		super();
		this.starsCount = starsCount;
		this.levelId = levelId;
	}
}
