package com.httpdemo.client;



import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.httpdemo.entity.QuoteRecord;



public class QuoteClientDemo {
	public static void main(String[] args) {
//		Map<String,Student>  map = new HashMap<String,Student>();
//		Student qu = new Student();
//		qu.setIndex(1);
//		qu.setName("啊啊");
//		qu.setSecu("123");
//		map.put("test1", qu);
//		
//		String str = String.valueOf(map);
//		
//		 Map<String,Student>  map1=getMap(str);
//		System.out.println(str);
		post();
	}
	
	
	private static  void post(){
		OutputStream outStrm=null;
		ObjectOutputStream objOutputStrm=null;
		InputStream inStrm =null;
		ObjectInputStream objInput=null;
		try {
			URL url = new URL("http://127.0.0.1:8080/server");
		    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

		    httpURLConnection.setDoInput(true);
		    httpURLConnection.setDoOutput(true);        // 设置该连接是可以输出的
		    httpURLConnection.setRequestMethod("POST"); // 设置请求方式
		    httpURLConnection.setRequestProperty("charset", "utf-8");
		    outStrm = httpURLConnection.getOutputStream();
		 // 现在通过输出流对象构建对象输出流对象，以实现输出可序列化的对象。   
		    objOutputStrm = new ObjectOutputStream(outStrm);   
		    Map<String,QuoteRecord>  map = new HashMap<String,QuoteRecord>();
			QuoteRecord qu = new QuoteRecord();
			qu.index=1;
			qu.isTrade=false;
			qu.minute=123;
			qu.setName("中国");
			qu.setCode("100.sh");
			
			QuoteRecord qu1 = new QuoteRecord();
			qu1.index=2;
			qu1.isTrade=false;
			qu1.minute=12113;
			qu1.setName("中ww国");
			qu1.setCode("100ww.sh");
			map.put("test", qu);
			map.put("test1", qu1);
		   // 向对象输出流写出数据，这些数据将存到内存缓冲区中   
		    objOutputStrm.writeObject(map);   
		     
		   // 刷新对象输出流，将任何字节都写入潜在的流中（些处为ObjectOutputStream）   
		    objOutputStrm.flush();   		     
		   // 关闭流对象。此时，不能再向对象输出流写入任何数据，先前写入的数据存在于内存缓冲区中,   
		   // 在调用下边的getInputStream()函数时才把准备好的http请求正式发送到服务器   		    
		     
		   // 调用HttpURLConnection连接对象的getInputStream()函数,   
		   // 将内存缓冲区中封装好的完整的HTTP请求电文发送到服务端。   
		    inStrm = httpURLConnection.getInputStream(); // <===注意，实际发送请求的代码段就在这里   
		    objInput = new ObjectInputStream(inStrm);
		  // 上边的httpConn.getInputStream()方法已调用,本次HTTP请求已结束,下边向对象输出流的输出已无意义，   
		  // 既使对象输出流没有调用close()方法，下边的操作也不会向对象输出流写入任何数据.   
		  // 因此，要重新发送数据时需要重新创建连接、重新设参数、重新创建流对象、重新写数据、   
		  // 重新发送数据(至于是否不用重新这些操作需要再研究)
		    
		   byte by=objInput.readByte();
		   String receive = String.valueOf(by);
		   System.out.println(receive);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				objOutputStrm.close();
				objOutputStrm.close();
				inStrm.close();
				objInput.close();
			} catch (IOException e) {
				e.printStackTrace();
			}   
		}
	    
	}
	
	
}
