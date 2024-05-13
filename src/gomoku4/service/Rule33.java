package gomoku4.service;

import gomoku4.components.Background;

// thread로 실행
public class Rule33 {

	Background mContext;
	private static final int BLOCK = 52;
	private final int MIN_X = 20; // 바둑판 왼쪽 끝 눈금 X좌표
	private final int MAX_X = 946; // 바둑판 오른쪽 끝 눈금 X좌표
	private final int MIN_Y = 20; // 바둑판 상단 끝 눈금 Y좌표
	private final int MAX_Y = 945; // 바둑판 하단 끝 눈금 Y좌표
	private int x;
	private int y;
	private int[][] stone;

	public Rule33(Background mContext) {
		this.mContext = mContext;
		this.stone = mContext.getMap();
	}
}
