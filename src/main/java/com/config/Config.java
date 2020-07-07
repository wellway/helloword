package com.config;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
	private static Config	instance	= new Config();

	private Config() {
		Init();
	}

	public static Config getCurrent() {
		return instance;
	}

	public String	start;
	public String	end;

	private void Init() {
		Properties prop = new Properties();
		InputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(
					"config.properties"));
			prop.load(in);

			start = prop.getProperty("start");
			end = prop.getProperty("end");
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		}
	}
}
