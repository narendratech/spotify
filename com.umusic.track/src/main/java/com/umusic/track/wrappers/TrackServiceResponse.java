package com.umusic.track.wrappers;

import java.util.List;

import com.umusic.track.error.Error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrackServiceResponse<T> {
	private Status status = Status.FAIL;
	private List<Error> errors;
	private T payload;
}
