package com.cbt.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class ConfigurationReader {
    private static Properties configFile;

    static {
        String path = System.getProperty("user.dir");
        try {
            FileInputStream input = new FileInputStream(path + "/configuration.properties");
            configFile = new Properties();
            configFile.load(input);
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load properties file!");
        }

    }
        /**
         * This method returns property value from configuration.properties file
         * @param keyName property name
         * @return property value
         */
        public static String getProperty(String keyName) {
            return configFile.getProperty(keyName);
        }

    }

