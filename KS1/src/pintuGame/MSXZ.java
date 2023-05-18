package pintuGame;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.*;


public class MSXZ extends JFrame {
	static int N=3;
	static String picture = "flog";
	static String historyTime;
	static boolean Start = false;
	private boolean MS,ifSer=false,ifCli=false;
	private int M=0;
	private String ss = null;
	private JButton ser,cli;
	static JTextField IPaddress = new JTextField();
	static JTextField connect = new JTextField();
	static JButton MS1,MS2,PK1,PK2,Return,restart;
	static JButton lev1,lev2,lev3,start;
	static JTextField time = new JTextField();
	static JTextField history = new JTextField();
	static JButton img[] = new JButton[4];
	private JLabel bkgLab,pre,view,timeLab,historyLab;
	private JPanel ctn,headpnl;
	private ImageIcon bkgImg,title,imgImg,LSimg,preImg,viewImg,startImg;
	static Time1  timeThread;
	private Font font = new Font("黑体", Font.BOLD, 15);
	private Font font1 = new Font(null, Font.ITALIC, 25);
	public MSXZ() {		
		ctn = new JPanel();
		this.setContentPane(ctn);
		ctn.setLayout(null);
		initialize();
		addAction();
		PK_go();
	}

	public void initialize() {
		LSimg = new ImageIcon(MSXZ.class.getResource("/pushA.png"));
		MS1 = new JButton(LSimg);
		LSimg = new ImageIcon(MSXZ.class.getResource("/pushB.png"));
		MS2 = new JButton(LSimg);
		LSimg = new ImageIcon(MSXZ.class.getResource("/pushD.png"));
		PK1 = new JButton(LSimg);
		LSimg = new ImageIcon(MSXZ.class.getResource("/pushC.png"));
		PK2 = new JButton(LSimg);
		LSimg = new ImageIcon(MSXZ.class.getResource("/push1.png"));
		lev1 = new JButton(LSimg);
		LSimg = new ImageIcon(MSXZ.class.getResource("/push2.png"));
		lev2 = new JButton(LSimg);
		LSimg = new ImageIcon(MSXZ.class.getResource("/push3.png"));
		lev3 = new JButton(LSimg);
		LSimg = new ImageIcon(MSXZ.class.getResource("/Ser.png"));
		ser = new JButton(LSimg);
		LSimg = new ImageIcon(MSXZ.class.getResource("/Cli.png"));
		cli = new JButton(LSimg);
		LSimg = new ImageIcon(MSXZ.class.getResource("/pink.png"));
		Return = new JButton(LSimg);
		bkgImg = new ImageIcon(MSXZ.class.getResource("/bk1.jpg"));
		bkgLab = new JLabel(bkgImg);
		title = new ImageIcon(MSXZ.class.getResource("/title.jpg"));
		Return.setBounds(5, 5, 40, 30);
		MS1.setBounds(60, 50, 100, 40);
		MS2.setBounds(60, 130, 100, 40);
		PK1.setBounds(60, 50, 100, 40);
		PK2.setBounds(60, 130, 100, 40);
		lev1.setBounds(60, 30, 100, 40);
		lev2.setBounds(60, 90, 100, 40);
		lev3.setBounds(60, 150, 100, 40);
		ser.setBounds(60, 70, 100, 40);
		cli.setBounds(60, 130, 100, 40);
		bkgLab.setSize(240,260);
		for(int i=0;i<img.length;i++) {
			imgImg = new ImageIcon(MSXZ.class.getResource("/img/"+i+".jpg"));
			img[i] = new JButton(imgImg);
		}
		img[0].setBounds(50, 70, 200, 200);
		img[1].setBounds(280, 70, 200, 200);
		img[2].setBounds(50, 320, 200, 200);
		img[3].setBounds(280, 320, 200, 200);
		timeLab = new JLabel("当前用时:");
		historyLab = new JLabel("历史记录:");
		historyLab.setBounds(310, 10, 90, 35);
		timeLab.setBounds(310, 45, 90,35);
		history = new JTextField();
		history.setBounds(377, 7, 100, 39);
		historyLab.setFont(font);
		timeLab.setFont(font);	
		history.setFont(font1);		
		history.setOpaque(false);	
		history.setBorder(null);
		history.setEditable(false);
	}
	public void PK_go() {
		ctn.removeAll();
		ctn.add(PK1);
		ctn.add(PK2);
		ctn.add(bkgLab,-1);
		ctn.repaint();
	}
	public void gotoMS() {
		ctn.removeAll();
		ctn.add(MS1);
		ctn.add(MS2);
		ctn.add(Return);
		ctn.add(bkgLab,-1);
		ctn.repaint();
	}
	public void gotoGQ()
	{
		bkgImg = new ImageIcon(MSXZ.class.getResource("/bk1.jpg"));
		bkgLab = new JLabel(bkgImg);
		bkgLab.setSize(240,260);
		ctn.removeAll();
		ctn.add(Return);
		ctn.add(lev1);
		ctn.add(lev2);
		ctn.add(lev3);
		ctn.add(bkgLab,-1);
		ctn.repaint();
		this.setSize(240, 260);
	}
	
