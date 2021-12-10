package hoang.duc.dung.boomoffline.entities.character.enemy.ai;

import hoang.duc.dung.boomoffline.Board;
import hoang.duc.dung.boomoffline.Game;
import hoang.duc.dung.boomoffline.entities.character.Player;
import hoang.duc.dung.boomoffline.entities.character.enemy.Enemy;

import java.util.ArrayList;

/**
 *
 */
public class AIMedium extends AI {

    private Board board_;
    private Player player_;
    private Enemy enemy_;
    private int radius_;

    public AIMedium(Board board, Player player, Enemy enemy) {
        board_ = board;
        player_ = player;
        enemy_ = enemy;
        radius_ = Game.getBoomRadius();
    }

    @Override
    public int calculateDirection() {
        int xEnemy = enemy_.getXTile();
        int yEnemy = enemy_.getYTile();

        // t - top; b - bottom; l - left; r - right
        // tl = top left; tr = top right; bl = bottom left; br = bottom right
        /**
            tl     t     tr

            l      m      r

            bl     b     br
        */
        // CanGo -1 tương ứng với cango[4]
        // 0     1      2      3     4(-1)
        boolean[] canGo = {true, true, true, true, true};

        ArrayList<Integer> way = new ArrayList<Integer>();

        int thread = 0;

        // Duyet toan bo list bom cua bang
        for (int i = 0; i < board_.getBooms().size(); i++) {
            // Phat hiện bom
            int xBoom = board_.getBooms().get(i).getXTile();
            int yBoom = board_.getBooms().get(i).getYTile();

            // Xét những quả bom trong miền sét
            if (detectBoomInRanger(xBoom, yBoom) != -1) {
                thread++;
                // tùy trường hợp thì mình sẽ sét cách  hướng KHÔNG THỂ ĐI
                // chú ý 4 thay cho -1;
                switch (detectBoomInRanger(xBoom, yBoom)) {
                    case 1:
                        canGo[UP] = canGo[LEFT] = false;
                        break;
                    case 2:
                        if (yEnemy - yBoom == radius_) {
                            canGo[4] = canGo[UP] = false;
                        } else {
                            canGo[4] = canGo[UP] = canGo[DOWN] = false;
                        }
                        break;
                    case 3:
                        canGo[UP] = canGo[RIGHT] = false;
                        break;
                    case 4:
                        if (xEnemy - xBoom == radius_) {
                            canGo[4] = canGo[LEFT] = false;
                        } else {
                            canGo[4] = canGo[LEFT] = canGo[RIGHT] = false;
                        }
                        break;
                    case 5:
                        break;
                    case 6:
                        if (xBoom - xEnemy == radius_) {
                            canGo[4] = canGo[RIGHT] = false;
                        } else {
                            canGo[4] = canGo[LEFT] = canGo[RIGHT] = false;
                        }
                        break;
                    case 7:
                        canGo[DOWN] = canGo[LEFT] = false;
                        break;
                    case 8:
                        if (yBoom - yEnemy == radius_) {
                            canGo[4] = canGo[DOWN] = false;
                        } else {
                            canGo[4] = canGo[UP] = canGo[DOWN] = false;
                        }
                        break;
                    case 9:
                        canGo[DOWN] = canGo[RIGHT] = false;
                        break;
                    default:
                        break;
                }
            }
        }

        // nếu ko có nguy  hiểm
        if (thread == 0) {
            int vertical = random.nextInt(2);
            if (vertical == 1) {
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

        for (int k = 0; k < canGo.length; k++) {
            if (canGo[k]) {
                if (k == 4) {
                    way.add(-1); // chuyển 4 là -1
                } else {
                    way.add(k);
                }
            }
        }


        // trường hợp không có đường đi hợp lý
        // thì sẽ cho ramdom bừa
        if (way.size() == 0) {
            return random.nextInt(4);
        }

        // tồn tại đường duy nhất
        if (way.size() == 1) {
            return way.get(0);
        }

        return way.get(random.nextInt(way.size()));
    }

    /**
     * tính toán tọa độ player so với con enemy
     * @return hướng đi
     */
    public int calculateColDirection() {
		if(player_.getXTile() < enemy_.getXTile())
			return LEFT;
		else if(player_.getXTile() > enemy_.getXTile())
			return RIGHT;
		
		return -1;
	}
	
	public int calculateRowDirection() {
		if(player_.getYTile() < enemy_.getYTile())
			return UP;
		else if(player_.getYTile() > enemy_.getYTile())
			return DOWN;
		return -1;
	}
        
    /**
     * Kiểm tra vị trí xung quanh enemy
     *        x1  x2  x3
     *   y1   1   2   3
     *   y2   4   e   6
     *   y3   7   8   9
     * @param xBoom toa do x bom
     * @param yBoom toa do y bom
     * @return vị chí của bom trong khu vực dò của enemy
     * @return -1 nếu không thuộc phạm vi dò
     */
    public int detectBoomInRanger(int xBoom, int yBoom) {
        int xEnemy = enemy_.getXTile();
        int yEnemy = enemy_.getYTile();

        // ngang
        if (yBoom == yEnemy) {
            // bom o ben phai
            if ((xBoom - xEnemy) > 0 && (xBoom - xEnemy) <= radius_) {
                return 6; // phai
            }
            // bom o ben trai
            if ((xEnemy - xBoom) > 0 && (xEnemy - xBoom) <= radius_) {
                return 4; // trai
            }
        }
        // doc
        if (xBoom == xEnemy) {
            // bom o duoi
            if ((yBoom - yEnemy) > 0 && (yBoom - yEnemy) <= radius_) {
                return 8; // duoi
            }
            // bom o tren
            if ((yEnemy - yBoom) > 0 && (yEnemy - yBoom) <= radius_) {
                return 2; // tren
            }
        }

        // goc tren
        if ((yEnemy - yBoom > 0) && (yEnemy - yBoom <= radius_)) {
            if ((xBoom - xEnemy) > 0 && ( xBoom - xEnemy) <= radius_) {
                return 3; // tren phai
            }
            if ((xEnemy - xBoom) > 0 && ( xEnemy - xBoom) <= radius_) {
                return 1; // tren trai
            }
        }
        // goc duoi
        if ((yBoom - yEnemy > 0) && (yBoom - yEnemy <= radius_)) {
            if ((xBoom - xEnemy) > 0 && (xBoom - xEnemy) <= radius_) {
                return 9; // duoi phai
            }
            if ((xEnemy - xBoom) > 0 && (xEnemy - xBoom) <= radius_) {
                return 7; // duoi trai
            }
        }

        // Khong co bom tra ve -1
        return -1;
    }

}
