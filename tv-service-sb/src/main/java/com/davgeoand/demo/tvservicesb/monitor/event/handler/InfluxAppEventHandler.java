package com.davgeoand.demo.tvservicesb.monitor.event.handler;


import com.davgeoand.demo.tvservicesb.helper.CommonAppProps;
import com.davgeoand.demo.tvservicesb.monitor.event.type.Event;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.write.Point;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Slf4j
@Service
@ConditionalOnProperty(
        value = "app.event.handler.type",
        havingValue = "influx")
public class InfluxAppEventHandler implements AppEventHandler {
    private final InfluxDBClient influxDBClient;
    @Autowired
    CommonAppProps commonAppProps;

    private InfluxAppEventHandler(@Value("${app.event.handler.influx.uri}") String uri,
                                  @Value("${app.event.handler.influx.org}") String org,
                                  @Value("${app.event.handler.influx.bucket}") String bucket,
                                  @Value("${app.event.handler.influx.token}") String token) {
        influxDBClient = InfluxDBClientFactory.create(uri, token.toCharArray(), org, bucket);
    }

    @Override
    public void sendEvent(Event event) {
        event.setTime(Instant.now());
        Point eventPoint = event.toPoint();
        eventPoint.addTags(commonAppProps.getCommonMap());
        WriteApiBlocking writeApi = influxDBClient.getWriteApiBlocking();
        writeApi.writePoint(eventPoint);
    }
}
