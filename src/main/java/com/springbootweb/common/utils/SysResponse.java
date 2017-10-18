package com.springbootweb.common.utils;

public class SysResponse {

    private long timeStamp = System.currentTimeMillis();
    private boolean success;
    private String message;
    private Object data;

    public SysResponse() {
        super();
    }

    public SysResponse(boolean success) {
        super();
        this.success = success;
    }

    public static SysResponse ok() {
        return new SysResponse(true);
    }

    public static SysResponse ok(String message) {
        SysResponse sysResponse = new SysResponse(true);
        sysResponse.setMessage(message);
        return sysResponse;
    }

    public static SysResponse ok(String message, Object data) {
        SysResponse sysResponse = new SysResponse(true);
        sysResponse.setMessage(message);
        sysResponse.setData(data);
        return sysResponse;
    }

    public static SysResponse fail() {
        return new SysResponse(false);
    }

    public static SysResponse fail(String message) {
        SysResponse sysResponse = new SysResponse(true);
        sysResponse.setMessage(message);
        return sysResponse;
    }

    public static SysResponse fail(String message, Object data) {
        SysResponse sysResponse = new SysResponse(true);
        sysResponse.setMessage(message);
        sysResponse.setData(data);
        return sysResponse;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
