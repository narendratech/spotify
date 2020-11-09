package com.umusic.track.config;
import org.springframework.beans.factory.annotation.Value;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ApplicationConfig {
	@Value("${spotify.client-id}")
	private String clientId;

	@Value("${spotify.client-secret}")
	private String clientSecret;

	@Value("${spotify.base.search.url}")
	private String spotifyBaseSearchUrl;

	@Value("${spotify.token.url}")
	private String spotifyTokenUrl;
}
