package gomoku3.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import gomoku.components.CountdownTimer;
import gomoku3.Gomoku;
import gomoku3.service.Timer;
import gomoku3.service.WinRule;

public class Background extends JFrame implements ActionListener {

	final int LINE_NUM = 1900;
	final int LINE_WIDTH = 1900;

	private final int[][] map = new int[LINE_NUM][LINE_NUM];

	private int x;
	private int y;
	private int color;
	private int count;
	private int blackcount;
	private int whitecount;
	private int choicecount;
	private int blackwin;
	private int whitewin;
	private int total;
	private boolean blackWinner;
	private boolean whiteWinner;
	private boolean game;
	private boolean flag = false;
	private boolean isClick;
	private boolean result;
	private boolean time;
	private Background mContext = this;
	private JLabel backgroundMap;
	private Gomoku gomoku = new Gomoku();
	public Target cursor;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button5;
	private JButton button6;
	private JButton button7;
	private JButton button8;
	private JButton button9;
	private JButton terran1;
	private JButton terran2;
	private JButton terran3;
	private JButton zerg1;
	private JButton zerg2;
	private JButton zerg3;
	private JButton protoss1;
	private JButton protoss2;
	private JButton protoss3;
	private JLabel resultbackground;
	private JLabel mainmenu;
	private JLabel turn;
	private JLabel tag;
	private JLabel whitePlayer;
	private JLabel blackPlayer;
	private JLabel player;
	private JLabel playerlabel;
	private JLabel backgroundLeft;
	private JLabel backgroundRight;
	private JLabel background2;
	private JLabel board;
	private JLabel white;
	private JLabel black;
	private JLabel blank;
	private JLabel blank2;
	private JLabel win;
	private JLayeredPane layeredPane;
	private CountdownTimer countdownTimer;
	private JLabel gamename;
	private Timer timer;
	private boolean[] races = new boolean[6]; // 종족 선택 넣기 0:블랙 테란 1: 블랙 토스 2: 블랙 저그 3: 화이트 테란 4: 화이트 토스 5: 화이트 저그

	public Background() {
		initData();
		setInitLayout();
		addEventListener();
		resetimage();
		addKeyListener();
		races[0] = true;
		races[4] = true;
	}

	public int getCount() {
		return count;
	}

	public int[][] getMap() {
		return map;
	}

	public int getColor() {
		return color;
	}

	public boolean isBlackWinner() {
		return blackWinner;
	}

	public boolean isWhiteWinner() {
		return whiteWinner;
	}

	public boolean[] getRaces() {
		return races;
	}
	

	public boolean isTime() {
		return time;
	}

