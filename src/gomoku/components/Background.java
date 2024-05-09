package gomoku.components;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import service.WinRule;

public class Background extends JFrame implements ActionListener{

	final int LINE_NUM = 1000;
	final int LINE_WIDTH = 1000;

	private final int[][] map = new int[LINE_NUM][LINE_NUM];
	private final int BLACK_STONE = 1; // 배열에 입력된 값이 1인 경우 그자리에는 흑돌이 있음
	private final int WHITE_STONE = 2; // 배열에 입력된 값이 2인 경우 그자리에는 백돌이 있음

	private JLabel backgroundMap;
	Cursor cursor;
	private int x;
	private int y;
	private int color;
	private boolean three;
	Background mContext = this;
	JButton button;
	JLabel result;
	
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

	private void initData() {
		backgroundMap = new JLabel(new ImageIcon("images/omokbackground.png"));
		setContentPane(backgroundMap);
		setTitle("오목게임");
		setSize(1000, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cursor = new Cursor(mContext);
		button = new JButton("다시 하기");
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
						map[cursor.getX()][cursor.getY()] = 1;
					} else {
						System.out.println("백돌 차례 입니다.");
						cursor.WhiteStone();
						map[cursor.getX()][cursor.getY()] = 2;
					}
					System.out.println(cursor.getX() + " ," + cursor.getY());
					System.out.println(map[cursor.getX()][cursor.getY()]);
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
	// 다른 메소드들 생략
}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton selectedButton = (JButton) e.getSource();
		if (selectedButton.getText().equals("button")) {
			removeAll();
	}
}
}