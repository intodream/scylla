package com.coeus.scylla.core;

import com.coeus.scylla.config.ScyllaRegistry;
import com.coeus.scylla.executer.ScyllaExecuter;
import com.coeus.scylla.executer.ScyllaPoolExecuter;

import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ScyllaAccumulation {

    public static Queue<ScyllaExecuter> scyllaQueue;

    private static int retryTimes = 3;

    static final Lock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    public static void addTask(ScyllaExecuter scyllaExecuter){
        lock.lock();
        try{
            boolean success = scyllaQueue.offer(scyllaExecuter);
            //如果当前队列已满，异步重试poll
            if (!success){
                ScyllaPoolExecuter.threadPool.submit(() -> {
                    boolean offerFlag = false;
                    for (int i = 0;i < retryTimes;i++){
                        if (addTaskRetry(scyllaExecuter)){
                            offerFlag = true;
                            condition.signalAll();
                            break;
                        }
                    }
                    if (!offerFlag){
                        throw new RuntimeException("任务添加失败,队列已满无法添加");
                    }
                });
            } else {
                condition.signalAll();
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static ScyllaExecuter getTask(){
        return scyllaQueue.poll();
    }

    private static boolean addTaskRetry(ScyllaExecuter scyllaExecuter){
        if (!scyllaQueue.offer(scyllaExecuter)){
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

}
