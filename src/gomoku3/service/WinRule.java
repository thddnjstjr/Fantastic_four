package gomoku3.service;

import gomoku3.components.Background;

public class WinRule implements Runnable {
	// 멤버변수
	int blackX; // 흑돌의 가로 스택(연속된 흑돌의 갯수)
	int blackY; // 흑돌의 세로 스택
	int whiteX; // 백돌의 가로 스택
	int whiteY; // 백돌의 세로 스택
	int[] blackDiagonal = new int[4]; // 흑돌의 대각선 스택
	int[] whiteDiagonal = new int[4]; // 백돌의 대각선 스택
	int[][] map; // map 좌표
	private Background mContext; // Background에서 좌표받아와야해서 객체생성
	int block = 52; // map[j][i]를 전부 탐색해서 흑돌이나 백돌이 있으면 그 좌표기준으로 52 /104 / 156/ 반경으로 탐색할 예정
	int block2 = 52; // 바둑판 한칸 크기
	int sixblock = 260; // 흑돌이나 백돌이 있는 좌표 기준으로 6번째칸에 같은돌이있으면 오목이 안됨

	public WinRule(Background mContext) { 
		this.mContext = mContext; 
		this.map = mContext.getMap(); // Background 에서 map 좌표 가져옴
	}

	@Override
	public void run() {
		Loop: while (true) {
			for (int i = 0; i < map.length - block; i++) { // i좌표를 0부터 맵끝까지 탐색하지만 탐색과정에서 인덱스크기를 벗어날수있기때문에 탐색범위는 빼준다
				for (int j = 0; j < map.length - block; j++) { // 위와 동일
					if (map[j][i] != 0) { // 만약 map[j][i] 좌표에 흑돌이나 백돌이 있다면 ([j]는 x [i]는 y 좌표)
						for (int t = 0; t < 4; t++) { // 그 좌표의 5번째칸까지 확인한다
							if (map[j + block][i] == 0 || map[j + block][i] == 2) { // 만약 [j] 좌표에서 +block만큼의 거리에 돌이 없거나 백돌이 있다면
								blackX = 0; // 흑돌 가로스택을 초기화한다
							} else if (map[j][i] == 1 && map[j + sixblock][i] == 1) { // 만약 그 좌표의 + 6번째 칸에 흑돌이있다면 반복문을 건너뛴다
								continue;
							}
							if (map[j + block][i] == 0 || map[j + block][i] == 1) { // 만약 [j] 좌표에서 +block만큼의 거리에 돌이 없거나 흑돌이 있다면
								whiteX = 0; // 백돌 가로스택을 초기화한다
							} else if (map[j][i] == 2 && map[j + sixblock][i] == 2) { // 만약 그 좌표의 + 6번째 칸에 백돌이있다면 반복문을 건너뛴다
								continue;
							}
							if (map[j][i] == 1 && map[j + block][i] == 1) { // 만약 흑돌이있는 좌표에서 +block만큼의 [j]거리에 흑돌이 있다면 +1스택
								if (map[j - block2][i] == 1) { // 만약 왼쪽 한칸만큼의 거리에 흑돌이있다면 반복문 건너뜀 (무조건 왼쪽 끝에서부터 판단하도록 설계)
									continue;
								}
								blackX++;
							}
							if (map[j][i] == 2 && map[j + block][i] == 2) { // 만약 백돌이있는 좌표에서 +block만큼의 [j]거리에 백돌이 있다면 +1스택
								if (map[j - block2][i] == 2) { // 만약 왼쪽 한칸만큼의 거리에 흑돌이있다면 반복문 건너뜀 (무조건 왼쪽 끝에서부터 판단하도록 설계)
									continue;
								}
								whiteX++;
							}
							block += 52; // 반복문이 한번 돌때마다 block을 52(한칸)씩 추가함 총 4번 반복하도록 설계하여 5개의 돌이 이어지는지 확인
						}
					}
					block = 52; // 가로스택 확인이 다 끝나면 block을 다시 52(한칸)으로 초기화
					if (map[i][j] != 0) { // 세로스택도 같은원리로 확인하기위해 안쪽반복문인 j를 y좌표로 옮김
						for (int t = 0; t < 4; t++) {
							if (map[i][j + block] == 0 || map[j][j + block] == 2) { // 가로스택과 같은 원리
								blackX = 0;
							} else if (map[i][j] == 1 && map[i][j + sixblock] == 1) {
								continue;
							}
							if (map[i][j + block] == 0 || map[i][j + block] == 1) {
								whiteX = 0;
							} else if (map[i][j] == 2 && map[i][j + sixblock] == 2) {
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
					if (blackX == 4) { // 만약 흑돌의 가로스택이 4스택이면 5개의돌이 서로 이어져있는것을 확인할수있다.
						// System.out.println("흑돌가로승리하셨습니다");
						mContext.blackWin();
						break Loop;
					} else if (whiteX == 4) {
						// System.out.println("백돌가로승리하셨습니다");
						mContext.whiteWin();
						break Loop;
					}
					if (blackY == 4) {
						//System.out.println("흑돌세로승리하셨습니다");
						mContext.blackWin();
						break Loop;
					} else if (whiteY == 4) {
						// System.out.println("백돌세로승리하셨습니다");
						mContext.whiteWin();
						break Loop;
					}
					block = 52;
					if (map[j][i] != 0) {
						for (int t = 0; t < 4; t++) {
							if (i < map.length / 2) { // 돌의 좌표가 block보다 작을경우 인덱스 범위를 벗어나게 되기때문에 block보다 작은 범위는 +(위에서 아래로)탐색하도록 설계
								if (i == 9) { // y좌표가 9일때는 해당 좌표보다 밑에있는 돌은 없기때문에 밑에는 보지않는다
									if (map[j][i] == 1 && map[j + block][i + block] == 1 && map[j + sixblock][i + sixblock] != 1) { // 만약 map[j][i]좌표에 흑돌이있을때 그 좌표에서 x로 +block y로 +block (오른쪽 아래 방향)에 흑돌이있고 여섯번째칸에 흑돌이없다면 대각선스택 +1
										blackDiagonal[0]++; // 대각선은 4방향이기때문에 4개의 배열을 생성해서 각각의 결과를 담는다
									}
									if (map[j][i] == 1 && map[j - block][i + block] == 1 && map[j - sixblock][i + sixblock] != 1) { // 위와 동일한 매커니즘으로 x로 -block y로 +block (왼쪽 아래 방향)
										blackDiagonal[2]++;
									}
									if (map[j][i] == 2 && map[j + block][i + block] == 2 && map[j + sixblock][i + sixblock] != 2) {
										whiteDiagonal[0]++;
									}
									if (map[j][i] == 2 && map[j - block][i + block] == 2 && map[j - sixblock][i + sixblock] != 2) {
										whiteDiagonal[2]++;
									}
								} else {
									if (map[j][i] == 1 && map[j + block][i + block] == 1 && map[j - block2][i - block2] != 1 && map[j + sixblock][i + sixblock] != 1) { // 탐색방향 반대방향쪽 한칸에 똑같은 돌이있는지 탐색 추가
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
							} else if (i >= map.length / 2) { // y좌표가 맵 절반보다 크다면 밑에서 위로 탐색하는 방식
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
						// System.out.println("흑돌 대각선 승리");
						mContext.blackWin();
						break Loop;
					}
					if (whiteDiagonal[0] == 4 || whiteDiagonal[1] == 4 || whiteDiagonal[2] == 4
							|| whiteDiagonal[3] == 4) {
						// System.out.println("백돌 대각선 승리");
						mContext.whiteWin();
						break Loop;
					}
					for (int y = 0; y < blackDiagonal.length; y++) { // j가 한바퀴 돌때마다 대각선 스택을 초기화
						blackDiagonal[y] = 0;
						whiteDiagonal[y] = 0;
					}
					blackX = 0; // j가 한바퀴 돌때마다 가로스택과 세로스택을 초기화
					blackY = 0;
					whiteX = 0;
					whiteY = 0;
				}
			}
		}
	}
}
