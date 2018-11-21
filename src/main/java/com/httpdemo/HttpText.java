package com.httpdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HttpText {
	public static void main(String[] args) {
		HttpText tt = new HttpText();
		tt.test();
	}

	private void test() {
		URL url;
		try {
			url = new URL("http://qt.gtimg.cn/?q=stdunixtime,marketStat,r_hkHSI,r_hkHSCEI,r_hkHSCCI,usDJI,usIXIC,usINX");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//			resourceProperty(conn);
			int responseCode = conn.getResponseCode();
			InputStream inStream = conn.getInputStream();
			 BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inStream,"GBK"));
	           String str1=null;
	           int i=0;
	           while((str1=bufferedReader.readLine())!=null){
	        	   i++;
	        	   if(i<=2)
	        		   continue;
	        	   
	        	   String[] arr = str1.split("~");
	        	   System.out.println(arr[0]+"____"+arr[1]);
	           }
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void resourceProperty(HttpURLConnection conn)
			throws java.net.ProtocolException {
		
		
		conn.setRequestProperty("Cache-Control","max-age=0");
		conn.setRequestMethod("GET");
		conn.setRequestProperty("If-None-Match","1503472689-0");
		conn.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
		conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
		conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.101 Safari/537.36");
		conn.setRequestProperty("Host","qt.gtimg.cn");
		conn.setRequestProperty("Proxy-Upgrade-Insecure-Requests","1");
		conn.setRequestProperty("Connection","keep-alive");
		
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setUseCaches(true);
		conn.setReadTimeout(6000);
	}
}
