package hoang.duc.dung.boomoffline.gui;

import hoang.duc.dung.boomoffline.Game;
import hoang.duc.dung.boomoffline.graphics.SpriteImage;
import hoang.duc.dung.boomoffline.sound.Sound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static hoang.duc.dung.boomoffline.graphics.SpriteImage.icons_button_start;
import static hoang.duc.dung.boomoffline.graphics.SpriteImage.icons_button_help;
import static hoang.duc.dung.boomoffline.graphics.SpriteImage.icons_button_exit;
import static hoang.duc.dung.boomoffline.gui.Container.GAME_PANEL;
import static hoang.duc.dung.boomoffline.gui.Container.HELP_PANEL;

public class MenuPanel extends JPanel implements ActionListener {

    public static final String START = "start";
    public static final String HELP = "help";
    public static final String EXIT = "exit";
    private int widthFrame_ = Game.WIDTH * Game.SCALE;
    private int heightFrame_ = (Game.HEIGHT + Game.TILES_SIZE) * Game.SCALE;
    private JButton jbStart_;
    private JButton jbHelp_;
    private JButton jbExit_;
    private Container container_;

    public MenuPanel(Container container) {
        initPanelMenu();
        initComponents();
        initListener();
        this.container_ = container;
    }

    private void initPanelMenu() {
        setBackground(Color.green);
        setLayout(null);
    }

    private void initComponents() {
        jbStart_ = new JButton(icons_button_start[0].getIcon());
        jbStart_.setRolloverIcon(icons_button_start[1].getIcon());
        jbStart_.setSize(icons_button_start[0].getWidth(), icons_button_start[0].getHeight());
        jbStart_.setLocation((widthFrame_ - jbStart_.getWidth()) / 2, heightFrame_ - jbStart_.getHeight() - 180);
        add(jbStart_);

        jbHelp_ = new JButton(icons_button_help[0].getIcon());
        jbHelp_.setRolloverIcon(icons_button_help[1].getIcon());
        jbHelp_.setSize(icons_button_help[0].getWidth(), icons_button_help[0].getHeight());
        jbHelp_.setLocation(10, 10);
        add(jbHelp_);

        jbExit_ = new JButton(icons_button_exit[0].getIcon());
        jbExit_.setRolloverIcon(icons_button_exit[1].getIcon());
        jbExit_.setSize(icons_button_exit[0].getWidth(), icons_button_exit[0].getHeight());
        jbExit_.setLocation(100, 10);
        add(jbExit_);
    }

    public void initListener() {
        jbStart_.addActionListener(this);
        jbStart_.setActionCommand(START);
        jbHelp_.addActionListener(this);
        jbHelp_.setActionCommand(HELP);
        jbExit_.addActionListener(this);
        jbExit_.setActionCommand(EXIT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(SpriteImage.images_panel_menu.getImage(), 0, 0, widthFrame_, heightFrame_, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String run = e.getActionCommand();
        switch (run) {
            case START:
                Sound.sound_click.playSound();
                container_.showCard(GAME_PANEL);
                break;
            case HELP:
                Sound.sound_click.playSound();
                container_.showCard(HELP_PANEL);
                break;
            case EXIT:
                Sound.sound_click.playSound();
                Sound.sound_bye_bye.playSound();
                System.exit(3000);
                break;
            default:
                break;
        }
    }
}