package com.httpdemo.client;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.httpdemo.entity.QuoteRecord;

public class SendResponseClient {
	public static void main(String[] args) {
		List<QuoteRecord> list = new ArrayList<QuoteRecord>();
		QuoteRecord quote = new QuoteRecord();
		quote.setAveragePrice(1.09);
		quote.setName("hello");
		quote.setCode("005");
		list.add(quote);
		while (true) {
			post(list, "http://127.0.0.1:7001/serverListObject");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * post数据
	 */
	public static void post(List<QuoteRecord> list, String postUrl) {
		HttpURLConnection httpURLConnection = null;
		try {
			URL url = new URL(postUrl);
			httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setDoInput(true);
			// 设置该连接是可以输出的
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setConnectTimeout(10000);
			httpURLConnection.setReadTimeout(10000);
			// 设置请求方式
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setRequestProperty("charset", "utf-8");
			// httpURLConnection.setRequestProperty("User-Agent", USER_AGENT);
			httpURLConnection.setRequestProperty("Accept-Language",
					"en-US,en;q=0.5");
			// Post 请求不能使用缓存
			httpURLConnection.setUseCaches(false);
			OutputStream outStrm = httpURLConnection.getOutputStream();
			// 现在通过输出流对象构建对象输出流对象，以实现输出可序列化的对象。
			ObjectOutputStream objOutputStrm = new ObjectOutputStream(outStrm);
			objOutputStrm.writeObject(list);

			// 刷新对象输出流
			objOutputStrm.flush();
			// objOutputStrm.close();
			int responseCode = httpURLConnection.getResponseCode();
			if (responseCode == 200) {
				InputStream is = httpURLConnection.getInputStream();
				int length = httpURLConnection.getContentLength();
				BufferedInputStream objInput = new BufferedInputStream(is);
				byte[] buf = new byte[length];
				int bj = objInput.read(buf, 0, length);
				objInput.close();
				is.close();
				System.out.println("===||" + new String(buf));
			} else {
				System.out.println("访问失败.." + responseCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (httpURLConnection != null) {
				httpURLConnection.disconnect();// 关闭连接
			}
		}
	}

	public static String get(String path) throws IOException {
		URL url = new URL(path);
		HttpURLConnection urlConnection = (HttpURLConnection) url
				.openConnection();// 打开连接
		int code = urlConnection.getResponseCode();
		InputStream in = null;
		if (code == 200) {
			in = urlConnection.getInputStream();
		} else {
			in = urlConnection.getErrorStream();
		}
		InputStreamReader reader = new InputStreamReader(in, "utf-8");
		BufferedReader br = new BufferedReader(reader); // 获取输入流
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line + "\n");
		}
		System.out.println(sb.toString());
		urlConnection.disconnect();
		return sb.toString();

	}
}