package com.excel;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
/**
 * 读取exce文件
 * @author qiqi
 *
 */
public class ReadExcelTest {
	@Test
	public void readExcel() {

		String file = "F:/document/richList/richListAfter.xls";

		File dir = new File(file);
		String fileName = dir.getName();

		StringBuffer sb = new StringBuffer();
		BufferedInputStream inStream = null;
		Workbook workbook = null;
		if (fileName.endsWith(".xlsx")) {
			try {
				workbook = new XSSFWorkbook(new BufferedInputStream(
						new FileInputStream(dir)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (fileName.endsWith(".xls")) {
			try {
				inStream = new BufferedInputStream(new FileInputStream(dir));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			try {
				workbook = new HSSFWorkbook(inStream);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		int sheets = workbook.getNumberOfSheets();

		Sheet sheet = workbook.getSheetAt(0);
		int rows = sheet.getPhysicalNumberOfRows();
		for (int r = 2; r <= rows; r++) {// 读取所有行
			Row row = sheet.getRow(r);
			if (row == null) {
				continue;
			}
			Cell cell0 = row.getCell(0);
			String merge_sh_name = BeanUtils.getCellValue(cell0);
			Cell cell3 = row.getCell(1);
			// 子股东名称
			String sh_name = BeanUtils.getCellValue(cell3);
			Cell cell4 = row.getCell(2);
			String sh_rate = BeanUtils.getCellValue(cell4);
			System.out.println(merge_sh_name + "     " + sh_name + "       "+ sh_rate);
		}
	}
}