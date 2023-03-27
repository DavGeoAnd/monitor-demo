package com.davgeoand.demo.tvservicesb.monitor.event.handler;


import com.davgeoand.demo.tvservicesb.monitor.event.type.Event;

public interface AppEventHandler {
    public void sendEvent(Event event);
}
