package hoang.duc.dung.boomoffline.gui;

import hoang.duc.dung.boomoffline.Game;
import hoang.duc.dung.boomoffline.graphics.SpriteImage;
import hoang.duc.dung.boomoffline.sound.Sound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static hoang.duc.dung.boomoffline.graphics.SpriteImage.icons_button_skip;
import static hoang.duc.dung.boomoffline.gui.Container.MENU_PANEL;

public class HelpPanel extends JPanel implements ActionListener {
    public static final String BACK = "back";
    private int widthFrame_ = Game.WIDTH * Game.SCALE;
    private int heightFrame_ = (Game.HEIGHT + Game.TILES_SIZE) * Game.SCALE;
    private JButton buttonBack_;
    private Container container_;


    public HelpPanel(Container container) {
        setBackground(Color.green);
        setLayout(null);
        initComponents();
        initListener();
        this.container_ = container;
    }

    private void initComponents() {
        buttonBack_ = new JButton(icons_button_skip[0].getIcon());
        buttonBack_.setRolloverIcon(icons_button_skip[1].getIcon());
        buttonBack_.setSize(icons_button_skip[0].getWidth(), icons_button_skip[0].getHeight());
        buttonBack_.setLocation(10, 10);
        add(buttonBack_);
    }

    public void initListener() {
        buttonBack_.addActionListener(this);
        buttonBack_.setActionCommand(BACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(SpriteImage.images_panel_help.getImage(), 0, 0, widthFrame_, heightFrame_, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String run = e.getActionCommand();
        switch (run) {
            case BACK:
                Sound.sound_click.playSound();
                container_.showCard(MENU_PANEL);
                break;
            default:
                break;
        }
    }
}