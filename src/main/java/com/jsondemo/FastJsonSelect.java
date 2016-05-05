package com.jsondemo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.jsonentity.Address;

public class FastJsonSelect {
	public void javaBeanPropertySelect() {
		Address address = new Address("广东省", "深圳市", "科苑南路", "580053");
		SimplePropertyPreFilter filter = new SimplePropertyPreFilter(
				Address.class, "province", "city","post");
		String jsonStu = JSON.toJSONString(address, filter);
		System.out.println("javaBeanPropertySelect==" + jsonStu);
	}

	public static void main(String[] args) {
		FastJsonSelect fast = new FastJsonSelect();
		//根据属性名称获取javaBean的属性值
		fast.javaBeanPropertySelect();
		
		
	}

}
