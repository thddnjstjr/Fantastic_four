package service;

// thread로 실행
public class Rule33 {

	private static int[][] board;
	private static int x; // row
	private static int y; // col
	private static int b; // 현재돌 1:흑, 2:백 currentPlayer = 1;
	private static int w; // 상대방 돌 otherPlayer = 2;
	private static int stone1 = 0;
	private static int stone2 = 0;
	private static int allstone = 0;
	private static int xx;
	private static int yy;
	private static boolean check;

	public Rule33(int x, int y) {
		this.board = new int[x][y];
	}

	private static boolean checkRule33() {
		int count = 0;
		// ← → 탐색
		count += findWidth();
		// ↑ ↓ 탐색
		count += findHeight();
		// ↖ ↘ 탐색
		count += findleftUpDiagonal();
		// ↙ ↗ 탐색
		count += findleftDownDiagonal();
		if (count >= 2) {
			return true;
		} else {
			return false;
		}
	}

	// ← → 탐색
	private static int findWidth() {
		int blinkXminus = 1;
		int blinkXplus = blinkXminus;

		// ← 탐색
		xx = x - 1;
		check = false;
		left: while (true) {
			// 좌표 끝 도달시
			if (xx == -1)
				break left;

			// 같은돌 만나는 경우
			// check 를 false 로 둠으로 연속으로 만나는지 체크
			if (board[xx][y] == b) {
				check = false;
				stone1++;
			}

			// 다른 돌 만나는 경우 탐색 중지
			if (board[xx][y] == w)
				break left;

			if (board[xx][y] == 0) {
				if (check == false) {
					check = true;
				} else {
					blinkXminus++;
					break left;
				}
			}

			if (blinkXminus == 1) {
				blinkXminus--;
			} else {
				break left;
			}
			xx--;
		}

		// → 탐색
		xx = x + 1;
		check = false;
		right: while (true) {
			// 좌표 끝 도달시
			if (xx == 16)
				break right;

			// 같은돌 만나는 경우
			// check 를 false 로 둠으로 연속으로 만나는지 체크
			if (board[xx][yy] == b) {
				check = false;
				stone2++;
			}

			// 다른 돌 만나는 경우 탐색 중지
			if (board[xx][y] == w)
				break right;

			if (board[xx][y] == 0) {
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
			xx++;
		}

		allstone = stone1 + stone2;
		if (allstone != 2) {
			return 0;
		}

		int left = (stone1 + blinkXminus);
		int right = (stone2 + blinkXplus);

		if (x - left == 0 || x + right == 15) {
			return 0;
		} else {
			if (board[x - left - 1][y] == w || board[x + right + 1][y] == w) {
				return 0;
			} else {
				return 1;
			}
		}
	}

	// ↑ ↓ 탐색
	private static int findHeight() {
		int blinkYminus = 1;
		int blinkYplus = blinkYminus;

		// ↑ 탐색
		yy = y - 1;
		check = false;
		top: while (true) {
			// 좌표 끝 도달시
			if (yy == -1)
				break top;

			// 같은돌 만나는 경우
			// check 를 false 로 둠으로 연속으로 만나는지 체크
			if (board[x][yy] == b) {
				check = false;
				stone1++;
			}

			// 다른 돌 만나는 경우 탐색 중지
			if (board[x][yy] == w)
				break top;

			if (board[x][yy] == 0) {
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
			yy--;
		}

		// ↓ 탐색
		yy = x + 1;
		check = false;
		bottom: while (true) {
			// 좌표 끝 도달시
			if (yy == 16)
				break bottom;

			// 같은돌 만나는 경우
			// check 를 false 로 둠으로 연속으로 만나는지 체크
			if (board[x][yy] == b) {
				check = false;
				stone2++;
			}

			// 다른 돌 만나는 경우 탐색 중지
			if (board[x][yy] == w)
				break bottom;

			if (board[x][yy] == 0) {
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
			yy++;
		}

		allstone = stone1 + stone2;
		if (allstone != 2) {
			return 0;
		}

		int top = (stone1 + blinkYminus);
		int bottom = (stone2 + blinkYplus);

		if (y - top == 0 || y + bottom == 15) {
			return 0;
		} else {
			if (board[x][y - top - 1] == w || board[x][y + bottom + 1] == w) {
				return 0;
			} else {
				return 1;
			}
		}
	}

	// ↖ ↘ 탐색
	public static int findleftUpDiagonal() {
		int blinkleftUpDiagonal = 1;
		int blinkRightDownDiagonal = blinkleftUpDiagonal;

		xx = x - 1;
		yy = y - 1;
		check = false;
		// ↖ 탐색
		leftUpDiagonal: while (true) {
			if (xx == -1 || y == -1) {
				break leftUpDiagonal;
			}
			if (board[xx][yy] == b) {
				check = false;
				stone1++;
			}

			if (board[xx][yy] == w) {
				break leftUpDiagonal;
			}
			if (board[xx][yy] == 0) {
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
			xx--;
			yy--;
		}

		// ↘ 탐색
		xx = x + 1;
		yy = y + 1;
		check = false;
		rightDownDiagonal: while (true) {
			if (xx == 16 || y == 16) {
				break rightDownDiagonal;
			}
			if (board[xx][yy] == b) {
				check = false;
				stone1++;
			}

			if (board[xx][yy] == w) {
				break rightDownDiagonal;
			}
			if (board[xx][yy] == 0) {
				if (check = false) {
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
			xx++;
			yy++;
		}
		allstone = stone1 + stone2;
		if (allstone != 2) {
			return 0;
		}

		int leftup = (stone1 + blinkleftUpDiagonal);
		int rightdown = (stone2 + blinkRightDownDiagonal);

		if (y - leftup == 0 || x - leftup == 0 || y + rightdown == 15 || x + rightdown == 15) {
			return 0;
		} else {
			if (board[x - leftup - 1][y - leftup - 1] == w || board[x + rightdown + 1][y + rightdown + 1] == w) {
				return 0;
			} else {
				return 1;
			}
		}
	}

	// ↙ ↗ 탐색
	public static int findleftDownDiagonal() {
		int blinkleftDownDiagonal = 1;
		int blinkRightUpDiagonal = blinkleftDownDiagonal;

		xx = x - 1;
		yy = y + 1;
		check = false;
		// ↙ 탐색
		leftDownDiagonal: while (true) {
			if (xx == -1 || y == -1) {
				break leftDownDiagonal;
			}
			if (board[xx][yy] == b) {
				check = false;
				stone1++;
			}

			if (board[xx][yy] == w) {
				break leftDownDiagonal;
			}
			if (board[xx][yy] == 0) {
				if (check = false) {
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
			xx--;
			yy++;
		}

		// ↗ 탐색
		xx = x + 1;
		yy = y - 1;
		check = false;
		rightUpDiagonal: while (true) {
			if (xx == 16 || y == 16) {
				break rightUpDiagonal;
			}
			if (board[xx][yy] == b) {
				check = false;
				stone1++;
			}

			if (board[xx][yy] == w) {
				break rightUpDiagonal;
			}
			if (board[xx][yy] == 0) {
				if (check = false) {
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
			xx++;
			yy++;
		}
		allstone = stone1 + stone2;
		if (allstone != 2) {
			return 0;
		}

		int leftdown = (stone1 + blinkleftDownDiagonal);
		int rightup = (stone2 + blinkRightUpDiagonal);

		if (y - leftdown == 0 || x - leftdown == 0 || y + rightup == 15 || x + rightup == 15) {
			return 0;
		} else {
			if (board[x - leftdown - 1][y + leftdown + 1] == w || board[x + rightup + 1][y - rightup - 1] == w) {
				return 0;
			} else {
				return 1;
			}
		}
	}
}
