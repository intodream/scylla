package com.coeus.scylla.executer;

import com.coeus.scylla.ScyllaThreadPool;
import com.coeus.scylla.config.PropertiesParser;
import com.coeus.scylla.config.ThreadPoolConfig;
import com.coeus.scylla.config.YmlParser;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * pool submit
 */
public class ScyllaPoolExecuter {

    protected static ExecutorService threadPool;

    static {
        ThreadPoolConfig config;
        InputStream resourceAsStream = ScyllaPoolExecuter.class.getResourceAsStream("/scylla.yml");
        if (resourceAsStream != null) {
            config = YmlParser.parse(YmlParser.loadYml(resourceAsStream));
        } else {
            resourceAsStream = ScyllaPoolExecuter.class.getResourceAsStream("/scylla.properties");
            config = PropertiesParser.parse(PropertiesParser.loadProperties(resourceAsStream));
        }
        threadPool = new ScyllaThreadPool(config.getCorePoolSize(), config.getMaxPoolSize(),
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
//    public static <T> List<Future<T>> submitAsList(List<ScyllaExecuter> executers) throws InterruptedException {
//        if (threadPool.isShutdown()){
//            throw new RuntimeException("Thread pool is shutdown");
//        }
//        return threadPool.invokeAll((Collection<? extends Callable<T>>) executers);
//    }

}