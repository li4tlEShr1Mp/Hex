package hex.rules;

import hex.resources.Chessboard;
import hex.resources.Pos;

public class BesidePawns {
	// ��������˳������
	// �յ㷽������
	static final int[] dx = { 1, 1, 0, 0, -1, -1 }; // mutex=0 dx->row
	static final int[] dy = { 0, -1, 1, -1, 0, 1 }; // mutex=1 dy->row

	// ѡȡindex�����Χ��Ч����λ��
	public static Pos[] besidePos(boolean[][] searchVisited, Pos index) {
		// ��Χ����λ������
		Pos[] positions = new Pos[6];
		if (Chessboard.mutex == 0/* Player1���ȷ��� */) {
			for (int n = 0; n < 6; n++) {
				if (isLegal(searchVisited, index.getRow() + dx[n], index.getCol() + dy[n])) {
					positions[n] = new Pos(index.getRow() + dx[n], index.getCol() + dy[n]);
				} else//����Ч����λ���ÿ�
					positions[n] = null;
			}
		} else/* Player2���ȷ���(Player1 Player2�����̶Գ� dx dy �Ե� ���ȷ���Ե�) */ {
			for (int n = 0; n < 6; n++) {
				if (isLegal(searchVisited, index.getRow() + dy[n], index.getCol() + dx[n])) {
					positions[n] = new Pos(index.getRow() + dy[n], index.getCol() + dx[n]);
				} else
					positions[n] = null;
			}
		}
		return positions;
		// �����ڽӿ��ж�����λ����
	}

	private static boolean isLegal(boolean[][] searchVisited, int pos1, int pos2) {
		// �ж��ڽ�λ���Ƿ������ж���ͨ����
		//�Ƿ���ʹ�
		return pos1 <= Chessboard.getSize() - 1 && pos2 <= Chessboard.getSize() - 1 && pos1 >= 0 && pos2 >= 0//���̷�Χ
				&& Chessboard.isSeatTaken[pos1][pos2] != false //�Ƿ��������
				&& Chessboard.board[pos1][pos2] == Chessboard.mutex //�Ƿ�Ϊͬ������
				&& searchVisited[pos1][pos2] != true;
	}
}
