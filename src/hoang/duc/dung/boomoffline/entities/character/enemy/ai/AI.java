package hoang.duc.dung.boomoffline.entities.character.enemy.ai;

import java.util.Random;

public abstract class AI {
	public static final int UP = 0;
	public static final int RIGHT = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	
	protected Random random = new Random();
	
	public abstract int calculateDirection();
}
