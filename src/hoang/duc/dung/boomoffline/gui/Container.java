package hoang.duc.dung.boomoffline.gui;

import hoang.duc.dung.boomoffline.sound.Sound;

import javax.swing.*;
import java.awt.*;

public class Container extends JPanel {
    public static final String GAME_PANEL = "GamePanel";
    public static final String MENU_PANEL = "MenuPanel";
    public static final String HELP_PANEL = "HelpPanel";

    private MenuPanel menuPanel_;
    private HelpPanel helpPanel_;
    private CardLayout cardLayout_;
    private boolean play_;

    public Container(Frame frame) {
        cardLayout_ = new CardLayout();

        helpPanel_ = new HelpPanel(this);
        menuPanel_ = new MenuPanel(this);
        setLayout(cardLayout_);
        add(helpPanel_, HELP_PANEL);
        add(menuPanel_, MENU_PANEL);

        setFocusable(true);
        play_ = false;

        showCard(MENU_PANEL);
    }

    public void showCard(String name) {
        if (name.equals(GAME_PANEL)) {
            Sound.sound_menu_1.stopSound();
            Sound.sound_game_start.playSound();
            Sound.sound_game_sound.playSound();
            Sound.sound_game_sound.loopSound(100);

            play_ = true;
            cardLayout_.show(this, GAME_PANEL);
        } else if (name.equals(HELP_PANEL)) {
            play_ = false;
            cardLayout_.show(this, name);
        } else if (name.equals(MENU_PANEL)) {
            Sound.sound_game_sound.stopSound();
            Sound.sound_menu_1.stopSound();
            Sound.sound_menu_1.playSound();
            Sound.sound_menu_1.loopSound(100);
            play_ = false;
            cardLayout_.show(this, MENU_PANEL);
        }
    }

    public boolean isPlay() {
        return play_;
    }
}