package gomoku4.service;

import gomoku4.components.Background;
import gomoku4.components.Target;

// thread로 실행
public class Rule34v2 {

	Background mContext;
	Target target;
	private static final int BLOCK = 52;
	private final int MAX_X = 1394; // 바둑판 오른쪽 끝 눈금 X좌표
	private final int MIN_X = 458; // 바둑판 왼쪽 끝 눈금 X좌표
	private final int MAX_Y = 945; // 바둑판 하단 끝 눈금 Y좌표
	private final int MIN_Y = 20; // 바둑판 상단 끝 눈금 Y좌표

	private int blackX;
	private int blackY;
	private int[][] map;
	private int[][] stone = new int[MAX_X][MAX_Y];

	public Rule33(Background mContext, int x, int y) {
		this.mContext = mContext;
		this.map = mContext.getMap();
		this.blackX = x;
		this.blackY = y;
		target = new Target(mContext);
	}

	public void checkRule33() {
		int count = 0;

		System.out.println("blackX : " + blackX + " , " + "blackY : " + blackY);
		int maxXline = Math.max(MIN_X, blackX - BLOCK * 2);
		int maxYline = Math.max(MIN_Y, blackY - BLOCK * 2);

		for (int i = maxXline; i <= Math.min(MAX_X, blackX + BLOCK * 2); i++) {
			for (int j = maxYline; j <= Math.min(MAX_Y, blackY + BLOCK * 2); j++) {
				if (map[i][j] == 1) {
					if (checkPattern(i, j, 1, 0) || checkPattern(i, j, 0, 1) || checkPattern(i, j, 1, 1)
							|| checkPattern(i, j, 1, -1)) {
						count++;
					}
				}
			}

		}
		System.out.println(count);
	}

	private boolean checkPattern(int x, int y, int dx, int dy) {
		int count = 0;
		for (int i = -2; i <= 2; i++) {
			int nx = x + i * dx;
			int ny = y + i * dy;
			if (map[nx][ny] == 1) {
				count++;
			} else {
				count = 0;
			}
			if (count >= 3) {
				return true;
			}
		}
		return false;
	}

}
