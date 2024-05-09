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
	}
	while(true){
		for (int i = 0; i < map.length -1; i++) {
			for(int j = 0; j < map.length -1; j += 52) {
			if (map[j][i] == map[j+52][i]) {
				xfive++;
			}
			}
			if(xfive > 4) {
				System.out.println("육목은 안됩니다.");
				continue;
			}
			else if (xfive == 4) {
				System.out.println("승리하셨습니다");
				win();
			}
			xfive = 0;
		}
	}
	while(true){
		for (int i = 0; i < map.length - 1; i++) {
			for(int j = 0; j < map.length -1; j += 52) {
				if (map[i][j] == map[i][j+52]) {
					yfive++;
					if (yfive == 4) {
						System.out.println("승리하셨습니다");
						win();
					}
				}
				yfive = 0;
			}
		}
	}
	while(true){
		for (int i = 0; i < map.length - 1; i++) {
			for(int j = 0; j < map.length -1; j++) {
				for(int t = 0; t< 260; t +=52) {
				if (map[j][i] == map[j+t][i+t]) {
					num[0]++;
				}
				 if (map[j][i] == map[j+t][i-t]) {
					num[1]++;
				}
				 if (map[j][i] == map[j-t][i+t]) {
					num[2]++;
				 }
				 if (map[j][i] == map[j-t][i-t]) {
					num[3]++;
				}
				if (num[0] == 4 || num[1] == 4 || num[2] == 4 || num[3] == 4) {
					System.out.println("승리하셨습니다");
					win();
				}
				}
				for(int t = 0; t < num.length; t++) {
					num[t] = 0;
				}
			}
		}
	}
	
	
	if(map.getY()==map.getY()&&five==true)
	{
		System.out.println("이겼습니다");
	}if(map.getX()==map.getX()&&five==true)
	{
		System.out.println("이겼습니다");
	}if(map.getY()==map.getY()&&five==true)
	{
		System.out.println("이겼습니다");
	}


}
