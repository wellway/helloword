package com.mapdemo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.jsonentity.User;

/**
 * list<Object> ,Map<Object,Object> 深复制
 * 
 * @author qiqi
 *
 */
public class MapCloneTest {
	/**
	 * map 深复制
	 * 
	 * @author qiqi
	 *
	 */
	class customHashMap extends HashMap<Object, Object> {
		private static final long serialVersionUID = 1L;

		public customHashMap() {
			super();
		}

		public customHashMap(int initialCapacity) {
			super(initialCapacity);
		}

		public Object clone() {
			Map<Object, Object> target = new HashMap<Object, Object>();
			try {
				ByteArrayOutputStream byteout = null;
				ObjectOutputStream out = null;
				Object obj = null;
				for (Iterator<Object> keyIt = this.keySet().iterator(); keyIt
						.hasNext();) {
					Object key = keyIt.next();
					byteout = new ByteArrayOutputStream();
					out = new ObjectOutputStream(byteout);
					out.writeObject(this.get(key));
					ByteArrayInputStream bytein = new ByteArrayInputStream(
							byteout.toByteArray());
					ObjectInputStream in = new ObjectInputStream(bytein);
					obj = in.readObject();// 反序列化出来，就相当于将原来的值和地址（即引用）copy了一份
					target.put(key, obj);// 习惯上一般key是不做修改的，因此没有copy key的必要
					// ，倘若有必要则同样反序列化出来就行了（如果是String类型也无需反序列化，上面已经解释的很清楚了）
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return target;
		}
	}

	public static void main(String[] args) {
		User user1 = new User();
		user1.setId("11111111111111");
		User user2 = new User();
		user2.setId("22222222222222");
		customHashMap source = (new MapCloneTest()).new customHashMap();
		source.put("key1", user1);
		source.put("key2", user2);
		System.out.println(source);
		Map target = (Map) source.clone();
		User user = (User) target.get("key1");
		user.setId("xxxxxxxxxxxxxx");// 改变副本的值
		System.out.println(target);// 对比结果
		System.out.println(source);// 源，没有被修改
	}

	/**
	 * List深度clone
	 * 
	 * @param src
	 * @return
	 */
	public static ArrayList<Object> copyList(ArrayList<Object> src) {
		ArrayList<Object> list = null;
		try {
			ByteArrayOutputStream byteout = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(byteout);
			out.writeObject(src);
			ByteArrayInputStream bytein = new ByteArrayInputStream(
					byteout.toByteArray());
			ObjectInputStream in = new ObjectInputStream(bytein);
			list = (ArrayList<Object>) in.readObject();
		} catch (Exception e) {
			System.out.println("cache copy error !");
			e.printStackTrace();
		}
		return list;
	}
}
