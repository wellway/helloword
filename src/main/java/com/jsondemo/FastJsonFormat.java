package com.jsondemo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import com.jsonentity.Employee;

public class FastJsonFormat {
	/**
	 * 复杂json格式的转换
	 */
	 @Test 
	public  void ComplexSerialization() {  
		 System.out.println("=============dateFormate===============");
	      HashMap<String, Object> map = new HashMap<String, Object>();  
	      map.put("username", "zhangsan");  
	      map.put("age", 24);  
	      map.put("sex", "男");  
	        
	      //map集合  
	      HashMap<String, Object> temp = new HashMap<String, Object>();  
	      temp.put("name", "xiaohong");  
	      temp.put("age", "23");  
	      map.put("girlInfo", temp);  
	      
	      //list集合  
	      List<String> list = new ArrayList<String>();  
	      list.add("爬山");  
	      list.add("骑车");  
	      list.add("旅游");  
	      map.put("hobby", list);  
	        
	      /*JSON 序列化，默认序列化出的JSON字符串中键值对是使用双引号，如果需要单引号的JSON字符串， [eg:String jsonString = JSON.toJSONString(map,   SerializerFeature.UseSingleQuotes);] 
	       *fastjson序列化时可以选择的SerializerFeature有十几个属性，你可以按照自己的需要去选择使用。  
	       */  
	      String jsonStrDefault = JSON.toJSONString(map);  
	      System.out.println("jsonStrDefault=" + jsonStrDefault);  
	      String jsonStrFormat = JSON.toJSONString(map,   SerializerFeature.UseSingleQuotes);
	      System.out.println("jsonStrFormat=="+jsonStrFormat);
	      
	    }  
	/**
	 * 日期格式化
	 */
	 @Test 
	 public void dateFormate(){  
		 System.out.println("=============dateFormate===============");
	      Date date=new Date();    
	      //输出毫秒值  
	      System.out.println(JSON.toJSONString(date));  
	      //默认格式为yyyy-MM-dd HH:mm:ss    
	      System.out.println(JSON.toJSONString(date, SerializerFeature.WriteDateUseDateFormat));  
	      //根据自定义格式输出日期   
	      System.out.println(JSON.toJSONStringWithDateFormat(date, "yyyy-MM-dd", SerializerFeature.WriteDateUseDateFormat));  
	      System.out.println(JSON.toJSONStringWithDateFormat(date, "dd HH:mm:ss", SerializerFeature.WriteDateUseDateFormat));  

	    }  
	 
	//fastjson 日期处理 
	 @Test 
	 public void test7(){ 
		 System.out.println("=============test7===============");
	 Date date = new Date(); 

	 String dateStr = JSON.toJSONString(date); 
	 System.out.println(dateStr); 

	 String dateStr2 = JSON.toJSONStringWithDateFormat(date, "yyyy-MM-dd HH:mm:ss"); 
	 System.out.println(dateStr2); 
	 //序列化实体 
	 Employee emp = new Employee("001", "张三", 23, new Date()); 
	 //法一 
	 String empStr = JSON.toJSONStringWithDateFormat(emp, "yyyy-MM-dd HH:mm:ss"); 
	 System.out.println("empStr="+empStr); 
	 //法二 
	 String empStr2 = JSON.toJSONString(emp, SerializerFeature.WriteDateUseDateFormat); 
	 System.out.println("empStr2="+empStr2); 
	 //法三 
	 SerializeConfig config = new SerializeConfig(); 
	 config.put(Date.class, new SimpleDateFormatSerializer("yyyy年MM月dd日 HH时mm分ss秒")); 
	 String empStr3 = JSON.toJSONString(emp, config); 
	 System.out.println("empStr3="+empStr3); 
	 } 	
}