package com.umusic.track.wrappers;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrackListServiceResponse<T> {
	private Status status = Status.FAIL;
	private List<Error> errors;
	private T payload;
}
