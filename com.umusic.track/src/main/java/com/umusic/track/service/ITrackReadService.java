package com.umusic.track.service;

import com.umusic.track.response.model.TrackReadServiceResponse;

public interface ITrackReadService {
	public TrackReadServiceResponse getTracksContainsByArtist(String artistName);
	public TrackReadServiceResponse getTrackByISRC(String isrc);
}
