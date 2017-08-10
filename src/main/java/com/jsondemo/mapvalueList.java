package com.jsondemo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import com.alibaba.fastjson.JSON;

public class mapvalueList {
	
	public void parsemapvalueList() throws FileNotFoundException{
		Scanner sc = new Scanner(new File("src/main/java/com/jsondemo/mapvaluejson.txt"));
		StringBuffer sb = new StringBuffer(); 
		while(sc.hasNextLine()){
			sb.append(sc.nextLine());
//			System.out.println(sc.nextLine());
		}
		
		String txt = sb.toString();
		Map<String, Object> map = JSON.parseObject(txt);
		for(Entry<String, Object> entry:map.entrySet()){			
			String value = String.valueOf(entry.getValue());
			System.out.println(entry.getValue());
		}
		
		
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		mapvalueList main = new mapvalueList();
		main.parsemapvalueList();
	}
}
