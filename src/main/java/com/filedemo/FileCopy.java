package com.filedemo;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class FileCopy{
	@Test
	public void copyFile() throws IOException{
		File srcFile= new File("");
		File destFile=new File("");
				
		FileUtils.copyFile(srcFile, destFile);
	}
	
	
	
}
