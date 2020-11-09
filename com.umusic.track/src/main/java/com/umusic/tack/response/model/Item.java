package com.umusic.tack.response.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {

	private Album album;
	private List<Artist> artists;
	private int popularity;
	private String name;

	@JsonProperty("external_ids")
	private ExternalIds externalIds;

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public List<Artist> getArtists() {
		return artists;
	}

	public void setArtists(List<Artist> artists) {
		this.artists = artists;
	}

	public int getPopularity() {
		return popularity;
	}

	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ExternalIds getExternalIds() {
		return externalIds;
	}

	public void setExternalIds(ExternalIds externalIds) {
		this.externalIds = externalIds;
	}
}
