package io.java.umusic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.java.umusic.interfaces.IReadUmusic;
import io.java.umusic.repository.UmusicReadRepository;

@Service
public class UmusicReadService implements IReadUmusic {
	
	@Autowired
	private UmusicReadRepository readRepository;
	
	@Override
	public String readumusic(String id, String type) {
		
		return null;
	}
	
	
}
