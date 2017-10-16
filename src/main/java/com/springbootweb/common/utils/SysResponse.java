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

	public SysResponse(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}

	public SysResponse(boolean success, Object data) {
		super();
		this.success = success;
		this.data = data;
	}

	public SysResponse(boolean success, String message, Object data) {
		super();
		this.success = success;
		this.message = message;
		this.data = data;
	}

	public SysResponse(long timeStamp, boolean success, String message, Object data) {
		super();
		this.timeStamp = timeStamp;
		this.success = success;
		this.message = message;
		this.data = data;
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
