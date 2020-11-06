package io.java.umusic.service;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;

import java.util.Arrays;
import java.util.Base64;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import io.java.umusic.config.UmusicAppConfiguration;
import io.java.umusic.interfaces.IngestionUmusic;
import io.java.umusic.model.Artist;
import io.java.umusic.model.ISRCTrack;
import io.java.umusic.model.Images;
import io.java.umusic.model.Items;
import io.java.umusic.model.MusicTracks;
import io.java.umusic.model.Tracks;
import io.java.umusic.repository.UmusicIngestionRepository;

@Service
public class UmusicIngestionService implements IngestionUmusic {
	@Autowired
	private UmusicAppConfiguration appConfig;

	@Autowired
	private UmusicIngestionRepository umusicRepository;

	/*
	 * Please use this method to get records from spotify and it will add the record in postgres sql
	 */
	@Override
	public String ingestByISRC(List<String> isRSCList) {
		try {
			MusicTracks trackDetails = null;
			String recordInserted = null ;
			for (String isRSCTrack : isRSCList) {
				String token = getAccessToken();
			    trackDetails = getByISRSC(isRSCTrack, token);
				Optional<Items> item = trackDetails.tracks.items.stream()
						.sorted(Comparator.comparingInt(Items::getPopularity).reversed()).findFirst();
				if (item != null) {
					Images image = item.get().getAlbum().images.get(0);
					String arrtists = item.get().getArtists().stream().map(Artist::getName).collect(Collectors.joining(","));
					SaveISRCTrack(new ISRCTrack(isRSCTrack, item.get().getName().toString(), image.getUrl().toString(), arrtists));
					recordInserted = "Record Inserted";
				}
			}
			return recordInserted;
		}

		catch (Exception ex) {
			// throw new Exception(e);
			return "";
		}
	}
	
	private void SaveISRCTrack(ISRCTrack istrcTrack) {
		umusicRepository.save(istrcTrack);
	}
	/*
	 * Please use this method to get spotify access token
	 */
	private String getAccessToken() {
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.set("Content-Type", APPLICATION_FORM_URLENCODED_VALUE);
			requestHeaders.set("Authorization", "Basic " + Base64.getEncoder()
					.encodeToString((appConfig.ClientId + ":" + appConfig.ClientSeceret).getBytes()));
			MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<String, String>();

			requestBody.put("grant_type", Arrays.asList("client_credentials"));
			ResponseEntity<Object> responseEntity = callAction(restTemplate, "getToken", appConfig.TokenUrl, POST,
					new HttpEntity<>(requestBody, requestHeaders), Object.class, null);
			Map<String, String> map = (Map<String, String>) responseEntity.getBody();
			String accessToken = (String) map.get("access_token");
			return accessToken;
		} catch (Exception ex) {
			return null;
		}
	}

	/*
	 * Please use this method to get the data by track id from spotify
	 */
	private MusicTracks getByISRSC(String isRSC, String token) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.set("Content-Type", "application/json");
			requestHeaders.set("Authorization", "Bearer " + token);

			Map<String, String> queryParams = new HashMap<>();
			queryParams.put("q", "isrc:" + isRSC);
			queryParams.put("type", "track");
			ResponseEntity<MusicTracks> responseEntity = callAction(restTemplate, "searchtrack", appConfig.Search, GET,
					new HttpEntity<>(null, requestHeaders), MusicTracks.class, queryParams);
			MusicTracks tracks = responseEntity.getBody();
			return tracks;
		} catch (Exception ex) {
			return null;
		}
	}

	/*
	 * Please use this method to for resttempate to set body and parameter it
	 * willgive as well resourse not found from something wrong with spotify api
	 */
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
