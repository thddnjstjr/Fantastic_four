package gomoku4.service;

import gomoku4.components.Background;
import gomoku4.components.Target;

// thread로 실행
public class Rule33 implements Runnable {

	private Background mContext;
	private Target target;
	private int[][] map;
	private static final int BLOCK = 52;
	private final int MIN_X = 20; // 바둑판 왼쪽 끝 눈금 X좌표
	private final int MAX_X = 946; // 바둑판 오른쪽 끝 눈금 X좌표
	private final int MIN_Y = 20; // 바둑판 상단 끝 눈금 Y좌표
	private final int MAX_Y = 945; // 바둑판 하단 끝 눈금 Y좌표

	private int left;
	private int right;
	private int up;
	private int down;
	private int leftup;
	private int rightdown;
	private int leftdown;
	private int rightup;
	private int i;
	private int j;

	// todo
	// 가로탐색 x축 탐색
	// 세로탐색 y축 탐색
	// 왼쪽 대각선 탐색 x-- y-- | x++ y++ 탐색
	// 오른쪽 대각선 탐색 x-- y++ | x++ y-- 탐색

	public Rule33(Background mContext) {
		this.mContext = mContext;
		this.target = new Target(mContext);
		this.map = mContext.getMap();
	}

	@Override
	public void run() {
		int xcount = 0;
		int ycount = 0;
		while (true) {
			for (i = MIN_X; i <= MAX_X; i++) { // x 축 탐색
				for (j = MIN_Y; j <= MAX_Y; j++) { // y 축 탐색
					if (map[i][j] != 0) {
						if (map[i][j] == 0) {
							if (isRule33(map)) {
								
							}
						}
					}
				}
			}
		}
	}

	private boolean isRule33(int[][] map) {
		// 양쪽으로 탐색해서 blink 가 2면 blink -1 후 count ++
		// count == 2이면 33
		// 좌표 양끝을 벗어날수는 없으며, 양끝에 blink 와 상대돌이 있으면 count = 0;

		if (search(map) == 2)
			return true;
		else
			return false;
	}

	private int search(int[][] map) {
		
		// [x-1][j-1] [x][j-1] [x+1][j-1]
		// [x-1][j]	  [x][j]   [x+1][j]
		// [x-1][j+1] [x][j+1] [x+1][j+1]
		
		for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map.length; y++) {
				
			}
		}
		
		return 0;
	}

	private boolean isOpen(int[][] map, int i, int j) {
		map = new int[i][j];
		if (map[i - BLOCK][j] == 0) {
			return true;
		}
		if (map[i + BLOCK][j] == 0) {
			return true;
		}
		if (map[i][j - BLOCK] == 0) {
			return true;
		}
		if (map[i][j + BLOCK] == 0) {
			return true;
		}
		if (map[i - BLOCK][j - BLOCK] == 0) {
			return true;
		}
		if (map[i + BLOCK][j + BLOCK] == 0) {
			return true;
		}
		if (map[i - BLOCK][j + BLOCK] == 0) {
			return true;
		}
		if (map[i + BLOCK][j - BLOCK] == 0) {
			return true;
		} else {
			return false;
		}
	}
}
