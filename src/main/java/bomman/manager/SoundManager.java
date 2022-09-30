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

    public static AudioClip bomb;
    public static AudioClip buff;
    public static AudioClip speed;

    /**
     * bomber sfx.
     */
    public static AudioClip walk;
    public static AudioClip dead;

    /**
     * all audio path.
     *
     */
    public static String bomb_p = "/bomman/sfx/explosion.mp3";
    public static String buff_p = "/bomman/sfx/buff.mp3";
    public static String speed_p = "/bomman/sfx/speed.mp3";
    public static String walk_p = "/bomman/sfx/walk.mp3";
    public static String dead_p = "/bomman/sfx/walk.mp3";

    public static void init() {
        try {
            bomb = new AudioClip(SoundManager.class.getResource(bomb_p).toURI().toString());
            buff = new AudioClip(SoundManager.class.getResource(buff_p).toURI().toString());
            speed = new AudioClip(SoundManager.class.getResource(speed_p).toURI().toString());
            walk = new AudioClip(SoundManager.class.getResource(walk_p).toURI().toString());
            dead = new AudioClip(SoundManager.class.getResource(dead_p).toURI().toString());

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


}