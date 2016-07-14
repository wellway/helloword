package com.httpdemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.KeyStore;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
/**
 * http://blog.chenxiaosheng.com/posts/2013-12-26/java-use-self_signed_certificate.html
 * @author qiqi
 *
 */
public class HttpsLoadCert {
	 public static void main(String[] args) throws Exception {

	        X509TrustManager sunJSSEX509TrustManager;
	        // 加载 Keytool 生成的证书文件
	        char[] passphrase;
	        String p = "changeit";
	        passphrase = p.toCharArray();
	        File file = new File("java.cnnic.cacert");
	        System.out.println("Loading KeyStore " + file + "...");
	        InputStream in = new FileInputStream(file);
	        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
	        ks.load(in, passphrase);
	        in.close();
	        // 构造 javax.net.ssl.TrustManager 对象
	        TrustManagerFactory tmf =
	        TrustManagerFactory.getInstance("SunX509", "SunJSSE");
	        tmf.init(ks);
	        TrustManager tms [] = tmf.getTrustManagers();
	        // 使用构造好的 TrustManager 访问相应的 https 站点
	        SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
	        sslContext.init(null, tms, new java.security.SecureRandom());
	        SSLSocketFactory ssf = sslContext.getSocketFactory();
	        URL myURL = new URL("https://replace.to.your.site.real.url/");
	        HttpsURLConnection httpsConn = (HttpsURLConnection) myURL.openConnection();
	        httpsConn.setSSLSocketFactory(ssf);
	        InputStreamReader insr = new InputStreamReader(httpsConn.getInputStream());
	        int respInt = insr.read();
	        while (respInt != -1) {
	            System.out.print((char) respInt);
	            respInt = insr.read();
	        }
	    }
}
