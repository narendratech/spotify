package com.umusic.track.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.umusic.tack.validation.IngestionRequestValidator;
import com.umusic.track.mappers.TrackMapper;
import com.umusic.track.repository.TrackIngestionRepository;
import com.umusic.track.repository.TrackReadRepository;
import com.umusic.track.service.SpotifyTrackService;
import com.umusic.track.service.TrackIngestionService;
import com.umusic.track.service.TrackReadService;


@Configuration
public class ServiceConfig {
	
	@Bean
	public TrackIngestionService ingestionService(IngestionRequestValidator ingestionRequestValidator,
			ApplicationConfig applicationConfig, SpotifyTrackService spotifyTokenService, TrackMapper musicTrackMapper,TrackIngestionRepository trackIngetionRepo) {
		return new TrackIngestionService(ingestionRequestValidator, applicationConfig, 
				spotifyTokenService, musicTrackMapper,trackIngetionRepo);
	}
	
	@Bean
	public TrackReadService trakcReadService(TrackReadRepository trackreadRepo) {
		return new TrackReadService(trackreadRepo);
	}
	
	

}
