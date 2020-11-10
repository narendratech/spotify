package com.umusic.track.mappers;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Named;

import com.umusic.track.response.model.Album;
import com.umusic.track.response.model.Artist;
import com.umusic.track.response.model.Image;

@Named("TrackCustomMapper")
public class TrackCustomMapper {

	@Named("ArtistsDataToArtistsNames")
	public String artistsDataToArtistsNames(List<Artist> artists) {
		List<String> artistNames = new ArrayList<>();
		for (Artist artist : artists) {
			artistNames.add(artist.getName());
		}
		String artistName = String.join(",",artistNames);
		return artistName;
	}
	
	@Named("AlbumToImage")
	public String albumToImage(Album album) {
		List<Image> images = album.getImages();
		return images.get(0).getUrl();
	}
	
	@Named("ArtistNameToList")
	public List<String> artistsNameToList(String artist) {
		List<String> artistNames = new ArrayList<>();
		String[] names = artist.split(",");
		for (String name : names) {
			artistNames.add(name);
		}
		return artistNames;
	}
}
