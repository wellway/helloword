package helloword;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
/**
 * 长连接
 * 服务器UrlServerDemo.java
 * @author yalongz
 *
 */
public class SendPostDemo {
	  public static void main(String[] args) throws Exception{
	    String urlPath = new String("http://localhost:8081/chaogu/productmanager.do"); 
	    //String urlPath = new String("http://localhost:8080/Test1/HelloWorld?name=丁丁".getBytes("UTF-8"));
//	    String param="productName="+URLEncoder.encode("丁丁","UTF-8");
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("productName", "nihao");
	    map.put("userName", "Lily");
	    String param = JSON.toJSONString(map);
	    //建立连接
	    URL url=new URL(urlPath);
	    HttpURLConnection httpConn=(HttpURLConnection)url.openConnection();
	    //设置参数
	    httpConn.setDoOutput(true);   //需要输出
	    httpConn.setDoInput(true);   //需要输入
	    httpConn.setUseCaches(false);  //不允许缓存
	    httpConn.setRequestMethod("POST");   //设置POST方式连接
	    //设置请求属性
	    httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	    httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
	    httpConn.setRequestProperty("Charset", "UTF-8");
	    //连接,也可以不用明文connect，使用下面的httpConn.getOutputStream()会自动connect
	    httpConn.connect();
	    //建立输入流，向指向的URL传入参数
//	    DataOutputStream dos=new DataOutputStream(httpConn.getOutputStream());
	    PrintWriter pw = new PrintWriter(new BufferedOutputStream(httpConn.getOutputStream()));
	    pw.write(param);
	    pw.flush();
	    pw.close();
	    //获得响应状态
	    int resultCode=httpConn.getResponseCode();
	    if(HttpURLConnection.HTTP_OK==resultCode){
	      StringBuffer sb=new StringBuffer();
	      String readLine=new String();
	      BufferedReader responseReader=new BufferedReader(new InputStreamReader(httpConn.getInputStream(),"UTF-8"));
	      while((readLine=responseReader.readLine())!=null){
	        sb.append(readLine).append("\n");
	      }
	      responseReader.close();
	      System.out.println(sb.toString());
	    } 
	  }
	}