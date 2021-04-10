package hex.client.gui;

import java.awt.Dimension;
import java.io.Serial;
import javax.swing.JFrame;
import hex.client.gui.graphics.HexBoard;
import hex.client.gui.graphics.Menu;
import hex.client.gui.graphics.Winning;
import hex.resources.Chessboard;

public class ClientWindow extends JFrame {
	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 1L;
	static ClientWindow HEX = null;
	private static final int windowWidth = 1600;
	private static final int windowHeight = 900;

	public static int getWindowWidth() {
		return windowWidth;
	}

	public static int getWindowHeight() {
		return windowHeight;
	}

	public static void initializeWindow(Menu menu) {
		if (HEX != null) {
			ClientWindow temp = HEX;
			temp.dispose();
		}
		HEX = new ClientWindow();
		HEX.setTitle("HEX");
		HEX.setSize(new Dimension(windowWidth, windowHeight));
		HEX.setResizable(false);
		HEX.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		HEX.setLocationRelativeTo(null);
		Chessboard.mutex = 0;
		HEX.setVisible(true);
		HEX.getContentPane().add(menu);
	}

	public static void jumpToWon(Winning winning) {
		HEX.getContentPane().add(winning);
	}

	public static void jumpToGame(HexBoard hexBoard) {
		HEX.getContentPane().add(hexBoard);
	}
}
