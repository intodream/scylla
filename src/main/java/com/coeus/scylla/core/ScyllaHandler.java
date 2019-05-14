package com.coeus.scylla.core;

import com.coeus.scylla.executer.ScyllaPoolExecuter;

public class ScyllaHandler extends Thread implements Runnable {

    @Override
    public void run() {
        while (true){
            ScyllaAccumulation.lock.lock();
            try {
                ScyllaAccumulation.condition.await();
                ScyllaPoolExecuter.submit(ScyllaAccumulation.getTask());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                ScyllaAccumulation.lock.unlock();
            }
        }
    }
}
