package com.httpdemo.loadOrReadByHttp;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import com.httpdemo.entity.StockBasic;

public class LoadXlsByHttpTest {
	String httpUrl = "http://www.swsindex.com/downloadfiles.aspx?swindexcode=SwClass&type=530&columnid=8892";
	String localPath = "E:/secu.xls";
	String localht = "E:/SwClass1.xls";
	String file = "F:/document/richList/richListAfter.xls";

	/**
	 * 通过http下载读取webexcel
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@Test
	public void loadAndReadByHttp() throws IOException, ClassNotFoundException {
		LoadXlsByHttp load = new LoadXlsByHttp(httpUrl, localPath);
		load.createLoadFile();
		Map<String, List<StockBasic>> map = load.getIndustryBasicBysecu();
		for (Entry<String, List<StockBasic>> entry : map.entrySet()) {
			System.out.println(entry.getKey() + "==" + entry.getValue());
		}
	}

	/**
	 * 读取本地webexcel
	 * 
	 * @throws IOException
	 */
	@Test
	public void readLocal() throws IOException {
		LoadXlsByHttp load = new LoadXlsByHttp(localPath);
		Map<String, List<StockBasic>> map = load.getIndustryBasicBysecu();
		for (Entry<String, List<StockBasic>> entry : map.entrySet()) {
			System.out.println(entry.getKey() + "==" + entry.getValue());
		}
	}
}
