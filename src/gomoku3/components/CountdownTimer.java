package gomoku3.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Closeable;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

public class CountdownTimer extends JLabel implements Runnable {

	Background mconText;
	Timer timer;
	
	private String[] count = {"0.png", "30.png", "29.png", "28.png", "27.png", "26.png", "25.png", "24.png", "23.png", "22.png",
			"21.png", "20.png", "19.png", "18.png", "17.png", "16.png", "15.png", "14.png", "13.png", "12.png",
			"11.png", "10.png", "9.png", "8.png", "7.png", "6.png", "5.png", "4.png", "3.png", "2.png", "1.png",
			"0.png" };
	private int index = 0;

	public CountdownTimer(Background mconText) {
		this.mconText = mconText;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}

	private void initData() {
		// 초기 이미지 설정
		setIcon(new ImageIcon("images/" + count[0]));
		setSize(500, 500);
		setLocation(100, 1);
		setVisible(true);
		mconText.add(this);
	}
	private void setInitLayout() {
		// Timer 설정: 1000ms(1초)마다 actionPerformed 메소드 호출
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// count 배열 내 이미지 반복
				if (mconText.isGame() == false) {
					((Timer) e.getSource()).stop();
					return;
				}
				if (index < count.length) {
					index++;
					setIcon(new ImageIcon("images/" + count[index]));
				} else {
					// 타이머 중지
					((Timer) e.getSource()).stop();
				}
				if (index == 31) {
					if ((mconText.getColor() % 2) == 0) {
						mconText.whiteWin();
						return;
					} else if ((mconText.getColor() % 2) == 1) {
						mconText.blackWin();
						return;
					}
				}
			}
		});

		timer.setInitialDelay(0); // 처음에 즉시 시작
		timer.start();
		
	}
	
	public void reset() {
		timer.stop();
		index = 0;
		timer.restart();
	}
	@Override
	public void run() {
		initData();
		setInitLayout();
	}

}