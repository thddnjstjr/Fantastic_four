package service;

public class WinRule {

	int x;
	int y;
	public WinRule(int x , int y) {
		this.x = x;
		this.y = y;
	}
	board[x][y] = 
	while(true){
		for (int i = 0; i < white.length - 1; i++) {
			for(int j = 0; j < white.length -1; j++) {
			if (white[j][i] == white[j+1][i]) {
				Xfive++;
			}
			}
			if(Xfive > 4) {
				System.out.println("육목은 안됩니다.");
				continue;
			}
			else if (Xfive == 4) {
				System.out.println("승리하셨습니다");
				win();
			}
			Xfive = 0;
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
					num1++;
				}
				 if (white[j][i] == white[j+t][i-t]) {
					num2++;
				}
				 if (white[j][i] == white[j-t][i+t]) {
					num3++;
				 }
				 if (white[j][i] == white[j-t][i-t]) {
					num4++;
				}
				if (num1 == 4 || num2 == 4 || num3 == 4 || num4 == 4) {
					if(6)
					System.out.println("승리하셨습니다");
					win();
				}
				}
				num1 = 0;
				num2 = 0;
				num3 = 0;
				num4 = 0;
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
