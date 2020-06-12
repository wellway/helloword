package com.mapdemo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapNullTest {
public static void main(String[] args) {
	Map<Object,String> map = new HashMap<Object,String>();
	String a =null;
	map.put(a, "11");
	map.put("", "12");
	System.out.println(map);
	System.out.println(map.get(""));
	System.out.println("=====================");
	ConcurrentHashMap<String, String> cmap = new ConcurrentHashMap<String, String>();
	cmap.put(a, "11");
	cmap.put("", "12");
	System.out.println(map);
	System.out.println(map.get(""));
}
}
