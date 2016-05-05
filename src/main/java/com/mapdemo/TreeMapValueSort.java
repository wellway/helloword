package com.mapdemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * 所有的元素都必须能够根据所提供的比较器来进行比较
 * 
 * @author qiqi
 *
 */
public class TreeMapValueSort {
	public static void main(String[] args) {
		Map<String, String> map = new TreeMap<String, String>();
		map.put("d", "ddddd");
		map.put("b", "bbbbb");
		map.put("a", "aaaaa");
		map.put("c", "ccccc");

		// 这里将map.entrySet()转换成list
		List<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>(
				map.entrySet());
		// 然后通过比较器来实现排序
		Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
			// 升序排序
			public int compare(Entry<String, String> o1,
					Entry<String, String> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}

		});

		for (Map.Entry<String, String> mapping : list) {
			System.out.println(mapping.getKey() + ":" + mapping.getValue());
		}
	}
}
