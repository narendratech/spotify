package com.umusic.track.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.umusic.track.domain.TrackDetailsDO;




public interface ITrackReadRepository extends JpaRepository<TrackDetailsDO,String> {
	@Query(value = "select * from public.isrctrack t where lower(t.artist_names) like %:artist%",nativeQuery=true)
	public List<TrackDetailsDO> findTracksByContainsArtist(@Param("artist") String artist);
} 
