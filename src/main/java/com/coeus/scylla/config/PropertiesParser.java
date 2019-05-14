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
        String queueSize = properties.getProperty("scylla.queueSize");
        config.setCorePoolSize(corePoolSize == null ? null : Integer.valueOf(corePoolSize));
        config.setMaxPoolSize(maxPoolSize == null ? null : Integer.valueOf(maxPoolSize));
        config.setKeepAliveTime(keepAliveTime == null ? null : Integer.valueOf(keepAliveTime));
        config.setTimeUnit(timeUnit);
        config.setQueueSize(queueSize == null ? null : Integer.valueOf(queueSize));
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
