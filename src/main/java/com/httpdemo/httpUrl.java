package com.httpdemo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class httpUrl {
	public static void main(String[] args) {
		httpDownload("http://table.finance.yahoo.com/table.csv?s=000001.sz","d:/test.csv");
	}
	public static boolean httpDownload(String httpUrl,String saveFile){  
	       // ���������ļ�  
	       int bytesum = 0;  
	       int byteread = 0;  
	  
	       URL url = null;  
	    try {  
	        url = new URL(httpUrl);  
	    } catch (MalformedURLException e1) {  
	        e1.printStackTrace();  
	        return false;  
	    }  
	 
       try {  
           URLConnection conn = url.openConnection();  
           InputStream inStream = conn.getInputStream();  
//           BufferedReader  buf = new BufferedReader(new BufferedInputStream(inStream));
           BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inStream));
           String str1=null;
           while((str1=bufferedReader.readLine())!=null){
        	   System.out.println(str1);
           }
//           FileOutputStream fs = new FileOutputStream(saveFile);  
////  
//           byte[] buffer = new byte[1204];  
//           while ((byteread = inStream.read(buffer)) != -1) {  
//               bytesum += byteread;  
//               String[] str=new String[bytesum];
//               System.out.println(str[0]);  
//               fs.write(buffer, 0, byteread);  
//           } 
           
         
           return true;  
       } catch (FileNotFoundException e) {  
           e.printStackTrace();  
           return false;  
       } catch (IOException e) {  
           e.printStackTrace();  
           return false;  
       }  
	   }  
}
