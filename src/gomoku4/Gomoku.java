package gomoku4;

import javax.swing.JFrame;

import gomoku4.components.Background;
import gomoku4.components.WhiteStone;

public class Gomoku {
	private static Background[] gomoku;
	private static int game = 0;
	
	
	public static void main(String[] args) {
		gomoku = new Background[100];
		gomoku[game] = new Background();
	}
	public void newGame() {
		gomoku[game].setVisible(false);
		game++;
		gomoku[game] = new Background();
	}
}
