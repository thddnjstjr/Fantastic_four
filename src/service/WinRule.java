package service;

import gomoku.components.Background;

public class WinRule implements Runnable {
	// 멤버변수
	int x;
	int y;
	int blackX;
	int blackY;
	int whiteX;
	int whiteY;
	int[] blackDiagonal = new int[4];
	int[] whiteDiagonal = new int[4];
	int[][] map;
	private Background mContext;
	int block = 52;

	public WinRule(Background mContext) {
		this.mContext = mContext;
		this.map = mContext.getMap();
	}
	@Override
	public void run() {
		Loop: while (true) {
			for (int i = 0; i < 949; i++) {
				for (int j = 0; j < 949; j++) {
					if (map[j][i] != 0) {
						if (map[j][i] == 1 && map[j + block][i] == 1) {
							blackX++;
						}
						if (map[j][i] == 1 && map[j][i + block] == 1) {
							blackY++;
						}
						if (map[j][i] == 2 && map[j + block][i] == 2) {
							whiteX++;
						}
						if (map[j][i] == 2 && map[j][i + block] == 2) {
							whiteY++;
						}
					}
					for (int t = 0; t < 4; t++) {
						if (map.length > j + block && map.length > i + block && 0 < i - block && 0 < j - block) {
							if (map[j][i] == 1 && map[j + block][i + block] == 1) {
								blackDiagonal[0]++;
							}
							if (map[j][i] == 1 && map[j + block][i - block] == 1) {
								blackDiagonal[1]++;
							}
							if (map[j][i] == 1 && map[j - block][i + block] == 1) {
								blackDiagonal[2]++;
							}
							if (map[j][i] == 1 && map[j - block][i - block] == 1) {
								blackDiagonal[3]++;
							}
							if (map[j][i] == 2 && map[j + block][i + block] == 2) {
								whiteDiagonal[0]++;
							}
							if (map[j][i] == 2 && map[j + block][i - block] == 2) {
								whiteDiagonal[1]++;
							}
							if (map[j][i] == 2 && map[j - block][i + block] == 2) {
								whiteDiagonal[2]++;
							}
							if (map[j][i] == 2 && map[j - block][i - block] == 2) {
								whiteDiagonal[3]++;
							}
						}
						block += 52;
					}
					block = 52;
					if (blackDiagonal[0] == 4 || blackDiagonal[1] == 4 || blackDiagonal[2] == 4 || blackDiagonal[3] == 4) {
						System.out.println("흑돌 대각선 승리");
						mContext.blackWin();
					}
					if (whiteDiagonal[0] == 4 || whiteDiagonal[1] == 4 || whiteDiagonal[2] == 4 || whiteDiagonal[3] == 4) {
						System.out.println("백돌 대각선 승리");
						mContext.whiteWin();
					}
					for (int y = 0; y < blackDiagonal.length; y++) {
						blackDiagonal[y] = 0;
						whiteDiagonal[y] = 0;
					}

				}

			}
			if (blackX > 4) {
				System.out.println("육목은 안됩니다.");
				break Loop;
			} else if (whiteX > 4) {
				System.out.println("육목은 안됩니다.");
				break Loop;
			} else if (blackY > 4) {
				System.out.println("육목은 안됩니다.");
				break Loop;
			} else if (whiteY > 4) {
				System.out.println("육목은 안됩니다.");
				break Loop;
			} else if (blackX == 4) {
				System.out.println("흑돌가로승리하셨습니다");
				mContext.blackWin();
				
			} else if (blackY == 4) {
				System.out.println("흑돌세로승리하셨습니다");
				mContext.blackWin();
			} else if (whiteX == 4) {
				System.out.println("백돌가로승리하셨습니다");
				mContext.whiteWin();
			} else if (whiteY == 4) {
				System.out.println("백돌세로승리하셨습니다");
				mContext.whiteWin();
			}
			blackX = 0;
			blackY = 0;
			whiteX = 0;
			whiteY = 0;
		}
	}
}
