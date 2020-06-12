package com.filedemo;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadBigFile {
	public static void main(String[] args) throws IOException {
		String filepath = "D:/log.log.2019-12-02";
		File file = new File(filepath);
		BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file));
		BufferedReader reader = new BufferedReader(new InputStreamReader(fis, "utf-8"), 5 * 1024 * 1024);// 用5M的缓冲读取文本文件  

		String line = "";
		long start = System.currentTimeMillis();
		while ((line = reader.readLine()) != null) {
//			System.out.println(line);
		}
		long end = System.currentTimeMillis();
		System.out.println("===================");
		System.out.println((end - start)/1000);
	}
}
