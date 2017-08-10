package com.httpdemo.loadOrReadByHttp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.ws.ProtocolException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.httpdemo.entity.StockBasic;

public class LoadXlsByHttp {
	private String localPath;
	private byte[] by;
	private Document doc;

	public LoadXlsByHttp(String localPath) throws IOException {
		this.localPath = localPath;
		this.doc = Jsoup.parse(new File(localPath), "gbk");
	}

	public LoadXlsByHttp(String httpUrl, String localPath) throws IOException {
		this.localPath = localPath;
		URL url = new URL(httpUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		resourceProperty(conn);
		int responseCode = conn.getResponseCode();
		InputStream inStream = conn.getInputStream();

		this.by = toByteArray(inStream);
		InputStream in = new ByteArrayInputStream(this.by);
		this.doc = Jsoup.parse(in, "gbk", "");
		in.close();
		inStream.close();

	}

	/**
	 * 流转换为byte
	 * 
	 * @param in
	 * @return
	 * @throws IOException
	 */
	public byte[] toByteArray(InputStream in) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024 * 4];
		int n = 0;
		while ((n = in.read(buffer)) != -1) {
			out.write(buffer, 0, n);
		}
		return out.toByteArray();
	}

	/**
	 * 生成文件
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void createLoadFile() throws IOException, ClassNotFoundException {
		RandomAccessFile writer = new RandomAccessFile(this.localPath, "rw");
		writer.write(this.by);
		writer.close();
	}

	/**
	 * 生成对象
	 * 
	 */

	public Map<String, List<StockBasic>> getIndustryBasicBysecu()
			throws IOException {
		Map<String, List<StockBasic>> map = new HashMap<String, List<StockBasic>>();
		Elements trs = this.doc.select("table").select("tr");
		for (int i = 1; i < trs.size(); i++) {
			StockBasic stock = new StockBasic();
			Elements tds = trs.get(i).select("td");
			String industryName = tds.get(0).text();
			stock.setCode(tds.get(1).text());
			stock.setName(tds.get(2).text());
			if (map.containsKey(industryName)) {
				List<StockBasic> list = map.get(industryName);
				list.add(stock);
			} else {
				List<StockBasic> list = new ArrayList<StockBasic>();
				list.add(stock);
				map.put(industryName, list);
			}

		}
		return map;
	}

	/**
	 * 清空数据
	 */

	public void clear() {
		this.by = null;
	}

	private void resourceProperty(HttpURLConnection conn)
			throws ProtocolException, java.net.ProtocolException {
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
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setUseCaches(true);
		conn.setReadTimeout(6000);
	}

	public String getLocalPath() {
		return localPath;
	}

	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}

	public byte[] getBy() {
		return by;
	}

	public void setBy(byte[] by) {
		this.by = by;
	}
}
