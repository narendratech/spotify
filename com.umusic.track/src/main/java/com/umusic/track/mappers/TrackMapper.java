package com.umusic.track.mappers;

import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.umusic.track.domain.TrackDetailsDO;
import com.umusic.track.response.model.Item;
import com.umusic.track.response.model.TrackDetails;

@Mapper(componentModel = "spring", uses = { TrackCustomMapper.class })
public interface TrackMapper {
	
	@Mapping(target = "isrcCode", source = "externalIds.isrc")
	@Mapping(target = "title", source = "name")
	@Mapping(target = "imageUrl", source = "album", qualifiedByName = { "TrackCustomMapper","AlbumToImage" })
	@Mapping(target = "artistNames", source = "artists", qualifiedByName = { "TrackCustomMapper", "ArtistsDataToArtistsNames" })
	TrackDetailsDO transformTrackToDO(Item item);
	
	
	@Mapping(target = "isrc", source = "isrcCode")
	@Mapping(target = "title", source = "title")
	@Mapping(target = "imageUrl", source = "imageUrl")
	@Mapping(target = "artistNames", source = "artistNames", qualifiedByName = { "TrackCustomMapper", "ArtistNameToList" })
	TrackDetails transformDOToTrack(TrackDetailsDO trackDetailsDO);
}


