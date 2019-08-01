package com.example.demo.bean;

import java.util.Map;

public class EventData {
    private String eventName;
    private Map<String, Object> data;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
