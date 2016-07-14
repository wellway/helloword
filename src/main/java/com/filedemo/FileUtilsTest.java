package com.filedemo;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FileUtilsTest {
	private String basePath = null;

	@Before
	public void setUp() {
		basePath = System.getProperty("user.dir") + "\\file\\";
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * 拷贝文件
	 * 
	 * @throws IOException
	 */
	@Test
	public void testCopy() throws IOException {
		File srcFile = new File(basePath + "a.txt");
		File destFile = new File(basePath + "b.txt");
		FileUtils.copyFile(srcFile, destFile);
	}

	/**
	 * 删除文件
	 * 
	 * @throws IOException
	 */
	@Test
	public void testDelete() throws IOException {
		File delFile = new File(basePath + "b.txt");
		FileUtils.forceDelete(delFile);
		// FileUtils.forceMkdir(delFile);
	}

	/**
	 * 比较文件内容
	 * 
	 * @throws IOException
	 */
	@Test
	public void testCompareFile() throws IOException {
		File srcFile = new File(basePath + "a.txt");
		File destFile = new File(basePath + "b.txt");
		boolean result = FileUtils.contentEquals(srcFile, destFile);
		System.out.println(result);
	}

	/**
	 * 移动文件
	 * 
	 * @throws IOException
	 */
	@Test
	public void testMoveFile() throws IOException {
		File srcFile = new File(basePath + "b.txt");
		File destDir = new File(basePath + "move");
		FileUtils.moveToDirectory(srcFile, destDir, true);
	}

	/**
	 * 读取文件内容
	 * 
	 * @throws IOException
	 */
	@Test
	public void testRead() throws IOException {
		File srcFile = new File(basePath + "a.txt");
		String content = FileUtils.readFileToString(srcFile);
		List<String> contents = FileUtils.readLines(srcFile);
		System.out.println(content);
		System.out.println("******************");
		for (String string : contents) {
			System.out.println(string);
		}
	}

	/**
	 * 写入文件内容
	 * 
	 * @throws IOException
	 */
	@Test
	public void testWrite() throws IOException {
		File srcFile = new File(basePath + "a.txt");
		FileUtils.writeStringToFile(srcFile, "\nyes文件", true);
	}

}
