package com.umusic.track.response.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrackDetails {

	private String isrc;
	private String title;
	private String imageUrl;
	private List<String> artistNames;

}
