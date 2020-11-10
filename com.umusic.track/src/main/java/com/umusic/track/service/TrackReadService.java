package com.umusic.track.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.umusic.track.builder.ResponseBuilder;
import com.umusic.track.domain.TrackDetailsDO;
import com.umusic.track.mappers.TrackMapper;
import com.umusic.track.repository.ITrackReadRepository;
import com.umusic.track.response.model.TrackDetails;
import com.umusic.track.response.model.TrackReadServiceResponse;

public class TrackReadService implements ITrackReadService  {

	private final ITrackReadRepository readRepository;
	private final ResponseBuilder responseBuilder;
	private final TrackMapper trackMapper;
	public TrackReadService(ITrackReadRepository readRepository,TrackMapper trackMapper,
			ResponseBuilder responseBuilder) {
		this.readRepository = readRepository;
		this.responseBuilder = responseBuilder;
		this.trackMapper = trackMapper;
	}
	@Override
	public TrackReadServiceResponse getTracksContainsByArtist(String artistName) {
		TrackReadServiceResponse trackReadServiceResponse= null;
		try {
		  List<TrackDetailsDO> trackDetailsDO = this.readRepository.findTracksByContainsArtist(artistName);
		  List<TrackDetails> trackDetails = transformDOToTrack(trackDetailsDO);
		  trackReadServiceResponse = responseBuilder.trackReadResponse(trackDetails);
		}
		catch(Exception ex) {
			trackReadServiceResponse = responseBuilder.trackUnKnownErrorReadResponse();
		}
		return trackReadServiceResponse;
	}

	@Override
	public TrackReadServiceResponse getTrackByISRC(String isrc) {
		TrackReadServiceResponse trackReadServiceResponse= null;
		try {
		  TrackDetailsDO trackDetailsDO =this.readRepository.findAll().stream().filter(i->i.getIsrcCode().equals(isrc)).findFirst().get();
		  if(trackDetailsDO!=null) {
			  TrackDetails trackDetail = trackMapper.transformDOToTrack((trackDetailsDO));
			  List<TrackDetails> trackDetails = new ArrayList<>();
			  trackDetails.add(trackDetail);
			  trackReadServiceResponse = responseBuilder.trackReadResponse(trackDetails);
		  }
		  else
		  {
			  trackReadServiceResponse = responseBuilder.trackNotFoundReadResponse();
		  }
		  
		}
		catch(Exception ex) {
			trackReadServiceResponse = responseBuilder.trackUnKnownErrorReadResponse();
		}
		return trackReadServiceResponse;
	}
	
	private List<TrackDetails> transformDOToTrack(List<TrackDetailsDO> trackDetailsDOList) {
		List<TrackDetails> trackDetails = new ArrayList<>();

		for (TrackDetailsDO trackDetailsDO : trackDetailsDOList) {
			trackDetails.add(trackMapper.transformDOToTrack((trackDetailsDO)));
		}

		return trackDetails;
	}
	
}
