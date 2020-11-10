package com.umusic.track.service;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;

import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.umusic.track.config.ApplicationConfig;
import com.umusic.track.response.model.MusicTrack;
import com.umusic.track.response.model.SpotifyTokenServiceResponse;

public class SpotifyTrackService {

	private final ApplicationConfig applicationConfig;
	private final RestTemplate restTemplate;
	public SpotifyTrackService(ApplicationConfig applicationConfig,RestTemplate restTemplate) {
		this.applicationConfig = applicationConfig;
		this.restTemplate = restTemplate;
	}
	public String getspotifyAccessToken() {

			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.set("Content-Type", APPLICATION_FORM_URLENCODED_VALUE);
			requestHeaders.set("Authorization", "Basic " + Base64.getEncoder()
					.encodeToString((applicationConfig.getClientId() + ":" + applicationConfig.getClientSecret()).getBytes()));
			MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<String, String>();
			requestBody.put("grant_type", Arrays.asList("client_credentials"));
			ResponseEntity<SpotifyTokenServiceResponse> responseEntity = callAction(restTemplate, "getToken", applicationConfig.getSpotifyTokenUrl(), POST,
					new HttpEntity<>(requestBody, requestHeaders), SpotifyTokenServiceResponse.class, null);
			 return  responseEntity.getBody().getAccessToken();
	}

	
	public MusicTrack getspotifyByISRSC(String isRSC, String token) {
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.set("Content-Type", "application/json");
			requestHeaders.set("Authorization", "Bearer " + token);

			Map<String, String> queryParams = new HashMap<>();
			queryParams.put("q", "isrc:" + isRSC);
			queryParams.put("type", "track");
			ResponseEntity<MusicTrack> responseEntity = callAction(restTemplate, "searchtrack", applicationConfig.getSpotifyBaseSearchUrl(), GET,
					new HttpEntity<>(null, requestHeaders), MusicTrack.class, queryParams);
			MusicTrack tracks = responseEntity.getBody();
			return tracks;
	}
	
	
	private <T> ResponseEntity<T> callAction(RestTemplate restTemplate, String message, String url, HttpMethod method,
			HttpEntity entity, Class<T> responseClass, Map<String, String> queryParameters) {
		ResponseEntity<T> responseEntity;
		try {
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
			if (queryParameters != null) {
				queryParameters.keySet().forEach(key -> builder.queryParam(key, queryParameters.get(key)));
			}
			responseEntity = restTemplate.exchange(builder.build(false).toUri(), method, entity, responseClass);
		} catch (RestClientResponseException e) {

			throw new RuntimeException(
					"Error while making the " + message + " rest call. Message:" + e.getResponseBodyAsString());
		}
		return responseEntity;
	}
}