	public void gotoIMG() {
		bkgImg = new ImageIcon(MSXZ.class.getResource("/bk2.jpg"));
		bkgLab = new JLabel(bkgImg);
		bkgLab.setSize(557,620);
		ctn.removeAll();
		for(int i=0;i<img.length;i++) {
			ctn.add(img[i]);
		}
		ctn.add(Return);
		ctn.add(bkgLab,-1);
		ctn.repaint();	
		this.setSize(557, 620);
		switch(N) {
		case 3:ss="简 单";break;
		case 4:ss="一 般";break;
		case 5:ss="困 难";break;
		}
		this.setTitle("拼图游戏  - "+ ss);
	}

	private void gotoPT1() {
		// TODO 自动生成的方法存根
		P1 pp1 = new P1();
		pp1.setBounds(30, 80, 480, 480);
		timeThread = new Time1();
		timeThread.start();
		time = new JTextField("00:00:00");
		time.setBounds(377, 41, 100, 39);
		time.setFont(font1);
		time.setOpaque(false);
		time.setBorder(null);
		time.setEditable(false);
		headpnl = new JPanel();
		headpnl.setBounds(0, 0, 557, 100);
		headpnl.setLayout(null);
		headpnl.setOpaque(false);
		String ss1 = "/img/"+picture+".jpg";
		String ss2 = "/img/"+picture+ "/" + picture +".jpg";
		preImg = new ImageIcon(MSXZ.class.getResource(ss1));
		viewImg = new ImageIcon(MSXZ.class.getResource(ss2));
		pre = new JLabel(preImg);
		view = new JLabel(viewImg);
		pre.setBounds(480, 15, 50, 50);
		view.setBounds(30,80,480,480);
		LSimg = new ImageIcon(MSXZ.class.getResource("/start1.png"));
		start = new JButton(LSimg);
		LSimg = new ImageIcon(MSXZ.class.getResource("/start2.png"));
		restart = new JButton(LSimg);
		start.setBounds(100, 15, 100, 45);
		restart.setBounds(100, 15, 100, 45);
		start.setVisible(true);
		restart.setVisible(false);
		readHis();
		start.addActionListener(e->{
			timeThread.startThread();
			timeThread.reset();			
			pp1.setVisible(true);
			pre.setVisible(true);
			Start = true;
			start.setVisible(false);
			restart.setVisible(true);
		});
		restart.addActionListener(e->{
			timeThread.stopThread();
			timeThread.reset();
			Start=false;
			gotoPT1();
			start.doClick();
		});
		pre.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				view.setVisible(true);
				pp1.setVisible(false);
			}
			public void mouseExited(MouseEvent e) {
				view.setVisible(false);
				pp1.setVisible(true);
			}
		});
		ctn.removeAll();
		ctn.add(headpnl);
		headpnl.add(time);
		headpnl.add(history);
		headpnl.add(timeLab);
		headpnl.add(historyLab);
		ctn.add(pp1);
		pre.setVisible(false);
		view.setVisible(false);
		pp1.setVisible(true);
		headpnl.add(pre);
		ctn.add(view);		
		headpnl.add(start);
		headpnl.add(restart);
		headpnl.add(Return);
		ctn.add(bkgLab,-1);
		ctn.repaint();
		String tt = null;
		switch(picture) {
		case "flog":tt=" - 妙蛙种子";break;
		case "girl":tt=" - 女孩";break;
		case "pika":tt=" - 皮卡丘";break;
		case "xyy":tt=" - 喜羊羊";break;
		}
		this.setTitle("拼图游戏  - "+ ss+tt);
	}
	public void gotoPT2(){
		this.setResizable(true);
		this.setSize(557, 780);
		this.setResizable(false);
		Panel1 p2 =  new Panel1();
		p2.setBounds(0, 80, 557, 700);
		timeThread = new Time1();
		timeThread.start();
		time = new JTextField("00:00:00");
		time.setBounds(377, 41, 100, 39);
		time.setFont(font1);
		time.setOpaque(false);
		time.setBorder(null);
		time.setEditable(false);
		headpnl = new JPanel();
		headpnl.setBounds(0, 0, 557, 100);
		headpnl.setLayout(null);
		headpnl.setOpaque(false);
		String ss2 = "/img/"+picture+ "/" + picture +".jpg";
		viewImg = new ImageIcon(MSXZ.class.getResource(ss2));
		view = new JLabel(viewImg);
		view.setBounds(30,80,480,480);
		LSimg = new ImageIcon(MSXZ.class.getResource("/start1.png"));
		start = new JButton(LSimg);
		LSimg = new ImageIcon(MSXZ.class.getResource("/start2.png"));
		restart = new JButton(LSimg);
		start.setBounds(100, 15, 100, 45);
		restart.setBounds(100, 15, 100, 45);
		restart.setVisible(false);
		start.setVisible(true);
		start.addActionListener(e->{
			timeThread.startThread();
			timeThread.reset();
			Start=true;
			start.setVisible(false);
			restart.setVisible(true);
		});
		restart.addActionListener(e->{
			timeThread.stopThread();
			timeThread.reset();
			Start=false;
			gotoPT2();
			start.doClick();
		});
		readHis();
		ctn.removeAll();
		ctn.add(headpnl);
		headpnl.add(time);
		headpnl.add(history);
		headpnl.add(timeLab);
		headpnl.add(historyLab);
		ctn.add(p2);	
		headpnl.add(start);
		headpnl.add(restart);
		headpnl.add(Return);
		ctn.add(bkgLab,-1);
		ctn.repaint();
		String tt = null;
		switch(picture) {
		case "flog":tt=" - 妙蛙种子";break;
		case "girl":tt=" - 女孩";break;
		case "pika":tt=" - 皮卡丘";break;
		case "xyy":tt=" - 喜羊羊";break;
		}
		this.setTitle("拼图游戏  - "+ ss+tt);
	}
	public void gotoConnect() {
		ctn.removeAll();
		ctn.add(ser);
		ctn.add(cli);
		ctn.add(bkgLab,-1);
		ctn.repaint();
	}
	public void readHis() {
		String fileUrl;
		if(MS) fileUrl = "D:/Users/16210/eclipse-workspace/KS1/bin/img/"+picture+"/"+N+"/sorce1.txt";
		else fileUrl = "D:/Users/16210/eclipse-workspace/KS1/bin/img/"+picture+"/"+N+"/sorce2.txt";
		FileInputStream fis;
		try {
			fis = new FileInputStream(fileUrl);
			InputStreamReader dis = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(dis);
			historyTime = br.readLine();
			history.setText(historyTime);
			br.close();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}	
	}
	public void addAction() {
		Return.addActionListener(e->{
			/*	if(ifSer) {
				try {
					Server.dos.writeUTF("Return");
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}*/
			switch(M) {
			case 0:{
				PK_go();
				break;
			}
			case 1:{
				gotoMS();
				break;
				
			}
			case 2:{
				this.setTitle("拼图游戏 ");
				gotoGQ();
				break;
			}
			case 3:{
				this.setTitle("拼图游戏  - "+ ss);
				gotoIMG();
				timeThread.stopThread();
				timeThread.reset();
				Start=false;
				break;
			}
			}
			M--;
		});
		PK1.addActionListener(e->{
			M=0;
			gotoMS();
		});
		PK2.addActionListener(e->{
			M=0;
			gotoConnect();
		});
		MS1.addActionListener(e->{
			MS=true;
			M=1;
			if(ifSer) {
				try {
					Server.dos.writeUTF("MS1");
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
			gotoGQ();			
		});
		MS2.addActionListener(e->{
			MS=false;
			M=1;
			if(ifSer) {
				try {
					Server.dos.writeUTF("MS2");
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
			gotoGQ();
		});

		lev1.addActionListener(e->{
			N=3;
			M=2;
			if(ifSer) {
				try {
					Server.dos.writeUTF("lev1");
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
			gotoIMG();		
		});
		lev2.addActionListener(e->{
			N=4;
			M=2;
			if(ifSer) {
				try {
					Server.dos.writeUTF("lev2");
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
			gotoIMG();
		});
		lev3.addActionListener(e->{
			N=5;
			M=2;
			if(ifSer) {
				try {
					Server.dos.writeUTF("lev3");
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
			gotoIMG();
		});
		
		img[0].addActionListener(e->{
			picture = "flog";
			M=3;
			if(ifSer) {
				try {
					Server.dos.writeUTF("img[0]");
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
			if(MS) gotoPT1();
			else {
				bkgImg = new ImageIcon(MSXZ.class.getResource("/gbk2.jpg"));
				bkgLab = new JLabel(bkgImg);
				bkgLab.setSize(557,780);
				gotoPT2();
			}
		});
		img[1].addActionListener(e->{
			picture = "girl";
			M=3;
			if(ifSer) {
				try {
					Server.dos.writeUTF("img[1]");
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
			if(MS) gotoPT1();
			else {
				bkgImg = new ImageIcon(MSXZ.class.getResource("/gbk2.jpg"));
				bkgLab = new JLabel(bkgImg);
				bkgLab.setSize(557,780);
				gotoPT2();
			}
		});
		img[2].addActionListener(e->{
			picture = "pika";
			M=3;
			if(ifSer) {
				try {
					Server.dos.writeUTF("img[2]");
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
			if(MS) gotoPT1();
			else {
				bkgImg = new ImageIcon(MSXZ.class.getResource("/gbk2.jpg"));
				bkgLab = new JLabel(bkgImg);
				bkgLab.setSize(557,780);
				gotoPT2();
			}
		});
		img[3].addActionListener(e->{
			picture = "xyy";
			M=3;
			if(ifSer) {
				try {
					Server.dos.writeUTF("img[3]");
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
			if(MS) gotoPT1();
			else {
				bkgImg = new ImageIcon(MSXZ.class.getResource("/gbk2.jpg"));
				bkgLab = new JLabel(bkgImg);
				bkgLab.setSize(557,780);
				gotoPT2();
			}
		});
		ser.addActionListener(e->{
			ifSer=true;
			Thread t;
			t = new Server();
			t.start();	
			gotoMS();
		});
		cli.addActionListener(e->{
			ifCli=true;
		    Thread t = new Client();
		    t.start();
		    gotoMS();
		});
	}
	
	public static void main(String[] args) {
		MSXZ ms = new MSXZ();
		ms.setVisible(true);
		ms.setSize(240, 260);
		ms.setResizable(false);
		ms.setTitle("拼图游戏");
		ms.setIconImage(ms.title.getImage());
		ms.setLocation(700, 150);
		ms.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}

