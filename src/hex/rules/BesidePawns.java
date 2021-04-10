package hex.rules;

import hex.resources.Chessboard;
import hex.resources.Pos;

public class BesidePawns {
	// 棋子优先顺序排列
	// 终点方向优先
	static final int[] dx = { 1, 1, 0, 0, -1, -1 }; // mutex=0 dx->row
	static final int[] dy = { 0, -1, 1, -1, 0, 1 }; // mutex=1 dy->row

	// 选取index点的周围有效棋子位置
	public static Pos[] besidePos(boolean[][] searchVisited, Pos index) {
		// 周围棋子位置数组
		Pos[] positions = new Pos[6];
		if (Chessboard.mutex == 0/* Player1优先方向 */) {
			for (int n = 0; n < 6; n++) {
				if (isLegal(searchVisited, index.getRow() + dx[n], index.getCol() + dy[n])) {
					positions[n] = new Pos(index.getRow() + dx[n], index.getCol() + dy[n]);
				} else//非有效棋子位置置空
					positions[n] = null;
			}
		} else/* Player2优先方向(Player1 Player2沿棋盘对称 dx dy 对调 优先方向对调) */ {
			for (int n = 0; n < 6; n++) {
				if (isLegal(searchVisited, index.getRow() + dy[n], index.getCol() + dx[n])) {
					positions[n] = new Pos(index.getRow() + dy[n], index.getCol() + dx[n]);
				} else
					positions[n] = null;
			}
		}
		return positions;
		// 返回邻接可判断棋子位置组
	}

	private static boolean isLegal(boolean[][] searchVisited, int pos1, int pos2) {
		// 判断邻接位置是否纳入判断连通数组
		//是否访问过
		return pos1 <= Chessboard.getSize() - 1 && pos2 <= Chessboard.getSize() - 1 && pos1 >= 0 && pos2 >= 0//棋盘范围
				&& Chessboard.isSeatTaken[pos1][pos2] != false //是否存在棋子
				&& Chessboard.board[pos1][pos2] == Chessboard.mutex //是否为同种棋子
				&& searchVisited[pos1][pos2] != true;
	}
}
