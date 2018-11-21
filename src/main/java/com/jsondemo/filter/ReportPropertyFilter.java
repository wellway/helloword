package com.jsondemo.filter;

import java.util.HashMap;

import com.alibaba.fastjson.serializer.*;

public class ReportPropertyFilter implements PropertyFilter {
	
	private HashMap<String, String> columnMaps;
	public ReportPropertyFilter(String[] columns)
	{
		columnMaps = new HashMap<String, String>();
		if(columns != null)
		{
			for(String str:columns)
			{
				String lowerStr = str.toLowerCase();
				if(!columnMaps.containsKey(lowerStr))
				{
					columnMaps.put(lowerStr, lowerStr);
				}
			}
		}
	}

	@Override
	public boolean apply(Object source, String name, Object value) {
		// TODO Auto-generated method stub
		return columnMaps.containsKey(name.toLowerCase());
	}

}
