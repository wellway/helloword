package com.paresehtml;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
/**
 * 解析html
 * resource https://jsoup.org/cookbook/extracting-data/dom-navigation
 * @author yalongz
 *
 */
public class HTMLDocumentTest {
	@Test
	public void pareHtmlString() throws IOException {
		pareHtmlByString();
		pareHtmlByUrl();
		parseHtmlByFile();
	}

	/**
	 * 根据String 类型
	 */
	public void pareHtmlByString() {
		String html = "<html><head><title>First parse</title></head>"
				+ "<body><p>Parsed HTML into a doc.</p></body></html>";
		Document doc = Jsoup.parse(html);

		System.out.println(doc.title());
	}

	/**
	 * 解析url
	 * 
	 * @throws IOException
	 */
	public void pareHtmlByUrl() throws IOException {
		Document doc = Jsoup.connect("http://example.com/").get();
		String title = doc.title();

		System.out.println(title);
	}
	/**
	 * Load a Document from a File
	 * @throws IOException
	 */
	public void parseHtmlByFile() throws IOException{
		File input = new File("/tmp/input.html");
		Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
		System.out.println();
	}
}
