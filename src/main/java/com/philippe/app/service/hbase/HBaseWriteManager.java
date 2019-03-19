package com.philippe.app.service.hbase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class HBaseWriteManager implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(HBaseWriteManager.class);

    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    @Override
    public void run() {
        LOGGER.debug("Entering run...");
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
    }
}
