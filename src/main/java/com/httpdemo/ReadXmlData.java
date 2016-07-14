package com.httpdemo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;



public class ReadXmlData {
public static void main(String[] args){
	httpDownload("http://10.26.134.25:8083/file/Security.XML","d:/test.xml");
	}

public static boolean httpDownload(String httpUrl,String saveFile){  	      
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
		 
		 SAXReader saxReader = new SAXReader();
			saxReader.setEncoding("UTF-8");
			try {
				Document document = saxReader.read(inStream);
				Element users = document.getRootElement();
				Element sub = FindFirstElement(users, "SecurityInfo");
				SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
				String current=sdf.format(new Date());
				for (Iterator i = sub.elementIterator(); i.hasNext();) {
				
					Element user = (Element) i.next();
					Element stockCode = user.element("stockcode");
					
					Element StockName = user.element("name");
					Element marketType = user.element("marketType");
					Element securityType = user.element("securityType");
					Element countryCode = user.element("Countrycode");
					Element status = user.element("status");
					Element industryType = user.element("industryType");
					Element PFShareQty = user.element("PFShareQty");
					Element lstSectCode = user.element("LstSectCode");
					Element ZGB = user.element("ZGB");
					Element EPS = user.element("EPS");
					Element py = user.element("py");
					
					String marketCot=marketType.getText();
					String name=StockName.getText();
					String  stockCode1=stockCode.getText();
					int tradeDate1=Integer.parseInt(current);
					int securityType1=Integer.parseInt(securityType.getText());
					float pfShareQty1=Float.parseFloat(PFShareQty.getText());
					float  zgb1=Float.parseFloat(ZGB.getText());
					if(EPS.getText()!=null&&EPS.getText()!=""){
						float eps=Float.parseFloat(String.valueOf(EPS.getText()));
					}
					String py1=py.getText()==null?"":py.getText();
					int lstSectCode1=Integer.parseInt(lstSectCode.getText());
					
					
					
					System.out.println(name+"==="+stockCode1+"==="+tradeDate1+"=="+securityType1+"=="+pfShareQty1);
					}	
				
			} catch (DocumentException e) {
				e.printStackTrace();
			}finally{
				inStream.close();
			}
		
		 return true;  
		} catch (FileNotFoundException e) {  
			e.printStackTrace();  
			return false;  
		} catch (IOException e) {  
			 e.printStackTrace();  
			 return false;  
		}  
		} 

	public static Element FindFirstElement(Element parent, String elementName) {
		for (Iterator i = parent.elementIterator(); i.hasNext();) {
			Element user = (Element) i.next();
			if (user.getName().equals(elementName)) {
				return user;
			}
			Element element = FindFirstElement(user, elementName);
			if (element != null) {
				return element;
		}
	}
	return null;
	}
}


