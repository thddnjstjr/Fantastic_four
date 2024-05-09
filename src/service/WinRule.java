package service;

import gomoku.components.Background;

public class WinRule implements Runnable {

	int x;
	int y;
	int bxfive;
	int byfive;
	int wxfive;
	int wyfive;
	int[] bnum = new int[4];
	int[] wnum = new int[4];
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
							bxfive++;
						}
						if (map[j][i] == 1 && map[j][i + block] == 1) {
							byfive++;
						}
						if (map[j][i] == 2 && map[j + block][i] == 2) {
							wxfive++;
						}
						if (map[j][i] == 2 && map[j][i + block] == 2) {
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
						}
					}

					for (int t = 0; t < 4; t++) {
						if (map.length > j + block && map.length > i + block && 0 < i - block && 0 < j - block) {
							if (map[j][i] == 1 && map[j + block][i + block] == 1) {
								bnum[0]++;
							}
							if (map[j][i] == 1 && map[j + block][i - block] == 1) {
								bnum[1]++;
							}
							if (map[j][i] == 1 && map[j - block][i + block] == 1) {
								bnum[2]++;
							}
							if (map[j][i] == 1 && map[j - block][i - block] == 1) {
								bnum[3]++;
							}
							if (map[j][i] == 2 && map[j + block][i + block] == 2) {
								wnum[0]++;
							}
							if (map[j][i] == 2 && map[j + block][i - block] == 2) {
								wnum[1]++;
							}
							if (map[j][i] == 2 && map[j - block][i + block] == 2) {
								wnum[2]++;
							}
							if (map[j][i] == 2 && map[j - block][i - block] == 2) {
								wnum[3]++;
							}
						}
						block += 52;
					}
					block = 52;
					if (bnum[0] == 4 || bnum[1] == 4 || bnum[2] == 4 || bnum[3] == 4) {
						System.out.println("흑돌 대각선 승리");
						break Loop;
					}
					if (wnum[0] == 4 || wnum[1] == 4 || wnum[2] == 4 || wnum[3] == 4) {
						System.out.println("백돌 대각선 승리");
						break Loop;
					}
					for (int y = 0; y < bnum.length; y++) {
						bnum[y] = 0;
						wnum[y] = 0;
					}
					bxfive = 0;
					byfive = 0;
					wxfive = 0;
					wyfive = 0;

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
		}
	}
}
>>>>>>> 50eaa5c502f02306af3a8a9a1f0305d764b923e5
