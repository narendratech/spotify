package com.umusic.track.service;

import com.umusic.track.request.model.IngestionServiceRequest;

public interface ITrackIngestionService {
	public void ingest(IngestionServiceRequest ingestionRequest);
}
