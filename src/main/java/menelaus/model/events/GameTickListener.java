package menelaus.model.events;

/**
 * Interface used for ticks
 * @author vouldjeff
 *
 */
public interface GameTickListener {
	/**
	 * All classes implementing this class must have logic for when a tick occurs
	 */
	void tick();
}
