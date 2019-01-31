package com.philippe.app.service.invokeendpoint.impl;

import com.philippe.app.service.invokeendpoint.CallingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CallingServiceImpl implements CallingService {
    @Override
    public void invokeNonBlockingEndpoint() {
        log.debug("Entrance of invokeNonBlockingEndpoint");
        // TODO
    }
}
