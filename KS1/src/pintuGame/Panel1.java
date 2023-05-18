package pintuGame;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;

public class Panel1 extends JPanel implements MouseListener,MouseInputListener{
	private int N =MSXZ.N;
	private String str = MSXZ.picture;
	private int px,kx,bsj=0;
	private int[] M = new int[N*N];
	private JLabel pic[] = new JLabel[N*N];
	private JLabel img[] = new JLabel[N*N];
	private int[] attractPic = new int[N*2];
	private JPanel pnl = new JPanel();
	private JPanel mainPnl = new JPanel();
	private JScrollPane jsp;
	private String imgUrl,curTime,fileUrl;
	private Point point = new Point(0,0);
	public Panel1() {
		switch(N) {
		case 3:px=160;kx=10;break;
		case 4:px=120;kx=5;break;
		case 5:px=96 ;kx=3;break;
		}
		this.setLayout(null);
		this.setVisible(true);
		this.setSize(557,700);
//		this.setBackground(Color.CYAN);	
		this.setOpaque(false);
		this.add(mainPnl);
		for(int i=0;i<img.length;i++)
		{
			imgUrl = "/img/" + str + "/" + N + "/" + (i + 1) + "b.gif";
			ImageIcon Img = new ImageIcon(Panel1.class.getResource(imgUrl));
			img[i] = new JLabel(Img);
			img[i].setBounds(30+i%N*px, i/N*px, px, px);
			img[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			this.add(img[i]);
		} 
		for(int i=0;i<pic.length;i++) {			
			imgUrl = "/img/" + str + "/" + N + "/" + (i + 1) + ".gif";
			ImageIcon img = new ImageIcon(Panel1.class.getResource(imgUrl));
			pic[i] = new JLabel(img);
			pic[i].addMouseListener(this);
			pic[i].addMouseMotionListener(this);
		}
		reset();
		jsp = new JScrollPane(pnl);
		jsp.setBounds(20, 490, 500, 190);
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setOpaque(false);
		jsp.getViewport().setOpaque(false);	
		jsp.setBorder(null);
		pnl.setOpaque(false);
		mainPnl.setLayout(null);
		mainPnl.setVisible(true);
		mainPnl.setBounds(0, 0, 557, 700);
		mainPnl.setOpaque(false);
		mainPnl.add(jsp,-1);
	}
	public void reset() {	
		for (int i = 0; i < N*N; i++) {
			M[i] = (int) (Math.random() * N*N);
			for(int j=0;j<i;j++) {
				if(M[j]==M[i]) {
					i--;
					break;
				}
			}
		}		
		for(int i=0;i<pic.length;i++) {
			pnl.add(pic[M[i]]);
		}			
	}
	
	public void initialize() {
	
	}
	public void attract(int x,int y,JLabel lbl) {
		if((x>20 && x<520) && (y<680 && y>490)) {
			int x1=(int)(Math.random()*2);
			if(x1!=0) x1=480;
			int x2=(int)(Math.random()*400);
			lbl.setLocation(x1, x2);
		}
		for(int i=0;i<N*2;i++) {
			if(i%2==0) attractPic[i]=i/2*px+kx;
			else attractPic[i]=i/2*px+px-kx;
		}
		for (int t=0,t1=0; t < N; t++,t1+=2) {
			for (int i = 0, j = 0; i < N; i++, j+=2) {
				if ((x > 30 + attractPic[t1] && x < 30 + attractPic[t1+ 1])
						&& (y > attractPic[j] && y < attractPic[j + 1]))
					lbl.setLocation(30 + attractPic[t1]-kx, attractPic[j]-kx);
			}
		}
	}
	public boolean iffinish() {
		for(int i=0; i<pic.length; i++) {
			int RX = i%N * px+30;
			int RY = i/N * px;
			int x = pic[i].getX();
			int y = pic[i].getY();
			if(x!=RX || y!=RY)
				return false;
			}
		return true;
	}
	public void finishShow(){
		MSXZ.Start=false;
		MSXZ.timeThread.stopThread();
		curTime = Time1.str;
		ImageIcon image = new ImageIcon(Panel1.class.getResource("/jieni.jpg"));
		fileUrl = "D:/Users/16210/eclipse-workspace/KS1/bin/img/"+str+"/"+N+"/sorce2.txt";
		try {
			if(ifbreak(curTime,MSXZ.historyTime)) {
				MSXZ.history.setText(curTime);
				JOptionPane.showMessageDialog(null, "获得新的记录:\n"+curTime+"!" ,"祝贺！",JOptionPane.ERROR_MESSAGE,image);
				FileOutputStream fos = new FileOutputStream(fileUrl);
				OutputStreamWriter dos = new OutputStreamWriter(fos);
				BufferedWriter bw = new BufferedWriter(dos);
				bw.write(curTime);
				bw.close();
			}
			else JOptionPane.showMessageDialog(null, "完成!");
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
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自动生成的方法存根
		JLabel Pic =(JLabel) e.getSource();
		point = SwingUtilities.convertPoint(Pic, e.getPoint(), Pic.getParent());
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO 自动生成的方法存根
		if(MSXZ.Start) { 
			JLabel Pic =(JLabel) e.getSource();
			Point newPoint=SwingUtilities.convertPoint(Pic,e.getPoint(),Pic.getParent());//转换坐标系统
			Pic.setLocation(Pic.getX()+(newPoint.x-point.x),Pic.getY()+(newPoint.y-point.y));
		    point=newPoint;
		    mainPnl.add(Pic,1);
		}
	}
	public void mouseReleased(MouseEvent e) {
		if(MSXZ.Start) {
			JLabel Pic =(JLabel) e.getSource();
			Point newPoint=SwingUtilities.convertPoint(Pic,e.getPoint(),Pic.getParent());
		    attract(newPoint.x,newPoint.y,Pic);
		    JScrollBar jsb = new JScrollBar();
		    jsb = jsp.getHorizontalScrollBar();
		    bsj++;
		    jsb.setValue(bsj);
		    if(iffinish())	finishShow();		    
		}
	}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	
}
