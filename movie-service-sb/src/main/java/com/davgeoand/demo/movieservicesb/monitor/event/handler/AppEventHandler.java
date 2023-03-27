package com.davgeoand.demo.movieservicesb.monitor.event.handler;

import com.davgeoand.demo.movieservicesb.monitor.event.type.Event;

public interface AppEventHandler {
    public void sendEvent(Event event);
}
