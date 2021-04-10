package hex.client.main;
import hex.client.gui.ClientWindow;
import hex.client.gui.graphics.Menu;
public class Game {
	public static void main(String[] args) {
		ClientWindow.initializeWindow(new Menu());
		System.out.println("start");
	}
}