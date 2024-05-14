package gomoku3.components;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import gomoku3.Gomoku;
import gomoku3.service.Rule;
import gomoku3.service.WinRule;

public class Background extends JFrame implements ActionListener {

	final int LINE_NUM = 1900;
	final int LINE_WIDTH = 1900;

	private final int[][] map = new int[LINE_NUM][LINE_NUM];
	private final int BLACK_STONE = 1; // 배열에 입력된 값이 1인 경우 그자리에는 흑돌이 있음
	private final int WHITE_STONE = 2; // 배열에 입력된 값이 2인 경우 그자리에는 백돌이 있음

	private JLabel backgroundMap;

	Target cursor;
	private int x;
	private int y;
	private int color;
	private int count;
	private int remote;
	private int blackcount;
	private int whitecount;
	private int choicecount;
	private boolean three;
	private boolean blackWinner;
	private boolean whiteWinner;
	private boolean game;
	boolean flag = false;
	boolean isClick;
	Gomoku gomoku = new Gomoku();
	private Background mContext = this;
	JButton button1;
	JButton button2;
	JButton button3;
	JButton button4;
	JButton button5;
	JButton button6;
	JButton button7;
	JButton terran1;
	JButton terran2;
	JButton terran3;
	JButton zerg1;
	JButton zerg2;
	JButton zerg3;
	JButton protoss1;
	JButton protoss2;
	JButton protoss3;
	JLabel blackwin;
	JLabel whitewin;
	JLabel mainmenu;
	JLabel turn;
	JLabel tag;
	JLabel whitePlayer;
	JLabel blackPlayer;
	JLabel player;
	JLabel playerlabel;
	JLabel backgroundLeft;
	JLabel backgroundRight;
	JLabel background2;
	JLabel board;
	JLabel white;
	JLabel black;
	JLabel blank;
	JLabel blank2;

