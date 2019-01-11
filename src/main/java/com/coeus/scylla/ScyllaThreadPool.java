package com.coeus.scylla;

import java.util.concurrent.*;

/**
 * ThreadPool
 */
public class ScyllaThreadPool extends ThreadPoolExecutor {

    private final static int CORE_POOLSIZE = 4;       //default core pool size
    private final static int MAX_POOLSIZE = 10;       //default max pool size
    private final static long KEEP_ALIVETIME = 30L;   //default keep aliveTime

    public ScyllaThreadPool() {
        this(CORE_POOLSIZE, MAX_POOLSIZE, KEEP_ALIVETIME, TimeUnit.SECONDS, new LinkedBlockingQueue<>(),
                Executors.privilegedThreadFactory(), new ScyllaPolicy());
    }

    public ScyllaThreadPool(int corePoolSize, int maxPoolSize) {
        this(corePoolSize, maxPoolSize, KEEP_ALIVETIME, TimeUnit.SECONDS, new LinkedBlockingQueue<>(),
                Executors.privilegedThreadFactory(), new ScyllaPolicy());
    }

    public ScyllaThreadPool(long keepAliveTime, TimeUnit timeUnit) {
        this(CORE_POOLSIZE, MAX_POOLSIZE, keepAliveTime, timeUnit, new LinkedBlockingQueue<>(),
                Executors.privilegedThreadFactory(), new ScyllaPolicy());
    }

    public ScyllaThreadPool(int corePoolSize, int maxPoolSize, BlockingQueue<Runnable> workQueue) {
        this(corePoolSize, maxPoolSize, KEEP_ALIVETIME, TimeUnit.SECONDS, workQueue,
                Executors.privilegedThreadFactory(), new ScyllaPolicy());
    }

    public ScyllaThreadPool(int corePoolSize, int maxPoolSize, long keepAliveTime, TimeUnit timeUnit,
                            BlockingQueue<Runnable> workQueue) {
        this(corePoolSize, maxPoolSize, keepAliveTime, timeUnit, workQueue,
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
            throw new RejectedExecutionException("Task " + r.toString() +
                    " rejected from " +
                    executor.toString());
        }
    }
}
