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
<<<<<<< HEAD
=======
	int block = 52;
>>>>>>> 50eaa5c502f02306af3a8a9a1f0305d764b923e5

	public WinRule(Background mContext) {
		this.mContext = mContext;
		this.map = mContext.getMap();
<<<<<<< HEAD
		for (int i = 0; i < num.length; i++) {
			num[i] = num[i];
		}
		WinRule();
	}

	public void WinRule() {
		while (true) {
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < 1000 || j < 948; j++) {
					if (map[j][i] != 0) {
						if (map[j][i] == map[j + 52][i]) {
							xfive++;
=======
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
<<<<<<< HEAD
							whiteY++;
=======
							wyfive++;
						}
						if (bxfive > 4) {
							System.out.println("육목은 안됩니다.");
							continue;
						}
						if (wxfive > 4) {
							System.out.println("육목은 안됩니다.");
							continue;
						}
						if (byfive > 4) {
							System.out.println("육목은 안됩니다.");
							continue;
						}
						if (wyfive > 4) {
							System.out.println("육목은 안됩니다.");
							continue;
						}
						if (bxfive == 4) {
							System.out.println("흑돌가로승리하셨습니다");
							break Loop;
						}
						if (byfive == 4) {
							System.out.println("흑돌세로승리하셨습니다");
							break Loop;
						}
						if (wxfive == 4) {
							System.out.println("백돌가로승리하셨습니다");
							break Loop;
						}
						if (wyfive == 4) {
							System.out.println("백돌세로승리하셨습니다");
							break Loop;
>>>>>>> 50eaa5c502f02306af3a8a9a1f0305d764b923e5
>>>>>>> 4a51b053734a6efc9cc74eb65af12c353250383b
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
						break Loop;
					}
					if (whiteDiagonal[0] == 4 || whiteDiagonal[1] == 4 || whiteDiagonal[2] == 4 || whiteDiagonal[3] == 4) {
						System.out.println("백돌 대각선 승리");
						break Loop;
					}
					for (int y = 0; y < blackDiagonal.length; y++) {
						blackDiagonal[y] = 0;
						whiteDiagonal[y] = 0;
					}

				}
<<<<<<< HEAD
				if (xfive > 4) {
					System.out.println("육목은 안됩니다.");
					continue;
				} else if (xfive == 4) {
					System.out.println("승리하셨습니다");
				}
				xfive = 0;
			}
			break;
		}
		while (true) {
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < 1000 || j < 948; j++) {
					if (map[i][j] == map[i][j + 52]) {
						yfive++;
						if (yfive == 4) {
							System.out.println("승리하셨습니다");
						}
					}
					yfive = 0;
				}
			}
			break;
		}
		while (true) {
			for (int i = 0; i < 1000 || i < 948; i++) {
				for (int j = 0; j < 1000 || j < 948; j++) {
					for (int t = 0; t < 260; t += 52) {
						if (map[j][i] == map[j + t][i + t]) {
							num[0]++;
						}

						if (map[j][i] == map[j + t][i - t]) {
							num[1]++;
						}
						if (map[j][i] == map[j - t][i + t]) {
							num[2]++;
						}
						if (map[j][i] == map[j - t][i - t]) {
							num[3]++;
						}
						if (num[0] == 4 || num[1] == 4 || num[2] == 4 || num[3] == 4) {
							System.out.println("승리하셨습니다");
						}
						for (int y = 0; y < num.length; y++) {
							num[y] = 0;
						}
					}
				}
			}
		}

	}
}
=======

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
				break Loop;
			} else if (blackY == 4) {
				System.out.println("흑돌세로승리하셨습니다");
				break Loop;
			} else if (whiteX == 4) {
				System.out.println("백돌가로승리하셨습니다");
				break Loop;
			} else if (whiteY == 4) {
				System.out.println("백돌세로승리하셨습니다");
				break Loop;
			}
			blackX = 0;
			blackY = 0;
			whiteX = 0;
			whiteY = 0;
		}
	}
}
>>>>>>> 50eaa5c502f02306af3a8a9a1f0305d764b923e5
