package com.umusic.track.service;

import com.umusic.track.builder.ResponseBuilder;
import com.umusic.track.domain.TrackDetailsDO;
import com.umusic.track.mappers.TrackMapper;
import com.umusic.track.repository.ITrackIngestionRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.util.CollectionUtils;

import com.umusic.track.error.Error;
import com.umusic.track.request.model.IngestionServiceRequest;
import com.umusic.track.response.model.TrackDetails;
import com.umusic.track.response.model.TrackIngestionServiceResponse;
import com.umusic.track.response.model.Item;
import com.umusic.track.response.model.MusicTrack;

import com.umusic.track.validation.IngestRequestValidator;


public class TrackIngestionService implements ITrackIngestionService {

	private final IngestRequestValidator ingestionRequestValidator;
	private final SpotifyTrackService spotifyTrackService;
	private final TrackMapper trackMapper;
	private final ITrackIngestionRepository trackIngetionRepository;
	private final ResponseBuilder responseBuilder;

	public TrackIngestionService(IngestRequestValidator ingestionRequestValidator, ITrackIngestionRepository trackIngetionRepository,
			SpotifyTrackService spotifyTrackService,TrackMapper trackMusicMapper,ResponseBuilder responseBuilder) {
		this.ingestionRequestValidator = ingestionRequestValidator;
		this.spotifyTrackService = spotifyTrackService;
		this.trackMapper = trackMusicMapper;
		this.trackIngetionRepository = trackIngetionRepository;
		this.responseBuilder = responseBuilder;
  	}

	@Override
	public TrackIngestionServiceResponse ingest(IngestionServiceRequest ingestionRequest) {
		try {
			 List<Error> errors =  this.ingestionRequestValidator.validateRequest(ingestionRequest);
			 if(!CollectionUtils.isEmpty(errors)) {
				 return responseBuilder.trackIngestionBadRequestResponse(errors);
			  }
			String isrc = ingestionRequest.getIsrc();
			TrackDetails trackDetails =  findTrackByISRC(isrc);
			if(trackDetails!=null) {
				return responseBuilder.trackExistsIngestionResponse();
			}
			String token = spotifyTrackService.getspotifyAccessToken();
			MusicTrack musicTrack = spotifyTrackService.getspotifyByISRSC(ingestionRequest.getIsrc(), token);
			Optional<Item> item = musicTrack.getTracks().getItems().stream()
					.sorted(Comparator.comparingInt(Item::getPopularity).reversed()).findFirst();
			Optional<TrackIngestionServiceResponse> response = responseBuilder.validateSpotifyResponse(item);
			if (response.isPresent()) {
				return response.get();
			}
			else
			{
				TrackDetailsDO trackDetailsDO = trackMapper.transformTrackToDO(item.get());
				saveTrackISRC(trackDetailsDO);
				return responseBuilder.trackIngestionResponse(isrc);
			}
		}
		catch (Exception ex) {
			return responseBuilder.trackUnKnownErrorIngestionResponse();
		}
	}
	private TrackDetails findTrackByISRC(String isrc) {
		Optional<TrackDetailsDO> trackDetailsDO = trackIngetionRepository.findAll().stream()
										.filter(i->i.getIsrcCode().toLowerCase().equals(isrc.toLowerCase())).findFirst();
		if(trackDetailsDO.isPresent()) {
			TrackDetails trackDetails = trackMapper.transformDOToTrack(trackDetailsDO.get());
			return trackDetails;
		}
		return null;
	}
	
	private void saveTrackISRC(TrackDetailsDO trackDetailsDO) {
		trackIngetionRepository.save(trackDetailsDO);
	}
	
}
