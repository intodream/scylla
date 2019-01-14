package com.coeus.scylla.config;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * load yml
 */
public class YmlParser {

    @SuppressWarnings("unchecked")
    public static ThreadPoolConfig parse(Map<String,Object> map){
        ThreadPoolConfig config = new ThreadPoolConfig();
        if (map != null){
            Map<String,Object> scylla = (Map<String,Object>) map.get("scylla");
            if (scylla != null){
                config.setCorePoolSize((Integer) scylla.get("corePoolSize"));
                config.setMaxPoolSize((Integer) scylla.get("maxPoolSize"));
                config.setKeepAliveTime((Integer) scylla.get("keepAliveTime"));
                config.setTimeUnit(scylla.get("timeUnit"));
            }
        }
        return config;
    }

    public static Map<String,Object> loadYml(InputStream inputStream){
        Map<String,Object> result = null;
        if (inputStream != null){
            Yaml yaml = new Yaml();
            result = yaml.load(inputStream);
        }
        return result;
    }


}