	public Background() {
		initData();
		setInitLayout();
		addEventListener();
		resetimage();
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
		white = new JLabel(new ImageIcon("images/white.png"));
		black = new JLabel(new ImageIcon("images/black.png"));
		blank = new JLabel(new ImageIcon("images/blank.png"));
		blank2 = new JLabel(new ImageIcon("images/blank.png"));
		backgroundMap = new JLabel(new ImageIcon("images/omokbackground.png"));
		background2 = new JLabel(new ImageIcon("images/background4.jpg"));
		mainmenu = new JLabel(new ImageIcon("images/main.png"));
		turn = new JLabel(new ImageIcon("images/blackStone.png"));
		whitePlayer = new JLabel(new ImageIcon("images/protoss.gif"));
		blackPlayer = new JLabel(new ImageIcon("images/terran.gif"));
		backgroundLeft = new JLabel(new ImageIcon("images/backgroundLeft.jpg"));
		backgroundRight = new JLabel(new ImageIcon("images/backgroundRight.jpg"));
		playerlabel = new JLabel(new ImageIcon("images/player.png"));
		player = new JLabel(blackPlayer.getIcon());
		board = new JLabel(new ImageIcon("images/board2.png"));
		tag = new JLabel(new ImageIcon("images/tag.png"));
		button1 = new JButton("다시 하기");
		button2 = new JButton("종료");
		button3 = new JButton(new ImageIcon("images/start.png"));
		button4 = new JButton("무르기");
		button5 = new JButton(new ImageIcon("images/character.png"));
		button6 = new JButton(new ImageIcon("images/quit.png"));
		button7 = new JButton(new ImageIcon("images/mainmenu.png"));
		terran1 = new JButton(new ImageIcon("images/terran.gif"));
		terran2 = new JButton(new ImageIcon("images/terran2.gif"));
		terran3 = new JButton(new ImageIcon("images/terran3.gif"));
		protoss1 = new JButton(new ImageIcon("images/protoss.gif"));
		protoss2 = new JButton(new ImageIcon("images/protoss2.gif"));
		protoss3 = new JButton(new ImageIcon("images/protoss3.gif"));
		zerg1 = new JButton(new ImageIcon("images/zerg1.gif"));
		zerg2 = new JButton(new ImageIcon("images/zerg2.gif"));
		zerg3 = new JButton(new ImageIcon("images/zerg3.gif"));
		blackwin = new JLabel(new ImageIcon("images/blackwin.gif"));
		setContentPane(mainmenu);
		setTitle("우주오목전쟁");
		setSize(1000, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void setInitLayout() {
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		add(button3);
		add(button5);
		add(button6);
		button3.setBounds(420, 650, 150, 50);
		button4.setBounds(200, 800, 150, 50);
		button5.setBounds(310, 730, 400, 50);
		button6.setBounds(410, 810, 180, 50);
		button3.setBorderPainted(false);
		button3.setContentAreaFilled(false);
		button5.setBorderPainted(false);
		button5.setContentAreaFilled(false);
		button6.setBorderPainted(false);
		button6.setContentAreaFilled(false);
		button7.setBorderPainted(false);
		button7.setContentAreaFilled(false);
		turn.setLocation(1600, 400);
		turn.setSize(100, 100);
		whitePlayer.setLocation(1460, 100);
		whitePlayer.setSize(180, 180);
		blackPlayer.setLocation(1650, 100);
		blackPlayer.setSize(180, 180);
		player.setLocation(1550, 80);
		player.setSize(200, 200);
		backgroundLeft.setLocation(0, 0);
		backgroundRight.setLocation(1440, 0);
		backgroundLeft.setSize(443, 1000);
		backgroundRight.setSize(470, 1000);
		tag.setLocation(1500, 280);
		tag.setSize(300, 300);
		playerlabel.setLocation(1525, 20);
		playerlabel.setSize(250, 280);
		board.setLocation(1450, 600);
		board.setSize(400, 350);
		black.setLocation(1080,100);
		white.setLocation(1430,100);
		blank.setLocation(1100,180);
		blank2.setLocation(1450,180);
		white.setSize(300,100);
		black.setSize(300,100);
		blank.setSize(250,250);
		blank2.setSize(250,250);
	}

	private void addEventListener() {
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		button4.addActionListener(this);
		button5.addActionListener(this);
		button6.addActionListener(this);
		button7.addActionListener(this);
		terran1.addActionListener(this);
		terran2.addActionListener(this);
		terran3.addActionListener(this);
		protoss1.addActionListener(this);
		protoss2.addActionListener(this);
		protoss3.addActionListener(this);
		zerg1.addActionListener(this);
		zerg2.addActionListener(this);
		zerg3.addActionListener(this);
	}
	
	private void addKeyListener() {
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
					remove(blackPlayer);
					if (blackWinner == false && whiteWinner == false) {
						if (map[cursor.getX()][cursor.getY()] == 0) {
							isClick = true;
							if ((color % 2) == 0) {
								cursor.BlackStone();
								map[cursor.getX()][cursor.getY()] = 1;
								turn.setIcon(new ImageIcon("images/whiteStone.png"));
								player.setIcon(whitePlayer.getIcon());
								blackcount++;
							} else {
								cursor.WhiteStone();
								map[cursor.getX()][cursor.getY()] = 2;
								turn.setIcon(new ImageIcon("images/blackStone.png"));
								player.setIcon(blackPlayer.getIcon());
								whitecount++;
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
	public void resetimage() {
		terran1.setBounds(200, 100, 200, 200);
		terran2.setBounds(200, 350, 200, 200);
		terran3.setBounds(200, 600, 200, 200);
		protoss1.setBounds(500, 100, 200, 200);
		protoss2.setBounds(500, 350, 200, 200);
		protoss3.setBounds(500, 600, 200, 200);
		zerg1.setBounds(800, 100, 200, 200);
		zerg2.setBounds(800, 350, 200, 200);
		zerg3.setBounds(800, 600, 200, 200);
	}
	public void mainMenu() {
		getContentPane().removeAll();
		setContentPane(mainmenu);
		setSize(1000,1000);
		setLocationRelativeTo(null);
		add(button3);
		add(button5);
		add(button6);
		button3.setLocation(420,650);
		repaint();
	}
	public void start() {
		getContentPane().removeAll();
		setContentPane(backgroundMap);
		cursor = new Target(mContext);
		setSize(1901, 1000);
		setLocationRelativeTo(null);
		add(cursor);
		add(turn);
		add(tag);
		add(blackPlayer);
		blackPlayer.setLocation(1550, 80);
		blackPlayer.setSize(200,200);
		add(player);
		add(playerlabel);
		add(board);
		add(button4);
		add(backgroundLeft);
		add(backgroundRight);
		game = true;
		addKeyListener();
		this.requestFocus();
		repaint();
	}

	public void characterSelect() {
		getContentPane().removeAll();
		setContentPane(background2);
		setSize(1900, 1000);
		setLocationRelativeTo(null);
		add(button7);
		add(terran1);
		add(terran2);
		add(terran3);
		add(protoss1);
		add(protoss2);
		add(protoss3);
		add(zerg1);
		add(zerg2);
		add(zerg3);
		add(button3);
		add(white);
		add(black);
		add(blank);
		add(blank2);
		button3.setBounds(1330,500,150,50);
		button7.setBounds(1265,570,300,100);
		repaint();
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
		setSize(1000, 1000);
		setLocationRelativeTo(null);
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
		setSize(1000, 1000);
		setLocationRelativeTo(null);
		button1.setBounds(370, 650, 100, 50);
		button2.setBounds(530, 650, 100, 50);
		repaint();
	}

	public void reset() {
		gomoku.newGame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton selectedButton = (JButton) e.getSource();
		if (selectedButton.getText().equals("다시 하기")) {
			reset();
		} else if (selectedButton.getText().equals("종료")) {
			System.exit(0);
		} else if (selectedButton == button3) {
			start();
		} else if (selectedButton.getText().equals("무르기")) {
			if (isClick) {
				if (color % 2 == 1) {
					map[cursor.getBlackStone().getRealx()][cursor.getBlackStone().getRealy()] = 0;
					remove(cursor.getBlackStone());
					blackcount--;
					turn.setIcon(new ImageIcon("images/blackStone.png"));
					player.setIcon(blackPlayer.getIcon());
					color++;
				} else if (color % 2 == 0) {
					map[cursor.getWhiteStone().getRealx()][cursor.getWhiteStone().getRealy()] = 0;
					remove(cursor.getWhiteStone());
					whitecount--;
					turn.setIcon(new ImageIcon("images/whiteStone.png"));
					player.setIcon(whitePlayer.getIcon());
					color++;
				}
			} else {
				System.out.println("무르기는 한번만 가능합니다");
			}
			repaint();
			this.requestFocus();
			isClick = false;
		} else if (selectedButton == button5) {
			characterSelect();
		} else if (selectedButton == button6) {
			System.exit(0);
		} else if (selectedButton == button7) {
			mainMenu();
		}else if (selectedButton == terran1) {
			if ((choicecount % 2) == 0) {
				resetimage();
				blackPlayer.setIcon(new ImageIcon("images/terran.gif"));
				terran1.setLocation(1125, 205);
				repaint();
			} else {
				whitePlayer.setIcon(new ImageIcon("images/terran.gif"));
				terran1.setLocation(1475, 205);
				repaint();
			}
			choicecount++;
		} else if (selectedButton == terran2) {
			if ((choicecount % 2) == 0) {
				resetimage();
				blackPlayer.setIcon(new ImageIcon("images/terran2.gif"));
				terran2.setLocation(1125, 205);
				repaint();
			} else {
				whitePlayer.setIcon(new ImageIcon("images/terran2.gif"));
				terran2.setLocation(1475, 205);
				repaint();
			}
			choicecount++;
		} else if (selectedButton == terran3) {
			if ((choicecount % 2) == 0) {
				resetimage();
				blackPlayer.setIcon(new ImageIcon("images/terran3.gif"));
				terran3.setLocation(1125, 205);
				repaint();
			} else {
				whitePlayer.setIcon(new ImageIcon("images/terran3.gif"));
				terran3.setLocation(1475, 205);
				repaint();
			}
			choicecount++;
		} else if (selectedButton == protoss1) {
			if ((choicecount % 2) == 0) {
				resetimage();
				blackPlayer.setIcon(new ImageIcon("images/protoss.gif"));
				protoss1.setLocation(1125, 205);
				repaint();
			} else {
				whitePlayer.setIcon(new ImageIcon("images/protoss.gif"));
				protoss1.setLocation(1475, 205);
				repaint();
			}
			choicecount++;
		} else if (selectedButton == protoss2) {
			if ((choicecount % 2) == 0) {
				resetimage();
				blackPlayer.setIcon(new ImageIcon("images/protoss2.gif"));
				protoss2.setLocation(1125, 205);
				repaint();
			} else {
				whitePlayer.setIcon(new ImageIcon("images/protoss2.gif"));
				protoss2.setLocation(1475, 205);
				repaint();
			}
			choicecount++;
		} else if (selectedButton == protoss3) {
			if ((choicecount % 2) == 0) {
				resetimage();
				blackPlayer.setIcon(new ImageIcon("images/protoss3.gif"));
				protoss3.setLocation(1125, 205);
				repaint();
			} else {
				whitePlayer.setIcon(new ImageIcon("images/protoss3.gif"));
				protoss3.setLocation(1475, 205);
				repaint();
			}
			choicecount++;
		} else if (selectedButton == zerg1) {
			if ((choicecount % 2) == 0) {
				resetimage();
				blackPlayer.setIcon(new ImageIcon("images/zerg1.gif"));
				zerg1.setLocation(1125, 205);
				repaint();
			} else {
				whitePlayer.setIcon(new ImageIcon("images/zerg1.gif"));
				zerg1.setLocation(1475, 205);
				repaint();
			}
			choicecount++;
		} else if (selectedButton == zerg2) {
			if ((choicecount % 2) == 0) {
				resetimage();
				blackPlayer.setIcon(new ImageIcon("images/zerg2.gif"));
				zerg2.setLocation(1125, 205);
				repaint();
			} else {
				whitePlayer.setIcon(new ImageIcon("images/zerg2.gif"));
				zerg2.setLocation(1475, 205);
				repaint();
			}
			choicecount++;
		} else if (selectedButton == zerg3) {
			if ((choicecount % 2) == 0) {
				resetimage();
				blackPlayer.setIcon(new ImageIcon("images/zerg3.gif"));
				zerg3.setLocation(1125, 205);
				repaint();
			} else {
				whitePlayer.setIcon(new ImageIcon("images/zerg3.gif"));
				zerg3.setLocation(1475, 205);
				repaint();
			}
			choicecount++;
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Font font = new Font("배찌체", Font.BOLD, 30);
		g.setFont(font);
		if (game == true) {
			g.drawString("이번 턴은", 1610, 440);
			g.drawString("플레이어", 1610, 90);
			g.drawString("놓인돌 갯수", 1610, 750);
			g.drawString("흑", 1580, 790);
			g.drawString("백", 1730, 790);
			g.drawString("" + blackcount, 1580, 830);
			g.drawString("" + whitecount, 1735, 830);
		}
	}

}