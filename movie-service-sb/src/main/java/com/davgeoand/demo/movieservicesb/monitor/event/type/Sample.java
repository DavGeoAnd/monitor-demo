package com.davgeoand.demo.movieservicesb.monitor.event.type;

import com.influxdb.client.write.Point;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Sample extends Event{
    private String message;

    @Override
    public Point toPoint() {
        return null;
    }
}
