package hoang.duc.dung.boomoffline.sound;

import hoang.duc.dung.boomoffline.graphics.SpriteSheet;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Sound {
    private Clip clip_;
    private String path_;

    // ---------------------------------------------------------------------------
    // | Import sound
    // ---------------------------------------------------------------------------
    public static Sound sound_menu_1 = new Sound("/sounds/menu_sound_1.wav");
    public static Sound sound_menu_2 = new Sound("/sounds/menu_sound_2.wav");
    public static Sound sound_click = new Sound("/sounds/click.wav");

    public static Sound sound_game_start = new Sound("/sounds/game_start.wav");
    public static Sound sound_game_sound = new Sound("/sounds/game_sound.wav");

    public static Sound sound_win = new Sound("/sounds/win.wav");
    public static Sound sound_lose = new Sound("/sounds/lose.wav");
    public static Sound sound_bye_bye = new Sound("/sounds/bye_bye.wav");

    public static Sound sound_move = new Sound("/sounds/move_1.wav");
    public static Sound sound_item = new Sound("/sounds/item_1.wav");
    public static Sound sound_place_boom = new Sound("/sounds/place_boom.wav");
    public static Sound sound_player_die = new Sound("/sounds/player_die.wav");
    public static Sound sound_enemy_die = new Sound("/sounds/enemy_die.wav");
    public static Sound sound_boom_explosion = new Sound("/sounds/boom_explosion.wav");
    public static Sound sound_brick_destroy = new Sound("/sounds/brick_destroy_1.wav");

    public static Sound sound_pirate = new Sound("/sounds/pirate.wav");

    public Sound(String path) {
        path_ = path;
    }

    private Clip getSound(String path) {
        try {
            URL url = SpriteSheet.class.getResource(path);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            return clip;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void playSound() {
        clip_ = getSound(path_);
        FloatControl gainControl = (FloatControl) clip_.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-1.0f);
        clip_.start();
    }

    public void stopSound() {
        if (clip_ != null)
            clip_.stop();
    }

    public void loopSound(int loop) {
        clip_.loop(loop);
    }

    public boolean isPlay() {
        return clip_.isOpen();
    }
}
