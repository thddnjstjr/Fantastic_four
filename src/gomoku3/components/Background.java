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
	JLabel board;
	Rule rule;

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
		turn = new JLabel(new ImageIcon("images/blackStone.png"));
		whitePlayer = new JLabel(new ImageIcon("images/protoss.gif"));
		blackPlayer = new JLabel(new ImageIcon("images/terran.gif"));
		backgroundLeft = new JLabel(new ImageIcon("images/backgroundLeft.jpg"));
		backgroundRight = new JLabel(new ImageIcon("images/backgroundRight.jpg"));
		playerlabel = new JLabel(new ImageIcon("images/player.png"));
		player = new JLabel(blackPlayer.getIcon());
		board = new JLabel(new ImageIcon("images/board2.png"));
		tag = new JLabel(new ImageIcon("images/tag.png"));
		setContentPane(mainmenu);
		setTitle("오목게임");
		setSize(1000, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		button1 = new JButton("다시 하기");
		button2 = new JButton("종료");
		button3 = new JButton("시작");
		button4 = new JButton("무르기");
		blackwin = new JLabel(new ImageIcon("images/blackwin.gif"));
	}

	private void setInitLayout() {
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		add(button3);
		button3.setBounds(450, 650, 100, 50);
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
		button4.setBounds(200, 800, 100, 50);
	}

	private void addEventListener() {
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		button4.addActionListener(this);
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
					if (blackWinner == false && whiteWinner == false) {
						if (map[cursor.getX()][cursor.getY()] == 0) {
							isClick = true;
							if ((color % 2) == 0) {
								System.out.println("백돌 차례 입니다.");
								cursor.BlackStone();
								map[cursor.getX()][cursor.getY()] = 1;
								turn.setIcon(new ImageIcon("images/whiteStone.png"));
								player.setIcon(whitePlayer.getIcon());
								blackcount++;
							} else {
								System.out.println("흑돌 차례 입니다.");
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

	public void start() {
		getContentPane().removeAll();
		cursor = new Target(mContext);
		setContentPane(backgroundMap);
		setSize(1900, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		add(cursor);
		add(turn);
		add(tag);
		add(player);
		add(playerlabel);
		add(board);
		add(button4);
		add(backgroundLeft);
		add(backgroundRight);
		remove(button3);
		game = true;
		addKeyListener();
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
		} else if (selectedButton.getText().equals("시작")) {
			start();
		} else if (selectedButton.getText().equals("무르기")) {
			if(isClick) {				
				if (color % 2 == 1) {
					map[cursor.getBlackStone().getRealx()][cursor.getBlackStone().getRealy()] = 0;
					remove(cursor.getBlackStone());
					blackcount--;
					color++;
				} else if (color % 2 == 0) {
					map[cursor.getWhiteStone().getRealx()][cursor.getWhiteStone().getRealy()] = 0;
					remove(cursor.getWhiteStone());
					whitecount--;
					color++;
				}
			}
			repaint();
			this.requestFocus();
			isClick = false;
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