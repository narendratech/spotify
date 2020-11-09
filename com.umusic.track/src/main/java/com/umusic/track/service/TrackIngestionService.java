package com.umusic.track.service;

import com.umusic.track.config.ApplicationConfig;
import com.umusic.track.domain.TrackDetailsDO;
import com.umusic.track.mappers.TrackMapper;
import com.umusic.track.repository.TrackIngestionRepository;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;

import java.util.Arrays;
import java.util.Base64;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.web.client.RestTemplate;

import com.umusic.tack.response.model.ISRCTrack;
import com.umusic.tack.response.model.Item;
import com.umusic.tack.response.model.MusicTrack;
import com.umusic.tack.response.model.SpotifyTokenServiceResponse;

import com.umusic.tack.validation.IngestionRequestValidator;
import com.umusic.track.request.model.IngestionServiceRequest;


public class TrackIngestionService implements ITrackIngestionService {

	private final IngestionRequestValidator ingestionRequestValidator;
	private final ApplicationConfig applicationConfig;
	private final SpotifyTrackService spotifyTrackService;
	private final TrackMapper trackMapper;
	private final TrackIngestionRepository trackIngetionRepo;

	public TrackIngestionService(IngestionRequestValidator ingestionRequestValidator, ApplicationConfig applicationConfig,
			SpotifyTrackService spotifyTrackService, TrackMapper trackMusicMapper,TrackIngestionRepository ingestionRepo) {
		this.ingestionRequestValidator = ingestionRequestValidator;
		this.applicationConfig = applicationConfig;
		this.spotifyTrackService = spotifyTrackService;
		this.trackMapper = trackMusicMapper;
		this.trackIngetionRepo = ingestionRepo;
	}

	@Override
	public void ingest(IngestionServiceRequest ingestionRequest) {
		try {
			String isrc = ingestionRequest.getIsrc();
			String token = spotifyTrackService.getspotifyAccessToken();
			MusicTrack trackDetails = spotifyTrackService.getspotifyByISRSC(ingestionRequest.getIsrc(), token);
			Item item = trackDetails.getTracks().getItems().stream()
					.sorted(Comparator.comparingInt(Item::getPopularity).reversed()).findFirst().get();
			if (item != null) {
					TrackDetailsDO trackDetailsDO = trackMapper.transformTrackToDO(item);
					String artisNames =  String.join(", ", trackDetailsDO.getArtistNames());
					SaveISRCTrack(new ISRCTrack(trackDetailsDO.getIsrc(), item.getName().toString(), trackDetailsDO.getImageUrl().toString(), artisNames));
					
				}					
		}
		catch (Exception ex) {
			// throw new Exception(e);
		}
	}
	
	private void SaveISRCTrack(ISRCTrack istrcTrack) {
		trackIngetionRepo.save(istrcTrack);
	}
	
}
