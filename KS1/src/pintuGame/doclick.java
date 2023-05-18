package pintuGame;

public class doclick extends Thread{
	private volatile boolean go = false;
	public void startThread() {
		go = true;
	}
	public doclick() {
		this.setDaemon(true);
	}
	public void run() {
		while (true) {
			while (go) {
				switch (Client.str) {
				case ("MS1"):{
					MSXZ.MS1.doClick();
					break;
				}
				case ("MS2"):{
					MSXZ.MS2.doClick();
					break;
				}
				case ("lev1"):{
					MSXZ.lev1.doClick();
					break;
				}
				case ("lev2"):{
					MSXZ.lev2.doClick();
					break;
				}
				case ("lev3"):{
					MSXZ.lev3.doClick();
					break;
				}
				case ("img[0]"):{
					MSXZ.img[0].doClick();
					break;
				}
				case ("img[1]"):{
					MSXZ.img[1].doClick();
					break;
				}
				case ("img[2]"):{
					MSXZ.img[2].doClick();
					break;
				}
				case ("img[3]"):{
					MSXZ.img[3].doClick();
					break;
				}
				case ("Return"):{
					MSXZ.Return.doClick();
					break;
				}
				}
				go = false;
			}
		}
	}
}
