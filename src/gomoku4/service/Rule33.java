package gomoku4.service;

import gomoku4.components.Background;

// thread로 실행
public class Rule33 implements Runnable {

	Background mContext;
	private static int x; // row
	private static int y; // col
	private static int[][] map; // 현재돌 1:흑, 2:백 currentPlayer = 1
	private static boolean check; // false 일 경우 연속되는지 확인
	private final static int BLOCK = 52; // 바둑판 눈금 한 칸
	private final static int MAX_X = 1394; // 바둑판 오른쪽 끝 눈금 X좌표
	private final static int MIN_X = 458; // 바둑판 왼쪽 끝 눈금 X좌표
	private final static int MAX_Y = 946; // 바둑판 하단 끝 눈금 Y좌표
	private final static int MIN_Y = 19; // 바둑판 상단 끝 눈금 Y좌표

	public Rule33(Background mContext, int x, int y) {
		this.mContext = mContext;
		this.map = mContext.getMap();
		this.x = x;
		this.y = y;
	}

	@Override
	public void run() {
		if (checkRule33()) {
			System.out.println("33");
			return;
		}
	}

	public boolean checkRule33() {
		int count = 0;
		// ← → 탐색
		count += findWidth();
		// ↑ ↓ 탐색
		count += findHeight();
		// ↖ ↘ 탐색
		count += findleftUpDiagonal();
		// ↙ ↗ 탐색
		count += findleftDownDiagonal();
		System.out.println("-------------");
		System.out.println("x : " + findWidth());
		System.out.println("y : " + findHeight());
		System.out.println("dx : " + findleftUpDiagonal());
		System.out.println("dy : " + findleftDownDiagonal());
		System.out.println("count : " + count);
		return count >= 2;
	}

	// ← → 탐색
	private static int findWidth() {
		int startX = x;
		int startY = y;
		int stone1 = 0;
		int stone2 = 0;
		int allstone = 0;
		boolean check; // false 일 경우 연속되는지 확인
		int blinkXminus = 1;

		// ← 탐색
		check = false;
		int blackX = startX;
		int blackY = startY;
		left: while (true) {
			// 좌표 끝 도달시
			if (blackX - BLOCK < MIN_X) {
				break left;
			}
			// 같은 돌 만나는 경우
			if (map[blackX - BLOCK][blackY] == 1) {
				check = false;
				stone1++;
			}
			// 다른 돌 만나는 경우 탐색 중지
			else if (map[blackX - BLOCK][blackY] == 2) {
				break left;
			}
			// 빈 공간 만나는 경우
			else if (map[blackX - BLOCK][blackY] == 0) {
				if (!check) {
					check = true;
				} else {
					blinkXminus++;
					break left;
				}
				if (blinkXminus == 1) {
					blinkXminus--;
				} else {
					break left;
				}
			}
			blackX -= BLOCK;
		}

		// → 탐색
		int blinkXplus = blinkXminus;
		if (blinkXminus == 1) {
			blinkXminus = 0;
		}
		check = false;
		blackX = startX;
		blackY = startY;
		right: while (true) {
			// 좌표 끝 도달시
			if (blackX + BLOCK > MAX_X) {
				break right;
			}
			// 같은 돌 만나는 경우
			if (map[blackX + BLOCK][blackY] == 1) {
				check = false;
				stone2++;
			}
			// 다른 돌 만나는 경우 탐색 중지
			else if (map[blackX + BLOCK][blackY] == 2) {
				break right;
			}
			// 빈 공간 만나는 경우
			else if (map[blackX + BLOCK][blackY] == 0) {
				if (!check) {
					check = true;
				} else {
					blinkXplus++;
					break right;
				}
				if (blinkXplus == 1) {
					blinkXplus--;
				} else {
					break right;
				}
			}
			blackX += BLOCK;
		}

		allstone = stone1 + stone2;
		if (allstone != 2) {
			return 0;
		}

		int left = stone1 + (blinkXminus * BLOCK);
		int right = stone2 + (blinkXplus * BLOCK);
		blackX = startX; // 좌표 초기화
		blackY = startY;

		if (blackX - left < MIN_X || blackX + right > MAX_X) {
			return 0;
		} else {
			// 상대 돌로 막힌 경우
			if (map[blackX - left - BLOCK][blackY] == 2 || map[blackX + right + BLOCK][blackY] == 2) {
				return 0;
			} else {
				// 열린 33일 때 1 반환
				return 1;
			}
		}
	}

