package com.coeus.scylla.config;

import java.util.concurrent.TimeUnit;

public class ScyllaConfig {

    private static final int DEFAULT_QUEUE_SIZE = 1;

    private Integer corePoolSize;
    private Integer maxPoolSize;
    private Long keepAliveTime;
    private TimeUnit timeUnit;
    private Integer queueSize;


    Integer getCorePoolSize() {
        return corePoolSize;
    }

    void setCorePoolSize(Integer corePoolSize) {
        if (corePoolSize == null){
            this.corePoolSize = ScyllaThreadPool.CORE_POOLSIZE;
        } else {
            this.corePoolSize = corePoolSize;
        }

    }

    Integer getMaxPoolSize() {
        return maxPoolSize;
    }

    void setMaxPoolSize(Integer maxPoolSize) {
        if (maxPoolSize == null){
            this.maxPoolSize = ScyllaThreadPool.MAX_POOLSIZE;
        } else {
            this.maxPoolSize = maxPoolSize;
        }

    }

    Long getKeepAliveTime() {
        return keepAliveTime;
    }

    void setKeepAliveTime(Integer keepAliveTime) {
        if (keepAliveTime == null){
            this.keepAliveTime = ScyllaThreadPool.KEEP_ALIVETIME;
        } else {
            this.keepAliveTime = Long.parseLong(keepAliveTime.toString());
        }
    }

    Integer getQueueSize() {
        return queueSize;
    }

    public void setQueueSize(Integer queueSize) {
        if (queueSize == null){
            this.queueSize = DEFAULT_QUEUE_SIZE;
        } else {
            this.queueSize = queueSize;
        }
    }

    TimeUnit getTimeUnit() {
        return timeUnit;
    }

    void setTimeUnit(Object timeUnit) {
        if (timeUnit == null){
            this.timeUnit = ScyllaThreadPool.TIME_UNIT;
        } else {
            switch (timeUnit.toString()) {
                case "SECONDS":
                    this.timeUnit = TimeUnit.SECONDS;
                    break;
                case "MILLISECONDS":
                    this.timeUnit = TimeUnit.MILLISECONDS;
                    break;
                case "HOURS":
                    this.timeUnit = TimeUnit.HOURS;
                    break;
                default:
                    this.timeUnit = TimeUnit.SECONDS;
            }
        }
    }
}
