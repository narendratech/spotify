package com.umusic.track.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.umusic.tack.response.model.ISRCTrack;


public interface TrackReadRepository extends JpaRepository<ISRCTrack,String> {
	
    @Query(value = "select * from public.isrctrack t where lower(t.artist_names) like %:artist%",nativeQuery=true)
	public List<ISRCTrack> findByArtistWildCard(@Param("artist") String artist);
}
