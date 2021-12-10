package hoang.duc.dung.boomoffline.level;

import hoang.duc.dung.boomoffline.Board;
import hoang.duc.dung.boomoffline.exceptions.LoadLevelException;

public abstract class Level implements ILevel {

	/**
	 * Ma trận chứa thông tin bản đồ, mỗi phần tử lưu giá trị kí tự đọc được
	 * từ ma trận bản đồ trong tệp cấu hình
	 */
	public static char[][] map_;

	protected int level_;
	protected int width_, height_;
	protected Board board_;

	protected static String[] codes = {//TODO: change this code system to actually load the code from each level.txt
			"level 1",
			"level 2",
			"level 3",
			"level 4",
			"level 5",
			"level 6",
			"level 7",
			"level 8",
	};

	public Level(int level, Board board) throws LoadLevelException {
		loadLevel(level);
		board_ = board;
	}

	@Override
	public abstract void loadLevel(int level) throws LoadLevelException;
	
	public abstract void createEntities();

	/*
	|--------------------------------------------------------------------------
	| Codes
	|--------------------------------------------------------------------------
	 */
	public int validCode(String str) {
		for (int i = 0; i < codes.length; i++) {
			if (codes[i].equals(str)) {
				return i;
			}
		}
		return -1;
	}
	
	public String getActualCode() {
		return codes[level_ -1];
	}

	public int getWidth() {
		return width_;
	}

	public int getHeight() {
		return height_;
	}

	public int getLevel() {
		return level_;
	}

}
