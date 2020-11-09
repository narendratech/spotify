package com.umusic.track.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrackDetailsDO {
	private String isrc;
	private String title;
	private String imageUrl;
	private List<String> artistNames;

}
