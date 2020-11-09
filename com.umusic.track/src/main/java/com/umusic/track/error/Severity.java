package com.umusic.track.error;

public enum Severity {
	INFO("Info"), 
	WARN("Warn"), 
	ERROR("Error");

	private final String description;

	private String getDescription() {
		return description;
	}

	private Severity(String description) {
		this.description = description;
	}
}
