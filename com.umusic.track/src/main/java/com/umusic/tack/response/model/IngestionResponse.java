package com.umusic.tack.response.model;

import java.util.List;

import com.umusic.track.wrappers.Status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngestionResponse {

	private String isrc;
	private String message;
}
