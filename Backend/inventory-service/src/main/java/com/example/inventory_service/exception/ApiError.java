package com.example.inventory_service.exception;

import java.time.Instant;

public class ApiError {
    private boolean success;
    private String message;
    private Object data;
    private Instant timestamp;

    public ApiError() {}

    public ApiError(boolean success, String message, Object data, Instant timestamp) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.timestamp = timestamp;
    }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Object getData() { return data; }
    public void setData(Object data) { this.data = data; }

    public Instant getTimestamp() { return timestamp; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }
}