package menelaus.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import menelaus.model.LevelsPackage;
import menelaus.view.game.GameWindowFrame;

/**
 * Utility methods to load/save LevelsPackages to and from disk.
 * @author vouldjeff
 */
public class LevelsPackagePersistenceUtil {
    /**
     * Save a LevelsPackage to a File.
     * @param levelsPackage The object to be serialized
     * @param outputFile Destination
     * @throws IOException 
     */
	public static void toFile(LevelsPackage levelsPackage, File outputFile) throws IOException {
        FileOutputStream fileToWrite = new FileOutputStream(outputFile);
        ObjectOutputStream objectToWrite = new ObjectOutputStream(fileToWrite);
        objectToWrite.writeObject(levelsPackage);
 
        fileToWrite.close();
    }
 
	/**
	 * Load a LevelsPackage from a File.
	 * @param inputFile File to read from
	 * @return The LevelsPackage object
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
    public static LevelsPackage fromFile(File inputFile) throws IOException, ClassNotFoundException {
//    	File f = new File("default-levels");
//        FileInputStream fileToRead = new FileInputStream(inputFile);
//        ObjectInputStream objectToRead = new ObjectInputStream(fileToRead);
//        Object obj = objectToRead.readObject();
//        objectToRead.close();
    	InputStream fileToRead;
    	fileToRead = LevelsPackagePersistenceUtil.class.getResourceAsStream("/default-levels");

//	        FileInputStream fileToRead = new FileInputStream(f);
	        ObjectInputStream objectToRead = new ObjectInputStream(fileToRead);
	        Object obj = objectToRead.readObject();
	        objectToRead.close();
        
        return (LevelsPackage) obj;
    }
}
