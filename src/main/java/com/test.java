package com;

import com.time.TimeConverterUtil;

public class test {
	public static void main(String[] args) {
		try {
			//			TimeConverterUtil t = new TimeConverterUtil();
			String time = TimeConverterUtil.utc2Local("20181024 04:42:46", "yyyyMMdd HH:mm:ss");
			System.out.println(time);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
