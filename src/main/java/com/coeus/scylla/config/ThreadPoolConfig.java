package com.coeus.scylla.config;

import com.coeus.scylla.ScyllaThreadPool;

import java.util.concurrent.TimeUnit;

public class ThreadPoolConfig {

    private Integer corePoolSize;
    private Integer maxPoolSize;
    private Long keepAliveTime;
    private TimeUnit timeUnit;


    public Integer getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(Integer corePoolSize) {
        if (corePoolSize == null){
            this.corePoolSize = ScyllaThreadPool.CORE_POOLSIZE;
        } else {
            this.corePoolSize = corePoolSize;
        }

    }

    public Integer getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(Integer maxPoolSize) {
        if (maxPoolSize == null){
            this.maxPoolSize = ScyllaThreadPool.MAX_POOLSIZE;
        } else {
            this.maxPoolSize = maxPoolSize;
        }

    }

    public Long getKeepAliveTime() {
        return keepAliveTime;
    }

    public void setKeepAliveTime(Integer keepAliveTime) {
        if (keepAliveTime == null){
            this.keepAliveTime = ScyllaThreadPool.KEEP_ALIVETIME;
        } else {
            this.keepAliveTime = Long.parseLong(keepAliveTime.toString());
        }
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(Object timeUnit) {
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