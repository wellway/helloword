package com.httpdemo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import jxl.read.biff.BiffException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class LoadExcelByHttp {
	public static void main(String[] args) throws BiffException, IOException {
		String httpUrl = "http://www.swsindex.com/downloadfiles.aspx?swindexcode=SwClass&type=530&columnid=8892";
		String localPath = "E:/secu.xls";
		String localPathxls = "E:/SwClass1.xls";
		String file = "F:/document/richList/richListAfter.xls";
		String filecsv = "E:/test.csv";
		// readLocalExcel(localPath, localPath);
		 loadDowm(httpUrl, localPath);

//		gethtml(localPath);
	}

	private static void gethtml(String localPath) throws IOException {
		// Document doc = Jsoup.connect("http://www.gaojig.com/cr.html").get();
		Document doc = Jsoup.parse(new File(localPath), "gbk");
		Elements trs = doc.select("table").select("tr");
		for (int i = 0; i < trs.size(); i++) {
			Elements tds = trs.get(i).select("td");
			for (int j = 0; j < tds.size(); j++) {
				String text = tds.get(j).text();
				System.out.println(text);
			}
		}
	}

	private static void loadDowm(String httpUrl, String localPath)
			throws BiffException {
		URL url = null;

		try {
			url = new URL(httpUrl);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			resourceProperty(conn);
			Map<String, List<String>> map = conn.getHeaderFields();
			for (Entry<String, List<String>> entry : map.entrySet()) {
				System.out.println(entry.getKey() + "==" + entry.getValue());
			}
			int responseCode = conn.getResponseCode();
			InputStream inStream = conn.getInputStream();
			
			Document doc = Jsoup.parse(inStream, "gbk", "");
			Elements trs = doc.select("table").select("tr");
			for (int i = 0; i < trs.size(); i++) {
				Elements tds = trs.get(i).select("td");
				for (int j = 0; j < tds.size(); j++) {
					String text = tds.get(j).text();
					System.out.println(text);
				}
			}
			
			
			
			
			/*
			 * InputStream inStream = conn.getInputStream(); // Workbook wb =
			 * Workbook.getWorkbook(inStream); WorkbookSettings
			 * workbookSettings=new WorkbookSettings();
			 * //编码代码有：GB18030，UTF-8，ISO-8859-1 等
			 * workbookSettings.setEncoding("GBK"); //关键代码，解决中文乱码 Workbook wb =
			 * Workbook.getWorkbook(inStream, workbookSettings);
			 * 
			 * 
			 * for(int i=1;i<wb.getSheet(0).getRows();i++){
			 * 
			 * Cell[] cell = wb.getSheet(0).getRow(i);
			 * System.out.println(cell[0]
			 * .getContents()+"=="+cell[1].getContents(
			 * )+"=="+cell[2].getContents
			 * ()+"=="+cell[3].getContents()+"=="+cell[4].getContents());
			 * 
			 * } wb.close();
			 */

			/**
			 * 生成字符
			 */
			/*
			 * BufferedReader in; if (responseCode < 299) { in = new
			 * BufferedReader(new InputStreamReader( conn.getInputStream(),
			 * "gbk")); } else { in = new BufferedReader(new InputStreamReader(
			 * conn.getErrorStream())); } StringBuilder res = new
			 * StringBuilder(); String inputLine; while ((inputLine =
			 * in.readLine()) != null) { System.out.println(inputLine);
			 * res.append(inputLine); }
			 */
			// System.out.println(res.toString());

			/**
			 * RandomAccessFile 生成
			 */
			RandomAccessFile writer = new RandomAccessFile(localPath, "rw");
			int len = conn.getContentLength();
			System.out
					.println("len==" + len + "code=" + conn.getResponseCode());
			byte[] buffer = new byte[1024];
			int le = 0;
			while ((le = inStream.read(buffer)) != -1) {
				writer.write(buffer, 0, le);
			}
			writer.close();

			/**
			 * 用FileOutputStream生成
			 */
			/*
			 * FileOutputStream os = new FileOutputStream(localPath);
			 * InputStream is =conn.getInputStream(); byte[] b = new byte[1024];
			 * int len = 0; while((len=is.read(b))!=-1){ os.write(b,0,len); }
			 * os.close(); is.close();
			 */

		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void resourceProperty(HttpURLConnection conn)
			throws ProtocolException {
		conn.setRequestProperty("Cookie",
				"ASP.NET_SessionId=vdiysvmwax1hr5hjvf5qxuva");
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		conn.setRequestProperty("Content-Type",
				"Application/vnd.ms-excel; charset=GB2312");
		conn.setRequestProperty("Accept-Encoding", "gzip, deflate, sdch");
		conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
		conn.setRequestProperty("Connection", "keep-alive");
		conn.setRequestProperty("Host", "www.swsindex.com");
		conn.setRequestProperty("Proxy-Authorization",
				"Basic emhhb3lhbG9uZzozMTREVjg2ZXZu");
		conn.setRequestProperty("Referer",
				"http://www.swsindex.com/idx0530.aspx");
		conn.setRequestProperty(
				"User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36");
		// conn.setRequestProperty("Upgrade-Insecure-Requests", "1");
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setUseCaches(true);
		// conn.setReadTimeout(6000);
	}

	private static void readLocalExcel(String path, String targerPath) {
		File file = new File(path);
		try {
			InputStream input = new FileInputStream(file);
			RandomAccessFile writer = new RandomAccessFile(targerPath, "rw");
			int len = input.available();
			System.out.println("len==" + len);
			// byte[] by = new byte[len];
			// input.read(by);
			// writer.write(by);
			// writer.close();
			BufferedReader buffer = new BufferedReader(new InputStreamReader(
					input, "gbk"));
			String inputLine;
			while ((inputLine = buffer.readLine()) != null) {
				System.out.println(inputLine);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
