package com.philippe.app.service.hbase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class HBaseWriteManager implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(HBaseWriteManager.class);

    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private LinkedBlockingQueue queue;

    public HBaseWriteManager() {
        queue = new LinkedBlockingQueue(100);
    }

    public void write(List<String> putList) {
        // TODO This write method is the way to pass the HBase Puts to the HBaseWriteManager.
        // TODO Replace List<String> with List<org.apache.hadoop.hbase.client.Put>
        // TODO For each Put, queue.put(put)
    }

    @Override
    public void run() {
        LOGGER.debug("Entering run...");
        // TODO put = queue.take()
        // TODO this.mutator.mutate(put) where mutator is org.apache.hadoop.hbase.client.BufferedMutator
    }

    public void startScheduleTask() {
        final Runnable flushHBaseTask = () -> flushHBaseCache();
        scheduler.scheduleAtFixedRate(flushHBaseTask, 1L, 10L, TimeUnit.SECONDS);
    }

    public void shutdown() {
        LOGGER.debug("Shutting down...");
    }

    private void flushHBaseCache() {
        LOGGER.debug("Flushing the HBase cache...");
        // TODO this.mutator.flush
    }
}
