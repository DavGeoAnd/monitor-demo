package com.davgeoand.demo.tvservicesb.monitor.metric;

import com.davgeoand.demo.tvservicesb.helper.CommonAppProps;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class MicrometerConfig {
    @Autowired
    CommonAppProps commonAppProps;

    @Bean
    public MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        return (registry) -> registry.config().commonTags(commonAppProps.getCommonTags());
    }
}
