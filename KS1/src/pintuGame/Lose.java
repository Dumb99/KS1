package pintuGame;
import javax.swing.*;
public class Lose extends Thread{
	public Lose() {
		this.setDaemon(true);
	}
	public void run() {
		try {
			while(true) {
				Thread.sleep(5);
				if(MSXZ.ifSer) {
					Thread.sleep(5);
					if(Server.finish) {
						MSXZ.Start=false;
						MSXZ.timeThread.stopThread();
						ImageIcon lose = new ImageIcon(Panel1.class.getResource("/lose.jpg"));
						JOptionPane.showMessageDialog(null, "很遗憾，你输了!" ,"失败！",JOptionPane.ERROR_MESSAGE,lose);
						Server.finish=false;
						break;
					}				
				}
				if(MSXZ.ifCli) {
					Thread.sleep(5);
					if(Client.finish) {
						MSXZ.Start=false;
						MSXZ.timeThread.stopThread();
						ImageIcon lose = new ImageIcon(Panel1.class.getResource("/lose.jpg"));
						JOptionPane.showMessageDialog(null, "很遗憾，你输了!" ,"失败！",JOptionPane.ERROR_MESSAGE,lose);
						Client.finish=false;
						break;
					}				
				}
			}
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
