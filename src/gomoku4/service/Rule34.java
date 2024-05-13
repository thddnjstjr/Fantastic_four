package gomoku4.service;

import java.util.Arrays;

import gomoku4.components.Background;
import gomoku4.components.Target;

// thread로 실행
public class Rule34 {

	Background mContext;
	private static int x; // row
	private static int y; // col
	private static int[][] b; // 현재돌 1:흑, 2:백 currentPlayer = 1
	private static boolean check;

	public Rule34(Background mContext) {
		this.mContext = mContext;
		this.b = mContext.getMap();
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
		System.out.println("count : " + count);
		if (count >= 2) {
			return true;
		} else {
			return false;
		}
	}

	// ← → 탐색
	private static int findWidth() {
		int stone1 = 0;
		int stone2 = 0;
		int allstone = 0;
		int blinkXminus = 1;
		int blinkXplus = blinkXminus;

		// ← 탐색
		check = false;
		left: while (true) {
			// 좌표 끝 도달시
			if (b[x][y] < 0)
				break left;
			// 같은돌 만나는 경우
			// check 를 false 로 둠으로 연속으로 만나는지 체크
			if (b[x - 1][y] == 1) {
				check = false;
				stone1++;
			}
			// 다른 돌 만나는 경우 탐색 중지
			if (b[x - 1][y] == 2)
				// break left;
				if (b[x - 1][y] == 0) {
					if (check == false) {
						check = true;
					} else {
						blinkXminus++;
						// break left;
					}
				}
			if (blinkXminus == 1) {
				blinkXminus--;
			} else {
				// break left;
			}
		}
		// }

		// → 탐색
		check = false;
		right: while (true) {
			// 좌표 끝 도달시
			if (b[x + 52][y] >= 1000)
				break right;
			// 같은돌 만나는 경우
			// check 를 false 로 둠으로 연속으로 만나는지 체크
			if (b[x + 52][y] == 1) {
				check = false;
				stone2++;
			}
			// 다른 돌 만나는 경우 탐색 중지
			if (b[x + 52][y] == 2)
				break right;
			if (b[x + 52][y] == 0) {
				if (check == false) {
					check = true;
				} else {
					blinkXplus++;
					break right;
				}
			}
			if (blinkXplus == 1) {
				blinkXplus--;
			} else {
				break right;
			}
		}
		allstone = stone1 + stone2;
		if (allstone != 2) {
			return 0;
		}
		int left = (stone1 + (blinkXminus * 52));
		int right = (stone2 + (blinkXplus * 52));

		if (x - left <= 0 || x + right >= 1000) { // 벽끝
			return 0;
		} else { // 상대돌로 막힌경우 - 열린33이 아님
			if (b[x - left - 52][y] == 2 || b[x + right + 52][y] == 2) {
				return 0;
			} else { // 열린 33일때 1 값 리턴
				return 1;
			}
		}
	}

	// ↑ ↓ 탐색
	private static int findHeight() {
		int stone1 = 0;
		int stone2 = 0;
		int allstone = 0;
		int blinkYminus = 1;
		int blinkYplus = blinkYminus;

		// ↑ 탐색
		check = false;
		top: while (true) {
			// 좌표 끝 도달시
			if (b[x][y - 52] < 0)
				break top;

			// 같은돌 만나는 경우
			// check 를 false 로 둠으로 연속으로 만나는지 체크
			if (b[x][y - 52] == 1) {
				check = false;
				stone1++;
			}

			// 다른 돌 만나는 경우 탐색 중지
			if (b[x][y - 52] == 2)
				break top;

			if (b[x][y - 52] == 0) {
				if (check == false) {
					check = true;
				} else {
					blinkYminus++;
					break top;
				}
			}

			if (blinkYminus == 1) {
				blinkYminus--;
			} else {
				break top;
			}
		}

		// ↓ 탐색
		check = false;
		bottom: while (true) {
			// 좌표 끝 도달시
			if (b[x][y + 52] > 1000)
				break bottom;

			// 같은돌 만나는 경우
			// check 를 false 로 둠으로 연속으로 만나는지 체크
			if (b[x][y + 52] == 1) {
				check = false;
				stone2++;
			}

			// 다른 돌 만나는 경우 탐색 중지
			if (b[x][y + 52] == 2)
				break bottom;

			if (b[x][y + 52] == 0) {
				if (check == false) {
					check = true;
				} else {
					blinkYplus++;
					break bottom;
				}
			}

			if (blinkYplus == 1) {
				blinkYplus--;
			} else {
				break bottom;
			}
		}
		allstone = stone1 + stone2;
		if (allstone != 2) {
			return 0;
		}

		int top = (stone1 + (blinkYminus * 52));
		int bottom = (stone2 + (blinkYplus * 52));

		if (y - top <= 0 || y + bottom >= 1000) {
			return 0;
		} else {
			if (b[x][y - top - 52] == 2 || b[x][y + bottom + 52] == 2) {
				return 0;
			} else {
				return 1;
			}
		}
	}