	private void initData() {
		total = blackcount + whitecount;
		gamename = new JLabel(new ImageIcon("images/gamename.png"));
		white = new JLabel(new ImageIcon("images/white.png"));
		black = new JLabel(new ImageIcon("images/black.png"));
		blank = new JLabel(new ImageIcon("images/blank.png"));
		blank2 = new JLabel(new ImageIcon("images/blank.png"));
		backgroundMap = new JLabel(new ImageIcon("images/omokbackground.png"));
		background2 = new JLabel(new ImageIcon("images/background4.jpg"));
		countdownTimer = new CountdownTimer();
		mainmenu = new JLabel(new ImageIcon("images/mainmenu.jpg"));
		turn = new JLabel(new ImageIcon("images/blackStone.png"));
		whitePlayer = new JLabel(new ImageIcon("images/protoss.gif"));
		blackPlayer = new JLabel(new ImageIcon("images/terran.gif"));
		backgroundLeft = new JLabel(new ImageIcon("images/backgroundLeft.jpg"));
		backgroundRight = new JLabel(new ImageIcon("images/backgroundRight.jpg"));
		playerlabel = new JLabel(new ImageIcon("images/player.png"));
		player = new JLabel(blackPlayer.getIcon());
		board = new JLabel(new ImageIcon("images/scoreboard.jpg"));
		tag = new JLabel(new ImageIcon("images/tag.png"));
		win = new JLabel(new ImageIcon("images/win.jpg"));
		button1 = new JButton(new ImageIcon("images/restart.png"));
		button2 = new JButton(new ImageIcon("images/exitbutton.png"));
		button3 = new JButton(new ImageIcon("images/start.png"));
		button4 = new JButton(new ImageIcon("images/back.png"));
		button5 = new JButton(new ImageIcon("images/character.png"));
		button6 = new JButton(new ImageIcon("images/quit.png"));
		button7 = new JButton(new ImageIcon("images/mainmenu.png"));
		button8 = new JButton(new ImageIcon("images/result.png"));
		button9 = new JButton(new ImageIcon("images/exitbutton.png"));
		terran1 = new JButton(new ImageIcon("images/terran.gif"));
		terran2 = new JButton(new ImageIcon("images/terran2.gif"));
		terran3 = new JButton(new ImageIcon("images/terran3.gif"));
		protoss1 = new JButton(new ImageIcon("images/protoss.gif"));
		protoss2 = new JButton(new ImageIcon("images/protoss2.gif"));
		protoss3 = new JButton(new ImageIcon("images/protoss3.gif"));
		zerg1 = new JButton(new ImageIcon("images/zerg1.gif"));
		zerg2 = new JButton(new ImageIcon("images/zerg2.gif"));
		zerg3 = new JButton(new ImageIcon("images/zerg3.gif"));
		resultbackground = new JLabel(new ImageIcon("images/blackwin.gif"));
		cursor = new Target(mContext);
		setContentPane(mainmenu);
		setTitle("우주오목전쟁");
		setSize(1900, 1000);
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
		add(gamename);
		button3.setBounds(420, 650, 150, 50);
		button4.setBounds(100, 800, 300, 100);
		button5.setBounds(310, 730, 400, 50);
		button6.setBounds(410, 810, 180, 50);
		button8.setBounds(580, 435, 620, 70);
		button9.setBounds(580, 525, 620, 70);
		button1.setBorderPainted(false);
		button1.setContentAreaFilled(false);
		button2.setBorderPainted(false);
		button2.setContentAreaFilled(false);
		button3.setBorderPainted(false);
		button3.setContentAreaFilled(false);
		button4.setBorderPainted(false);
		button4.setContentAreaFilled(false);
		button5.setBorderPainted(false);
		button5.setContentAreaFilled(false);
		button6.setBorderPainted(false);
		button6.setContentAreaFilled(false);
		button7.setBorderPainted(false);
		button7.setContentAreaFilled(false);
		button8.setBorderPainted(false);
		button8.setContentAreaFilled(false);
		button9.setBorderPainted(false);
		button9.setContentAreaFilled(false);
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
		black.setLocation(1080, 100);
		white.setLocation(1430, 100);
		blank.setLocation(1100, 180);
		blank2.setLocation(1450, 180);
		white.setSize(300, 100);
		black.setSize(300, 100);
		blank.setSize(250, 250);
		blank2.setSize(250, 250);
		win.setSize(800, 300);
		win.setLocation(500, 300);
		gamename.setLocation(350, 80);
		gamename.setSize(1300, 150);
	}

	private void addEventListener() {
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		button4.addActionListener(this);
		button5.addActionListener(this);
		button6.addActionListener(this);
		button7.addActionListener(this);
		button8.addActionListener(this);
		button9.addActionListener(this);
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
					repaint();
					break;
				case KeyEvent.VK_RIGHT:
					cursor.right();
					repaint();
					break;
				case KeyEvent.VK_UP:
					cursor.up();
					repaint();
					break;
				case KeyEvent.VK_DOWN:
					cursor.down();
					repaint();
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
							repaint();
							color++;
							break;
						} else {
							System.out.println(map[cursor.getX()][cursor.getY()]);
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
		setSize(1900, 1000);
		setLocationRelativeTo(null);
		add(gamename);
		add(button3);
		add(button5);
		add(button6);
		button3.setLocation(420, 650);
		repaint();
	}

	public void start() {
		time = true;
		game = true;
		new Thread(new WinRule(mContext)).start();
		new Thread(timer = new Timer(mContext)).start();
		getContentPane().removeAll();
		setContentPane(backgroundMap);
		setSize(1901, 1000);
		setLocationRelativeTo(null);
		add(cursor);
		cursor.setX(926);
		cursor.setY(477);
		add(turn);
		add(tag);
		add(blackPlayer);
		blackPlayer.setLocation(1550, 80);
		blackPlayer.setSize(200, 200);
		add(player);
		add(playerlabel);
		add(board);
		add(button4);
		add(backgroundLeft);
		add(backgroundRight);
		this.requestFocus();
		repaint();
	}

	public void characterSelect() {
		getContentPane().removeAll();
		setContentPane(background2);
		setSize(1899, 1000);
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
		button3.setBounds(1330, 500, 150, 50);
		button7.setBounds(1265, 570, 300, 100);
		repaint();
	}

	public void blackWin() {
		add(win, 0);
		add(button8, 0);
		add(button9, 0);
		repaint();
		time = false;
		blackWinner = true;
		blackwin++;
	}

	public void whiteWin() {
		add(win, 0);
		add(button8, 0);
		add(button9, 0);
		repaint();
		whiteWinner = true;
		whitewin++;
	}

