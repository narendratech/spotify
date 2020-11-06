package io.java.umusic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.java.umusic.model.ISRCTrack;

@Repository
public interface UmusicIngestionRepository extends JpaRepository<ISRCTrack,String> {

}
