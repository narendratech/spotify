package com.umusic.track.service;

import java.util.List;
import java.util.Optional;

import com.umusic.tack.response.model.ISRCTrack;

public interface ITrackReadService {
	public List<ISRCTrack> findByArtistWildCard(String artistName);
	public ISRCTrack getByISRCTrack(String isrcCode);
}
