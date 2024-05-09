package gomoku.components;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import gomoku.interfaces.CursorWay;
import gomoku.interfaces.Moveable;

public class Cursor extends JLabel implements Moveable {

	// mContext
	Background mContext;
	Stone stone;
	// todo -- 좌표계 설정 완료후 바꿔주기!
	final int BLOCK = 52; // 바둑판 눈금 한 칸
	final int MAX_X = 946; // 바둑판 오른쪽 끝 눈금 X좌표
	final int MIN_X = 20; // 바둑판 왼쪽 끝 눈금 X좌표
	final int MAX_Y = 945; // 바둑판 하단 끝 눈금 Y좌표
	final int MIN_Y = 20; // 바둑판 상단 끝 눈금 Y좌표
	
	private int x;
	private int y;
	private ImageIcon cursorImg;
	private WhiteStone whiteStone;
	private BlackStone blackStone;
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

	public Background getmContext() {
		return mContext;
	}

	public void setmContext(Background mContext) {
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

	public Cursor(Background mContext) {
		this.mContext = mContext;
		initData();
		setInitLayout();

	}

	private void initData() {
		cursorImg = new ImageIcon("images/corsur.png");
		cursorImg = new ImageIcon("images/bubble.png");
		cursorImg = new ImageIcon("images/cursor.png");

		x = 478; // 초기값 , 추후 중앙값으로 수정
		y = 477;

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
		setIcon(cursorImg);
		setSize(50, 50);
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
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
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
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
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
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
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
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}

<<<<<<< HEAD
	public void playStone() {		
		new Thread(() -> {
			while (true) {
				stone.playStone();
			}
		}).start();
		
	}

=======
	public void WhiteStone() {
		whiteStone = new WhiteStone(mContext);
		mContext.add(whiteStone);
	}
	public void BlackStone() {
		blackStone = new BlackStone(mContext);
		mContext.add(blackStone);
	}
>>>>>>> d6f818249901a90bdfcbf1a51d170f3be8985c61
}
