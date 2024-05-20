package gomoku.components;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class BlackStone extends JLabel {
	// mContext
	final int BLOCK = 50;

	Background mContext;
	Target cursor;
	// x , y 좌표값
	private int x;
	private int y;
	private int realx;
	private int realy;

	public int getRealx() {
		return realx;
	}

	public int getRealy() {
		return realy;
	}

	// 흑돌 백돌 이미지
	private ImageIcon blackStone;

	// 돌의 색 상태 - color -> 1 (흑돌 턴) , 2(백돌 턴)
	private int color;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public ImageIcon getBlackStone() {
		return blackStone;
	}

	public void setBlackStone(ImageIcon blackStone) {
		this.blackStone = blackStone;
	}

	public void setWhiteStone(ImageIcon whiteStone) {
		whiteStone = whiteStone;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public BlackStone(Background mContext) {
		this.mContext = mContext;
		this.cursor = mContext.cursor;
		realx = mContext.cursor.getX();
		realy = mContext.cursor.getY();
		initData();
		setInitLayout();
		// new Thread().start(); 스톤 실행시 쓰레드로 동작하도록 구현
	}

	public void initData() {
<<<<<<< HEAD
		blackStone = new ImageIcon("images/blackstone.png");
=======
		blackStone = new ImageIcon("images/blackStone.png");
>>>>>>> 87befaeddcdfd97c83694b2bcde92acbf88ce6df
		x = mContext.cursor.getX() - 10;
		y = mContext.cursor.getY() - 45;
		color = 1; // color 값 받아와서 적용될 수 있게 수정.

	}

	public void setInitLayout() {
		// stone.getColor?
		setIcon(blackStone);
		setSize(100, 100);
		setLocation(x, y);
	}
}
