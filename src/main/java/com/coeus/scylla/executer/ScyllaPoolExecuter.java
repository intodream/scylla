package com.coeus.scylla.executer;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * pool submit
 */
public class ScyllaPoolExecuter {

    public static ExecutorService threadPool;

    @SuppressWarnings("unchecked")
    public static Future submit(Object instance, Method method, Object[] params) {
        if (threadPool.isShutdown()) {
            throw new RuntimeException("Thread pool is shutdown");
        }
        return submit(new ScyllaExecuter(instance, method, params));
    }

    public static Future submit(ScyllaExecuter executer){
        return threadPool.submit(executer);
    }

//    @SuppressWarnings("unchecked")
//    public static List<Future> submitAsList(List<? extends Callable<Object>> executers) throws InterruptedException {
//        if (threadPool.isShutdown()){
//            throw new RuntimeException("Thread pool is shutdown");
//        }
//
//    }

}