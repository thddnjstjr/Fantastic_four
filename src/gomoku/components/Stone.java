package gomoku.components;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Stone extends JLabel {

	// mContext
	final int BLOCK = 50;
	
	Background mContext;
	Cursor cursor;
	Stone stone;

	// x , y 좌표값
	private int x;
	private int y;

	// 흑돌 백돌 이미지
	private ImageIcon blackStone;
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

	public ImageIcon getBlackStone() {
		return blackStone;
	}

	public void setBlackStone(ImageIcon blackStone) {
		this.blackStone = blackStone;
	}

	public ImageIcon getWhiteStone() {
		return whiteStone;
	}

	public void setWhiteStone(ImageIcon whiteStone) {
		this.whiteStone = whiteStone;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public Stone(Background mContext) {
		this.mContext = mContext;
		this.x = mContext.getX()*BLOCK;
		initData();
		setInitLayout();
		// new Thread().start(); 스톤 실행시 쓰레드로 동작하도록 구현
	}

	public void initData() {
		blackStone = new ImageIcon("images/blackStone.png");
		whiteStone = new ImageIcon("images/whiteStone.png");
		x = cursor.getX(); // todo - 값 받아와서 적용될 수 있게 수정
		y = cursor.getY(); // todo - 값 받아와서 적용될 수 있게 수정

		color = 1; // color 값 받아와서 적용될 수 있게 수정.

	}

	public void setInitLayout() {
		// stone.getColor?
		if (color == 1) {
			setIcon(blackStone);
		}
		if (color == 2) {
			setIcon(whiteStone);
		}
		setSize(50, 50);
		setLocation(x, y);
	}

	public void playStone() {
		// todo - 중복 착점 불가 방어적 코드 추가
		// arraylist.contains쓰기
		
		stone = new Stone(mContext);		
		stone.setColor(1);
		if (color == 1) {
			// - todo 쓰레드로 구성되게 코드 수정
<<<<<<< HEAD
			mContext.add(stone);
=======
			Stone stone = new Stone(mContext);
>>>>>>> d6f818249901a90bdfcbf1a51d170f3be8985c61
			setLocation(x, y);
			color = 2;
		}
		if (color == 2) {
			// - todo 쓰레드로 구성되게 코드 수정
<<<<<<< HEAD
			mContext.add(stone);
=======
			Stone stone = new Stone(mContext);
>>>>>>> d6f818249901a90bdfcbf1a51d170f3be8985c61
			setLocation(x, y);
			color = 1;
		}
	}

}
