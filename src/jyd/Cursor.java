package jyd;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import interfaces.Moveable;
import state.CursorWay;

public class Cursor extends JLabel implements Moveable {

	// mContext
	Gomoku mContext;
	// todo -- 좌표계 설정 완료후 바꿔주기!
	final int BLOCK = 50; // 바둑판 눈금 한 칸
	final int MAX_X = 2000; // 바둑판 오른쪽 끝 눈금 X좌표
	final int MIN_X = 20; // 바둑판 왼쪽 끝 눈금 X좌표
	final int MAX_Y = 2000; // 바둑판 하단 끝 눈금 Y좌표
	final int MIN_Y = 20; // 바둑판 상단 끝 눈금 Y좌표

	private int x;
	private int y;
	private ImageIcon cursorImg;

	// 커서의 움직임 상태
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;

	// 바둑판 끝에 충돌한 상태
	private boolean rightWallCrash;
	private boolean leftWallCrash;
	private boolean topWallCrash;
	private boolean bottomWallCrash;

	CursorWay cursorWay;

	public Gomoku getmContext() {
		return mContext;
	}

	public void setmContext(Gomoku mContext) {
		this.mContext = mContext;
	}

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

	public ImageIcon getCursorImg() {
		return cursorImg;
	}

	public void setCursorImg(ImageIcon cursorImg) {
		this.cursorImg = cursorImg;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isRightWallCrash() {
		return rightWallCrash;
	}

	public void setRightWallCrash(boolean rightWallCrash) {
		this.rightWallCrash = rightWallCrash;
	}

	public boolean isLeftWallCrash() {
		return leftWallCrash;
	}

	public void setLeftWallCrash(boolean leftWallCrash) {
		this.leftWallCrash = leftWallCrash;
	}

	public boolean isTopWallCrash() {
		return topWallCrash;
	}

	public void setTopWallCrash(boolean topWallCrash) {
		this.topWallCrash = topWallCrash;
	}

	public boolean isBottomWallCrash() {
		return bottomWallCrash;
	}

	public void setBottomWallCrash(boolean bottomWallCrash) {
		this.bottomWallCrash = bottomWallCrash;
	}

	public CursorWay getCursorWay() {
		return cursorWay;
	}

	public void setCursorWay(CursorWay cursorWay) {
		this.cursorWay = cursorWay;
	}

	public Cursor(Gomoku mContext) {
		this.mContext = mContext;
		initData();
		setInitLayout();

	}

	private void initData() {
		cursorImg = new ImageIcon("images/cursor.png");

		x = 500; // 초기값 , 추후 중앙값으로 수정
		y = 500;

		// 커서의 초기 상태값 정지상태.
		left = false;
		right = false;
		up = false;
		down = false;

		// 벽에 충돌한 상태
		leftWallCrash = false;
		rightWallCrash = false;
		topWallCrash = false;
		bottomWallCrash = false;
	}

	private void setInitLayout() {
		setIcon(cursor);
		setSize(150, 150);
		setLocation(x, y);
	}

	// 커서 이동 메서드 (바둑판 끝 감지 구현)

	public void left() {
		if (x <= MIN_X) {
			leftWallCrash = true;
			return;
		} else {
			cursorWay = CursorWay.LEFT;
			left = true;
			new Thread(new Runnable() {
				public void run() {
					x -= BLOCK; // - todo : 좌표계 설정 이후 BLOCK값을 눈금 크기에 맞게 수정
					setLocation(x, y);
					Thread.sleep(50);
				}
			}).start();
		}

	}

	public void right() {
		if (x >= MAX_X) {
			rightWallCrash = true;
			return;
		} else {
			cursorWay = CursorWay.RIGHT;
			right = true;
			new Thread(new Runnable() {
				public void run() {
					x += BLOCK; // - todo : 좌표계 설정 이후 BLOCK값을 눈금 크기에 맞게 수정
					setLocation(x, y);
					Thread.sleep(50);
				}
			}).start();
		}
	}

	public void up() {
		if (y <= MIN_Y) {
			topWallCrash = true;
			return;
		} else {
			cursorWay = CursorWay.UP;
			up = true;
			new Thread(new Runnable() {
				public void run() {
					y -= BLOCK; // - todo : 좌표계 설정 이후 BLOCK값을 눈금 크기에 맞게 수정
					setLocation(x, y);
					Thread.sleep(50);
				}
			}).start();
		}
	}

	public void down() {
		if (y >= MAX_Y) {
			bottomWallCrash = true;
			return;
		} else {
			cursorWay = CursorWay.DOWN;
			down = true;
			new Thread(new Runnable() {
				public void run() {
					y += BLOCK; // - todo : 좌표계 설정 이후 BLOCK값을 눈금 크기에 맞게 수정
					setLocation(x, y);
					Thread.sleep(50);
				}
			}).start();
		}
	}

	public static void main(String[] args) {
		new Cursor();
	}
}