	// ↑ ↓ 탐색
	private static int findHeight() {
		int startX = x;
		int startY = y;
		int stone1 = 0;
		int stone2 = 0;
		int allstone = 0;
		boolean check;
		int blinkYminus = 1;

		// ↑ 탐색
		check = false;
		int blackX = startX;
		int blackY = startY;
		top: while (true) {
			if (blackY - BLOCK < MIN_Y) {
				break top;
			}
			if (map[blackX][blackY - BLOCK] == 1) {
				check = false;
				stone1++;
			} else if (map[blackX][blackY - BLOCK] == 2) {
				break top;
			} else if (map[blackX][blackY - BLOCK] == 0) {
				if (!check) {
					check = true;
				} else {
					blinkYminus++;
					break top;
				}
				if (blinkYminus == 1) {
					blinkYminus--;
				} else {
					break top;
				}
			}
			blackY -= BLOCK;
		}
		// ↓ 탐색
		int blinkYplus = blinkYminus;
		if (blinkYminus == 1) {
			blinkYminus = 0;
		}
		check = false;
		blackX = startX;
		blackY = startY;
		bottom: while (true) {
			if (blackY + BLOCK > MAX_Y) {
				break bottom;
			}
			if (map[blackX][blackY + BLOCK] == 1) {
				check = false;
				stone2++;
			} else if (map[blackX][blackY + BLOCK] == 2) {
				break bottom;
			} else if (map[blackX][blackY + BLOCK] == 0) {
				if (!check) {
					check = true;
				} else {
					blinkYplus++;
					break bottom;
				}
				if (blinkYplus == 1) {
					blinkYplus--;
				} else {
					break bottom;
				}
			}
			blackY += BLOCK;
		}
		allstone = stone1 + stone2;
		if (allstone != 2) {
			return 0;
		}

		int top = (stone1 + (blinkYminus * BLOCK));
		int bottom = (stone2 + (blinkYplus * BLOCK));
		blackX = startX; // 좌표 초기화
		blackY = startY;

		if (blackY - top < MIN_Y || blackY + bottom > MAX_Y) {
			return 0;
		} else {
			if (map[blackX][blackY - top - BLOCK] == 2 || map[blackX][blackY + bottom + BLOCK] == 2) {
				return 0;
			} else {
				return 1;
			}
		}
	}

	// ↖ ↘ 탐색
	public static int findleftUpDiagonal() {
		int startX = x;
		int startY = y;
		int stone1 = 0;
		int stone2 = 0;
		int allstone = 0;
		boolean check; // false 일 경우 연속되는지 확인
		int leftUpBlink = 1;

		// ↖ 탐색
		check = false;
		int blackX = startX;
		int blackY = startY;
		leftUpDiagonal: while (true) {
			if (blackX - BLOCK < MIN_X || blackY - BLOCK < MIN_Y) {
				break leftUpDiagonal;
			}
			if (map[blackX - BLOCK][blackY - BLOCK] == 1) {
				check = false;
				stone1++;
			} else if (map[blackX - BLOCK][blackY - BLOCK] == 2) {
				break leftUpDiagonal;
			} else if (map[blackX - BLOCK][blackY - BLOCK] == 0) {
				if (!check) {
					check = true;
				} else {
					leftUpBlink++;
					break leftUpDiagonal;
				}
				if (leftUpBlink == 1) {
					leftUpBlink--;
				} else {
					break leftUpDiagonal;
				}
			}
			blackX -= BLOCK;
			blackY -= BLOCK;
		}
		// ↘ 탐색
		int RightDownBlink = leftUpBlink;
		if (leftUpBlink == 1) {
			leftUpBlink = 0;
		}
		check = false;
		blackX = startX;
		blackY = startY;
		rightDownDiagonal: while (true) {
			if (blackX + BLOCK > MAX_X || blackY + BLOCK > MAX_Y) {
				break rightDownDiagonal;
			}
			if (map[blackX + BLOCK][blackY + BLOCK] == 1) {
				check = false;
				stone2++;
			} else if (map[blackX + BLOCK][blackY + BLOCK] == 2) {
				break rightDownDiagonal;
			} else if (map[blackX + BLOCK][blackY + BLOCK] == 0) {
				if (!check) {
					check = true;
				} else {
					RightDownBlink++;
					break rightDownDiagonal;
				}
				if (RightDownBlink == 1) {
					RightDownBlink--;
				} else {
					break rightDownDiagonal;
				}
			}
			blackX += BLOCK;
			blackY += BLOCK;
		}
		allstone = stone1 + stone2;
		if (allstone != 2) {
			return 0;
		}

		int leftup = (stone1 + (leftUpBlink * BLOCK));
		int rightdown = (stone2 + (RightDownBlink * BLOCK));
		blackX = startX; // 좌표 초기화
		blackY = startY;

		if (blackX - leftup < MIN_X || blackX + rightdown > MAX_X || blackY - leftup < MIN_Y
				|| blackY + rightdown > MAX_Y) {
			return 0;
		} else {
			if (map[blackX - leftup - BLOCK][blackY - leftup - BLOCK] == 2
					|| map[blackX + rightdown + BLOCK][blackY + rightdown + BLOCK] == 2) {
				return 0;
			} else {
				return 1;
			}
		}
	}

