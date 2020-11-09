package com.umusic.tack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.umusic.tack.response.model.ISRCTrack;
import com.umusic.tack.response.model.TrackServiceResponse;
import com.umusic.track.service.TrackReadService;


@RestController
public class TrackReadController {
	
	@Autowired
	private TrackReadService trackreadService;
	
	@GetMapping(value = "/track/getByISRC/{isrcCode}", consumes = "application/json", produces = "application/json")
	public ISRCTrack getTrackById(@PathVariable String isrcCode) {
		return trackreadService.getByISRCTrack(isrcCode);
	}
	
	@GetMapping(value = "/track/getByArtist/{artist}", consumes = "application/json", produces = "application/json")
	public List<ISRCTrack> getByArtist(@PathVariable String artist) {
		return trackreadService.findByArtistWildCard(artist);
	}
}
