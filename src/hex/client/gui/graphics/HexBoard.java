package hex.client.gui.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;
import javax.swing.JPanel;

import hex.client.gui.ClientWindow;
import hex.client.gui.MouseInfo;
import hex.resources.Chessboard;
import hex.resources.Pos;
import hex.rules.AIPawn;
import hex.rules.IsWon;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.Serial;

//棋盘图像
public class HexBoard extends JPanel {
	/**
	 * serialVersionUID适用于java序列化机制
	 **/
	@Serial
	private static final long serialVersionUID = 5052004495051269823L;
	// 棋子数组
	public static final Pawn[][] pawns = new Pawn[Chessboard.getSize()][Chessboard.getSize()];

	// 使用棋子构造棋盘
	public HexBoard() {
		this.setVisible(true);
		for (int i = 0; i < Chessboard.getSize(); i++) {
			for (int j = 0; j < Chessboard.getSize(); j++) {
				pawns[i][j] = new Pawn(i, j);
			}
		}
		addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
			}

			// 鼠标监听按下操作
			public void mouseReleased(MouseEvent e) {
				Pos position = MouseInfo.getPos(e.getX(), e.getY());// 返回按下点的对应棋子位置
				boolean temp = position == null || Chessboard.isSeatTaken[position.getRow()][position.getCol()];
				setPawn(position, ColorMapping.mapColor(Chessboard.mutex));// 下棋操作
				if (Chessboard.isAI && !temp) {// 全局变量、有PVE执行AI算法
					position = AIPawn.optimizedAlgorithm(position);
					setPawn(position, ColorMapping.mapColor(Chessboard.mutex));
				}

			}

			public void mouseClicked(MouseEvent e) {

			}

			public void mouseEntered(MouseEvent e) {

			}

