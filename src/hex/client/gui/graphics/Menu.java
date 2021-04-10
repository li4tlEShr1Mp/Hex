package hex.client.gui.graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;

import javax.swing.JButton;
import javax.swing.JPanel;

import hex.client.gui.ClientWindow;
import hex.resources.Chessboard;

public class Menu extends JPanel{
	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = -3028359665114757196L;
	final JButton button_1;
	final JButton button_2;
	
	//this.add(new JLabel("请选择模式"));
	public Menu() {
		button_1 = new JButton("PVP");
		button_1.addActionListener(e -> {
			Chessboard.isAI=false;
			jump();

		});
		//button_1.setBounds(200, 800, 100, 50);
		add(button_1);
		button_2 = new JButton("PVE");
		button_2.addActionListener(e -> {
			Chessboard.isAI=true;
			jump();
		});
		add(button_2);
	}
	public void jump() {
		this.setVisible(false);
		ClientWindow.jumpToGame(new HexBoard());
	}
}
