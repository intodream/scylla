package com.coeus.scylla.executer;

import com.coeus.scylla.ScyllaThreadPool;
import com.coeus.scylla.config.PropertiesParser;
import com.coeus.scylla.config.ThreadPoolConfig;
import com.coeus.scylla.config.YmlParser;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * pool submit
 */
public class ScyllaPoolExecuter {

    public static ExecutorService threadPool;

    static {
        ThreadPoolConfig config = null;

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

        //如果没有加载到配置，则使用默认配置
        threadPool = config == null ? new ScyllaThreadPool() : new ScyllaThreadPool(config.getCorePoolSize(), config.getMaxPoolSize(),
                config.getKeepAliveTime(), config.getTimeUnit());
    }

    @SuppressWarnings("unchecked")
    static Future submit(Object instance, Method method, Object[] params) {
        if (threadPool.isShutdown()) {
            throw new RuntimeException("Thread pool is shutdown");
        }
        return threadPool.submit(new ScyllaExecuter(instance, method, params));
    }

//    @SuppressWarnings("unchecked")
//    public static List<Future> submitAsList(List<? extends Callable<Object>> executers) throws InterruptedException {
//        if (threadPool.isShutdown()){
//            throw new RuntimeException("Thread pool is shutdown");
//        }
//
//    }

}