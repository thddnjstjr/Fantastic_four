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

			}
		}
	}
}
