package com.philippe.app.service.iterations;

import java.util.concurrent.BlockingQueue;

public interface IteratorService {
    void process(BlockingQueue<String> queue);
}
