package com.umusic.track.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.umusic.tack.validation.IngestionRequestValidator;
import com.umusic.track.mappers.TrackCustomMapper;
import com.umusic.track.service.SpotifyTrackService;

@Configuration
public class SupportConfig {
	
	@Bean
	public IngestionRequestValidator ingestionRequestValidator() {
		return new IngestionRequestValidator();
	}
	
	@Bean
	public ApplicationConfig applicationConfig() {
		return new ApplicationConfig();
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}


	@Bean
	public TrackCustomMapper musicTrackCustomMapper() {
		return new TrackCustomMapper();
	}
	
	@Bean 
	public SpotifyTrackService spotifyTokenService(ApplicationConfig applicationConfig,RestTemplate restTemplate) {
		return new SpotifyTrackService(applicationConfig,restTemplate);
	}
}

