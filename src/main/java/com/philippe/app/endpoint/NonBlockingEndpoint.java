package com.philippe.app.endpoint;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.ForkJoinPool;

@RestController
@Slf4j
public class NonBlockingEndpoint {

    @GetMapping(value = "/async-deferredresult", produces = "application/json")
    public DeferredResult<ResponseEntity<?>> handleRequest() {
        log.debug("Received async-deferredresult request");
        DeferredResult<ResponseEntity<?>> output = new DeferredResult<>();

        ForkJoinPool.commonPool().submit(() -> {
            log.debug("Processing in a separate thread");
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
            }
            output.setResult(ResponseEntity.ok("ok"));
        });

        log.debug("servlet thread freed");
        return output;
    }
}
