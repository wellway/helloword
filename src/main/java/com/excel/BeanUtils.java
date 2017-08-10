package com.excel;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;

public class BeanUtils {

	/**
	 * 获取单元格的值
	 * 
	 * @param cell
	 * @return
	 */
	public static String getCellValue(Cell cell) {
		DecimalFormat format = new DecimalFormat("0.#######");
		if (cell == null)
			return "";
		if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
			return cell.getStringCellValue();
		} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(cell.getBooleanCellValue());
		} else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
			// System.out.println(cell.getCellFormula() );
			// System.out.println(String.valueOf(cell.getNumericCellValue()));
			return String.valueOf(cell.getNumericCellValue());

		} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			String value = null;
			double d = cell.getNumericCellValue();
			if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
				SimpleDateFormat sdf = null;
				if (cell.getCellStyle().getDataFormat() == HSSFDataFormat
						.getBuiltinFormat("h:mm")) {
					sdf = new SimpleDateFormat("HH:mm");
				} else {// 日期
					sdf = new SimpleDateFormat("yyyy-MM-dd");
				}
				Date date = cell.getDateCellValue();
				return sdf.format(date);
			} else if (cell.getCellStyle().getDataFormat() == 58) {
				// 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
				SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
				Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(d);
				return sdf.format(date);
			} else if (cell.getCellStyle().getDataFormat() == 14
					|| cell.getCellStyle().getDataFormat() == 31
					|| cell.getCellStyle().getDataFormat() == 57) {
				// 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(d);
				return sdf.format(date);
			} else if (cell.getCellStyle().getDataFormatString().indexOf("%") != -1) {
				return format.format(cell.getNumericCellValue() * 100) + "%";
			} else {
				Pattern pattern2 = Pattern
						.compile("\\d+\\.\\d+$|-\\d+\\.\\d+$");// 判断是否为小数
				Matcher isNum = pattern2.matcher(String.valueOf(d));
				CellStyle style = cell.getCellStyle();

				String temp = style.getDataFormatString();

				// 单元格设置成常规
				if (temp != null && temp.equals("General")) {
					if (!isNum.matches()) {
						BigDecimal bd = new BigDecimal(d);
						return format.format(d);
						// return String.valueOf(bd.toString());
					} else {
						return format.format(d);
					}
				} else {
					return format.format(cell.getNumericCellValue());
				}
			}
		}
		return "";
	}
}