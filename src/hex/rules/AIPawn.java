package hex.rules;

import java.util.Random;

import hex.resources.*;

public class AIPawn {
	public static Pos optimizedAlgorithm(Pos lastPos) {
		Pos pos = null;
		if (lastPos == null)
			return null;
		if (lastPos.getRow() + 2 <= Chessboard.getSize() - 1 && lastPos.getCol() - 1 >= 0
				&& !Chessboard.isSeatTaken[lastPos.getRow() + 2][lastPos.getCol() - 1]) {
			pos = new Pos(lastPos.getRow() + 2, lastPos.getCol() - 1);
			return pos;
		} else if (lastPos.getRow() - 2 >= 0 && lastPos.getCol() + 1 <= Chessboard.getSize() - 1
				&& !Chessboard.isSeatTaken[lastPos.getRow() - 2][lastPos.getCol() + 1]) {
			pos = new Pos(lastPos.getRow() - 2, lastPos.getCol() + 1);
			return pos;
		} else {
			while (true) {
				Random r = new Random();
				int row = r.nextInt(11);
				int col = r.nextInt(11);
				if (!Chessboard.isSeatTaken[row][col]) {
					pos = new Pos(row, col);
					return pos;

				}
			}

		}
	}
}