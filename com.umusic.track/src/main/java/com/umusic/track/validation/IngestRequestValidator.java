package com.umusic.track.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;
import com.umusic.track.error.Error;
import com.umusic.track.error.ErrorCode;
import com.umusic.track.error.Severity;
import com.umusic.track.request.model.IngestionServiceRequest;


public class IngestRequestValidator {
	public List<Error> validateRequest(IngestionServiceRequest request){
		List<Error> errorList = new ArrayList<>();
		
		String isrc = request.getIsrc();
		if(StringUtils.isEmpty((isrc))) {
			Error error = new Error(ErrorCode.BAD_REQUEST_ISRC_EMPTY.getErrorCode(),"ISRC code cannot be null/empty",Severity.ERROR);
			errorList.add(error);
		}
		return errorList;
  }
}