package com.umusic.track.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Error {
	private ErrorCode errorCode;
	private String description;
	private String info;
	private Severity severity;
}