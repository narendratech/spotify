package com.umusic.track.mappers;

import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.umusic.tack.response.model.Item;
import com.umusic.track.domain.TrackDetailsDO;

@Mapper(componentModel = "spring", uses = { TrackCustomMapper.class })
public interface TrackMapper {
	
	@Mapping(target = "isrc", source = "externalIds.isrc")
	@Mapping(target = "title", source = "name")
	@Mapping(target = "imageUrl", source = "album", qualifiedByName = { "TrackCustomMapper",
			"ArtistsDataToArtistsNames" })
	@Mapping(target = "artistNames", source = "artists", qualifiedByName = { "TrackCustomMapper", "AlbumToImage" })
	TrackDetailsDO transformTrackToDO(Item item);
}
