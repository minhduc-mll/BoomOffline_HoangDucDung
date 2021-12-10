package hoang.duc.dung.boomoffline.entities.character.enemy.ai;

import hoang.duc.dung.boomoffline.Board;
import hoang.duc.dung.boomoffline.Game;
import hoang.duc.dung.boomoffline.entities.character.Player;
import hoang.duc.dung.boomoffline.entities.character.enemy.Enemy;
import hoang.duc.dung.boomoffline.entities.tile.destroyable.BrickTile;
import hoang.duc.dung.boomoffline.level.FileLevel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Dùng BFS để tìm đường đi ngắn nhất
 */
public class AIAdvance extends AI {
    private Board board_;
    private Player player_;
    private Enemy enemy_;

    public ArrayList<Integer> path_ = new ArrayList(); // Đường đi ngắn nhất
    public int height_ = Game.TILES_SCREEN_HEIGHT; // Cột dọc
    public int width_ = Game.TILES_SCREEN_WIDTH; // Cột ngang

    public int[][] matrix_ = new int[height_][width_]; // 1 ma trận 2 chiều
    public int[][] node_ = new int[height_ * width_][4]; // Tạo ma trận đỉnh kề
    public int maxOfNode_ = height_ * width_; // Số lượng đỉnh tối đa

    private String wall = "#ABMNO";
    private String brick = "*xCDEFGHIJKLPQRS";

    public AIAdvance(Board board, Player player, Enemy enemy) {
        board_ = board;
        player_ = player;
        enemy_ = enemy;
    }

    public void getMatrix() {
        int nameOfVertex = 1;
        for (int i = 0; i < height_; i++) {
            for (int j = 0; j < width_; j++) {
                if ( wall.contains(String.valueOf(FileLevel.map_[i][j])) ) {
                    matrix_[i][j] = 0;
                } else if ( brick.contains(String.valueOf(FileLevel.map_[i][j])) ) {
                    matrix_[i][j] = nameOfVertex * (-1);
                    nameOfVertex++;
                } else {
                    this.matrix_[i][j] = nameOfVertex;
                    nameOfVertex++;
                }
            }
        }
    }

    /**
     * Cập nhật lại ma trận kề
     */
    public void updateDestroy_BrickTile() {
        if (BrickTile.xTileBroken_.isEmpty() || BrickTile.yTileBroken_.isEmpty())
            return;

        for (int i = 0; i < BrickTile.xTileBroken_.size(); i++) {
            int xBrick = BrickTile.xTileBroken_.get(i);
            int yBrick = BrickTile.yTileBroken_.get(i);

            // Nếu gạch đã bị phá hủy
            if (matrix_[yBrick][xBrick] < 0) {
                // nếu chưa phá âm thì lấy abs thành dương (dương là đã phá)
                matrix_[yBrick][xBrick] = matrix_[yBrick][xBrick] * (-1);
            }
        }
    }

    /**
     * Chuyển ma trận đỉnh sang ma trận kề
     */
    public void convertNearNodeMatrix() {
        for (int i = 1; i < height_ - 1; i++) {
            for (int j = 1; j < width_ - 1; j++) {
                if (this.matrix_[i][j] > 0) {
                    // cùng hàng
                    // bên trái
                    if (this.matrix_[i][j - 1] > 0) {
                        this.node_[this.matrix_[i][j]][0] = this.matrix_[i][j - 1];
                    } else {
                        this.node_[this.matrix_[i][j]][0] = 0; // không có đỉnh kể
                    }
                    // bên phải
                    if (this.matrix_[i][j + 1] > 0) {
                        this.node_[this.matrix_[i][j]][1] = this.matrix_[i][j + 1];
                    } else {
                        this.node_[this.matrix_[i][j]][1] = 0;
                    }

                    // cùng cột
                    // bên trên
                    if (this.matrix_[i - 1][j] > 0) {
                        this.node_[this.matrix_[i][j]][2] = this.matrix_[i - 1][j];
                    } else {
                        this.node_[this.matrix_[i][j]][2] = 0;
                    }
                    // bên dưới
                    if (this.matrix_[i + 1][j] > 0) {
                        this.node_[this.matrix_[i][j]][3] = this.matrix_[i + 1][j];
                    } else {
                        this.node_[this.matrix_[i][j]][3] = 0;
                    }
                }
            }
        }
    }