			public void mouseExited(MouseEvent e) {
			}
		});
		addMouseMotionListener(new MouseMotionListener() {
			public void mouseDragged(MouseEvent e) {
			}

			// 鼠标悬浮高光提示
			// 临时指针
			private Pos lastHoverPos = null;

			public void mouseMoved(MouseEvent e) {
				Pos hoverPosition = MouseInfo.getPos(e.getX(), e.getY());
				if (lastHoverPos == null || lastHoverPos != hoverPosition) {
					hoverRepaint(lastHoverPos, new Color(219, 219, 219));
					// 色彩空间映射
					hoverRepaint(hoverPosition, ColorMapping.mapHoverColor(Chessboard.mutex));
					lastHoverPos = hoverPosition;
				} else {
				}
			}
		});
	}

	// 下棋操作、改变棋子颜色
	public void setPawn(Pos pos, Color color) {
		if (pos == null || Chessboard.isSeatTaken[pos.getRow()][pos.getCol()])
			return;
		pawns[pos.getRow()][pos.getCol()].setColor(color);
		Chessboard.board[pos.getRow()][pos.getCol()] = Chessboard.mutex;
		Chessboard.isSeatTaken[pos.getRow()][pos.getCol()] = true;
		if (IsWon.isWin()) {
			this.setVisible(false);
			ClientWindow.jumpToWon(new Winning());
		}
		// 下棋权移交
		if (Chessboard.mutex == 1) {
			Chessboard.mutex = 0;
		} else if (Chessboard.mutex == 0)
			Chessboard.mutex = 1;
		repaint();
	}

	// 高光重绘
	public void hoverRepaint(Pos pos, Color color) {
		if (pos == null || Chessboard.isSeatTaken[pos.getRow()][pos.getCol()])
			return;
		pawns[pos.getRow()][pos.getCol()].setColor(color);
		repaint();
	}

	public void paint(Graphics g) {
		// 棋盘绘制
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		// 绘制边界
		drawBorder(g2d);
		// 绘制棋子
		for (int i = 0; i < Chessboard.getSize(); i++) {
			for (int j = 0; j < Chessboard.getSize(); j++) {
				GeneralPath p = new GeneralPath();
				// 六边连线
				p.moveTo(pawns[i][j].getXi(0), pawns[i][j].getYi(0));
				p.lineTo(pawns[i][j].getXi(1), pawns[i][j].getYi(1));
				p.lineTo(pawns[i][j].getXi(2), pawns[i][j].getYi(2));
				p.lineTo(pawns[i][j].getXi(3), pawns[i][j].getYi(3));
				p.lineTo(pawns[i][j].getXi(4), pawns[i][j].getYi(4));
				p.lineTo(pawns[i][j].getXi(5), pawns[i][j].getYi(5));
				p.lineTo(pawns[i][j].getXi(0), pawns[i][j].getYi(0));
				p.closePath();
				// 棋子填充色
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2d.setColor(pawns[i][j].getColor());
				g2d.fill(p);
				g2d.setColor(new Color(103, 103, 103));
				g2d.draw(p);
			}
		}
	}

	static class vector {
		final float x;
		final float y;

		// 向量
		vector(float x, float y) {
			this.x = x;
			this.y = y;
		}
	}

	// 边界绘制
	private void drawBorder(Graphics2D g) {
		// 向量计算
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		vector vO = new vector(pawns[0][0].getXi(0) - pawns[0][0].getXi(3),
				pawns[0][0].getYi(0) - pawns[0][0].getYi(3));
		// 左上（棋盘）
		vector pUL = new vector(pawns[0][0].getXi(0) + vO.x, pawns[0][0].getYi(0) + vO.y);
		// 右下
		vector pDR = new vector(pawns[Chessboard.getSize() - 1][Chessboard.getSize() - 1].getXi(3) - vO.x,
				pawns[Chessboard.getSize() - 1][Chessboard.getSize() - 1].getYi(3) - vO.y);
		// 右上
		vector pUR = new vector(pUL.x + (pDR.x - pUL.x) * 2 / 3, pUL.y);
		// 左下
		vector pDL = new vector(pUL.x + (pDR.x - pUL.x) * 1 / 3, pDR.y);
		GeneralPath p = new GeneralPath();
		p.moveTo(pUL.x, pUL.y);
		p.lineTo(pUR.x, pUR.y);//
		p.lineTo(pDL.x, pDL.y);
		p.lineTo(pDR.x, pDR.y);
		p.lineTo(pUL.x, pUL.y);
		g.setColor(new Color(255, 68, 68));
		g.fill(p);
		GeneralPath q = new GeneralPath();
		q.moveTo(pUL.x, pUL.y);
		q.lineTo(pDL.x, pDL.y);
		q.lineTo(pUR.x, pUR.y);
		q.lineTo(pDR.x, pDR.y);
		q.lineTo(pUL.x, pUL.y);
		g.setColor(new Color(45, 183, 255));
		g.fill(q);
		// 去角
		GeneralPath delete = new GeneralPath();
		delete.moveTo(pawns[0][0].getXi(5) + vO.x / 1.9, pawns[0][0].getYi(5) + vO.y / 1.9);
		delete.lineTo(pawns[0][0].getXi(1) + vO.x / 1.9, pawns[0][0].getYi(1) + vO.y / 1.9);
		delete.lineTo(pUL.x+vO.x, pUL.y+vO.y);
		delete.moveTo(pawns[Chessboard.getSize() - 1][Chessboard.getSize() - 1].getXi(2) - vO.x / 1.9,
				pawns[Chessboard.getSize() - 1][Chessboard.getSize() - 1].getYi(2) - vO.y / 1.9);
		delete.lineTo(pawns[Chessboard.getSize() - 1][Chessboard.getSize() - 1].getXi(4) - vO.x / 1.9,
				pawns[Chessboard.getSize() - 1][Chessboard.getSize() - 1].getYi(4) - vO.y / 1.9);
		delete.lineTo(pDR.x-vO.x, pDR.y-vO.y);
		g.setColor(new Color(238, 238, 238));
		g.fill(delete);
	}
}