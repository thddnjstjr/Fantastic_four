package gomoku.components;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class CountdownTimer extends JLabel implements Runnable {

    private Background mContext;
    private String[] count = { "1.png", "2.png" };

    public CountdownTimer(Background mContext) {
        this.mContext = mContext;
    }

    @Override
    public void run() {
        initData();
        setInitLayout();
    }

    private void initData() {
        // 초기 이미지 설정
        setIcon(new ImageIcon("images/" + count[0]));
        setSize(100, 100);
        setLocation(100, 200);
        mContext.add(this); // 이 레이블을 배경에 추가
    }

    private void setInitLayout() {
        // count 배열 내 이미지 반복
        for (int i = 0; i < count.length; i++) {
            try {
                // 이미지 변경
                setIcon(new ImageIcon("images/" + count[i]));
                // 5초간 슬립
                Thread.sleep(5000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    
}