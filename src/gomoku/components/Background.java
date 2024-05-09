package gomoku.components;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

<<<<<<< HEAD
import service.Rule;
=======
import service.WinRule;
>>>>>>> 50eaa5c502f02306af3a8a9a1f0305d764b923e5

public class Background extends JFrame implements ActionListener{

	final int LINE_NUM = 1000;
	final int LINE_WIDTH = 1000;

	private final int[][] map = new int[LINE_NUM][LINE_NUM];
	private final int BLACK_STONE = 1; // 배열에 입력된 값이 1인 경우 그자리에는 흑돌이 있음
	private final int WHITE_STONE = 2; // 배열에 입력된 값이 2인 경우 그자리에는 백돌이 있음

	private JLabel backgroundMap;
<<<<<<< HEAD
=======

	public int[][] getMap() {
		return map;
	}
<<<<<<< HEAD

	private Player player;

	private Stone stone;
	Target cursor;
=======
>>>>>>> 4a51b053734a6efc9cc74eb65af12c353250383b
	Cursor cursor;
>>>>>>> 50eaa5c502f02306af3a8a9a1f0305d764b923e5
	private int x;
	private int y;
	private int color;
	private boolean three;
	Background mContext = this;
<<<<<<< HEAD
	JButton button;
	JLabel result;
	
=======
	Rule rule;

>>>>>>> 4a51b053734a6efc9cc74eb65af12c353250383b
	public Background() {
		initData();
		setInitLayout();
		addEventListener();
		new Thread(new WinRule(mContext)).start();
	}
	public int[][] getMap() {
		return map;
	}
	public void setThree(boolean three) {
		this.three = three;
	}

	public int getColor() {
		return color;
	}

	private void initData() {
		backgroundMap = new JLabel(new ImageIcon("images/omokbackground.png"));
		setContentPane(backgroundMap);
		setTitle("오목게임");
		setSize(1000, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
<<<<<<< HEAD
		cursor = new Cursor(mContext);
		button = new JButton("다시 하기");
=======
		cursor = new Target(mContext);

		rule = new Rule(mContext);
>>>>>>> 4a51b053734a6efc9cc74eb65af12c353250383b
	}

	private void setInitLayout() {
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		backgroundMap.add(button);
		add(cursor);
	}

	private void addEventListener() {
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
<<<<<<< HEAD
				// System.out.println(cursor.getX() + " ," + cursor.getY());
=======
>>>>>>> 50eaa5c502f02306af3a8a9a1f0305d764b923e5
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:  
					cursor.left();
					break;
				case KeyEvent.VK_RIGHT:
					cursor.right();
					break;
				case KeyEvent.VK_UP:
					cursor.up();
					break;
				case KeyEvent.VK_DOWN:
					cursor.down();
					break;
				case KeyEvent.VK_SPACE:
					if(map[cursor.getX()][cursor.getY()] == 0) {
					if ((color % 2) == 0) {
						System.out.println("흑돌 차례 입니다.");
						cursor.BlackStone();
						getBlack(cursor.getX(), cursor.getY());
						map[cursor.getX()][cursor.getY()] = 1;

					} else {
						System.out.println("백돌 차례 입니다.");
						cursor.WhiteStone();
						map[cursor.getX()][cursor.getY()] = 2;
					}
<<<<<<< HEAD
					// System.out.println(map[cursor.getX()][cursor.getY()]);
=======
					System.out.println(cursor.getX() + " ," + cursor.getY());
					System.out.println(map[cursor.getX()][cursor.getY()]);
>>>>>>> 50eaa5c502f02306af3a8a9a1f0305d764b923e5
					repaint();
					color++;
					break;
					} else {
						System.out.println("같은자리에는 놓을수없습니다.");
						return;
					}
				}
			}
		});
<<<<<<< HEAD
	// 다른 메소드들 생략
}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton selectedButton = (JButton) e.getSource();
		if (selectedButton.getText().equals("button")) {
			removeAll();
	}
}
=======
		// 다른 메소드들 생략
	}
	public static int[][] getBlack(int x, int y) {
		int[][] b = new int[x][y];
		return b;
	}
>>>>>>> 4a51b053734a6efc9cc74eb65af12c353250383b
}