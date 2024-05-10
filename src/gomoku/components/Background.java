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

import service.Rule;
import service.WinRule;

public class Background extends JFrame implements ActionListener{

	final int LINE_NUM = 1000;
	final int LINE_WIDTH = 1000;

	private final int[][] map = new int[LINE_NUM][LINE_NUM];
	private final int BLACK_STONE = 1; // 배열에 입력된 값이 1인 경우 그자리에는 흑돌이 있음
	private final int WHITE_STONE = 2; // 배열에 입력된 값이 2인 경우 그자리에는 백돌이 있음

	private JLabel backgroundMap;

	Target[] cursor;
	private int x;
	private int y;
	private int color;
	private boolean three;
	private int count;
	Background mContext = this;
	JButton button1;
	JButton button2;
	JLabel result;
	boolean flag = false;
	Rule rule;

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
		cursor = new Target[30];
		cursor[count] = new Target(mContext);
		button1 = new JButton("다시 하기");
		button2 = new JButton("종료");
		result = new JLabel(new ImageIcon("images/ghost.gif"));
		result.setSize(1000,1000);
	//	rule = new Rule(mContext);
	}

	private void setInitLayout() {
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		add(cursor[count]);
		repaint();
	}

	private void addEventListener() {
		button1.addActionListener(this);
		button2.addActionListener(this);
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// System.out.println(cursor.getX() + " ," + cursor.getY());
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:  
					cursor[count].left();
					break;
				case KeyEvent.VK_RIGHT:
					cursor[count].right();
					break;
				case KeyEvent.VK_UP:
					cursor[count].up();
					break;
				case KeyEvent.VK_DOWN:
					cursor[count].down();
					break;
				case KeyEvent.VK_SPACE:
					if(map[cursor[count].getX()][cursor[count].getY()] == 0) {
					if ((color % 2) == 0) {
						System.out.println("흑돌 차례 입니다.");
						cursor[count].BlackStone();
						map[cursor[count].getX()][cursor[count].getY()] = 1;

					} else {
						System.out.println("백돌 차례 입니다.");
						cursor[count].WhiteStone();
						map[cursor[count].getX()][cursor[count].getY()] = 2;
					}
					// System.out.println(map[cursor.getX()][cursor.getY()]);
					System.out.println(cursor[count].getX() + " ," + cursor[count].getY());
					System.out.println(map[cursor[count].getX()][cursor[count].getY()]);
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
	public void win() {
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map.length; j++) {
				map[i][j] = 0;
			}
		}
		getContentPane().removeAll();
		setContentPane(result);
		add(button1);
		add(button2);
		button1.setBounds(350, 650,100,50);
		button2.setBounds(550, 650,100,50);
		repaint();
	}
	public void reset() {
		getContentPane().removeAll();
		setContentPane(backgroundMap);
		count++;
		cursor[count] = new Target(mContext);
		add(cursor[count]);
		repaint();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton selectedButton = (JButton) e.getSource();
		if (selectedButton.getText().equals("다시 하기")) {
			reset();
	}
}
		// 다른 메소드들 생략
	
}