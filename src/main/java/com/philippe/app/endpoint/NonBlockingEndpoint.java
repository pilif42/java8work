package com.philippe.app.endpoint;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.ForkJoinPool;

@RestController
@Slf4j
public class NonBlockingEndpoint {

    private final static long TIME_OUT_IN_MILLISECONDS = 500l;

    @GetMapping(value = "/async-deferredresult", produces = "application/json")
    public DeferredResult<ResponseEntity<?>> handleRequest() {
        log.debug("Received async-deferredresult request");

        DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<>(TIME_OUT_IN_MILLISECONDS);
        deferredResult.onTimeout(() -> {
            deferredResult.setErrorResult(ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("Request timeout occurred."));
            log.info("Processing timed out.");
        });
        deferredResult.onCompletion(() -> log.info("Processing complete."));

        ForkJoinPool.commonPool().submit(() -> {
            log.debug("Processing in a separate thread");
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
            }
            deferredResult.setResult(ResponseEntity.ok("ok"));
        });

        log.debug("servlet thread freed");
        return deferredResult;
    }
}
