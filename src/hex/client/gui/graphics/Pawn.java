package hex.client.gui.graphics;

import java.awt.Color;

import hex.client.gui.ClientWindow;
import hex.resources.Chessboard;
import hex.resources.Pos;

public class Pawn {
	//常量：偏移量、大小、坐标起始点
	public static final float size = 40f;
	private static final float offsetX = ClientWindow.getWindowWidth() / 2.0f +size;
	private static final float offsetY = ClientWindow.getWindowHeight() / 2.0f ;
	private static final float[] X = { 0 + offsetX, 0.866f * size + offsetX, 1.732f * size + offsetX,
			1.732f * size + offsetX, 0.866f * size + offsetX, 0 + offsetX };
	private static final float[] Y = { 0.5f * size + offsetY, 0 + offsetY, 0.5f * size + offsetY, 1.5f * size + offsetY,
			2 * size + offsetY, 1.5f * size + offsetY };
	
	//单棋子顶点坐标
	private final float[] x=new float[6];
	private final float[] y=new float[6];
	//棋子颜色
	private  Color color=new Color(219,219,219);
	Pawn(int row, int col) {
		Pos pos = new Pos(row, col);
		for (int i = 0; i < 6; i++) {
			//单棋子顶点坐标计算
			x[i] = X[i] + (pos.getCol() - (Chessboard.getSize() / 2) - 1) * size * 1.732f
					+ (pos.getRow() - (Chessboard.getSize() / 2) - 1) *size*0.866f;
			y[i] = Y[i] + (pos.getRow() - (Chessboard.getSize() / 2) - 1) * size * 1.5f;
		}
	}

	//返回单棋子顶点坐标
	public float getXi(int i) {
		return x[i];
	}

	public float getYi(int i) {
		return y[i];
	}
	public Color getColor() {
		return color;
	}
	//设置棋子颜色
	public void setColor(Color color) {
		this.color=color;
	}
}

