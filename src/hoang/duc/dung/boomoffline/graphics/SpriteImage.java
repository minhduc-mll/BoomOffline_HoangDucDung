package hoang.duc.dung.boomoffline.graphics;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class SpriteImage {
    private int width_;
    private int height_;
    private int imageWidth_;
    private int imageHeight_;
    private Image image_;
    private Icon icon_;

    // ---------------------------------------------------------------------------
    // | Menu & button sprites
    // ---------------------------------------------------------------------------
    public static SpriteImage images_panel_menu =
            new SpriteImage("/images/panel_menu.png");
    public static SpriteImage images_panel_help =
            new SpriteImage("/images/panel_help.png");

    public static SpriteImage icons_button_play =
            new SpriteImage("/images/button_play.png", 40, 40);
    public static SpriteImage icons_button_pause =
            new SpriteImage("/images/button_pause.png", 40, 40);
    public static SpriteImage[] icons_button_start = {
            new SpriteImage("/images/button_start_1.png", 180, 68),
            new SpriteImage("/images/button_start_2.png", 180, 68),
    };
    public static SpriteImage[] icons_button_help = {
            new SpriteImage("/images/button_help_1.png", 80, 30),
            new SpriteImage("/images/button_help_2.png", 80, 30),
    };
    public static SpriteImage[] icons_button_exit = {
            new SpriteImage("/images/button_exit_1.png", 80, 30),
            new SpriteImage("/images/button_exit_2.png", 80, 30),
    };
    public static SpriteImage[] icons_button_skip = {
            new SpriteImage("/images/button_skip_1.png", 80, 30),
            new SpriteImage("/images/button_skip_2.png", 80, 30),
    };
    public static SpriteImage[] icons_button_back = {
            new SpriteImage("/images/button_back_1.png", 40, 40),
            new SpriteImage("/images/button_back_2.png", 40, 40),
    };

    // --------------------------------------------------------------------------
    // | Board sprites
    // --------------------------------------------------------------------------
    public static SpriteImage images_background =
            new SpriteImage("/images/panel_help.png", 1920, 1080);
    public static SpriteImage images_map_barrier_1 =
            new SpriteImage("/images/map_barrier_1.png");
    public static SpriteImage images_map_barrier_2 =
            new SpriteImage("/images/map_barrier_2.png");
    public static SpriteImage images_map_box_1 =
            new SpriteImage("/images/map_box_1.png");
    public static SpriteImage images_map_box_2 =
            new SpriteImage("/images/map_box_2.png");
    public static SpriteImage images_map_box_3 =
            new SpriteImage("/images/map_box_3.png");
    public static SpriteImage images_map_box_4 =
            new SpriteImage("/images/map_box_4.png");
    public static SpriteImage images_map_floor =
            new SpriteImage("/images/map_floor.png");
    public static SpriteImage images_map_floor_1 =
            new SpriteImage("/images/map_floor_1.png");
    public static SpriteImage images_map_floor_2 =
            new SpriteImage("/images/map_floor_2.png");
    public static SpriteImage images_map_grass =
            new SpriteImage("/images/map_grass.png");
    public static SpriteImage images_map_house_1 =
            new SpriteImage("/images/map_house_1.png");
    public static SpriteImage images_map_house_2 =
            new SpriteImage("/images/map_house_2.png");
    public static SpriteImage images_map_house_3 =
            new SpriteImage("/images/map_house_3.png");
    public static SpriteImage images_map_lego =
            new SpriteImage("/images/map_lego.png");
    public static SpriteImage images_map_lego_box_1 =
            new SpriteImage("/images/map_lego_box_1.png");
    public static SpriteImage images_map_lego_box_2 =
            new SpriteImage("/images/map_lego_box_2.png");
    public static SpriteImage images_map_present =
            new SpriteImage("/images/map_present.png");
    public static SpriteImage images_map_stone =
            new SpriteImage("/images/map_stone.png");
    public static SpriteImage images_map_street_1 =
            new SpriteImage("/images/map_street_1.png");
    public static SpriteImage images_map_street_2 =
            new SpriteImage("/images/map_street_2.png");
    public static SpriteImage images_map_street_3 =
            new SpriteImage("/images/map_street_3.png");
    public static SpriteImage images_map_street_4 =
            new SpriteImage("/images/map_street_4.png");
    public static SpriteImage images_map_street_5 =
            new SpriteImage("/images/map_street_5.png");
    public static SpriteImage images_map_torch =
            new SpriteImage("/images/map_torch.png");
    public static SpriteImage images_map_tree_1 =
            new SpriteImage("/images/map_tree_1.png");
    public static SpriteImage images_map_tree_2 =
            new SpriteImage("/images/map_tree_2.png");
    public static SpriteImage images_map_tree_3 =
            new SpriteImage("/images/map_tree_3.png");
    public static SpriteImage[] images_map_tree = {
            new SpriteImage("/images/map_tree_4.png"),
            new SpriteImage("/images/map_tree_5.png"),
            new SpriteImage("/images/map_tree_6.png"),
            new SpriteImage("/images/map_tree_7.png"),
            new SpriteImage("/images/map_tree_8.png"),
    };
    public static SpriteImage images_map_wood =
            new SpriteImage("/images/map_wood.png");


    // --------------------------------------------------------------------------
    // | Player Sprites
    // --------------------------------------------------------------------------
    public static SpriteImage[] images_player_up = {
            new SpriteImage("/images/player_up_1.png"),
            new SpriteImage("/images/player_up_2.png"),
            new SpriteImage("/images/player_up_3.png"),
            new SpriteImage("/images/player_up_4.png"),
            new SpriteImage("/images/player_up_5.png"),
    };
    public static SpriteImage[] images_player_down = {
            new SpriteImage("/images/player_down_1.png"),
            new SpriteImage("/images/player_down_2.png"),
            new SpriteImage("/images/player_down_3.png"),
            new SpriteImage("/images/player_down_4.png"),
            new SpriteImage("/images/player_down_5.png"),
    };
    public static SpriteImage[] images_player_left = {
            new SpriteImage("/images/player_left_1.png"),
            new SpriteImage("/images/player_left_2.png"),
            new SpriteImage("/images/player_left_3.png"),
            new SpriteImage("/images/player_left_4.png"),
            new SpriteImage("/images/player_left_5.png"),
    };
    public static SpriteImage[] images_player_right = {
            new SpriteImage("/images/player_right_1.png"),
            new SpriteImage("/images/player_right_2.png"),
            new SpriteImage("/images/player_right_3.png"),
            new SpriteImage("/images/player_right_4.png"),
            new SpriteImage("/images/player_right_5.png"),
    };
    public static SpriteImage[] images_player_feel = {
            new SpriteImage("/images/player_feel_1.png"),
            new SpriteImage("/images/player_feel_2.png"),
    };
    public static SpriteImage[] images_player_dead = {
            new SpriteImage("/images/player_dead_1.png"),
            new SpriteImage("/images/player_dead_2.png"),
            new SpriteImage("/images/player_dead_3.png"),
            new SpriteImage("/images/player_dead_4.png"),
            new SpriteImage("/images/player_dead_5.png"),
            new SpriteImage("/images/player_dead_6.png"),
    };
    public static SpriteImage[] images_animation = {
            new SpriteImage("/images/animation1.png"),
            new SpriteImage("/images/animation2.png"),
            new SpriteImage("/images/animation3.png"),
            new SpriteImage("/images/animation4.png"),
            new SpriteImage("/images/animation5.png"),
            new SpriteImage("/images/animation6.png"),
            new SpriteImage("/images/animation7.png"),
            new SpriteImage("/images/animation8.png"),
    };

    // --------------------------------------------------------------------------
    // | Character
    // --------------------------------------------------------------------------
    // Mummy
    public static SpriteImage[] images_enemy_mummy_up = {
            new SpriteImage("/images/enemy_mummy_up_1.png"),
            new SpriteImage("/images/enemy_mummy_up_2.png"),
            new SpriteImage("/images/enemy_mummy_up_3.png"),
            new SpriteImage("/images/enemy_mummy_up_4.png"),
            new SpriteImage("/images/enemy_mummy_up_5.png"),
    };
    public static SpriteImage[] images_enemy_mummy_down = {
            new SpriteImage("/images/enemy_mummy_down_1.png"),
            new SpriteImage("/images/enemy_mummy_down_2.png"),
            new SpriteImage("/images/enemy_mummy_down_3.png"),
            new SpriteImage("/images/enemy_mummy_down_4.png"),
            new SpriteImage("/images/enemy_mummy_down_5.png"),
    };
    public static SpriteImage[] images_enemy_mummy_left = {
            new SpriteImage("/images/enemy_mummy_left_1.png"),
            new SpriteImage("/images/enemy_mummy_left_2.png"),
            new SpriteImage("/images/enemy_mummy_left_3.png"),
            new SpriteImage("/images/enemy_mummy_left_4.png"),
            new SpriteImage("/images/enemy_mummy_left_5.png"),
    };
    public static SpriteImage[] images_enemy_mummy_right = {
            new SpriteImage("/images/enemy_mummy_right_1.png"),
            new SpriteImage("/images/enemy_mummy_right_2.png"),
            new SpriteImage("/images/enemy_mummy_right_3.png"),
            new SpriteImage("/images/enemy_mummy_right_4.png"),
            new SpriteImage("/images/enemy_mummy_right_5.png"),
    };

    // Gold
    public static SpriteImage[] images_enemy_gold_up = {
            new SpriteImage("/images/enemy_gold_up.png"),
    };
    public static SpriteImage[] images_enemy_gold_down = {
            new SpriteImage("/images/enemy_gold_down.png"),
    };
    public static SpriteImage[] images_enemy_gold_left = {
            new SpriteImage("/images/enemy_gold_left.png"),
    };
    public static SpriteImage[] images_enemy_gold_right = {
            new SpriteImage("/images/enemy_gold_right.png"),
    };
    public static SpriteImage[] images_enemy_gold_dead = {
            new SpriteImage("/images/enemy_gold_dead_1.png"),
            new SpriteImage("/images/enemy_gold_dead_2.png"),
            new SpriteImage("/images/enemy_gold_dead_3.png"),
    };

    // Octopus
    public static SpriteImage[] images_enemy_octopus_up = {
            new SpriteImage("/images/enemy_octopus_up.png"),
    };
    public static SpriteImage[] images_enemy_octopus_down = {
            new SpriteImage("/images/enemy_octopus_down.png"),
    };
    public static SpriteImage[] images_enemy_octopus_left = {
            new SpriteImage("/images/enemy_octopus_left.png"),
    };
    public static SpriteImage[] images_enemy_octopus_right = {
            new SpriteImage("/images/enemy_octopus_right.png"),
    };

    // Pirate
    public static SpriteImage[] images_enemy_pirate_up = {
            new SpriteImage("/images/enemy_pirate_up_1.png"),
            new SpriteImage("/images/enemy_pirate_up_2.png"),
            new SpriteImage("/images/enemy_pirate_up_3.png"),
            new SpriteImage("/images/enemy_pirate_up_4.png"),
            new SpriteImage("/images/enemy_pirate_up_5.png"),
    };
    public static SpriteImage[] images_enemy_pirate_down = {
            new SpriteImage("/images/enemy_pirate_down_1.png"),
            new SpriteImage("/images/enemy_pirate_down_2.png"),
            new SpriteImage("/images/enemy_pirate_down_3.png"),
            new SpriteImage("/images/enemy_pirate_down_4.png"),
            new SpriteImage("/images/enemy_pirate_down_5.png"),
    };
    public static SpriteImage[] images_enemy_pirate_left = {
            new SpriteImage("/images/enemy_pirate_left_1.png"),
            new SpriteImage("/images/enemy_pirate_left_2.png"),
            new SpriteImage("/images/enemy_pirate_left_3.png"),
            new SpriteImage("/images/enemy_pirate_left_4.png"),
            new SpriteImage("/images/enemy_pirate_left_5.png"),
    };
    public static SpriteImage[] images_enemy_pirate_right = {
            new SpriteImage("/images/enemy_pirate_right_1.png"),
            new SpriteImage("/images/enemy_pirate_right_2.png"),
            new SpriteImage("/images/enemy_pirate_right_3.png"),
            new SpriteImage("/images/enemy_pirate_right_4.png"),
            new SpriteImage("/images/enemy_pirate_right_5.png"),
    };
    public static SpriteImage[] images_enemy_pirate_feel = {
            new SpriteImage("/images/enemy_pirate_feel_1.png"),
            new SpriteImage("/images/enemy_pirate_feel_2.png"),
    };
    public static SpriteImage[] images_enemy_pirate_dead = {
            new SpriteImage("/images/enemy_pirate_dead_1.png"),
            new SpriteImage("/images/enemy_pirate_dead_2.png"),
            new SpriteImage("/images/enemy_pirate_dead_3.png"),
            new SpriteImage("/images/enemy_pirate_dead_4.png"),
    };

    // Ghost
    // Up
    public static SpriteImage[][] images_enemy_ghost_up = {
    {
            new SpriteImage("/images/ghost/top_1_1.png"),
            new SpriteImage("/images/ghost/top_1_2.png"),
            new SpriteImage("/images/ghost/top_1_3.png"),
    }, {
            new SpriteImage("/images/ghost/top_2_1.png"),
            new SpriteImage("/images/ghost/top_2_2.png"),
            new SpriteImage("/images/ghost/top_2_3.png"),
    }, {
            new SpriteImage("/images/ghost/top_3_1.png"),
            new SpriteImage("/images/ghost/top_3_2.png"),
            new SpriteImage("/images/ghost/top_3_3.png"),
    }, {
            new SpriteImage("/images/ghost/top_4_1.png"),
            new SpriteImage("/images/ghost/top_4_2.png"),
            new SpriteImage("/images/ghost/top_4_3.png"),
    }, {
            new SpriteImage("/images/ghost/top_5_1.png"),
            new SpriteImage("/images/ghost/top_5_2.png"),
            new SpriteImage("/images/ghost/top_5_3.png"),
    }, {
            new SpriteImage("/images/ghost/top_6_1.png"),
            new SpriteImage("/images/ghost/top_6_2.png"),
            new SpriteImage("/images/ghost/top_6_3.png"),
    }, {
            new SpriteImage("/images/ghost/top_7_1.png"),
            new SpriteImage("/images/ghost/top_7_2.png"),
            new SpriteImage("/images/ghost/top_7_3.png"),
    }, {
            new SpriteImage("/images/ghost/top_8_1.png"),
            new SpriteImage("/images/ghost/top_8_2.png"),
            new SpriteImage("/images/ghost/top_8_3.png"),
    },
    };

    // Down
    public static SpriteImage[][] images_enemy_ghost_down = {
    {
            new SpriteImage("/images/ghost/down_1_1.png"),
            new SpriteImage("/images/ghost/down_1_2.png"),
            new SpriteImage("/images/ghost/down_1_3.png"),
    }, {
            new SpriteImage("/images/ghost/down_2_1.png"),
            new SpriteImage("/images/ghost/down_2_2.png"),
            new SpriteImage("/images/ghost/down_2_3.png"),
    }, {
            new SpriteImage("/images/ghost/down_3_1.png"),
            new SpriteImage("/images/ghost/down_3_2.png"),
            new SpriteImage("/images/ghost/down_3_3.png"),
    }, {
            new SpriteImage("/images/ghost/down_4_1.png"),
            new SpriteImage("/images/ghost/down_4_2.png"),
            new SpriteImage("/images/ghost/down_4_3.png"),
    }, {
            new SpriteImage("/images/ghost/down_5_1.png"),
            new SpriteImage("/images/ghost/down_5_2.png"),
            new SpriteImage("/images/ghost/down_5_3.png"),
    }, {
            new SpriteImage("/images/ghost/down_6_1.png"),
            new SpriteImage("/images/ghost/down_6_2.png"),
            new SpriteImage("/images/ghost/down_6_3.png"),
    }, {
            new SpriteImage("/images/ghost/down_7_1.png"),
            new SpriteImage("/images/ghost/down_7_2.png"),
            new SpriteImage("/images/ghost/down_7_3.png"),
    }, {
            new SpriteImage("/images/ghost/down_8_1.png"),
            new SpriteImage("/images/ghost/down_8_2.png"),
            new SpriteImage("/images/ghost/down_8_3.png"),
    },
    };

    // Left
    public static SpriteImage[][] images_enemy_ghost_left = {
    {
            new SpriteImage("/images/ghost/left_1_1.png"),
            new SpriteImage("/images/ghost/left_1_2.png"),
            new SpriteImage("/images/ghost/left_1_3.png"),
    }, {
            new SpriteImage("/images/ghost/left_2_1.png"),
            new SpriteImage("/images/ghost/left_2_2.png"),
            new SpriteImage("/images/ghost/left_2_3.png"),
    }, {
            new SpriteImage("/images/ghost/left_3_1.png"),
            new SpriteImage("/images/ghost/left_3_2.png"),
            new SpriteImage("/images/ghost/left_3_3.png"),
    }, {
            new SpriteImage("/images/ghost/left_4_1.png"),
            new SpriteImage("/images/ghost/left_4_2.png"),
            new SpriteImage("/images/ghost/left_4_3.png"),
    }, {
            new SpriteImage("/images/ghost/left_5_1.png"),
            new SpriteImage("/images/ghost/left_5_2.png"),
            new SpriteImage("/images/ghost/left_5_3.png"),
    }, {
            new SpriteImage("/images/ghost/left_6_1.png"),
            new SpriteImage("/images/ghost/left_6_2.png"),
            new SpriteImage("/images/ghost/left_6_3.png"),
    }, {
            new SpriteImage("/images/ghost/left_7_1.png"),
            new SpriteImage("/images/ghost/left_7_2.png"),
            new SpriteImage("/images/ghost/left_7_3.png"),
    }, {
            new SpriteImage("/images/ghost/left_8_1.png"),
            new SpriteImage("/images/ghost/left_8_2.png"),
            new SpriteImage("/images/ghost/left_8_3.png"),
    },
    };

    // Right
    public static SpriteImage[][] images_enemy_ghost_right = {
    {
            new SpriteImage("/images/ghost/right_1_1.png"),
            new SpriteImage("/images/ghost/right_1_2.png"),
            new SpriteImage("/images/ghost/right_1_3.png"),
    }, {
            new SpriteImage("/images/ghost/right_2_1.png"),
            new SpriteImage("/images/ghost/right_2_2.png"),
            new SpriteImage("/images/ghost/right_2_3.png"),
    }, {
            new SpriteImage("/images/ghost/right_3_1.png"),
            new SpriteImage("/images/ghost/right_3_2.png"),
            new SpriteImage("/images/ghost/right_3_3.png"),
    }, {
            new SpriteImage("/images/ghost/right_4_1.png"),
            new SpriteImage("/images/ghost/right_4_2.png"),
            new SpriteImage("/images/ghost/right_4_3.png"),
    }, {
            new SpriteImage("/images/ghost/right_5_1.png"),
            new SpriteImage("/images/ghost/right_5_2.png"),
            new SpriteImage("/images/ghost/right_5_3.png"),
    }, {
            new SpriteImage("/images/ghost/right_6_1.png"),
            new SpriteImage("/images/ghost/right_6_2.png"),
            new SpriteImage("/images/ghost/right_6_3.png"),
    }, {
            new SpriteImage("/images/ghost/right_7_1.png"),
            new SpriteImage("/images/ghost/right_7_2.png"),
            new SpriteImage("/images/ghost/right_7_3.png"),
    }, {
            new SpriteImage("/images/ghost/right_8_1.png"),
            new SpriteImage("/images/ghost/right_8_2.png"),
            new SpriteImage("/images/ghost/right_8_3.png"),
    },
    };

    // Dead
    public static SpriteImage[] images_enemy_ghost_dead = {
            new SpriteImage("/images/ghost/down_4_1.png"),
            new SpriteImage("/images/ghost/down_3_1.png"),
    };

    // ENEMY DEAD
    public static SpriteImage[] images_enemy_dead = {
            new SpriteImage("/images/enemy_dead_1.png"),
            new SpriteImage("/images/enemy_dead_2.png"),
            new SpriteImage("/images/enemy_dead_3.png"),
            new SpriteImage("/images/enemy_dead_4.png"),
    };

    // --------------------------------------------------------------------------
    // | Boom Sprites
    // --------------------------------------------------------------------------
    public static SpriteImage[] images_boom = {
            new SpriteImage("/images/boom_1.png"),
            new SpriteImage("/images/boom_2.png"),
            new SpriteImage("/images/boom_3.png"),
            new SpriteImage("/images/boom_4.png"),
    };
    public static SpriteImage[] images_boom_black = {
            new SpriteImage("/images/boom_black_1.png"),
            new SpriteImage("/images/boom_black_2.png"),
            new SpriteImage("/images/boom_black_3.png"),
            new SpriteImage("/images/boom_black_4.png"),
            new SpriteImage("/images/boom_black_5.png"),
            new SpriteImage("/images/boom_black_6.png"),
            new SpriteImage("/images/boom_black_7.png"),
            new SpriteImage("/images/boom_black_8.png"),
    };

    // --------------------------------------------------------------------------
    // | WaterSegment Sprites
    // --------------------------------------------------------------------------
    public static SpriteImage[] images_explosion_mid = {
            new SpriteImage("/images/explosion_mid.png"),
    };
    public static SpriteImage[] images_explosion_vertical_up = {
            new SpriteImage("/images/explosion_vertical_up.png"),
            new SpriteImage("/images/explosion_vertical_up_last.png"),
    };
    public static SpriteImage[] images_explosion_vertical_down = {
            new SpriteImage("/images/explosion_vertical_down.png"),
            new SpriteImage("/images/explosion_vertical_down_last.png"),
    };
    public static SpriteImage[] images_explosion_horizontal_left = {
            new SpriteImage("/images/explosion_horizontal_left.png"),
            new SpriteImage("/images/explosion_horizontal_left_last.png"),
    };
    public static SpriteImage[] images_explosion_horizontal_right = {
            new SpriteImage("/images/explosion_horizontal_right.png"),
            new SpriteImage("/images/explosion_horizontal_right_last.png"),
    };

    // --------------------------------------------------------------------------
    // | Brick FlameSegment
    // --------------------------------------------------------------------------


    // --------------------------------------------------------------------------
    // | Items
    // --------------------------------------------------------------------------
    public static SpriteImage images_item_boom =
            new SpriteImage("/images/item_boom.png");
    public static SpriteImage images_item_boom_length =
            new SpriteImage("/images/item_boom_length.png");
    public static SpriteImage images_item_speed =
            new SpriteImage("/images/item_speed.png");
    public static SpriteImage images_item_new_boom =
            new SpriteImage("/images/boom_black_8.png");
    public static SpriteImage[] images_coin = {
            new SpriteImage("/images/coin1.png"),
            new SpriteImage("/images/coin2.png"),
            new SpriteImage("/images/coin3.png"),
            new SpriteImage("/images/coin4.png"),
            new SpriteImage("/images/coin5.png"),
            new SpriteImage("/images/coin6.png"),
            new SpriteImage("/images/coin7.png"),
            new SpriteImage("/images/coin8.png"),
    };

    public SpriteImage(String path) {
        load(path);
    }
    
    public SpriteImage(String path, int width, int height) {
        load(path);
        width_ = width;
        height_ = height;
        icon_ = new ImageIcon(setImageDimension(image_, width_, height_));
    }

    private void load(String path) {
        try {
            URL url = SpriteSheet.class.getResource(path);
            assert url != null;
            BufferedImage image = ImageIO.read(url);
            imageWidth_ = image.getWidth();
            imageHeight_ = image.getHeight();
            image_ = image;
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static Image setImageDimension(Image image, int width, int height) {
        return image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }

    public static SpriteImage movingImage(SpriteImage[] images, int animate, int time) {
        int calc = animate % time;
        int diff = time / images.length;

        for (int i = 0; i < images.length; i++) {
            if (calc >= diff * (i) && calc < diff * (i + 1)) {
                return images[i];
            }
        }
        return images[0];
    }

    public int getWidth() {
        return width_;
    }

    public void setWidth(int width) {
        width_ = width;
    }

    public int getHeight() {
        return height_;
    }

    public void setHeight(int height) {
        height_ = height;
    }

    public int getImageWidth() {
        return imageWidth_;
    }

    public int getImageHeight() {
        return imageHeight_;
    }

    public Image getImage() {
        return image_;
    }

    public Icon getIcon() {
        return icon_;
    }
}
