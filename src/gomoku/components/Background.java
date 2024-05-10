package gomoku.components;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Calendar;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gomoku.Gomoku;
import service.Rule;
import service.WinRule;

public class Background extends JFrame implements ActionListener {

	final int LINE_NUM = 1000;
	final int LINE_WIDTH = 1000;
	final int SECOND = 30;

	private final int[][] map = new int[LINE_NUM][LINE_NUM];
	private final int BLACK_STONE = 1; // 배열에 입력된 값이 1인 경우 그자리에는 흑돌이 있음
	private final int WHITE_STONE = 2; // 배열에 입력된 값이 2인 경우 그자리에는 백돌이 있음

	private JLabel backgroundMap;
	private TimerNum timerNum;
	private Thread threadNum;
	private JPanel panel;

	
	
	Target[] cursor;
	private int x;
	private int y;
	private int color;
	private boolean three;
	private int count;
	private int remote;
	private boolean blackWinner;
	private boolean whiteWinner;
	boolean flag = false;
	Gomoku gomoku = new Gomoku();
	Background mContext = this;
	JButton button1;
	JButton button2;
	JButton button3;
	JLabel blackwin;
	JLabel whitewin;
	JLabel mainmenu;
	Rule rule;
	JLabel times;

	public Background() {
		initData();
		setInitLayout();
		addEventListener();
		new Thread(new WinRule(mContext)).start();
		
	}

	public int getCount() {
		return count;
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
		mainmenu = new JLabel(new ImageIcon("images/ghost.gif"));
		setContentPane(mainmenu);
		setTitle("오목게임");
		setSize(1000, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		cursor = new Target[30];
		cursor[count] = new Target(mContext);
		button1 = new JButton("다시 하기");
		button2 = new JButton("종료");
		// rule = new Rule(mContext);
		
		button3 = new JButton("시작");
		blackwin = new JLabel(new ImageIcon("images/blackwin.gif"));
		blackwin.setSize(1000, 1000);
	}

	private void setInitLayout() {
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		add(button3);
		button3.setBounds(450, 650, 100, 50);
	}

	private void addEventListener() {
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					cursor[count].left();
					System.out.println("작동중");
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
					if (map[cursor[count].getX()][cursor[count].getY()] == 0) {
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
					if (blackWinner == false && whiteWinner == false) {
						if (map[cursor.getX()][cursor.getY()] == 0) {
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
			}
			});
		
		// 다른 메소드들 생략
	}
		
	private void addKeyListener() {
		System.out.println("작동중");
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					System.out.println("작동중");
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
					if (blackWinner == false && whiteWinner == false) {
						if (map[cursor.getX()][cursor.getY()] == 0) {
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
			}
		});
	}
	public void start() {
		getContentPane().removeAll();
		cursor = new Target(mContext);
		setContentPane(backgroundMap);
		setSize(1000,1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		add(cursor);
		remove(button3);
		this.requestFocus();
	}

	public void blackWin() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				map[i][j] = 0;
			}
		}
		blackWinner = true;
		getContentPane().removeAll();
		setContentPane(blackwin);
		add(button1);
		add(button2);
		button1.setBounds(350, 650, 100, 50);
		button2.setBounds(550, 650, 100, 50);
		repaint();
	}

	public void reset() {
		button1.setBounds(370, 650, 100, 50);
		button2.setBounds(530, 650, 100, 50);
		repaint();
	}

	public void whiteWin() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				map[i][j] = 0;
			}
		}
		whiteWinner = true;
		getContentPane().removeAll();
		setContentPane(blackwin);
		add(button1);
		add(button2);
		button1.setBounds(370, 650, 100, 50);
		button2.setBounds(530, 650, 100, 50);
	}

	public void reset() {
		gomoku.newGame();
	}
	
	public void actionPerformed(ActionEvent e) {
		JButton selectedButton = (JButton) e.getSource();
		if (selectedButton.getText().equals("다시 하기")) {
			reset();
		}
	}

	
	} else if (selectedButton.getText().equals("종료")) {
			System.exit(0);
		} else if (selectedButton.getText().equals("시작")) {
			start();
		}
	}


}
