package gomoku3.components;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import gomoku4.components.Background;
import gomoku4.components.Target;

public class WhiteStone extends JLabel{
	// mContext
	final int BLOCK = 50;
	
	Background mContext;
	Target cursor;
	// x , y 좌표값
	private int x;
	private int y;
	
	// 흑돌 백돌 이미지
	private ImageIcon whiteStone;

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


	public ImageIcon getWhiteStone() {
		return whiteStone;
	}


	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public WhiteStone(Background mContext) {
		this.mContext = mContext;
		this.cursor = mContext.cursor;
		initData();
		setInitLayout();
		// new Thread().start(); 스톤 실행시 쓰레드로 동작하도록 구현
	}

	public void initData() {
		whiteStone = new ImageIcon("images/whiteStone.png");
		x = mContext.cursor.getX() - 10;
		y = mContext.cursor.getY() - 21;
		color = 1; // color 값 받아와서 적용될 수 있게 수정.

	}

	public void setInitLayout() {
		// stone.getColor?
		setIcon(whiteStone);
		setSize(50, 50);
		setLocation(x, y);
	}
}
