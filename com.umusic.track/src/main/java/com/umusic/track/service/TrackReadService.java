package com.umusic.track.service;

import java.util.List;
import java.util.Optional;

import com.umusic.tack.response.model.ISRCTrack;
import com.umusic.track.repository.TrackReadRepository;

public class TrackReadService implements ITrackReadService  {

	private final TrackReadRepository readRepository;
	public TrackReadService(TrackReadRepository trackreadRepo) {
		this.readRepository = trackreadRepo;
	}
	@Override
	public List<ISRCTrack> findByArtistWildCard(String artistName) {
		List<ISRCTrack> isrcTrack = this.readRepository.findByArtistWildCard(artistName);
		readRepository.findByArtistWildCard(artistName.toLowerCase()).forEach(isrcTrack::add);
		return isrcTrack;
	}

	@Override
	public ISRCTrack getByISRCTrack(String isrcCode) {
		ISRCTrack track = this.readRepository.findAll().stream().filter(i->i.getIsrcCode().equals(isrcCode)).findFirst().get();
		return track;
	}
	
}
