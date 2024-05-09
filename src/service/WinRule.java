package service;

import gomoku.components.Background;

public class WinRule {

	int x;
	int y;
	int xfive;
	int yfive;
	int[] num = new int[4];
	int[][] map;
	private Background mContext;

	public WinRule(Background mContext) {
		this.mContext = mContext;
		this.map = mContext.getMap();
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
						}
					}
				}
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