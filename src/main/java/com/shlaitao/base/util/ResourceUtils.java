package com.shlaitao.base.util;

import java.io.IOException;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;


public class ResourceUtils {

	public static Map propertyMap = new ConcurrentHashMap();

	public static void LoadResource(String fileName) throws IOException {
		ResourceBundle config = ResourceBundle.getBundle(fileName);

		propertyMap.put(fileName, config);

	}

	public static String getProperty(String fileName, String key) {
		ResourceBundle props = (ResourceBundle) propertyMap.get(fileName);
		try {
			if (props != null) {
				return props.getString(key);
			} else {

				LoadResource(fileName);

				return getProperty(fileName, key);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
