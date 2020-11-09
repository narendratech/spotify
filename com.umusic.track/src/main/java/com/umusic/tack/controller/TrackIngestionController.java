package com.umusic.tack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.umusic.tack.response.model.TrackServiceResponse;
import com.umusic.track.request.model.IngestionServiceRequest;
import com.umusic.track.service.TrackIngestionService;

@RestController
public class TrackIngestionController {
	@Autowired
	private TrackIngestionService ingestionService;
	
	@PostMapping(value = "/storeMusicTrack", consumes = "application/json", produces = "application/json")
	public TrackServiceResponse storeMusicTrack(@RequestBody IngestionServiceRequest request) {
		ingestionService.ingest(request);
		return null;
	}
}
