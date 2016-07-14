package com.httpdemo;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class HttpUtils {
	public static void main(String[] args) {
		try {
			get("http://mvms.yicai.com/api/analysts?sort=initial");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * post请求
	 * 
	 * @throws IOException
	 */
	public static String post(String urlStr, String json) throws IOException {
		URL url = new URL(urlStr);
		HttpURLConnection httpURLConnection = (HttpURLConnection) url
				.openConnection();

		httpURLConnection.setDoInput(true);
		httpURLConnection.setDoOutput(true); // 设置该连接是可以输出的
		httpURLConnection.setRequestMethod("POST"); // 设置请求方式
		httpURLConnection.setRequestProperty("charset", "utf-8");
		httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
		httpURLConnection.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded;charset=" + "utf-8");

		PrintWriter pw = new PrintWriter(new BufferedOutputStream(
				httpURLConnection.getOutputStream()));
		pw.write(json);
		pw.flush();
		pw.close();
		int code = httpURLConnection.getResponseCode();
		InputStream in = null;
		if (code == 200) {
			in = httpURLConnection.getInputStream();
		} else {
			in = httpURLConnection.getErrorStream();
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(in,
				"utf-8"));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) { // 读取数据
			sb.append(line + "\n");
		}

		return sb.toString();
	}

	/**
	 * 实例化一个java.net.URL对象；
	 * 通过URL对象的openConnection()方法得到一个java.net.URLConnection;
	 * 通过URLConnection对象的getInputStream()方法获得输入流； 读取输入流； 关闭资源。
	 * 
	 * @author yalongz "http://mvms.yicai.com/api/analysts?sort=initial"
	 * @throws IOException
	 */
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
		// System.out.println(decodeUnicode(sb.toString()));
		return decodeUnicode(sb.toString());
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

	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendGet(String url, String param, List<Integer> list) {

		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			HttpURLConnection connection = (HttpURLConnection) realUrl
					.openConnection();
			// HttpURLConnection httpUrlConnection = (HttpURLConnection)
			// connection;
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// connection.setRequestProperty("Accept-Charset",
			// "ISO-8859-1,utf-8;q=0.7,*;q=0.7");
			// connection.setRequestProperty("Content-Type",
			// "application/x-www-form-urlencoded");
			// connection.setRequestProperty("Accept-Language",
			// "en-us,en;q=0.5");
			// 建立实际的连接
			connection.connect();
			int httpCode = (Integer) connection.getResponseCode();
			list.add(httpCode);

			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}

			String line;
			in = httpCode == 200 ? new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "UTF-8"))
					: new BufferedReader(new InputStreamReader(
							connection.getErrorStream(), "UTF-8"));
			// String content=connection.get;
			// if(httpCode==200)
			//
			// // 定义 BufferedReader输入流来读取URL的响应
			// in = new BufferedReader(new InputStreamReader(
			// connection.getInputStream(),"utf-8"));
			//
			// else
			//
			// in = new BufferedReader(new InputStreamReader(
			// connection.getErrorStream(),"utf-8"));

			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, String param, List<Integer> list) {

		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			HttpURLConnection conn = (HttpURLConnection) realUrl
					.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	public static String sendHttpHeader(String url, String token,
			List<Integer> list) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			HttpURLConnection conn = (HttpURLConnection) realUrl
					.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setRequestProperty("Authorization:", "Bearer " + token);
			conn.setRequestProperty("Content-Type", "application/json");

			conn.connect();
			int httpCode = (Integer) conn.getResponseCode();
			list.add(httpCode);

			String line;
			in = httpCode == 200 ? new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "utf-8")) : new BufferedReader(
					new InputStreamReader(conn.getErrorStream(), "utf-8"));
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 GET 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		return result;
	}

}