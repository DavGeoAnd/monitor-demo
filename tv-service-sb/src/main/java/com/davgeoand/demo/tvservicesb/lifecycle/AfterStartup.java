package com.davgeoand.demo.tvservicesb.lifecycle;


import com.davgeoand.demo.tvservicesb.helper.CommonAppProps;
import com.davgeoand.demo.tvservicesb.monitor.event.handler.AppEventHandler;
import com.davgeoand.demo.tvservicesb.monitor.event.type.AppStart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;

import java.lang.management.ManagementFactory;

@Configuration
public class AfterStartup {
    @Autowired
    CommonAppProps commonAppProps;
    @Autowired
    AppEventHandler appEventHandler;
    @Autowired
    Environment environment;

    @EventListener(ApplicationReadyEvent.class)
    public void afterStartupEvent(ApplicationReadyEvent applicationReadyEvent) {
        appEventHandler.sendEvent(AppStart.builder()
                .buildVersion(commonAppProps.getBuildVersion())
                .javaVersion(environment.getProperty("java.runtime.version"))
                .appStartTimestamp(ManagementFactory.getRuntimeMXBean().getStartTime())
                .appReadyTime(applicationReadyEvent.getTimeTaken().toMillis())
                .build());
    }
}
