package io.java.umusic.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UmusicAppConfiguration {
  
	@Value("${spotify.client-id}")
	public  String ClientId;
	
	@Value("${spotify.client-secret}")
	public  String ClientSeceret;
	
	@Value("${spotify.search}")
	public  String Search;
	
	@Value("${spotify.tokenUrl}")
	public  String TokenUrl;
}
