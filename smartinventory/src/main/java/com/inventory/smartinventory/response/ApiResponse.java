package com.inventory.smartinventory.response;

import java.time.LocalDateTime;

public class ApiResponse<T> {

    // NEW
    private boolean success;

    // NEW
    private String message;

    // NEW
    private T data;

    // NEW
    private LocalDateTime timestamp;

    // Default Constructor
    public ApiResponse() {
        this.timestamp = LocalDateTime.now();
    }

    // Parameterized Constructor
    public ApiResponse(boolean success,
                       String message,
                       T data) {

        this.success = success;
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

}