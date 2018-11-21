package com.jsondemo.filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.jsonentity.Address;
import com.jsonentity.TagRec;

public class FilterObjListProperty {
	public static void main(String[] args) {
		FilterObjListProperty filter = new FilterObjListProperty();
		List<Address> list = new ArrayList<Address>();
		List<TagRec> rsiList = new ArrayList<TagRec>();
		Address address = new Address("113","nihao","ff","555");
		TagRec tag1 = new TagRec("1",1,"rsi金叉");
		TagRec tag2 = new TagRec("2",0,"rsi死叉");
		rsiList.add(tag1);
		rsiList.add(tag2);
		address.setKdj(rsiList);
	//	list.add(address);
		HashMap<String, String> dict = new HashMap<String, String>();
		dict.put("column", "province,city,kdj");
		filter.getMsgByNeedColumn(dict, address);
	}
	
	
	protected static Map<String, Object> getMsgByNeedColumn(HashMap<String, String> dict, Object rec) {
		String[] result = null;
		if (dict.containsKey("column")) {
			String allcolumn = dict.get("column");
			result = allcolumn.split(",");
		}
		ReportPropertyFilter filter = new ReportPropertyFilter(result);
		String msg;
		if (result != null && !"".equals(result)) {
			SerializeWriter sw = new SerializeWriter();
			JSONSerializer serialize = new JSONSerializer(sw);
			serialize.getPropertyFilters().add(filter);
			serialize.write(rec);
			msg = sw.toString();
		} else {
			msg = JSON.toJSONString(rec);
		}
		Map<String, Object> listMap = JSON.parseObject(msg,
				new TypeReference<Map<String, Object>>() {
				});
		System.out.println("==="+listMap);
		return listMap;
		
	}
}
