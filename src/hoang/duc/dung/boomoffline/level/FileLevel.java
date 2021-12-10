package hoang.duc.dung.boomoffline.level;

import hoang.duc.dung.boomoffline.Board;
import hoang.duc.dung.boomoffline.Game;
import hoang.duc.dung.boomoffline.entities.Entity;
import hoang.duc.dung.boomoffline.entities.LayeredEntity;
import hoang.duc.dung.boomoffline.entities.character.Player;
import hoang.duc.dung.boomoffline.entities.character.enemy.Baby;
import hoang.duc.dung.boomoffline.entities.character.enemy.Pirate;
import hoang.duc.dung.boomoffline.entities.character.enemy.Ghost;
import hoang.duc.dung.boomoffline.entities.character.enemy.Octopus;
import hoang.duc.dung.boomoffline.entities.character.enemy.Mummy;
import hoang.duc.dung.boomoffline.entities.tile.GrassTile;
import hoang.duc.dung.boomoffline.entities.tile.PortalTile;
import hoang.duc.dung.boomoffline.entities.tile.WallTile;
import hoang.duc.dung.boomoffline.entities.tile.destroyable.BrickTile;
import hoang.duc.dung.boomoffline.entities.tile.items.*;
import hoang.duc.dung.boomoffline.exceptions.LoadLevelException;
import hoang.duc.dung.boomoffline.graphics.Screen;
import hoang.duc.dung.boomoffline.graphics.Sprite;
import hoang.duc.dung.boomoffline.graphics.SpriteImage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;

public class FileLevel extends Level {
	Queue<Integer> enemyQueue = new LinkedList<>();

	public FileLevel(int level, Board board) throws LoadLevelException {
		super(level, board);
	}
	
	@Override
	public void loadLevel(int level) throws LoadLevelException {
		try {
//			URL absPath = FileLevel.class.getResource("/levels/Level" + level + ".txt");
			URL absPath = FileLevel.class.getResource("/levels/Map" + level + ".txt");
			
			BufferedReader in = new BufferedReader(new InputStreamReader(absPath.openStream()));

			String data = in.readLine();

			/*
			 * dùng split để tách data dòng đầu
			 * Có dạng: level   số hàng   số cột
			 * ví dụ nó có dạng :1 13 31
			 * 1 là level
			 * 13 là hàng
			 * 31 là cột
			 */
			String[] tokens = data.split(" ");
			
			level_ = Integer.parseInt(tokens[0]); // token[0] là string level
			height_ = Integer.parseInt(tokens[1]); // token[0] là string hàng
			width_ = Integer.parseInt(tokens[2]); // token[0] là string cột

			map_ = new char[height_][width_];
			
			for (int i = 0; i < height_; ++i) {
				data = in.readLine().substring(0, width_);

				for (int j = 0; j < width_; j++) {
					map_[i][j] = data.charAt(j);
				}
 			}
			
			in.close();
		} catch (IOException e) {
			throw new LoadLevelException("Error loading level: " + "/levels/Level" + level + ".txt", e);
		}
	}
	
	@Override
	public void createEntities() {
		for (int y = 0; y < height_; y++) {
			for (int x = 0; x < width_; x++) {
				addLevelEntity( map_[y][x], x, y );
			}
		}
		while (!enemyQueue.isEmpty()) {
			int number = enemyQueue.poll();
			int x = enemyQueue.poll();
			int y = enemyQueue.poll();
			switch (number) {
				case 1:
					board_.addCharacter( new Baby(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, board_));
					break;
				case 2:
					board_.addCharacter( new Mummy(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, board_));
					break;
				case 3:
					board_.addCharacter( new Ghost(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, board_));
					break;
				case 4:
					board_.addCharacter( new Octopus(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, board_));
					break;
				case 5:
					board_.addCharacter( new Pirate(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, board_));
					break;
				default:
					break;
			}
		}
	}
	
