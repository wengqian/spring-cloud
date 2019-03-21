package com.wq.microcore.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketTest {
	public static void main(){
		try{
			Socket so = new Socket("192.164.241.1",61402);
			String login_str ="";
			OutputStream out =so.getOutputStream();
			out.write(login_str.getBytes());

			InputStream in =so.getInputStream();
			
			byte[] buf = new byte[1024];
			int len = in.read(buf);
			String text = new String(buf,0,len);
			System.out.println(text);
			
			so.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	
	}
}
