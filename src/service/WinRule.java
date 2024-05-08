package service;

public class WinRule {

	int x;
	int y;
	public WinRule(int x , int y) {
		this.x = x;
		this.y = y;
	}
	while(true){
		for (int i = 0; i < white.length - 1; i++) {
			for(int j = 0; j < white.length -1; j++) {
			if (white[j][i] == white[j+1][i]) {
				Xfive++;
				if (Xfive == 4) {
					System.out.println("승리하셨습니다");
					win();
				}
			}
			Xfive = 0;
			}
		}
	}
	while(true){
		for (int i = 0; i < white.length - 1; i++) {
			for(int j = 0; j < white.length -1; j++) {
				if (white[j][i] == white[j+1][i]) {
					Yfive++;
					if (Yfive == 4) {
						System.out.println("승리하셨습니다");
						win();
					}
				}
				Yfive = 0;
			}
		}
	}
	while(true){
		for (int i = 0; i < white.length - 1; i++) {
			for(int j = 0; j < white.length -1; j++) {
				for(int t = 1; t< 6; t++) {
				if (white[j][i] == white[j+t][i+t]) {
					Zfive++;
				}
				}
				Zfive = 0;
				for(int t = 1; t< 6; t++) {
				 if (white[j][i] == white[j+t][i-t]) {
					Zfive++;
				}
				}
				Zfive = 0;
				 for(int t = 1; t< 6; t++) {
				 if (white[j][i] == white[j-t][i+t]) {
					Zfive++;
				}
				 }
				 Zfive = 0;
				 for(int t = 1; t< 6; t++) {
				 if (white[j][i] == white[j-t][i-t]) {
					Zfive++;
				}
				 }
				 Zfive = 0;
				if (Zfive == 4) {
					System.out.println("승리하셨습니다");
					win();
				}
				}
			}
		}
	}
	
	
	if(white.getY()==white.getY()&&five==true)
	{
		System.out.println("이겼습니다");
	}if(white.getX()==white.getX()&&five==true)
	{
		System.out.println("이겼습니다");
	}if(white.getY()==white.getY()&&five==true)
	{
		System.out.println("이겼습니다");
	}

	int White_IntervalX = Math.abs(white.getX() - white.getX());
	int White_IntervalY = Math.abs(white.getY() - white.getY());

}