	public void addLevelEntity(char c, int x, int y) {
		int pos = x + y * width_;

		// Chọn gạch nền
		Entity grass;
		switch (level_) {
			case 1:
				if ((x + y) % 2 == 0) {
					grass = new GrassTile(x, y, Sprite.grass, SpriteImage.images_map_floor_1);
				} else {
					grass = new GrassTile(x, y, Sprite.grass, SpriteImage.images_map_floor_2);
				}
				break;
			case 2:
				grass = new GrassTile(x ,y, Sprite.grass, SpriteImage.images_map_floor);
				break;
			case 3:
				grass = new GrassTile(x ,y, Sprite.grass, SpriteImage.images_map_grass);
				break;
			case 4:
				grass = new GrassTile(x, y, Sprite.grass, SpriteImage.images_map_lego);
				break;
			default:
				grass = new GrassTile(x, y, Sprite.grass, SpriteImage.images_map_grass);
				break;
		}


		// Load hình ảnh
		switch(c) {
			// Player
			case 'p':
				board_.addEntitie(pos, grass);

				board_.addCharacter( new Player(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, board_) );
				Screen.setOffset(0, 0);
				break;

			// Tiles
			case '#': 
				board_.addEntitie(pos, new LayeredEntity(x, y, grass,
						new WallTile(x ,y, Sprite.wall, SpriteImage.images_map_stone)) );
				break;
			case '*': 
				board_.addEntitie(pos, new LayeredEntity(x, y, grass,
						new BrickTile(x ,y, Sprite.brick, SpriteImage.images_map_wood)) );
				break;
			case 'x': 
				board_.addEntitie(pos, new LayeredEntity(x, y, grass,
						new PortalTile(x ,y, board_, Sprite.portal, SpriteImage.images_map_house_1)) );
				break;
			case ' ': 
				board_.addEntitie(pos, grass);
				break;
			case 'A':
				board_.addEntitie(pos, new LayeredEntity(x, y, grass,
						new WallTile(x, y, Sprite.wall, SpriteImage.images_map_barrier_1)));
				break;
			case 'B':
				board_.addEntitie(pos, new LayeredEntity(x, y, grass,
						new WallTile(x, y, Sprite.wall, SpriteImage.images_map_barrier_2)));
				break;
			case 'C':
				board_.addEntitie(pos, new LayeredEntity(x, y, grass,
						new BrickTile(x, y, Sprite.brick, SpriteImage.images_map_box_1)));
				break;
			case 'D':
				board_.addEntitie(pos, new LayeredEntity(x, y, grass,
						new BrickTile(x, y, Sprite.brick, SpriteImage.images_map_box_2)));
				break;
			case 'E':
				board_.addEntitie(pos, new LayeredEntity(x, y, grass,
						new BrickTile(x, y, Sprite.brick, SpriteImage.images_map_box_3)));
				break;
			case 'F':
				board_.addEntitie(pos, new LayeredEntity(x, y, grass,
						new BrickTile(x, y, Sprite.brick, SpriteImage.images_map_box_4)));
				break;
			case 'G':
				board_.addEntitie(pos, new LayeredEntity(x, y, grass,
						new BrickTile(x, y, Sprite.brick, SpriteImage.images_map_house_1)));
				break;
			case 'H':
				board_.addEntitie(pos, new LayeredEntity(x, y, grass,
						new BrickTile(x, y, Sprite.brick, SpriteImage.images_map_house_2)));
				break;
			case 'I':
				board_.addEntitie(pos, new LayeredEntity(x, y, grass,
						new BrickTile(x, y, Sprite.brick, SpriteImage.images_map_house_3)));
				break;
			case 'J':
				board_.addEntitie(pos, new LayeredEntity(x, y, grass,
						new BrickTile(x, y, Sprite.wall, SpriteImage.images_map_lego_box_1)));
				break;
			case 'K':
				board_.addEntitie(pos, new LayeredEntity(x, y, grass,
						new BrickTile(x, y, Sprite.wall, SpriteImage.images_map_lego_box_2)));
				break;
			case 'L':
				board_.addEntitie(pos, new LayeredEntity(x, y, grass,
						new BrickTile(x, y, Sprite.wall, SpriteImage.images_map_present)));
				break;
			case 'M':
				board_.addEntitie(pos, new LayeredEntity(x, y, grass,
						new WallTile(x, y, Sprite.wall, SpriteImage.images_map_stone)));
				break;
			case 'N':
				board_.addEntitie(pos, new LayeredEntity(x, y, grass,
						new WallTile(x, y, Sprite.wall, SpriteImage.images_map_torch)));
				break;
			case 'O':
				board_.addEntitie(pos, new LayeredEntity(x, y, grass,
						new BrickTile(x, y, Sprite.wall, SpriteImage.images_map_wood)));
				break;
			case 'P':
				board_.addEntitie(pos, new LayeredEntity(x, y, grass,
						new BrickTile(x, y, Sprite.wall, SpriteImage.images_map_tree_1)));
				break;
			case 'Q':
				board_.addEntitie(pos, new LayeredEntity(x, y, grass,
						new BrickTile(x, y, Sprite.wall, SpriteImage.images_map_tree_2)));
				break;
			case 'R':
				board_.addEntitie(pos, new LayeredEntity(x, y, grass,
						new BrickTile(x, y, Sprite.wall, SpriteImage.images_map_tree_3)));
				break;
			case 'S':
				board_.addEntitie(pos, new LayeredEntity(x, y, grass,
						new BrickTile(x, y, Sprite.wall, SpriteImage.images_map_tree[0])));
				break;
			case 'T':
				board_.addEntitie(pos, new LayeredEntity(x, y, grass,
						new GrassTile(x, y, Sprite.wall, SpriteImage.images_map_street_1)));
				break;
			case 'U':
				board_.addEntitie(pos, new LayeredEntity(x, y, grass,
						new GrassTile(x, y, Sprite.wall, SpriteImage.images_map_street_2)));
				break;
			case 'V':
				board_.addEntitie(pos, new LayeredEntity(x, y, grass,
						new GrassTile(x, y, Sprite.wall, SpriteImage.images_map_street_3)));
				break;
			case 'W':
				board_.addEntitie(pos, new LayeredEntity(x, y, grass,
						new GrassTile(x, y, Sprite.wall, SpriteImage.images_map_street_4)));
				break;
			case 'X':
				board_.addEntitie(pos, new LayeredEntity(x, y, grass,
						new GrassTile(x, y, Sprite.wall, SpriteImage.images_map_street_5)));
				break;

			// Items
			case 'b':
				LayeredEntity layer = new LayeredEntity(x, y, grass,
						new BrickTile(x ,y, Sprite.brick, SpriteImage.images_map_wood));

				if(board_.isItemUsed(x, y, level_) == false) {
					layer.addBeforeTop(new ItemBooms(x, y, level_, Sprite.item_booms, SpriteImage.images_item_boom));
				}

				board_.addEntitie(pos, layer);
				break;
			case 's':
				layer = new LayeredEntity(x, y, grass,
						new BrickTile(x ,y, Sprite.brick, SpriteImage.images_map_wood));

				if(board_.isItemUsed(x, y, level_) == false) {
					layer.addBeforeTop(new ItemSpeed(x, y, level_, Sprite.item_speed, SpriteImage.images_item_speed));
				}

				board_.addEntitie(pos, layer);
				break;
			case 'f':
				layer = new LayeredEntity(x, y, grass,
						new BrickTile(x ,y, Sprite.brick, SpriteImage.images_map_wood));

				if(board_.isItemUsed(x, y, level_) == false) {
					layer.addBeforeTop(new ItemFlames(x, y, level_, Sprite.item_flames, SpriteImage.images_item_boom_length));
				}

				board_.addEntitie(pos, layer);
				break;
			case 'n':
				layer = new LayeredEntity(x, y, grass,
						new BrickTile(x ,y, Sprite.brick, SpriteImage.images_map_wood));

				if(board_.isItemUsed(x, y, level_) == false) {
					layer.addBeforeTop(new ItemNewBoom(x, y, level_, Sprite.item_speed, SpriteImage.images_item_new_boom));
				}

				board_.addEntitie(pos, layer);
				break;
			case 'c':
				board_.addEntitie(pos, new LayeredEntity(x, y, grass,
						new ItemCoin(x, y, level_, Sprite.item_speed, SpriteImage.images_coin[0])));
				break;
			case 'a':
				layer = new LayeredEntity(x, y, grass);

				if(board_.isItemUsed(x, y, level_) == false) {
					layer.addTop(new ItemBooms(x, y, level_, Sprite.item_booms, SpriteImage.images_item_boom));
				}

				board_.addEntitie(pos, layer);
				break;
			case 'd':
				layer = new LayeredEntity(x, y, grass);

				if(board_.isItemUsed(x, y, level_) == false) {
					layer.addTop(new ItemSpeed(x, y, level_, Sprite.item_speed, SpriteImage.images_item_speed));
				}

				board_.addEntitie(pos, layer);
				break;
			case 'e':
				layer = new LayeredEntity(x, y, grass);

				if(board_.isItemUsed(x, y, level_) == false) {
					layer.addTop(new ItemFlames(x, y, level_, Sprite.item_flames, SpriteImage.images_item_boom_length));
				}

				board_.addEntitie(pos, layer);
				break;
			case 'g':
				layer = new LayeredEntity(x, y, grass);

				if(board_.isItemUsed(x, y, level_) == false) {
					layer.addTop(new ItemNewBoom(x, y, level_, Sprite.item_speed, SpriteImage.images_item_new_boom));
				}

				board_.addEntitie(pos, layer);
				break;

			// Enemies
			case '1':
				board_.addEntitie(pos, grass);
				enemyQueue.add(1);
				enemyQueue.add(x);
				enemyQueue.add(y);
				// board_.addCharacter( new Baby(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, board_));
				break;
			case '2':
				board_.addEntitie(pos, grass);
				enemyQueue.add(2);
				enemyQueue.add(x);
				enemyQueue.add(y);
				// board_.addCharacter( new Mummy(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, board_));
				break;
			case '3':
				board_.addEntitie(pos, grass);
				enemyQueue.add(3);
				enemyQueue.add(x);
				enemyQueue.add(y);
				// board_.addCharacter( new Pirate(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, board_));
				break;
			case '4':
				board_.addEntitie(pos, grass);
				enemyQueue.add(4);
				enemyQueue.add(x);
				enemyQueue.add(y);
				// board_.addCharacter( new Octopus(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, board_));
				break;
			case '5':
				board_.addEntitie(pos, grass);
				enemyQueue.add(5);
				enemyQueue.add(x);
				enemyQueue.add(y);
				// board_.addCharacter( new Ghost(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, board_));
				break;
			default:
				board_.addEntitie(pos, grass);
				break;
		}
	}
}
