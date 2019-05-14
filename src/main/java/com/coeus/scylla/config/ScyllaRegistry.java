package com.coeus.scylla.config;

import com.coeus.scylla.core.ScyllaAccumulation;
import com.coeus.scylla.executer.ScyllaPoolExecuter;

import java.io.InputStream;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * init singleton ScyllaConfig, scyllaQueue, threadPool
 */
public class ScyllaRegistry {

    static {
        ScyllaConfig config = ScyllaSingleton.INSTANCE.instance();
        ScyllaAccumulation.scyllaQueue = new LinkedBlockingQueue<>(config.getQueueSize());
        ScyllaPoolExecuter.threadPool = new ScyllaThreadPool(config.getCorePoolSize(), config.getMaxPoolSize(),
                config.getKeepAliveTime(), config.getTimeUnit());
    }

    private enum ScyllaSingleton {
        INSTANCE;

        private ScyllaConfig config;

        ScyllaSingleton(){
            //加载yml配置文件
            InputStream resourceAsStream = ScyllaPoolExecuter.class.getResourceAsStream("/scylla.yml");
            if (resourceAsStream != null) {
                config = YmlParser.parse(YmlParser.loadYml(resourceAsStream));
            } else {
                //加载properties配置文件
                resourceAsStream = ScyllaPoolExecuter.class.getResourceAsStream("/scylla.properties");
                if (resourceAsStream != null){
                    config = PropertiesParser.parse(PropertiesParser.loadProperties(resourceAsStream));
                }
            }
        }

        public ScyllaConfig instance(){
            return config;
        }
    }
}
