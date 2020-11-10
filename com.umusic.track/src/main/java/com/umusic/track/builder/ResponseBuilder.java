package com.umusic.track.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.umusic.track.error.Error;
import com.umusic.track.error.ErrorCode;
import com.umusic.track.error.Severity;
import com.umusic.track.response.model.IngestionResponse;
import com.umusic.track.response.model.Item;
import com.umusic.track.response.model.TrackDetails;
import com.umusic.track.response.model.TrackIngestionServiceResponse;
import com.umusic.track.response.model.TrackReadServiceResponse;
import com.umusic.track.wrappers.Status;

public class ResponseBuilder {
	private static final String TRACK_NOT_FOUND = "Track Details not found";
	private static final String TRACK_DETAILS_INSERTED_SUCCESS = "Music Track details inserted successfully";
	private static final String UNKNOWN_INGESTION_ERROR_OCCURRED = "Error occurred while persisting track details";
	private static final String UNKNOWN_READ_ERROR_OCCURRED = "Error occurred while retrieving track details";
	private static final String TRACK_ISRC_CONFLICT= "Track ISRC Details are exists in database";
	
	public TrackIngestionServiceResponse trackIngestionBadRequestResponse(List<Error> errors) {
		return trackIngestionErrorResponse(errors, Status.BAD_REQUEST);
	}

	public TrackIngestionServiceResponse trackUnKnownErrorIngestionResponse() {
		List<Error> errors = new ArrayList<>();
		Error error = new Error(ErrorCode.UNKNOWN_ERROR.getErrorCode(), UNKNOWN_INGESTION_ERROR_OCCURRED,
				Severity.ERROR);
		errors.add(error);

		return trackIngestionErrorResponse(errors, Status.FAIL);
	}
	
	public TrackIngestionServiceResponse trackExistsIngestionResponse() {
		List<Error> errors = new ArrayList<>();
		Error error = new Error(ErrorCode.ISRC_CONFLICT.getErrorCode(), TRACK_ISRC_CONFLICT,
				Severity.ERROR);
		errors.add(error);

		return trackIngestionErrorResponse(errors, Status.FAIL);
	}

	public Optional<TrackIngestionServiceResponse> validateSpotifyResponse(Optional<Item> item) {
		TrackIngestionServiceResponse trackServiceResponse = null;
		if (item.get() == null || StringUtils.isEmpty(item.get().getName())
				|| CollectionUtils.isEmpty(item.get().getArtists()) || null == item.get().getAlbum()
				|| CollectionUtils.isEmpty(item.get().getAlbum().getImages())) {
			List<Error> errors = new ArrayList<>();
			Error error = new Error(ErrorCode.ISRC_NOT_FOUND.getErrorCode(), TRACK_NOT_FOUND, Severity.ERROR);
			errors.add(error);
			trackServiceResponse = trackIngestionErrorResponse(errors, Status.NOT_FOUND);
		}

		return Optional.ofNullable(trackServiceResponse);
	}

	public TrackIngestionServiceResponse trackIngestionResponse(String isrc) {
		IngestionResponse ingestionResponse = new IngestionResponse();
		ingestionResponse.setIsrc(isrc);
		ingestionResponse.setMessage(TRACK_DETAILS_INSERTED_SUCCESS);

		TrackIngestionServiceResponse trackServiceResponse = new TrackIngestionServiceResponse();
		trackServiceResponse.setStatus(Status.OK);
		trackServiceResponse.setErrors(new ArrayList<>());
		trackServiceResponse.setPayload(ingestionResponse);

		return trackServiceResponse;
	}

	private TrackIngestionServiceResponse trackIngestionErrorResponse(List<Error> errors, Status status) {
		TrackIngestionServiceResponse trackServiceResponse = new TrackIngestionServiceResponse();
		trackServiceResponse.setStatus(status);
		trackServiceResponse.setErrors(errors);
		trackServiceResponse.setPayload(null);

		return trackServiceResponse;
	}

	public TrackReadServiceResponse trackNotFoundReadResponse() {
		List<Error> errors = new ArrayList<>();
		Error error = new Error(ErrorCode.ISRC_NOT_FOUND.getErrorCode(), TRACK_NOT_FOUND, Severity.ERROR);
		errors.add(error);

		return trackReadErrorResponse(errors, Status.NOT_FOUND);
	}

	public TrackReadServiceResponse trackUnKnownErrorReadResponse() {
		List<Error> errors = new ArrayList<>();
		Error error = new Error(ErrorCode.UNKNOWN_ERROR.getErrorCode(), UNKNOWN_READ_ERROR_OCCURRED, Severity.ERROR);
		errors.add(error);

		return trackReadErrorResponse(errors, Status.FAIL);
	}

	public TrackReadServiceResponse trackReadResponse(List<TrackDetails> trackDetails) {
		TrackReadServiceResponse trackServiceResponse = new TrackReadServiceResponse();
		trackServiceResponse.setStatus(Status.OK);
		trackServiceResponse.setErrors(new ArrayList<>());
		trackServiceResponse.setPayload(trackDetails);

		return trackServiceResponse;
	}

	private TrackReadServiceResponse trackReadErrorResponse(List<Error> errors, Status status) {
		TrackReadServiceResponse trackServiceResponse = new TrackReadServiceResponse();
		trackServiceResponse.setStatus(status);
		trackServiceResponse.setErrors(errors);
		trackServiceResponse.setPayload(null);

		return trackServiceResponse;
	}
}
