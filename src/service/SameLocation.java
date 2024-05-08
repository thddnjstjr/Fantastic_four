package service;

public class SameLocation {
	private static int[][] board;
	private static int x;
	private static int y;
	private Gomoku gomoku;

	public SameLocation(int x, int y, Gomoku gomoku) {
		this.board = new int[x][y]; // 새로운 바둑돌
		this.gomoku = gomoku; // 기존 바둑돌
	}

	public void sameLocation() {
		if (gomoku == board[x][y]) {
			System.out.println("같은 위치에 돌을 둘수 없습니다.");
			return;
		}
	}
}
