package gomoku3.service;

import gomoku3.components.Background;

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
	int block2 = 52;

	public WinRule(Background mContext) {
		this.mContext = mContext;
		this.map = mContext.getMap();
	}

	@Override
	public void run() {
		Loop: while (true) {
			for (int i = 0; i < map.length - block; i++) {
				for (int j = 0; j < map.length - block; j++) {
					if (map[j][i] != 0) {
						if (map[j][i] == 1 && map[j + block][i] == 1) {
							blackX++;
						}
						if (map[j][i] == 2 && map[j + block][i] == 2) {
							whiteX++;
						}
						if(map[j+block][i] == 0 || map[j+block][i] == 2) {
							blackX = 0;
						}
						if(map[j+block][i] == 0 || map[j+block][i] == 1) {
							whiteX = 0;
						}
					}
					if (map[i][j] != 0) {
						for (int t = 0; t < 4; t++) {
							if (map[i][j] == 1 && map[i][j + block] == 1) {
								blackY++;
							}
							if (map[i][j] == 2 && map[i][j + block] == 2) {
								whiteY++;
							}
							block += 52;
						}
					}
					block = 0;
					for (int y = 0; y < blackDiagonal.length; y++) {
						blackDiagonal[y] = 0;
						whiteDiagonal[y] = 0;
					}
					for (int t = 0; t < 19; t++) {
						if (map[j][i] == 1 || map[j][i] == 2) {
							if (map.length > j + block + block2 && map.length > i + block + block2
									&& 0 < i - block - block2 && 0 < j - block - block2) {
								if (map[j + block][i + block] == 1
										&& map[j + block + block2][i + block + block2] == 1) {
									blackDiagonal[0]++;
								}
								if (map[j + block][i - block] == 1
										&& map[j + block + block2][i - block - block2] == 1) {
									if (map[j - block][i + block] == 1
											&& map[j - block - block2][i + block + block2] == 1) {
										blackDiagonal[1]--;
									} else {
										blackDiagonal[1]++;
									}
								}
								if (map[j - block][i + block] == 1
										&& map[j - block - block2][i + block + block2] == 1) {
									blackDiagonal[2]++;
								}
								if (map[j - block][i - block] == 1
										&& map[j - block - block2][i - block - block2] == 1) {
									blackDiagonal[3]++;
								}
								if (map[j + block][i + block] == 2
										&& map[j + block + block2][i + block + block2] == 2) {
									whiteDiagonal[0]++;
								}
								if (map[j + block][i - block] == 2
										&& map[j + block + block2][i - block - block2] == 2) {
									whiteDiagonal[1]++;
								}
								if (map[j - block][i + block] == 2
										&& map[j - block - block2][i + block + block2] == 2) {
									whiteDiagonal[2]++;
								}
								if (map[j - block][i - block] == 2
										&& map[j - block - block2][i - block - block2] == 2) {
									whiteDiagonal[3]++;
								}
							}
							block += 52;
						} else {
							for (int y = 0; y < blackDiagonal.length; y++) {
								blackDiagonal[y] = 0;
								whiteDiagonal[y] = 0;
							}
						}
					}
					block = 52;
					if (blackDiagonal[0] == 4 || blackDiagonal[1] == 4 || blackDiagonal[2] == 4
							|| blackDiagonal[3] == 4) {
						System.out.println("흑돌 대각선 승리");
						System.out.println(j + "" + i);
						System.out.println("승리" + blackDiagonal[0]);
						System.out.println("승리" + blackDiagonal[1]);
						System.out.println("승리" + blackDiagonal[2]);
						System.out.println("승리" + blackDiagonal[3]);
						mContext.blackWin();
						break Loop;
					}
					if (whiteDiagonal[0] == 4 || whiteDiagonal[1] == 4 || whiteDiagonal[2] == 4
							|| whiteDiagonal[3] == 4) {
						System.out.println("백돌 대각선 승리");
						mContext.whiteWin();
						break Loop;
					}
				}
				if (blackX == 4) {
					System.out.println("흑돌가로승리하셨습니다");
					mContext.blackWin();
					break Loop;
				} else if (whiteX == 4) {
					System.out.println("백돌가로승리하셨습니다");
					mContext.whiteWin();
					break Loop;
				}
				if (blackY == 4) {
					System.out.println("흑돌세로승리하셨습니다");
					mContext.blackWin();
					break Loop;
				} else if (whiteY == 4) {
					System.out.println("백돌세로승리하셨습니다");
					mContext.whiteWin();
					break Loop;
				}
				blackX = 0;
				blackY = 0;
				whiteX = 0;
				whiteY = 0;
			}
		}
	}
}
