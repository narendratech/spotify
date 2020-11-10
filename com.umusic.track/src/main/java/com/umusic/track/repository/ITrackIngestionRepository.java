package com.umusic.track.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.umusic.track.domain.TrackDetailsDO;


public interface ITrackIngestionRepository extends JpaRepository<TrackDetailsDO,String> {
	
}
