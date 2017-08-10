package com.pdf.wkhtml2pdf;

public class MainCore {
	public static void main(String[] args) {
		String path="http://blog.csdn.net";
		String pdfurl ="d:/test11.pdf";
		HtmlToPdf.convert(path, pdfurl);
	}
}
