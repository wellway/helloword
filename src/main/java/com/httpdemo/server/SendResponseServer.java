package com.httpdemo.server;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;

import com.httpdemo.entity.QuoteRecord;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

/**
 * 应用程序接收http请求
 * 
 * @author yalongz
 */

public class SendResponseServer {
	public static void main(String[] args) {
		InetSocketAddress addr = new InetSocketAddress(7001);
		HttpServer server = null;
		try {
			server = HttpServer.create(addr, 5);
		} catch (IOException e) {
			e.printStackTrace();
		}
		server.createContext("/serverListObject", new MyHandler2());
		server.setExecutor(Executors.newCachedThreadPool());
		server.start();
		
		System.out.println("Server is listening on port 7001");
	}

}

class MyHandler2 implements HttpHandler {

	public void handle(HttpExchange exchange) {
		try {
			Headers responseHeaders = exchange.getResponseHeaders();
			String requestMethod = exchange.getRequestMethod();
			// responseHeaders.set("Content-Type", "text/plain; charset=utf-8");
			String responseStr = "receive";
			int len = responseStr.getBytes().length;
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, len);
			OutputStream out = exchange.getResponseBody();
			BufferedOutputStream objOut = new BufferedOutputStream(out);
			objOut.write(responseStr.getBytes());
			objOut.flush();

			if (requestMethod.equalsIgnoreCase("GET")) {
				/**
				 * method get
				 */
				URI requestedUri = exchange.getRequestURI();
				String query = requestedUri.getRawQuery();

				OutputStream responseBody = exchange.getResponseBody();
				Headers requestHeaders = exchange.getRequestHeaders();
				Set<String> keySet = requestHeaders.keySet();
				Iterator<String> iter = keySet.iterator();
				while (iter.hasNext()) {
					String key = iter.next();
					List values = requestHeaders.get(key);
					String s = key + " = " + values.toString() + "\n";
					responseBody.write(s.getBytes());
				}
				responseBody.write("jdk自带轻量级http server例子".getBytes());
				responseBody.close();
			}
			if (requestMethod.equalsIgnoreCase("POST")) {
				/**
				 * method post
				 */

				InputStream resquestBody = exchange.getRequestBody();
				ObjectInputStream objInStream = new ObjectInputStream(
						resquestBody);
				Object obj = null;
				try {
					obj = objInStream.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}

				List<QuoteRecord> list = (List<QuoteRecord>) obj;
				System.out.println("receive...." + list);
				objInStream.close();
				resquestBody.close();
				objOut.close();
				out.close();
				exchange.close();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}