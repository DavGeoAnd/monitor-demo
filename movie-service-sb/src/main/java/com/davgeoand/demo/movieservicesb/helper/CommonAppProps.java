package com.davgeoand.demo.movieservicesb.helper;

import io.micrometer.core.instrument.Tags;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

@Slf4j
@Component
public class CommonAppProps {
    @Autowired
    BuildProperties buildProperties;

    @Autowired
    Environment environment;

    String appName;
    String appEnv;
    String appHost;

    private CommonAppProps(BuildProperties buildProperties,
                           Environment environment) {
        this.buildProperties = buildProperties;
        this.environment = environment;
        this.appName = buildProperties.getName();
        this.appEnv = environment.getProperty("app.environment");
        try {
            this.appHost = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            log.warn("Couldn't get host. Setting to localhost");
            this.appHost = "localhost";
        }
    }

    public String getAppName() {
        return buildProperties.getName();
    }

    public String getBuildVersion() {
        return buildProperties.getVersion();
    }

    public Tags getCommonTags() {
        log.info("Common tags: appName - {} / appEnv - {} / appHost - {}", appName, appEnv, appHost);
        return Tags.of("appName", appName, "appEnv", appEnv, "appHost", appHost);
    }

    public Map<String, String> getCommonMap() {
        return Map.of("appName", appName, "appEnv", appEnv, "appHost", appHost);
    }
}