	// ↖ ↘ 탐색
	public static int findleftUpDiagonal() {
		int stone1 = 0;
		int stone2 = 0;
		int allstone = 0;
		int blinkleftUpDiagonal = 1;
		int blinkRightDownDiagonal = blinkleftUpDiagonal;

		check = false;
		// ↖ 탐색
		leftUpDiagonal: while (true) {
			if (b[x - 52][y - 52] < 0) {
				break leftUpDiagonal;
			}
			if (b[x - 52][y - 52] == 1) {
				check = false;
				stone1++;
			}

			if (b[x - 52][y - 52] == 2) {
				break leftUpDiagonal;
			}
			if (b[x - 52][y - 52] == 0) {
				if (check = false) {
					check = true;
				} else {
					blinkleftUpDiagonal++;
					break leftUpDiagonal;
				}

				if (blinkleftUpDiagonal == 1) {
					blinkleftUpDiagonal--;
				} else {
					break leftUpDiagonal;
				}
			}
		}

		// ↘ 탐색
		check = false;
		rightDownDiagonal: while (true) {
			if (b[x + 52][y + 52] > 1000) {
				break rightDownDiagonal;
			}
			if (b[x + 52][y + 52] == 1) {
				check = false;
				stone1++;
			}

			if (b[x + 52][y + 52] == 2) {
				break rightDownDiagonal;
			}
			if (b[x + 52][y + 52] == 0) {
				if (check == false) {
					check = true;
				} else {
					blinkRightDownDiagonal++;
					break rightDownDiagonal;
				}

				if (blinkRightDownDiagonal == 1) {
					blinkRightDownDiagonal--;
				} else {
					break rightDownDiagonal;
				}
			}
		}
		allstone = stone1 + stone2;
		if (allstone != 2) {
			return 0;
		}

		int leftup = (stone1 + (blinkleftUpDiagonal * 52));
		int rightdown = (stone2 + (blinkRightDownDiagonal * 52));

		if (y - leftup < 0 || x - leftup < 0 || y + rightdown > 1000 || x + rightdown > 1000) {
			return 0;
		} else {
			if (b[x - leftup - 52][y - leftup - 52] == 2 || b[x + rightdown + 52][y + rightdown + 52] == 2) {
				return 0;
			} else {
				return 1;
			}
		}
	}

	// ↙ ↗ 탐색
	public static int findleftDownDiagonal() {
		int stone1 = 0;
		int stone2 = 0;
		int allstone = 0;
		int blinkleftDownDiagonal = 1;
		int blinkRightUpDiagonal = blinkleftDownDiagonal;

		check = false;
		// ↙ 탐색
		leftDownDiagonal: while (true) {
			if (b[x - 52][y + 52] < 0 || b[x - 52][y + 52] >= 1000) {
				break leftDownDiagonal;
			}
			if (b[x - 52][y + 52] == 1) {
				check = false;
				stone1++;
			}

			if (b[x - 52][y + 52] == 2) {
				break leftDownDiagonal;
			}
			if (b[x - 52][y + 52] == 0) {
				if (check == false) {
					check = true;
				} else {
					blinkleftDownDiagonal++;
					break leftDownDiagonal;
				}

				if (blinkleftDownDiagonal == 1) {
					blinkleftDownDiagonal--;
				} else {
					break leftDownDiagonal;
				}
			}
		}

		// ↗ 탐색
		check = false;
		rightUpDiagonal: while (true) {
			if (b[x + 52][y - 52] >= 0 || b[x + 52][y - 52] < 0) {
				break rightUpDiagonal;
			}
			if (b[x + 52][y - 52] == 1) {
				check = false;
				stone1++;
			}

			if (b[x + 52][y - 52] == 2) {
				break rightUpDiagonal;
			}
			if (b[x + 52][y - 52] == 0) {
				if (check == false) {
					check = true;
				} else {
					blinkRightUpDiagonal++;
					break rightUpDiagonal;
				}

				if (blinkRightUpDiagonal == 1) {
					blinkRightUpDiagonal--;
				} else {
					break rightUpDiagonal;
				}
			}
		}
		allstone = stone1 + stone2;
		if (allstone != 2) {
			return 0;
		}

		int leftdown = (stone1 + (blinkleftDownDiagonal * 52));
		int rightup = (stone2 + (blinkRightUpDiagonal * 52));

		if (y - leftdown < 0 || x - leftdown < 0 || y + rightup > 1000 || x + rightup > 1000) {
			return 0;
		} else {
			if (b[x - leftdown - 52][y + leftdown + 52] == 2 || b[x + rightup + 52][y - rightup - 52] == 2) {
				return 0;
			} else {
				return 1;
			}
		}
	}
}
