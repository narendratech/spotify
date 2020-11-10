package com.umusic.track.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.umusic.track.request.model.IngestionServiceRequest;
import com.umusic.track.response.model.TrackIngestionServiceResponse;
import com.umusic.track.service.TrackIngestionService;

@RestController
public class TrackIngestionController {
	@Autowired
	private TrackIngestionService ingestionService;
	
	@PostMapping(value = "/storeMusicTrack", consumes = "application/json", produces = "application/json")
	public TrackIngestionServiceResponse storeMusicTrack(@RequestBody IngestionServiceRequest request) {
		return ingestionService.ingest(request);
		
	}
}
