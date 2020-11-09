package com.umusic.track.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umusic.tack.response.model.ISRCTrack;


public interface TrackIngestionRepository extends JpaRepository<ISRCTrack,String> {

}
