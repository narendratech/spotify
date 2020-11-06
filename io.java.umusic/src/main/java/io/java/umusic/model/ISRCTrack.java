package io.java.umusic.model;

import javax.persistence.*;

@Entity
public class ISRCTrack {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;

	private String isrc;
	private String title;
	private String imageUrl;
	private String artistNames;

	public ISRCTrack(String isrc, String title, String imageUrl, String artistsName) {
		this.isrc = isrc;
		this.title = title;
		this.imageUrl = imageUrl;
		this.artistNames = artistsName;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getIsrc() {
		return isrc;
	}

	public void setIsrc(String isrc) {
		this.isrc = isrc;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getArtistNames() {
		return artistNames;
	}

	public void setArtistNames(String artistNames) {
		this.artistNames = artistNames;
	}

	public String getISRC() {
		return isrc;
	}

}
