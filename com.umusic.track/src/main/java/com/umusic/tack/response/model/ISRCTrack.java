package com.umusic.tack.response.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ISRCTrack {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;

	private String isrcCode;
	private String title;
	private String imageUrl;
	private String artistNames;

	public ISRCTrack() {
		
	}
	public ISRCTrack(String isrc, String title, String imageUrl, String artistsName) {
		this.isrcCode = isrc;
		this.title = title;
		this.imageUrl = imageUrl;
		this.artistNames = artistsName;
	}

		

}
