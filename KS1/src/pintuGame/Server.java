package pintuGame;

import java.net.*;
import java.io.*;

public class Server extends Thread{
	private ServerSocket ss;
	private Socket s;
	static DataInputStream dis;
	static DataOutputStream dos;
	String str;
	public Server(){
		this.setDaemon(true);
		try {
			ss= new ServerSocket(9469);
			s = ss.accept();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
	public void run() {
		while(true) {
			try {
				 dis = new DataInputStream(s.getInputStream());
//				 System.out.println(dis.readUTF());
				 dos = new DataOutputStream(s.getOutputStream());
//				 dos.writeUTF("лл�����ң�" + s.getLocalSocketAddress() + "\nGoodbye!");
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}	
	}
	public void close()
	{
		try {
			s.close();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
}
