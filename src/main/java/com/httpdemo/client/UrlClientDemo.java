package com.httpdemo.client;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class UrlClientDemo {
	public static void main(String[] args) {
		//get();
		post();
	}
	/**
	 * post请求
	 */
	private static  void post(){
		try {
			URL url = new URL("http://127.0.0.1:8081/server");
		    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

		    httpURLConnection.setDoInput(true);
		    httpURLConnection.setDoOutput(true);        // 设置该连接是可以输出的
		    httpURLConnection.setRequestMethod("POST"); // 设置请求方式
		    httpURLConnection.setRequestProperty("charset", "utf-8");

		    PrintWriter pw = new PrintWriter(new BufferedOutputStream(httpURLConnection.getOutputStream()));
		    Map dataMap = new HashMap();
			dataMap.put(1, 12);
			dataMap.put(2, "sf");
			dataMap.put(3, "wer");
			
			String str = String.valueOf(dataMap);
		    pw.write(str);
		    
//		    pw.write("name=welcome");                   // 向连接中输出数据（相当于发送数据给服务器）
//		    pw.write("&age=14");
		   
		    pw.flush();
		    pw.close();

		    BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(),"utf-8"));
		    String line = null;
		    StringBuilder sb = new StringBuilder();
		    while ((line = br.readLine()) != null) {    // 读取数据
		        sb.append(line + "\n");
		    }
		    
		    System.out.println(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	}
	/**
	 * 实例化一个java.net.URL对象；
	 * 	通过URL对象的openConnection()方法得到一个java.net.URLConnection;
	 * 	通过URLConnection对象的getInputStream()方法获得输入流；
	 * 	读取输入流；
	 * 	关闭资源。
	 * @author yalongz
	 *
	 */
	private static void get(){
		URLConnection urlConnection=null;
		try {
			URL url = new URL("http://mvms.yicai.com/api/analysts?sort=initial");
		    urlConnection = url.openConnection();                                                    // 打开连接
		    BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"utf-8")); // 获取输入流
		    String line = null;
		    StringBuilder sb = new StringBuilder();
		    while ((line = br.readLine()) != null) {
		        sb.append(line + "\n");
		    }

		    System.out.println(decodeUnicode(sb.toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}	    
	}
	
	public static String decodeUnicode(String theString) {      
		   
	    char aChar;      
	   
	     int len = theString.length();      
	   
	    StringBuffer outBuffer = new StringBuffer(len);      
	   
	    for (int x = 0; x < len;) {      
	   
	     aChar = theString.charAt(x++);      
	   
	     if (aChar == '\\') {      
	   
	      aChar = theString.charAt(x++);      
	   
	      if (aChar == 'u') {      
	   
	       // Read the xxxx      
	   
	       int value = 0;      
	   
	       for (int i = 0; i < 4; i++) {      
	   
	        aChar = theString.charAt(x++);      
	   
	        switch (aChar) {      
	   
	        case '0':      
	   
	        case '1':      
	   
	        case '2':      
	   
	        case '3':      
	   
	       case '4':      
	   
	        case '5':      
	   
	         case '6':      
	          case '7':      
	          case '8':      
	          case '9':      
	           value = (value << 4) + aChar - '0';      
	           break;      
	          case 'a':      
	          case 'b':      
	          case 'c':      
	          case 'd':      
	          case 'e':      
	          case 'f':      
	           value = (value << 4) + 10 + aChar - 'a';      
	          break;      
	          case 'A':      
	          case 'B':      
	          case 'C':      
	          case 'D':      
	          case 'E':      
	          case 'F':      
	           value = (value << 4) + 10 + aChar - 'A';      
	           break;      
	          default:      
	           throw new IllegalArgumentException(      
	             "Malformed   \\uxxxx   encoding.");      
	          }      
	   
	        }      
	         outBuffer.append((char) value);      
	        } else {      
	         if (aChar == 't')      
	          aChar = '\t';      
	         else if (aChar == 'r')      
	          aChar = '\r';      
	   
	         else if (aChar == 'n')      
	   
	          aChar = '\n';      
	   
	         else if (aChar == 'f')      
	   
	          aChar = '\f';      
	   
	         outBuffer.append(aChar);      
	   
	        }      
	   
	       } else     
	   
	       outBuffer.append(aChar);      
	   
	      }      
	   
	      return outBuffer.toString();      
	   
	     }     
}
