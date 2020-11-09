package com.umusic.track.error;

public enum ErrorCode {
	BAD_REQUEST_ISRC_EMPTY("400.PLAY_LIST_TRACKER.100"),
	ISRC_NOT_FOUND("404.PLAY_LIST_TRACKER.100"), 
	ARTIST_NOT_FOUND("404.PLAY_LIST_TRACKER.101"), 
	UNKNOWN_ERROR("500.PLAY_LIST_TRACKER.100");

	private final String errorCode;

	public String getErrorCode() {
		return errorCode;
	}

	private ErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
