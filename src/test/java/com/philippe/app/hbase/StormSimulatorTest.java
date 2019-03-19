package com.philippe.app.hbase;

import com.philippe.app.service.hbase.HBaseWriteManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.String.format;

public class StormSimulatorTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(StormSimulatorTest.class);

    public static void main(String args[]) {
        HBaseWriteManager hBaseWriteManager = new HBaseWriteManager();
        Thread streamToHBaseThread = new Thread(hBaseWriteManager);
        streamToHBaseThread.setUncaughtExceptionHandler(getErrorHandler());

        LOGGER.debug("About to start thread to HBase...");
        streamToHBaseThread.start();
        Runtime.getRuntime().addShutdownHook(new Thread(hBaseWriteManager::shutdown));

        hBaseWriteManager.startScheduleTask();
    }

    private static Thread.UncaughtExceptionHandler getErrorHandler() {
        return (th, e) -> {
            String errorMsg = e.getMessage();
            LOGGER.debug(format("Unhandled exception caught - %", errorMsg), e);
        };
    }
}
