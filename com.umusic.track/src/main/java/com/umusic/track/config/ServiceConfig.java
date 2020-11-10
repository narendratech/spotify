package com.umusic.track.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.umusic.track.builder.ResponseBuilder;
import com.umusic.track.mappers.TrackMapper;
import com.umusic.track.repository.ITrackIngestionRepository;
import com.umusic.track.repository.ITrackReadRepository;
import com.umusic.track.service.SpotifyTrackService;
import com.umusic.track.service.TrackIngestionService;
import com.umusic.track.service.TrackReadService;
import com.umusic.track.validation.IngestRequestValidator;


@Configuration

public class ServiceConfig {
		
	@Bean
	public TrackIngestionService trackIngestionService(IngestRequestValidator ingestionRequestValidator,ITrackIngestionRepository itrackIngestionRepository,
			    SpotifyTrackService spotifyTokenService, TrackMapper musicTrackMapper,ResponseBuilder responseBuilder) {
		return new TrackIngestionService(ingestionRequestValidator, itrackIngestionRepository, 
				spotifyTokenService, musicTrackMapper,responseBuilder);
	}
	
	@Bean
	public TrackReadService trakcReadService(ITrackReadRepository  itrackReadRepository,TrackMapper trackMapper,
			ResponseBuilder responseBuilder) {
		return new TrackReadService(itrackReadRepository,trackMapper,responseBuilder);
	}
	
	
}
