package com.mapdemo;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * TreeMap默认是升序的，如果我们需要改变排序方式，则需要使用比较器：Comparator。
 * Comparator可以对集合对象或者数组进行排序的比较器接口，实现该接口的public compare(T o1,To2)方法即可实现排序，
 * 该方法主要是根据第一个参数o1,小于、等于或者大于o2分别返回负整数、0或者正整数
 * 
 * @author qiqi
 *
 */
public class TreeMapKeySort {
	public static void main(String[] args) {
		Map<String, String> map = new TreeMap<String, String>(
				new Comparator<String>() {
					public int compare(String obj1, String obj2) {
						// 降序排序
						return obj2.compareTo(obj1);
					}
				});
		map.put("c", "ccccc");
		map.put("a", "aaaaa");
		map.put("b", "bbbbb");
		map.put("d", "ddddd");

		Set<String> keySet = map.keySet();
		Iterator<String> iter = keySet.iterator();
		while (iter.hasNext()) {
			String key = iter.next();
			System.out.println(key + ":" + map.get(key));
		}
	}
}