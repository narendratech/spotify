package com.umusic.tack.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.util.StringUtils;
import com.umusic.track.error.Error;
import com.umusic.track.error.ErrorCode;
import com.umusic.track.error.Severity;
import com.umusic.track.request.model.IngestionServiceRequest;



public class IngestionRequestValidator {
	public Optional<List<Error>> validateRequest(IngestionServiceRequest request){
		List<Error> errorList = null;
		String isrc = request.getIsrc();
		if(StringUtils.isEmpty((isrc))) {
			errorList = new ArrayList<>();
			Error error = new Error();
			error.setErrorCode(ErrorCode.BAD_REQUEST_ISRC_EMPTY);
			error.setDescription("ISRC code cannot be null/empty");
			error.setSeverity(Severity.ERROR);
			errorList.add(error);
		}
		return Optional.ofNullable(errorList);
  }
}