package hex.client.gui.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serial;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import hex.client.gui.ClientWindow;
import hex.resources.Chessboard;
//胜利界面
public class Winning extends JPanel {
	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 8255297849457523016L;
	private static Color color;
	private static String text;
	private static final URL imgURL=Winning.class.getResource("/image/Reset.png");
	private static final Image RESET = new ImageIcon(imgURL).getImage();
	private final int offsetX = ClientWindow.getWindowWidth() / 2;
	private final int offsetY = ClientWindow.getWindowHeight() / 2;
	private final int imageSize = 100;

	public Winning() {
		addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
			}

			// 鼠标监听按下操作
			public void mouseReleased(MouseEvent e) {
				if (e.getX() > offsetX - imageSize / 2 && e.getX() < offsetX + imageSize / 2
						&& e.getY() < offsetY + imageSize&&e.getY()>offsetY) {
					Chessboard.Reset();
					ClientWindow.initializeWindow(new Menu());				
					System.out.print("Done!");					
				}
			}

			public void mouseClicked(MouseEvent e) {

			}

			public void mouseEntered(MouseEvent e) {

			}

			public void mouseExited(MouseEvent e) {
			}
		});
	}

	public static void setText(String text) {
		Winning.text = text;
	}

	public static void setColor(Color color) {
		Winning.color = color;
	}

	public void paint(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.setColor(color);
		int fontSize = 100;
		g2d.setFont(new Font("等线", Font.BOLD, fontSize));
		g2d.drawString(text, offsetX - fontSize * 5 / 2f, offsetY - fontSize);
		g.drawImage(RESET, offsetX - imageSize / 2, offsetY, imageSize, imageSize, null);
	}
}
