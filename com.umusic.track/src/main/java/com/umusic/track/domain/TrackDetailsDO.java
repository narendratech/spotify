package com.umusic.track.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="isrctrack")
public class TrackDetailsDO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id") 
	private int Id;
		
	@Column(name = "isrc_code") 
	private String isrcCode;	
	
	@Column(name = "title") 
	private String title;
	
	@Column(name = "image_url")
	private String imageUrl;
	
	@Column(name = "artist_names")
	private String artistNames;

}
