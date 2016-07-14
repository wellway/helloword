package com.httpdemo.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
/**
 *	应用程序接收http请求
 * @author yalongz
 * 
 * JDK6 提供了一个简单的Http Server API,据此我们可以构建自己的嵌入式Http Server,
 * 它支持Http和Https协议,提供了HTTP1.1的部分实现，没有被实现的那部分可以通过扩展已有的Http Server API来实现,
 * 程序员必须自己实现HttpHandler接口,HttpServer会调用HttpHandler实现类的回调方法来处理客户端请求,在 这里,
 * 我们把一个Http请求和它的响应称为一个交换,包装成HttpExchange类,HttpServer负责将HttpExchange传给 HttpHandler实现类的回调方法. 
 *
 */
//jdk自带轻量级http server例子  http://127.0.0.1:8080/server  
public class UrlServerDemo {  
    public static void main(String[] args) throws IOException {  
    	//设置端口
        InetSocketAddress addr = new InetSocketAddress(8081);  
        //create(addr,num)num 表示队列能接受num个请求。如果队列的请求超过num个，就不再加入队列，而是直接断开连接。
        HttpServer server = HttpServer.create(addr, 5);  
  
        server.createContext("/server", new MyHandler());  
      //线程控制
       // server.setExecutor(Executors.newCachedThreadPool());  
        server.setExecutor(Executors.newFixedThreadPool(5));
        server.start();  
        System.out.println("Server is listening on port 8081");  
    }  
}  
  
class MyHandler implements HttpHandler {  
    public void handle(HttpExchange exchange) throws IOException { 
    	
    	 Headers responseHeaders = exchange.getResponseHeaders();  
        String requestMethod = exchange.getRequestMethod();  
        responseHeaders.set("Content-Type", "text/plain; charset=utf-8");  

        exchange.sendResponseHeaders(200, 0);  
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
            Map<String, Object> parameters = 
                (Map<String, Object>)exchange.getAttribute("parameters"); 
            
            InputStream input = exchange.getRequestBody();
            ObjectInputStream objInput = new ObjectInputStream(input);
            try {
				Object obj = objInput.readObject();
				System.out.println(obj);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			
           /* InputStreamReader isr = 
                new InputStreamReader(input,"utf-8");                     
            BufferedReader br = new BufferedReader(isr); 
            String query=null;
            while((query=br.readLine())!=null){
            	System.out.println(query);
            }*/
        }
    }  
}  