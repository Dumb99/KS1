package pintuGame;

public class Time1 extends Thread{
	private int t1=0,t2=0,t3=0;
	static String str;
	private volatile boolean go = false;
	public void stopThread() {
		go = false;
	}
	public void startThread() {
		go = true;
	}
	public void reset() {
		t1=t2=t3=0;
	}
	public String getTwo(int i) {
		String I;
		if(i<10) I="0"+i;
		else I=String.valueOf(i);
		return I;		
	}
	@Override
	public void run() {
		while (true) {	
			while (go) {
				try {
					Thread.sleep(9);
					t1++;
					if (t1 == 99) {
						t1 = 0;
						t2++;
						if (t2 == 59) {
							t1 = 0;
							t2 = 0;
							t3++;
						}
					}
					str = getTwo(t3) + ":" + getTwo(t2) + ":" + getTwo(t1);
					MSXZ.time.setText(str);
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			} 
		} 
	}
	
}
