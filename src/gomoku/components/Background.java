package gomoku.components;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Background extends JFrame {

    final int LINE_NUM = 19;
    final int LINE_WIDTH = 19;

    private final int[][] map = new int[LINE_NUM][LINE_NUM];
    private final int BLACK_STONE = 1; // 배열에 입력된 값이 1인 경우 그자리에는 흑돌이 있음
    private final int WHITE_STONE = 2; // 배열에 입력된 값이 2인 경우 그자리에는 백돌이 있음

    private JLabel backgroundMap;
    private Player player;

    public Background() {
        initData();
        setInitLayout();
        addEventListener();
    }

    private void initData() {
        backgroundMap = new JLabel(new ImageIcon("images/omokbackground.png"));
        setContentPane(backgroundMap);
        setTitle("오목게임");
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setInitLayout() {
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addEventListener() {
        this.addKeyListener(new KeyAdapter() {
        	
        	public void keypressed(KeyEvent e) {
        		System.out.println(e.getKeyCode());
        		
        		switch(e.getKeyCode()) {
        		case KeyEvent.VK_LEFT:
        			
        		}
        	}
        	
        	
		});
    }

    // 다른 메소드들 생략
}