package org.afTestNG.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertiesFile {

	private static ReadPropertiesFile instance = null;

	private static Properties property;

	private ReadPropertiesFile() {
		property = new Properties();
		try {
			property.load(new FileInputStream(new File("src/test/resources/config.properties")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ReadPropertiesFile getInstance() {
		if (instance == null)
			instance = new ReadPropertiesFile();
		return instance;
	}

	public String getProperty(String key) {
		return property.getProperty(key);
	}
}
