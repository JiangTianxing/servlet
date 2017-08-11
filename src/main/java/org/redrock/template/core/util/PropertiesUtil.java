package org.redrock.template.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 配置文件加载类
 */
public class PropertiesUtil {
    public static Properties getProperties(String filename) {
        Properties properties = null;
        try {
            InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(filename);
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
