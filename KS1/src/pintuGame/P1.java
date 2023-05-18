package pintuGame;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class P1 extends JPanel implements MouseListener,KeyListener{
	private int N = MSXZ.N;
	private String str = MSXZ.picture;
	private int px;
	private JButton pic[] = new JButton[N*N-1];
	private Rectangle pn;
	private String imageUrl,curTime,fileUrl;
	public P1()
	{
		switch(N)
		{
		case 3:px=160;break;
		case 4:px=120;break;
		case 5:px=96 ;break;
		}
		for(int i=0; i<pic.length; i++) {
			imageUrl = "/img/"+ str +"/"+ N + "/"+ (i+1)+".gif";
			ImageIcon image = new ImageIcon(P1.class.getResource(imageUrl)); 
			pic[i] = new JButton(image);
			pic[i].setSize(px,px);
		}
		
		initialize();
		this.setLayout(null);
		this.setSize(480, 480);
	}
	public boolean test(int x) {
		if(x>=0 && x<=480-px) 
			return true;
		else
			return false;
	}
	public void reset(JButton[] btn) {
		for(int i=0;i <500; i++) {		
			int r1 = (int)(Math.random()*10);
			if(r1<5) r1=1;
			else r1=-1;
			int r2 = (int)(Math.random()*2);
			int X = (int) pn.getX();
			int Y = (int) pn.getY();			
			if(r2==0) {
				int xx = X+r1*px;
				if (test(xx)) {
					for (int j = 0; j < btn.length; j++) {
						if (btn[j].getX() == xx && btn[j].getY() == Y) {
							btn[j].setLocation(X, Y);
							pn.setLocation(xx, Y);
						}
					} 
				}					
			}
			else {
				int yy = Y+r1*px;
				if (test(yy)) {
					for (int j = 0; j < btn.length; j++) {
						if (btn[j].getY() == yy && btn[j].getX() == X) {
							btn[j].setLocation(X, Y);
							pn.setLocation(X, yy);
						}
					} 
				}	
			}		
		}
		while(pn.getX()!=480-px) {
			int X = (int) pn.getX();
			for(int i=0; i<pic.length; i++) {
				if(pic[i].getX()==X+px && pic[i].getY()==pn.getY())
					pic[i].setLocation(X, (int) pn.getY());
			}				
			pn.setLocation(X+px, (int) pn.getY());
		}
		while(pn.getY()!=480-px) {
			int Y = (int) pn.getY();
			for(int i=0; i<pic.length; i++) {
				if(pic[i].getY()==Y+px && pic[i].getX()==pn.getX())
					pic[i].setLocation((int) pn.getX(), Y);
			}	
			pn.setLocation((int) pn.getX(), Y+px);
		}
		
	}
	public void initialize() {
		for(int i=0; i<pic.length; i++) {
			int x = i%N*px;
			int y = i/N*px;
			pic[i].setLocation(x, y);
			pic[i].addKeyListener(this);
			pic[i].addMouseListener(this);
			this.add(pic[i]);
		}
		pn= new Rectangle(480-px,480-px,px,px);
		reset(pic);
	}
	public boolean finished(JButton[] btn) {
		for(int i=0; i<btn.length; i++) {
			int RX = i%N * px;
			int RY = i/N * px;
			int x = btn[i].getX();
			int y = btn[i].getY();
			if(x!=RX || y!=RY)
				return false;
		}
		return true;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO 自动生成的方法存根
		 if(MSXZ.Start) {
			 int K = e.getKeyCode();
				int X = (int) pn.getX();
				int Y = (int) pn.getY();
				if(K == KeyEvent.VK_DOWN || K == KeyEvent.VK_W) {
					if(Y!=0) {
						int y=Y-px;
						for(int i=0;i<pic.length;i++) {
							if(pic[i].getX()==X && pic[i].getY() == y) {
								pn.setLocation(X, y);
								pic[i].setLocation(X, Y);
								break;
							}						
						}
					}
				}
				if(K == KeyEvent.VK_UP || K == KeyEvent.VK_S) {
					if(Y!=480-px) {
						int y=Y+px;
						for(int i=0; i<pic.length; i++) {
							if(pic[i].getX()==X && pic[i].getY()==y) {
								pn.setLocation(X,y);
								pic[i].setLocation(X, Y);
								break;
							}
						}
						if(finished(pic)) {
							finishShow();
						}
					}
				}
				if(K == KeyEvent.VK_RIGHT || K == KeyEvent.VK_A) {
					if(X!=0) {
						int x = X - px;
						for(int i=0; i<pic.length; i++) {
							if(pic[i].getX()==x && pic[i].getY()==Y) {
								pn.setLocation(x, Y);
								pic[i].setLocation(X, Y);
								break;
							}
						}
					}
				}
				if(K == KeyEvent.VK_LEFT || K == KeyEvent.VK_D) {
					if(X!=480-px) {
						int x = X+px;
						for(int i=0; i<pic.length; i++) {
							if(pic[i].getX()==x && pic[i].getY()==Y) {
								pn.setLocation(x, Y);
								pic[i].setLocation(X, Y);
								break;
							}
						}
						if(finished(pic)) {
							finishShow();
						}
					}
				}
		 }		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自动生成的方法存根
		if(MSXZ.Start) {
			JButton btn = (JButton)e.getSource();
			int X = btn.getX();
			int Y = btn.getY();
			int nX = (int) pn.getX();
			int nY = (int) pn.getY();
			if((X==nX && (Y-nY==px || Y-nY==-px))
					|| (Y==nY && (X-nX==px ||X-nX==-px))) {
				btn.setLocation(nX, nY);
				pn.setLocation(X, Y);
				this.repaint();
			}
			if(finished(pic)) {
				finishShow();
			}
		}
	}
	public void finishShow() {
		MSXZ.Start = false;
		MSXZ.timeThread.stopThread();
		curTime=Time1.str;
		ImageIcon img = new ImageIcon(P1.class.getResource("/jieni.jpg"));
		fileUrl = "D:/Users/16210/eclipse-workspace/KS1/bin/img/"+str+"/"+N+"/sorce1.txt";
		try {
			if(ifbreak(curTime,MSXZ.historyTime)) {
				MSXZ.history.setText(curTime);
				JOptionPane.showMessageDialog(null, "获得新的记录:\n"+curTime+"!" ,"祝贺！",JOptionPane.ERROR_MESSAGE,img);
				FileOutputStream fos = new FileOutputStream(fileUrl);
				OutputStreamWriter dos = new OutputStreamWriter(fos);
				BufferedWriter bw = new BufferedWriter(dos);
				bw.write(curTime);
				bw.close();
			}
			else {
				JOptionPane.showMessageDialog(null, "完成!");
			}
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	public boolean ifbreak(String s,String t) {
		String[] ss,tt;
		ss = s.split(":");
		tt = t.split(":");
		int[] S = new int[ss.length];
		int[] T = new int[tt.length];
		for(int i=0;i<ss.length;i++)
			S[i]=Integer.valueOf(ss[i]);
		for(int i=0;i<tt.length;i++) 
			T[i]=Integer.valueOf(tt[i]);
		if(S[0]<T[0]) return true;
		else if(S[0]==T[0] && S[1]<T[1]) return true;
		else if(S[0]==T[0] && S[1]==T[1] && S[2]<T[2]) return true;
		else return false;
	}

	public void keyReleased(KeyEvent e) {}
 	public void keyTyped(KeyEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}	
}
