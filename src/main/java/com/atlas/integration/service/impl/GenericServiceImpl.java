package com.atlas.integration.service.impl;

import com.atlas.integration.model.Adress;
import com.atlas.integration.model.Coordinates;
import com.atlas.integration.model.CoordinatesInfo;
import com.atlas.integration.model.Status;
import com.atlas.integration.model.User;
import com.atlas.integration.model.apigoogle.ListAdressGoogle;
import com.atlas.integration.repository.AdressRepository;
import com.atlas.integration.repository.UserRepository;
import com.atlas.integration.service.GenericService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
public class GenericServiceImpl implements GenericService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AdressRepository AdressRepository;

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public List<User> findAllUsers() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public List<Adress> findAllAdress() {
		return (List<Adress>) AdressRepository.findAll();
	}

	@Override
	public Status addUser(String adress) {
		Adress adr = new Adress();
		adr.setName(adress);
		AdressRepository.save(adr);
		return Status.SAVED;
	}

	@Override
	public Coordinates getCoordinates(String direction) {
		Coordinates Coord = new Coordinates();
		CoordinatesInfo CoordInfo = new CoordinatesInfo();
		
		String uri = null;
		try {
			uri = "https://maps.googleapis.com/maps/api/geocode/json?address="
					+ URLEncoder.encode(direction, "UTF-8").replaceAll("\\+", "%20") + "&key=AIzaSyCuIVykQgP51nfQ7YtR2nBQLKo4ihn1Eyc";
			
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			
			ListAdressGoogle myObjects = mapper.readValue(new URL(uri),  ListAdressGoogle.class);
			
			if(!myObjects.getStatus().equals(Status.ZERO_RESULTS.toString())){
				
				CoordInfo.coorX = myObjects.getResults().get(0).getGeometry().getLocation().lat;
				CoordInfo.coorY = myObjects.getResults().get(0).getGeometry().getLocation().lng;
				CoordInfo.direction = direction;
				CoordInfo.directionNorm =  myObjects.getResults().get(0).getFormatted_address();
				Coord.setCoord(CoordInfo);
				Coord.setStatus(Status.OK);
			}
			
			else{
				CoordInfo.setDirection(direction);
				Coord.setStatus(Status.ZERO_RESULTS);
			}
			
			
			
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Coord;
	}

	@Override
	public List<Coordinates> getCoordinates() {
		List<Coordinates> arrayCoord = new ArrayList<Coordinates>();
		List<Adress> allAdress = findAllAdress();
		//MIRAR DIRECCIO Pl. Jar Jar Binks, 42, Barcelona
		for(Adress direction : allAdress){
			Coordinates coord = getCoordinates(direction.getName());
			arrayCoord.add(coord);
		}
		
		return arrayCoord;
	}

}
