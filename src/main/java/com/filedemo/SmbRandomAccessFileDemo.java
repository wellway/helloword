package com.filedemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbRandomAccessFile;

public class SmbRandomAccessFileDemo {
	public static void main(String[] args) throws IOException {
		String domainip = "10.26.132.17";
		String username = "datashare";
		String password = "CBN@123";
		String secuUrl = "smb://" + domainip + "/data/txt/mktdtsz00.txt";
		NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(
				domainip, username, password); // 先登录验证
		try {

			SmbFile smbSecuFile = new SmbFile(secuUrl, auth);
			if (!smbSecuFile.exists())
				smbSecuFile.createNewFile();

			File file = new File("E:/mktdtsz00.txt");

			FileInputStream in = new FileInputStream(file);
			byte[] buffer = new byte[in.available()];
			in.read(buffer);
			SmbRandomAccessFile writer = new SmbRandomAccessFile(smbSecuFile,
					"rw");
			writer.write(buffer);
			in.close();
			writer.close();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
