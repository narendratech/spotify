package com.umusic.track.wrappers;

public enum Status {

	OK(200, "Request succeeded"), 
	BAD_REQUEST(400, "Bad Request"), 
	NOT_FOUND(404, "Not found"), 
	FAIL(500,"Server error");

	private int code;
	private final String description;

	public String getDescription() {
		return description;
	}

	public int getCode() {
		return code;
	}

	private Status(int code, String description) {
		this.description = description;
		this.code = code;
	}
}
