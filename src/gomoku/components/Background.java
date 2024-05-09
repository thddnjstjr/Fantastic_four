package gomoku.components;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import service.WinRule;

public class Background extends JFrame {

	final int LINE_NUM = 1000;
	final int LINE_WIDTH = 1000;

	private final int[][] map = new int[LINE_NUM][LINE_NUM];
	private final int BLACK_STONE = 1; // 배열에 입력된 값이 1인 경우 그자리에는 흑돌이 있음
	private final int WHITE_STONE = 2; // 배열에 입력된 값이 2인 경우 그자리에는 백돌이 있음

	private JLabel backgroundMap;
	public int[][] getMap() {
		return map;
	}
	Cursor cursor;
	private int x;
	private int y;
	private int color;
	Background mContext = this;


	public Background() {
		initData();
		setInitLayout();
		addEventListener();
		new Thread(new WinRule(mContext)).start();
	}

	private void initData() {
		backgroundMap = new JLabel(new ImageIcon("images/omokbackground.png"));
		setContentPane(backgroundMap);
		setTitle("오목게임");
		setSize(1000, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cursor = new Cursor(mContext);

	}

	private void setInitLayout() {
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);

		add(cursor);
	}

	private void addEventListener() {
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
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
					if ((color % 2) == 0) {
						cursor.BlackStone();
						map[cursor.getX()][cursor.getY()] = 1;
					} else {
						cursor.WhiteStone();
						map[cursor.getX()][cursor.getY()] = 2;
					}
					System.out.println(cursor.getX() + " ," + cursor.getY());
					System.out.println(map[cursor.getX()][cursor.getY()]);
					repaint();
					color++;
					break;
				}
			}
		});
	// 다른 메소드들 생략
}
}