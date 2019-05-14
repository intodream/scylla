package com.coeus.scylla.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * load properties config
 */
class PropertiesParser {

    static ScyllaConfig parse(Properties properties){
        ScyllaConfig config = new ScyllaConfig();
        String corePoolSize = properties.getProperty("scylla.corePoolSize");
        String maxPoolSize = properties.getProperty("scylla.maxPoolSize");
        String keepAliveTime = properties.getProperty("scylla.keepAliveTime");
        String timeUnit = properties.getProperty("scylla.timeUnit");
        config.setCorePoolSize(corePoolSize == null ? null : Integer.parseInt(corePoolSize));
        config.setMaxPoolSize(maxPoolSize == null ? null : Integer.parseInt(maxPoolSize));
        config.setKeepAliveTime(keepAliveTime == null ? null : Integer.parseInt(keepAliveTime));
        config.setTimeUnit(timeUnit);
        return config;
    }

    static Properties loadProperties(InputStream inputStream){
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

}
