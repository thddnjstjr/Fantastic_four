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
	int sixblock = 260;

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
						for (int t = 0; t < 4; t++) {
							if (map[j + block][i] == 0 || map[j + block][i] == 2) {
								blackX = 0;
							} else if (map[j + sixblock][i] == 1) {
								continue;
							}
							if (map[j + block][i] == 0 || map[j + block][i] == 1) {
								whiteX = 0;
							} else if (map[j + sixblock][i] == 2) {
								continue;
							}
							if (map[j][i] == 1 && map[j + block][i] == 1) {
								if (map[j - block2][i] == 1) {
									continue;
								}
								blackX++;
							}
							if (map[j][i] == 2 && map[j + block][i] == 2) {
								if (map[j - block2][i] == 2) {
									continue;
								}
								whiteX++;
							}
							block += 52;
						}
					}
					block = 52;
					if (map[i][j] != 0) {
						for (int t = 0; t < 4; t++) {
							if (map[i][j + block] == 0 || map[j][j + block] == 2) {
								blackX = 0;
							} else if (map[i][j + sixblock] == 1) {
								continue;
							}
							if (map[i][j + block] == 0 || map[i][j + block] == 1) {
								whiteX = 0;
							} else if (map[i][j + sixblock] == 2) {
								continue;
							}
							if (map[i][j] == 1 && map[i][j + block] == 1) {
								if (j == 9) {
									blackY++;
								} else if (map[i][j - block2] == 1) {
									continue;
								} else {
									blackY++;
								}
							}
							if (map[i][j] == 2 && map[i][j + block] == 2) {
								if (j == 9) {
									whiteY++;
								} else if (map[i][j - block2] == 2) {
									continue;
								} else {
									whiteY++;
								}
							}
							block += 52;
						}
					}
					if (blackX == 4) {
						System.out.println("흑돌가로승리하셨습니다");
						System.out.println(j + " , " + i);
						mContext.blackWin();
						break Loop;
					} else if (whiteX == 4) {
						System.out.println("백돌가로승리하셨습니다");
						mContext.whiteWin();
						break Loop;
					}
					if (blackY == 4) {
						System.out.println("흑돌세로승리하셨습니다");
						System.out.println(i + " , " + j);
						mContext.blackWin();
						break Loop;
					} else if (whiteY == 4) {
						System.out.println("백돌세로승리하셨습니다");
						mContext.whiteWin();
						break Loop;
					}
					block = 52;
					if (map[j][i] != 0) {
						for (int t = 0; t < 4; t++) {
							if (i < map.length / 2) {
								if (i == 9) {
									if (map[j][i] == 1 && map[j + block][i + block] == 1 && map[j + sixblock][i + sixblock] != 1) {
										blackDiagonal[0]++;
									}
									if (map[j][i] == 1 && map[j - block][i + block] == 1 && map[j - sixblock][i + sixblock] != 1) {
										blackDiagonal[2]++;
									}
									if (map[j][i] == 2 && map[j + block][i + block] == 2 && map[j + sixblock][i + sixblock] != 2) {
										whiteDiagonal[0]++;
									}
									if (map[j][i] == 2 && map[j - block][i + block] == 2 && map[j - sixblock][i + sixblock] != 2) {
										whiteDiagonal[2]++;
									}
								} else {
									if (map[j][i] == 1 && map[j + block][i + block] == 1 && map[j - block2][i - block2] != 1 && map[j + sixblock][i + sixblock] != 1) {
										blackDiagonal[0]++;
									}
									if (map[j][i] == 1 && map[j - block][i + block] == 1 && map[j + block2][i - block2] != 1 && map[j - sixblock][i + sixblock] != 1) {
										blackDiagonal[2]++;
									}
									if (map[j][i] == 2 && map[j + block][i + block] == 2 && map[j - block2][i - block2] != 2 && map[j + sixblock][i + sixblock] != 2) {
										whiteDiagonal[0]++;
									}
									if (map[j][i] == 2 && map[j - block][i + block] == 2 && map[j + block2][i - block2] != 2 && map[j - sixblock][i + sixblock] != 2) {
										whiteDiagonal[2]++;
									}
								}
							} else if (i >= map.length / 2) {
								if (map[j][i] == 1 && map[j + block][i - block] == 1 && map[j - block2][i + block2] != 1 && map[j + sixblock][i - sixblock] != 1) {
									blackDiagonal[1]++;
								}
								if (map[j][i] == 1 && map[j - block][i - block] == 1 && map[j + block2][i + block2] != 1 && map[j - sixblock][i - sixblock] != 1) {
									blackDiagonal[3]++;
								}
								if (map[j][i] == 2 && map[j + block][i - block] == 2 && map[j - block2][i + block2] != 2 && map[j + sixblock][i - sixblock] != 2) {
									whiteDiagonal[1]++;
								}
								if (map[j][i] == 2 && map[j - block][i - block] == 2 && map[j + block2][i + block2] != 2 && map[j - sixblock][i - sixblock] != 2) {
									whiteDiagonal[3]++;
								}
							}
							block += 52;
						}
					}
					block = 52;
					if (blackDiagonal[0] == 4 || blackDiagonal[1] == 4 || blackDiagonal[2] == 4
							|| blackDiagonal[3] == 4) {
						System.out.println(j + " , " + i);
						System.out.println(blackDiagonal[0]);
						System.out.println(blackDiagonal[1]);
						System.out.println(blackDiagonal[2]);
						System.out.println(blackDiagonal[3]);
						System.out.println("흑돌 대각선 승리");
						mContext.blackWin();
						break Loop;
					}
					if (whiteDiagonal[0] == 4 || whiteDiagonal[1] == 4 || whiteDiagonal[2] == 4
							|| whiteDiagonal[3] == 4) {
						System.out.println("백돌 대각선 승리");
						mContext.whiteWin();
						break Loop;
					}
					for (int y = 0; y < blackDiagonal.length; y++) {
						blackDiagonal[y] = 0;
						whiteDiagonal[y] = 0;
					}
					blackX = 0;
					blackY = 0;
					whiteX = 0;
					whiteY = 0;
				}
			}
		}
	}
}