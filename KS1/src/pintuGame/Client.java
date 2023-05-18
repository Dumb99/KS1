package pintuGame;

import java.net.*;
import java.io.*;

public class Client extends Thread{
	static String str;
	Socket s;
	public Client() {
		this.setDaemon(true);
		try {
			s = new Socket("127.0.0.1", 9469);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	public void run() {
	      while (true) {
			try {
				doclick doc = new doclick();
				doc.start();
				OutputStream os = s.getOutputStream();
				DataOutputStream dos = new DataOutputStream(os);
				dos.writeUTF("Hello from ");
				InputStream is = s.getInputStream();
				DataInputStream dis = new DataInputStream(is);
				str = dis.readUTF();
				doc.startThread();
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}
	public void close()
	{
		try {
			s.close();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
