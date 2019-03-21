package com.wq.microcore.util;

import java.io.InputStream;
import java.util.Properties;

public class PropUtil {

	private static final Properties prop = loadPropertiesFile("setting.properties");

	private static Properties loadPropertiesFile(String filePath) {
		InputStream in;
		try {
			in = PropUtil.class.getClassLoader().getResourceAsStream(filePath);
			Properties p = new Properties();
			p.load(in);
			return p;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getString(String key,String defaultVal) {
		if (prop != null)
			return prop.getProperty(key);
		return defaultVal;
	}

	public static Integer getInteger(String key, int defaultVal) {
		if (prop != null) {
			String intStr = prop.getProperty(key);
			if (intStr != null && !"".equals(intStr)) {
				return Integer.parseInt(intStr);
			}
		}
		return defaultVal;
	}
	
	public static void main(String[] args) {
		
		System.out.println(PropUtil.getString("sorl.http.czrk", ""));
		
	}
}
