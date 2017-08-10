package com.gzip;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.zip.GZIPOutputStream;

public class GzipUtils {
	public static byte[] writeCompress(ArrayList<Object> list) {
		byte[] data = null;
		try {
			// 建立字节数组输出流
			ByteArrayOutputStream o = new ByteArrayOutputStream();
			// 建立gzip压缩输出流
			GZIPOutputStream gzout = new GZIPOutputStream(o);
			// 建立对象序列化输出流
			ObjectOutputStream out = new ObjectOutputStream(gzout);
			out.writeObject(list);
			out.flush();
			out.close();
			gzout.close();
			data = o.toByteArray();
			o.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	public static ArrayList<Object> readCompress(byte[] data) {
		ArrayList<Object> list = null;
		try {
			// 建立字节数组输入流
			ByteArrayInputStream i = new ByteArrayInputStream(data);
			// 建立gzip解压输入流
//			GZIPInputStream gzin = new GZIPInputStream(i);
			// 建立对象序列化输入流
			ObjectInputStream in = new ObjectInputStream(i);
			// 按制定类型还原对象
			list = (ArrayList) in.readObject();
			i.close();
//			gzin.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
