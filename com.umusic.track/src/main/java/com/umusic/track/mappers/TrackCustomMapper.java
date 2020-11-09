package com.umusic.track.mappers;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Named;

import com.umusic.tack.response.model.Album;
import com.umusic.tack.response.model.Artist;
import com.umusic.tack.response.model.Image;

@Named("TrackCustomMapper")
public class TrackCustomMapper {

	@Named("ArtistsDataToArtistsNames")
	public List<String> artistsDataToArtistsNames(List<Artist> artists) {
		List<String> artistNames = new ArrayList<>();
		for (Artist artist : artists) {
			artistNames.add(artist.getName());
		}

		return artistNames;
	}
	
	@Named("AlbumToImage")
	public String albumToImage(Album album) {
		List<Image> images = album.getImages();
		return images.get(0).getUrl();
	}
}
