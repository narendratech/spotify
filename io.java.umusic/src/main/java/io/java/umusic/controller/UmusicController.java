package io.java.umusic.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.java.umusic.interfaces.IReadUmusic;
import io.java.umusic.interfaces.IngestionUmusic;
import io.java.umusic.model.MusicISRSC;



@RestController
public class UmusicController {

	@Autowired
    private IReadUmusic ireadMusic;
	
	@Autowired
    private IngestionUmusic ingestion;
	
	@RequestMapping(method = RequestMethod.POST, value = "/storeISRC")
	public String storeISRC(@RequestBody MusicISRSC musicISRSC) {
		try 
		{
		    String token = ingestion.ingestByISRC(musicISRSC.musicISRSC);
		    return token;
		}
		catch(Exception ex) {
			//System.out.println(ex.printStackTrace());
			return null;
		}
		
	}
	@RequestMapping(method = RequestMethod.GET, value = "/trackISRC")
	public String getISRCTrack(@RequestBody MusicISRSC musicISRSC) {
		try 
		{
		    //String token = ingestion.ingestByISRC(musicISRSC.musicISRSC);
		    return "";
		}
		catch(Exception ex) {
			//System.out.println(ex.printStackTrace());
			return null;
		}
		
	}
		
}