	public void result() {
		timer.getrTime();
		game = false;
		result = true;
		getContentPane().removeAll();
		setContentPane(resultbackground);
		if (blackWinner == true) {
			if (races[0] == true) {
				resultbackground.setIcon(new ImageIcon("images/terranwin.jpg"));
			} else if (races[1] == true) {
				resultbackground.setIcon(new ImageIcon("images/protosswin.jpg"));
			} else if (races[2] == true) {
				resultbackground.setIcon(new ImageIcon("images/zergwin.jpg"));
			}
		} else if (whiteWinner == true) {
			if (races[3] == true) {
				resultbackground.setIcon(new ImageIcon("images/terranwin.jpg"));
			} else if (races[4] == true) {
				resultbackground.setIcon(new ImageIcon("images/protosswin.jpg"));
			} else if (races[5] == true) {
				resultbackground.setIcon(new ImageIcon("images/zergwin.jpg"));
			}
		}
		add(button1);
		add(button2);
		setSize(1900, 1000);
		setLocationRelativeTo(null);
		button1.setBounds(1250, 804, 210, 80);
		button2.setBounds(1500, 800, 200, 80);
		repaint();
	}

	public void reset() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				map[i][j] = 0;
			}
		}
		color = 0;
		blackcount = 0;
		whitecount = 0;
		blackWinner = false;
		whiteWinner = false;
		result = false;
		mainMenu();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton selectedButton = (JButton) e.getSource();
		if (selectedButton == button1) {
			reset();
		} else if (selectedButton == button2 || selectedButton == button9) {
			System.exit(0);
		} else if (selectedButton == button3) {
			start();
		} else if (selectedButton == button8) {
			result();
		} else if (selectedButton == button4) {
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
		} else if (selectedButton == terran1) {
			if ((choicecount % 2) == 0) {
				resetimage();
				blackPlayer.setIcon(new ImageIcon("images/terran.gif"));
				terran1.setLocation(1125, 205);
				repaint();
				for (int i = 0; i < races.length / 2; i++) {
					if (i == 0) {
						races[i] = true;
					} else {
						races[i] = false;
					}
				}
			} else {
				whitePlayer.setIcon(new ImageIcon("images/terran.gif"));
				terran1.setLocation(1475, 205);
				repaint();
				for (int i = 3; i < races.length; i++) {
					if (i == 3) {
						races[i] = true;
					} else {
						races[i] = false;
					}
				}
			}
			choicecount++;
		} else if (selectedButton == terran2) {
			if ((choicecount % 2) == 0) {
				resetimage();
				blackPlayer.setIcon(new ImageIcon("images/terran2.gif"));
				terran2.setLocation(1125, 205);
				repaint();
				for (int i = 0; i < races.length / 2; i++) {
					if (i == 0) {
						races[i] = true;
					} else {
						races[i] = false;
					}
				}
			} else {
				whitePlayer.setIcon(new ImageIcon("images/terran2.gif"));
				terran2.setLocation(1475, 205);
				repaint();
				for (int i = 3; i < races.length; i++) {
					if (i == 3) {
						races[i] = true;
					} else {
						races[i] = false;
					}
				}
			}
			choicecount++;
		} else if (selectedButton == terran3) {
			if ((choicecount % 2) == 0) {
				resetimage();
				blackPlayer.setIcon(new ImageIcon("images/terran3.gif"));
				terran3.setLocation(1125, 205);
				repaint();
				for (int i = 0; i < races.length / 2; i++) {
					if (i == 0) {
						races[i] = true;
					} else {
						races[i] = false;
					}
				}
			} else {
				whitePlayer.setIcon(new ImageIcon("images/terran3.gif"));
				terran3.setLocation(1475, 205);
				repaint();
				for (int i = 3; i < races.length; i++) {
					if (i == 3) {
						races[i] = true;
					} else {
						races[i] = false;
					}
				}
			}
			choicecount++;
		} else if (selectedButton == protoss1) {
			if ((choicecount % 2) == 0) {
				resetimage();
				blackPlayer.setIcon(new ImageIcon("images/protoss.gif"));
				protoss1.setLocation(1125, 205);
				repaint();
				for (int i = 0; i < races.length / 2; i++) {
					if (i == 1) {
						races[i] = true;
					} else {
						races[i] = false;
					}
				}
			} else {
				whitePlayer.setIcon(new ImageIcon("images/protoss.gif"));
				protoss1.setLocation(1475, 205);
				repaint();
				for (int i = 3; i < races.length; i++) {
					if (i == 4) {
						races[i] = true;
					} else {
						races[i] = false;
					}
				}
			}
			choicecount++;
		} else if (selectedButton == protoss2) {
			if ((choicecount % 2) == 0) {
				resetimage();
				blackPlayer.setIcon(new ImageIcon("images/protoss2.gif"));
				protoss2.setLocation(1125, 205);
				repaint();
				for (int i = 0; i < races.length / 2; i++) {
					if (i == 1) {
						races[i] = true;
					} else {
						races[i] = false;
					}
				}
			} else {
				whitePlayer.setIcon(new ImageIcon("images/protoss2.gif"));
				protoss2.setLocation(1475, 205);
				repaint();
				for (int i = 3; i < races.length; i++) {
					if (i == 4) {
						races[i] = true;
					} else {
						races[i] = false;
					}
				}
			}
			choicecount++;
		} else if (selectedButton == protoss3) {
			if ((choicecount % 2) == 0) {
				resetimage();
				blackPlayer.setIcon(new ImageIcon("images/protoss3.gif"));
				protoss3.setLocation(1125, 205);
				repaint();
				for (int i = 0; i < races.length / 2; i++) {
					if (i == 1) {
						races[i] = true;
					} else {
						races[i] = false;
					}
				}
			} else {
				whitePlayer.setIcon(new ImageIcon("images/protoss3.gif"));
				protoss3.setLocation(1475, 205);
				repaint();
				for (int i = 3; i < races.length; i++) {
					if (i == 4) {
						races[i] = true;
					} else {
						races[i] = false;
					}
				}
			}
			choicecount++;
		} else if (selectedButton == zerg1) {
			if ((choicecount % 2) == 0) {
				resetimage();
				blackPlayer.setIcon(new ImageIcon("images/zerg1.gif"));
				zerg1.setLocation(1125, 205);
				repaint();
				for (int i = 0; i < races.length / 2; i++) {
					if (i == 2) {
						races[i] = true;
					} else {
						races[i] = false;
					}
				}
			} else {
				whitePlayer.setIcon(new ImageIcon("images/zerg1.gif"));
				zerg1.setLocation(1475, 205);
				repaint();
				for (int i = 3; i < races.length; i++) {
					if (i == 5) {
						races[i] = true;
					} else {
						races[i] = false;
					}
				}
			}
			choicecount++;
		} else if (selectedButton == zerg2) {
			if ((choicecount % 2) == 0) {
				resetimage();
				blackPlayer.setIcon(new ImageIcon("images/zerg2.gif"));
				zerg2.setLocation(1125, 205);
				repaint();
				for (int i = 0; i < races.length / 2; i++) {
					if (i == 2) {
						races[i] = true;
					} else {
						races[i] = false;
					}
				}
			} else {
				whitePlayer.setIcon(new ImageIcon("images/zerg2.gif"));
				zerg2.setLocation(1475, 205);
				repaint();
				for (int i = 3; i < races.length; i++) {
					if (i == 5) {
						races[i] = true;
					} else {
						races[i] = false;
					}
				}
			}
			choicecount++;
		} else if (selectedButton == zerg3) {
			if ((choicecount % 2) == 0) {
				resetimage();
				blackPlayer.setIcon(new ImageIcon("images/zerg3.gif"));
				zerg3.setLocation(1125, 205);
				repaint();
				for (int i = 0; i < races.length / 2; i++) {
					if (i == 2) {
						races[i] = true;
					} else {
						races[i] = false;
					}
				}
			} else {
				whitePlayer.setIcon(new ImageIcon("images/zerg3.gif"));
				zerg3.setLocation(1475, 205);
				repaint();
				for (int i = 3; i < races.length; i++) {
					if (i == 5) {
						races[i] = true;
					} else {
						races[i] = false;
					}
				}
			}
			choicecount++;
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Font font = new Font("Kostar", Font.BOLD, 35);
		Font font2 = new Font("Kostar",Font.BOLD,45);
 		g.setFont(font);
		if (game == true) {
			g.drawString("이번 턴은", 1590, 440);
			g.drawString("플레이어", 1590, 90);
			g.drawString("놓인돌 갯수", 1580, 730);
			g.drawString("흑", 1570, 790);
			g.drawString("백", 1720, 790);
			g.drawString("" + blackcount, 1575, 830);
			g.drawString("" + whitecount, 1725, 830);
		}
		if(result == true) {
			g.setColor(Color.white);
			g.drawString("흑돌 승리 횟수 : " + blackwin,160, 400);
			g.drawString("백돌 승리 횟수 : " + whitewin,160, 500);
			g.drawString("돌 놓인 횟수 : " + total ,160, 600);
			g.setFont(font2);
		g.drawString("경기 시간 : "+(timer.getrTime()/1000) / 60 +"분 "+timer.getrTime()/1000 % 60 +"초",160,300);
		}
	}

}