	// ↙ ↗ 탐색
	public static int findleftDownDiagonal() {
		int startX = x;
		int startY = y;
		int stone1 = 0;
		int stone2 = 0;
		int allstone = 0;
		boolean check; // false 일 경우 연속되는지 확인
		int leftDownBlink = 1;

		int blackX = startX;
		int blackY = startY;
		// ↙ 탐색
		check = false;
		leftDownDy: while (true) {
			if (blackX - BLOCK < MIN_X || blackY + BLOCK > MAX_Y) {
				break leftDownDy;
			}
			if (map[blackX - BLOCK][blackY + BLOCK] == 1) {
				check = false;
				stone1++;
			} else if (map[blackX - BLOCK][blackY + BLOCK] == 2) {
				break leftDownDy;
			} else if (map[blackX - BLOCK][blackY + BLOCK] == 0) {
				if (!check) {
					check = true;
				} else {
					leftDownBlink++;
					break leftDownDy;
				}
				if (leftDownBlink == 1) {
					leftDownBlink--;
				} else {
					break leftDownDy;
				}
			}
			blackX -= BLOCK;
			blackY += BLOCK;
		}

		// ↗ 탐색
		int RightUpBlink = leftDownBlink;
		if (leftDownBlink == 1) {
			leftDownBlink = 0;
		}
		check = false;
		blackX = startX;
		blackY = startY;
		rightUpDy: while (true) {
			if (blackX + BLOCK > MAX_X || blackY - BLOCK < MIN_Y) {
				break rightUpDy;
			}
			if (map[blackX + BLOCK][blackY - BLOCK] == 1) {
				check = false;
				stone2++;
			} else if (map[blackX + BLOCK][blackY - BLOCK] == 2) {
				break rightUpDy;
			} else if (map[blackX + BLOCK][blackY - BLOCK] == 0) {
				if (!check) {
					check = true;
				} else {
					RightUpBlink++;
					break rightUpDy;
				}
				if (RightUpBlink == 1) {
					RightUpBlink--;
				} else {
					break rightUpDy;
				}
			}
			blackX += BLOCK;
			blackY -= BLOCK;
		}
		allstone = stone1 + stone2;
		if (allstone != 2) {
			return 0;
		}
		int leftdown = (stone1 + (leftDownBlink * BLOCK));
		int rightup = (stone2 + (RightUpBlink * BLOCK));
		blackX = startX; // 좌표 초기화
		blackY = startY;

		if (blackX - leftdown < MIN_X || blackX + rightup > MAX_X || blackY + leftdown > MAX_Y
				|| blackY - rightup < MIN_Y) {
			return 0;
		} else {
			if (map[blackX - leftdown - BLOCK][blackY + leftdown + BLOCK] == 2
					|| map[blackX + rightup + BLOCK][blackY - rightup - BLOCK] == 2) {
				return 0;
			} else {
				return 1;
			}
		}
	}
}
