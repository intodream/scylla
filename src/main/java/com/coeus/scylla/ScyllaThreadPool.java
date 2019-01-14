package com.coeus.scylla;

import java.util.concurrent.*;

/**
 * ThreadPool
 */
public class ScyllaThreadPool extends ThreadPoolExecutor {

    public final static int CORE_POOLSIZE = 4;       //default core pool size
    public final static int MAX_POOLSIZE = 10;       //default max pool size
    public final static long KEEP_ALIVETIME = 30L;   //default keep aliveTime
    public final static TimeUnit TIME_UNIT = TimeUnit.SECONDS;     //default time unit

    public ScyllaThreadPool() {
        this(CORE_POOLSIZE, MAX_POOLSIZE, KEEP_ALIVETIME, TIME_UNIT, new LinkedBlockingQueue<>(),
                Executors.privilegedThreadFactory(), new ScyllaPolicy());
    }

    public ScyllaThreadPool(Integer corePoolSize, Integer maxPoolSize, Long keepAliveTime, TimeUnit timeUnit) {
        this(corePoolSize, maxPoolSize, keepAliveTime, timeUnit, new LinkedBlockingQueue<>(),
                Executors.privilegedThreadFactory(), new ScyllaPolicy());
    }

    private ScyllaThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                             BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    /**
     * threadPool saturated policy
     */
    public static class ScyllaPolicy implements RejectedExecutionHandler {

        public ScyllaPolicy() {
        }

        //饱和策略，先抛出异常，后期做处理，做重新提交
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            if (!executor.isShutdown()){

            }
            throw new RejectedExecutionException("Task " + r.toString() +
                    " rejected from " +
                    executor.toString());
        }
    }
}
