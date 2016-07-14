package com.mapdemo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.junit.Test;

public class MapHandle {
	/*
	 * 从数组（通过hash值）取得链表头，然后通过equals比较key，如果相同，就覆盖老的值，并返回老的值。（该key在hashmap中已存在）
	 * 
	 * 否则新增一个entry，返回null。新增的元素为链表头，以前相同数组位置的挂在后面。
	 * 
	 * 另外：modCount是为了避免读取一批数据时，在循环读取的过程中发生了修改，就抛异常
	 * 
	 * if (modCount != expectedModCount) throw new
	 * ConcurrentModificationException();
	 */

	@Test
	public void MapputReturn() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("d", "ddddd");
		map.put("b", "bbbbb");
		map.put("a", "aaaaa");
		map.put("c", "ccccc");

		System.out.println("key exist return old value：" + map.put("c", 1111));
		System.out.println("key exist return old value：" + map.put("c", 22222));
		System.out.println("key not exist return null ：" + map.put("e", 1111));
	}

	@Test
	public void copyMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("d", "ddddd");
		map.put("b", "bbbbb");
		map.put("a", "aaaaa");
		map.put("c", "ccccc");

	}

	/**
	 * map的深copy 注释：map.putAll()为浅复制
	 * 
	 * @param src
	 * @return
	 */
	public Map<Object, Object> deepCloneMap(Map<Object, Object> src) {
		Map<Object, Object> des = new HashMap<Object, Object>();
		for (Iterator<Object> it = src.keySet().iterator(); it.hasNext();) {
			Object key = it.next();
			Object value = src.get(key);
			des.put(key, value);
			// des.put(key.clone(),value.clone());
		}
		return des;
	}
}
