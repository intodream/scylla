package com.coeus.scylla.config;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

/**
 * load yml config
 */
class YmlParser {

    @SuppressWarnings("unchecked")
    static ScyllaConfig parse(Map<String, Object> map){
        ScyllaConfig config = new ScyllaConfig();
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

    static Map<String,Object> loadYml(InputStream inputStream){
        Map<String,Object> result = null;
        if (inputStream != null){
            Yaml yaml = new Yaml();
            result = yaml.load(inputStream);
        }
        return result;
    }


}
