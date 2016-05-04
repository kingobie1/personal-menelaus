package menelaus.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import menelaus.model.Level;
import menelaus.model.LevelStars;
import menelaus.model.LevelsPackage;
import menelaus.model.SavedGames;

/**
 * Saves and loads all game progress to/from disk.
 * @author vouldjeff
 *
 */
public class SavedGamesUtil {
    private SavedGames savedGames;
    private File file;
    
    /**
     * Creates a new SavedGamesUtil which loads progress from disk and persists it when necessary.
     * @param file File to save/load progress
     */
    public SavedGamesUtil(File file) {
		this.file = file;
		try {
			loadFromFile();
		} catch (Exception e) {
			savedGames = new SavedGames();
		}
	}
    
    /**
     * Returns loaded progress or an empty object if no levels have been played.
     * @return SavedGames object
     */
    public SavedGames getSavedGames() {
    	return savedGames;
    }
	
    /**
     * Adds a new LevelStars whenever a player finishes a level and persists the SavedGames to disk.
     * @param levelStars Newly acquired stars
     * @throws IOException file is probably missing
     */
    public void addLevelStars(LevelStars levelStars) throws IOException {
    	if (levelStars == null || levelStars.getStarsCount() == 0) {
    		return;
    	}
    	
    	savedGames.addLevelStars(levelStars);
    	toFile();
    }
    
    /**
     * Returns the next level to be played based on already played ones.
     * @param levelsPackage Package to be searched
     * @return Next level to be played. Returns null if all levels were played.
     */
    public Level getNextPlayableLevelInPackage(LevelsPackage levelsPackage) {
    	for (Level level : levelsPackage.getLevels()) {
			if (savedGames.getStarsForLevel(level) == null) {
				return level;
			}
		}
    	
    	return null;
    }
    
	/**
     * Save SavedGames to a File.
     * @param savedGames The object to be serialized
     * @param outputFile Destination
     */
	void toFile() throws IOException {
        FileOutputStream fileToWrite = new FileOutputStream(file);
        ObjectOutputStream objectToWrite = new ObjectOutputStream(fileToWrite);
        objectToWrite.writeObject(savedGames);
 
        fileToWrite.close();
    }
 
	/**
	 * Load SavedGames from a File.
	 * @param inputFile File to read from
	 * 
	 */
	void loadFromFile() throws IOException, ClassNotFoundException {
        FileInputStream fileToRead = new FileInputStream(file);
        ObjectInputStream objectToRead = new ObjectInputStream(fileToRead);
        Object obj = objectToRead.readObject();
        objectToRead.close();
        
        savedGames = (SavedGames) obj;
    }
}
