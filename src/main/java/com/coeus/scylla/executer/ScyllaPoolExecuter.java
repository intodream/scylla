package com.coeus.scylla.executer;

import com.coeus.scylla.ScyllaThreadPool;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * pool submit
 */
public class ScyllaPoolExecuter {

    protected static ExecutorService threadPool;

    static {
        threadPool = new ScyllaThreadPool();
    }

    @SuppressWarnings("unchecked")
    public Future submit(Object instance, Method method, Object[] params){
        return threadPool.submit(new ScyllaExecuter(instance, method, params));
    }

}
