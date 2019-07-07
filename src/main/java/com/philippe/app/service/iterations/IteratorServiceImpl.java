package com.philippe.app.service.iterations;

import com.philippe.app.service.strings.ManipulationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;

@Service
@Slf4j
public class IteratorServiceImpl implements IteratorService {

    @Autowired
    private ManipulationService manipulationService;

    @Override
    public void process(BlockingQueue<String> queue) {
        queue.forEach(str -> {
            log.debug("str is {}", str);
            manipulationService.transform(str);
            queue.remove(str);
        });
    }
}
