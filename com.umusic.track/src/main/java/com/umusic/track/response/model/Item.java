package com.umusic.track.response.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {

	private Album album;
	private List<Artist> artists;
	private int popularity;
	private String name;

	@JsonProperty("external_ids")
	private ExternalIds externalIds;

	
}
