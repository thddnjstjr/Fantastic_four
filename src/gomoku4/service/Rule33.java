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
		int blankXminus = 1;

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
					blankXminus++;
					break left;
				}
				if (blankXminus == 1) {
					blankXminus--;
				} else {
					break left;
				}
			}
			blackX -= BLOCK;
		}

		// → 탐색
		int blankXplus = blankXminus;
		if (blankXminus == 1) {
			blankXminus = 0;
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
					blankXplus++;
					break right;
				}
				if (blankXplus == 1) {
					blankXplus--;
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

		int left = stone1 + (blankXminus * BLOCK);
		int right = stone2 + (blankXplus * BLOCK);
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
		int blankYminus = 1;

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
					blankYminus++;
					break top;
				}
				if (blankYminus == 1) {
					blankYminus--;
				} else {
					break top;
				}
			}
			blackY -= BLOCK;
		}
		// ↓ 탐색
		int blankYplus = blankYminus;
		if (blankYminus == 1) {
			blankYminus = 0;
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
					blankYplus++;
					break bottom;
				}
				if (blankYplus == 1) {
					blankYplus--;
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

		int top = (stone1 + (blankYminus * BLOCK));
		int bottom = (stone2 + (blankYplus * BLOCK));
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
		int leftUpblank = 1;

		// ↖ 탐색
		check = false;
		int blackX = startX;
		int blackY = startY;
		leftUpDy: while (true) {
			if (blackX - BLOCK < MIN_X || blackY - BLOCK < MIN_Y) {
				break leftUpDy;
			}
			if (map[blackX - BLOCK][blackY - BLOCK] == 1) {
				check = false;
				stone1++;
			} else if (map[blackX - BLOCK][blackY - BLOCK] == 2) {
				break leftUpDy;
			} else if (map[blackX - BLOCK][blackY - BLOCK] == 0) {
				if (!check) {
					check = true;
				} else {
					leftUpblank++;
					break leftUpDy;
				}
				if (leftUpblank == 1) {
					leftUpblank--;
				} else {
					break leftUpDy;
				}
			}
			blackX -= BLOCK;
			blackY -= BLOCK;
		}
		// ↘ 탐색
		int RightDownblank = leftUpblank;
		if (leftUpblank == 1) {
			leftUpblank = 0;
		}
		check = false;
		blackX = startX;
		blackY = startY;
		rightDownDy: while (true) {
			if (blackX + BLOCK > MAX_X || blackY + BLOCK > MAX_Y) {
				break rightDownDy;
			}
			if (map[blackX + BLOCK][blackY + BLOCK] == 1) {
				check = false;
				stone2++;
			} else if (map[blackX + BLOCK][blackY + BLOCK] == 2) {
				break rightDownDy;
			} else if (map[blackX + BLOCK][blackY + BLOCK] == 0) {
				if (!check) {
					check = true;
				} else {
					RightDownblank++;
					break rightDownDy;
				}
				if (RightDownblank == 1) {
					RightDownblank--;
				} else {
					break rightDownDy;
				}
			}
			blackX += BLOCK;
			blackY += BLOCK;
		}
		allstone = stone1 + stone2;
		if (allstone != 2) {
			return 0;
		}

		int leftup = (stone1 + (leftUpblank * BLOCK));
		int rightdown = (stone2 + (RightDownblank * BLOCK));
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
		int leftDownblank = 1;

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
					leftDownblank++;
					break leftDownDy;
				}
				if (leftDownblank == 1) {
					leftDownblank--;
				} else {
					break leftDownDy;
				}
			}
			blackX -= BLOCK;
			blackY += BLOCK;
		}

		// ↗ 탐색
		int RightUpblank = leftDownblank;
		if (leftDownblank == 1) {
			leftDownblank = 0;
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
					RightUpblank++;
					break rightUpDy;
				}
				if (RightUpblank == 1) {
					RightUpblank--;
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
		int leftdown = (stone1 + (leftDownblank * BLOCK));
		int rightup = (stone2 + (RightUpblank * BLOCK));
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
