package bomman.manager;

import java.net.URL;
import javafx.scene.media.AudioClip;
import java.io.File;
import java.net.URISyntaxException;

/**
 * This class will manage all the sounds in the game.
 */
public class SoundManager {
	/**
	 * entities sfx.
	 */

//    public static AudioClip bomb = new AudioClip("https://wavlist.com/wav/cymbal2.wav");
//    public static AudioClip buff = new AudioClip(new File("../sfx/explosion.mp3").toURI().toString());
//    public static AudioClip portal = new AudioClip(new File("../sfx/explosion.mp3").toURI().toString());
//    public static AudioClip speed = new AudioClip(new File("../sfx/explosion.mp3").toURI().toString());
//
//    /**
//     * bomber sfx.
//     */
//
	public static AudioClip walk;
//    public static AudioClip dead = new AudioClip(new File("../sfx/explosion.mp3").toURI().toString());
//    public static AudioClip wind = new AudioClip(new File("../sfx/explosion.mp3").toURI().toString());
//
//    /**
//     * menu and others sfx.
//     */
//
//    public static AudioClip hover = new AudioClip(new File("../sfx/explosion.mp3").toURI().toString());

	public static void init() {
		try {
			walk = new AudioClip(SoundManager.class.getResource("/bomman/sfx/explosion.mp3").toURI().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}