package com.umusic.track.response.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Album {
	private List<Image> images;
	private String name;
}
