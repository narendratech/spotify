package com.umusic.track.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.umusic.track.response.model.TrackReadServiceResponse;
import com.umusic.track.service.TrackReadService;


@RestController
public class TrackReadController {
	
	@Autowired
	private TrackReadService trackreadService;
	
	@GetMapping(value = "/track/getTrackByISRC/{isrc}", consumes = "application/json", produces = "application/json")
	public TrackReadServiceResponse getTrackById(@PathVariable String isrc) {
		return trackreadService.getTrackByISRC(isrc);
	}
	
	@GetMapping(value = "/track/getTracksContainsByArtist/{artist}", consumes = "application/json", produces = "application/json")
	public TrackReadServiceResponse getByArtist(@PathVariable String artist) {
		return trackreadService.getTracksContainsByArtist(artist);
	}
}
