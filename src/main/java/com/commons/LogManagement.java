package com.commons;

import org.apache.log4j.Logger;

public class LogManagement {
	private static Logger log = Logger.getLogger("logger");
	public static void Error(String string, Exception ex, boolean b) {
		// TODO Auto-generated method stub
		log.error(string,ex);
	}

	public static void Info(String msg) {
		// TODO Auto-generated method stub
		log.info(msg);
		
	}

	public static void Error(String msg, boolean b) {
		// TODO Auto-generated method stub
		log.error(msg);
	}

	public static void Warning(String msg, Exception ex) {
		// TODO Auto-generated method stub
		log.warn(msg, ex);
	}

	public static void Warning(String msg) {
		log.warn(msg);
	}

}
