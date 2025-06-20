package com.fabien.logging;

import java.time.LocalDateTime;


public class LogEvent {
    private String serviceName;
    private String level;
    private String message;
    private LocalDateTime timestamp;

    public LogEvent(String serviceName, String level, String message) {
        this.serviceName = serviceName;
        this.level = level;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
