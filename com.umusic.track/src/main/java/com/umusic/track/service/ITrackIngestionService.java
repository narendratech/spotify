package com.umusic.track.service;

import com.umusic.track.request.model.IngestionServiceRequest;
import com.umusic.track.response.model.TrackIngestionServiceResponse;

public interface ITrackIngestionService {
 public TrackIngestionServiceResponse ingest(IngestionServiceRequest ingestionRequest);
}
