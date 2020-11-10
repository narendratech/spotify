package com.umusic.track.error;

public enum ErrorCode {
	BAD_REQUEST_ISRC_EMPTY("400.TRACK_ISRC"),
	ISRC_NOT_FOUND("404.TRACK_ISRC"), 
	UNKNOWN_ERROR("500.TRACK_ISRC"),
	ISRC_CONFLICT("409.TRACK_ISRC");
	
	private final String errorCode;

	public String getErrorCode() {
		return errorCode;
	}

	private ErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
