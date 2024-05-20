package gomoku.service;

import gomoku.components.Background;

public class Timer implements Runnable {

	private Background mconText;
	private long rTime;

	public Timer(Background mconText) {
		this.mconText = mconText;
	}
	
	public long getrTime() {
		return rTime;
	}

	@Override
	public void run() {
		long sTime = System.currentTimeMillis();
		while (mconText.isTime()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			long cTime = System.currentTimeMillis();
			rTime = cTime - sTime;
			// System.out.println(rTime/1000+"초");
		}
		 while(mconText.isTime()){
	        try {
	             Thread.sleep(1000);
	        } catch (InterruptedException e) {}
	        long cTime = System.currentTimeMillis();
	        rTime = cTime - sTime;
	        // System.out.println(rTime/1000+"초");
	        }
	}
}

