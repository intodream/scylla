package com.coeus.scylla.core;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class ScyllaAccumulation {

    public static Queue scyllaCache = new LinkedBlockingQueue(5000);


}
