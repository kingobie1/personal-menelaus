package menelaus.util;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Sound Manager handles all game sounds.
 * @author Obatola Seward-Evans
 */
public class SoundManager {
    private AudioClip click;
    /** check if game is mute or not. */
    private boolean isMute = false;

    /**
     * HashTable for memoization.
     */
    private Map<SoundType, AudioClip> clipTable = new HashMap<SoundType, AudioClip>();
    String filePath;

    private static SoundManager instance = new SoundManager();

    /**
     * constructor loads all files.
     */
    private SoundManager() {
        LoadFiles();
    }

    /**
     * get SoundManager instance.
     * @return instance.
     */
    public static SoundManager getInstance() {
        return instance;
    }

    /**
     * play sound of the given sound type.
     * @param soundType the sound type to play.
     */
    public void playSound(SoundType soundType) {
        if (isMute || !clipTable.containsKey(soundType)) {
            return;
        }
        if (clipTable.get(soundType) != null)
            clipTable.get(soundType).play();
    }

    /**
     * load all sound files.
     */
    private void LoadFiles() {
        putSoundInMap(SoundType.BUTTONSOUND);
        putSoundInMap(SoundType.CLOSEAPP);
        putSoundInMap(SoundType.COMPLETESOUND);
        putSoundInMap(SoundType.EXITSOUND);
        putSoundInMap(SoundType.LOSESOUND);
        putSoundInMap(SoundType.PRESSPIECE);
        putSoundInMap(SoundType.PRESSTILE);
        putSoundInMap(SoundType.RESTARTSOUND);
        putSoundInMap(SoundType.WINSOUND);
    }

    /**
     * put sound file in hashTable.
     * @param type Sound type to add to clipMap.
     * @author Obatola Seward-Evans
     */
    private void putSoundInMap(SoundType type) {
        switch (type) {
            case BUTTONSOUND:
                clipTable.put(type, getSoundFile("/assets/click.wav"));
                break;
            case WINSOUND:
                clipTable.put(type, getSoundFile("/assets/win2.wav"));
                break;
            case LOSESOUND:
                clipTable.put(type, getSoundFile("/assets/lose.wav"));
                break;
            case COMPLETESOUND:
                clipTable.put(type, getSoundFile("/assets/complete.wav"));
                break;
            case EXITSOUND:
                clipTable.put(type, getSoundFile("/assets/close_app.wav"));
                break;
            case PRESSPIECE:
                clipTable.put(type, getSoundFile("/assets/place_piece.wav"));
                break;
            case PRESSTILE:
                clipTable.put(type, getSoundFile("/assets/click_tile.wav"));
                break;
            case RESTARTSOUND:
                clipTable.put(type, getSoundFile("/assets/restart.wav"));
                break;
            default:
                break;
        }
    }

    /**
     * get sound from file.
     * @param file_name the string of the file name.
     * @return AudioClip the audio clip that can later be played.
     */
    private AudioClip getSoundFile(String file_name) {
        URL urlClick = SoundManager.class.getResource(file_name);
        if (urlClick != null) {
            click = Applet.newAudioClip(urlClick);
        }
        return click;
    }

    /**
     * set sound to be mute or not.
     */
    public void changeMute() {
        if (isMute) {
            isMute = false;
        } else {
            isMute = true;
        }
    }

    /**
     * check if mute or not.
     * @return boolean true if mute, false if not.
     */
    public boolean isMute() {
        return isMute;
    }

}
