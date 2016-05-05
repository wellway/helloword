package com.jsonentity;

import java.util.Date;
import java.util.Random;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

public class Bar {
	public static SerializeConfig mapping = new SerializeConfig();
	private String barName;
	private int barAge;
	private Date barDate = new Date();
	static {
		mapping.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd"));
	}
	{
		Random r = new Random();
		barName = "sss_" + String.valueOf(r.nextFloat());
		barAge = r.nextInt();
	}

	public String getBarName() {
		return barName;
	}

	public void setBarName(String barName) {
		this.barName = barName;
	}

	public int getBarAge() {
		return barAge;
	}

	public void setBarAge(int barAge) {
		this.barAge = barAge;
	}

	public Date getBarDate() {
		return barDate;
	}

	public void setBarDate(Date barDate) {
		this.barDate = barDate;
	}

	@Override
	public String toString() {
		return "Bar{" + "barName='" + barName + '\'' + ", barAge=" + barAge
				+ ", barDate=" + barDate + '}';
	}

}