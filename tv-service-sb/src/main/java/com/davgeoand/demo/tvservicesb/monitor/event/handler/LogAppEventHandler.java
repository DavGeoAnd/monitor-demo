package com.davgeoand.demo.tvservicesb.monitor.event.handler;

import com.davgeoand.demo.tvservicesb.monitor.event.type.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Slf4j
@Service
@ConditionalOnProperty(
        value = "app.event.handler.type",
        havingValue = "log",
        matchIfMissing = true)
public class LogAppEventHandler implements AppEventHandler {
    @Override
    public void sendEvent(Event event) {
        event.setTime(Instant.now());
        log.info(event.toString());
    }
}
