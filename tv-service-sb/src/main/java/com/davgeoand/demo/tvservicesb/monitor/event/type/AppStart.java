package com.davgeoand.demo.tvservicesb.monitor.event.type;

import com.influxdb.client.write.Point;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder()
public class AppStart extends Event {
    private String buildVersion;
    private String javaVersion;
    private long appStartTimestamp;
    private long appReadyTime;

    @Override
    public Point toPoint() {
        return Point.measurement("appStart")
                .addField("buildVersion", buildVersion)
                .addField("javaVersion", javaVersion)
                .addField("appStartTimestamp", appStartTimestamp)
                .addField("appReadyTime", appReadyTime);
    }
}
