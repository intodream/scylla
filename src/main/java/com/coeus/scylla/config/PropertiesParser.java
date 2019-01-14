package com.coeus.scylla.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesParser {

    public static ThreadPoolConfig parse(Properties properties){
        ThreadPoolConfig config = new ThreadPoolConfig();
        String corePoolSize = properties.getProperty("scylla.corePoolSize");
        if (corePoolSize == null){
            config.setCorePoolSize(null);
        } else {
            config.setCorePoolSize(Integer.parseInt(corePoolSize));
        }
        String maxPoolSize = properties.getProperty("scylla.maxPoolSize");
        if (maxPoolSize == null){
            config.setMaxPoolSize(null);
        } else {
            config.setMaxPoolSize(Integer.parseInt(maxPoolSize));
        }
        String keepAliveTime = properties.getProperty("scylla.keepAliveTime");
        if (keepAliveTime == null){
            config.setKeepAliveTime(null);
        } else {
            config.setKeepAliveTime(Integer.parseInt(keepAliveTime));
        }
        String timeUnit = properties.getProperty("scylla.timeUnit");
        if (timeUnit == null){
            config.setTimeUnit(null);
        } else {
            config.setTimeUnit(timeUnit);
        }
        return config;
    }

    public static Properties loadProperties(InputStream inputStream){
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

}
