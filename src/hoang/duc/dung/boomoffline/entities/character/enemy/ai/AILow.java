package hoang.duc.dung.boomoffline.entities.character.enemy.ai;

import hoang.duc.dung.boomoffline.entities.character.Player;
import hoang.duc.dung.boomoffline.entities.character.enemy.Enemy;

/**
 *
 */
public class AILow extends AI {
	Player player_;
	Enemy enemy_;

	public AILow(Player player, Enemy e) {
		player_ = player;
		enemy_ = e;
	}

	@Override
	public int calculateDirection() {

		if(player_ == null)
			return random.nextInt(4);

		int vertical = random.nextInt(2);

		if(vertical == 1) {
			int v = calculateRowDirection();
			if(v != -1)
				return v;
			else
				return calculateColDirection();

		} else {
			int h = calculateColDirection();

			if(h != -1)
				return h;
			else
				return calculateRowDirection();
		}

	}

	protected int calculateColDirection() {
		if(player_.getXTile() < enemy_.getXTile())
			return LEFT;
		else if(player_.getXTile() > enemy_.getXTile())
			return RIGHT;

		return -1;
	}

	protected int calculateRowDirection() {
		if(player_.getYTile() < enemy_.getYTile())
			return UP;
		else if(player_.getYTile() > enemy_.getYTile())
			return DOWN;
		return -1;
	}

}
