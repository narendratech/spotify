package com.umusic.track.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.umusic.track.builder.ResponseBuilder;
import com.umusic.track.mappers.TrackCustomMapper;
import com.umusic.track.service.SpotifyTrackService;
import com.umusic.track.validation.IngestRequestValidator;

@Configuration
public class SupportConfig {
	
	@Bean
	public IngestRequestValidator ingestionRequestValidator() {
		return new IngestRequestValidator();
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
		
	@Bean
	public ResponseBuilder responseBuilder() {
		return new ResponseBuilder();
	}

}