    /**
     * update vị chí của bom dùng để né bom
     * nếu enemy nằm trong flame của bom thì dừng ngay việc
     * làm âm đỉnh từ enemy đến vị chí dự kiến kết thúc của flame
     * 
     */
    public void updateMatrix() {
        // update bom
        int r = Game.getBoomRadius(); // bán kính bom nổ
        // toa độ enemy
        int xe = this.enemy_.getXTile();
        int ye = this.enemy_.getYTile();
        int xb = this.player_.getXTile();
        int yb = this.player_.getYTile();

        for (int i = 0; i < this.player_.getBooms().size(); i++) {
            // Lấy tọa độ quả bom
            int xt = this.player_.getBooms().get(i).getXTile();
            int yt = this.player_.getBooms().get(i).getYTile();

            // Làm âm đỉnh tại vị trí quả bom
            this.matrix_[yt][xt] *= -1;
            // Làm âm đỉnh tại vị trí ảnh hưởng của bom

            // xét ngang
            // phải
            for (int j = 1; j <= r; j++) {
                if (this.matrix_[yt][xt + j] > 0 && yt != ye && (xt + j) != xe) {
                    // Làm âm đỉnh nếu enemy trong vùng ảnh hưởng của bom
                    this.matrix_[yt][xt + j] *= -1;
                } else {
                    break;// break khi gặp người
                }
            }

            for (int j = 1; j <= r; j++) {
                if (this.matrix_[yt][xt - j] > 0 && yt != ye && (xt - j) != xe) {
                    this.matrix_[yt][xt - j] *= -1;
                } else {
                    break;
                }
            }
            // xét dọc
            // dưới
            for (int j = 1; j <= r; j++) {
                if (this.matrix_[yt + j][xt] > 0 && (yt + j) != ye && xt != xe) {
                    this.matrix_[yt + j][xt] *= -1;
                } else {
                    break;
                }
            }
            // trên
            for (int j = 1; j <= r; j++) {
                if (this.matrix_[yt - j][xt] > 0 && (yt - j) != ye && xt != xe) {
                    this.matrix_[yt - j][xt] *= -1;
                } else {
                    break;
                }
            }
        }
    }

    /**
     *
     * @param start đỉnh bắt đầu
     * @param end   đỉnh kết thúc
     * @return đỉnh sẽ đi bên cạnh start
     * @throws IllegalStateException
     */
    int BreathFistSearch(int start, int end) throws IllegalStateException { // exception khi queue ko còn chỗ
        // Queue chứa các node
        Queue<Integer> queueofNode = new LinkedList<Integer>();

        int[] parent = new int[maxOfNode_ + 1];
        boolean[] visted = new boolean[maxOfNode_ + 1];

        // trường hợp bommer hoặc người chơi trong khu vực bom tức đỉnh âm thì sẽ đc xử
        // lý ở đây.

        if (start < 0)
            start *= -1;
        if (end < 0)
            end *= -1;

        visted[start] = false;
        parent[start] = -1; // sét mặc định đỉnh cha
        parent[end] = -1;

        // thêm đỉnh start vào đầu
        queueofNode.add(start);

        while (!queueofNode.isEmpty()) {
            // dequeue phần tử đầu
            int currentNode = queueofNode.poll();

            // duyệt toàn bộ đỉnh kể với current , nếu chưa visit thì dán cho là visit
            for (int i = 0; i < 4; i++) {
                if (visted[node_[currentNode][i]] == false && node_[currentNode][i] != 0) {
                    // dán nhãn đã thăm
                    visted[node_[currentNode][i]] = true;
                    // gán đỉnh cha
                    parent[node_[currentNode][i]] = currentNode;
                    // cho vào queue
                    queueofNode.add(node_[currentNode][i]);
                }
            }
        }

        // temp là đường đi ngắn nhất
        int temp = parent[end];

        // thêm node cuối
        if (temp != -1) {
            path_.add(end);
            path_.add(temp);
            while (temp != start) { // nếu chưa đi đến gốc
                temp = parent[temp];
                path_.add(temp);
            }

            // Trả về đỉnh kế tiếp start
            return path_.get(path_.size() - 2);
        }

        // Không có đường đi , đứng im thôi :V
        return -1;
    }

    @Override
    public int calculateDirection() {
        // TODO: cài đặt thuật toán tìm đường đi

        this.getMatrix();
        this.updateMatrix();
        this.updateDestroy_BrickTile();
        this.convertNearNodeMatrix();
        // Phải update liên tục các thông số

        // Đỉnh đầu
        int start = this.matrix_[this.enemy_.getYTile()][this.enemy_.getXTile()];
        // Tọa độ player và cũng là đỉnh cuối của đường đi
        int end = this.matrix_[this.player_.getYTile()][this.player_.getXTile()];

        // Đỉnh cần đi tiếp theo
        int result = this.BreathFistSearch(start, end);

        if (result == -1)
            return -1; // -1 thì đứng yên đó thôi :V

        if (result - start == 1)
            return 1; // đi phải
        if (start - result == 1)
            return 3; // đi trái
        if (start > result)
            return 0; // đi lên
        if (start < result)
            return 2; // đi xuống

        return -1;
    }
}