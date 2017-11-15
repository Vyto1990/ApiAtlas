package com.atlas.integration.service;



import java.util.List;

import com.atlas.integration.domain.Adress;
import com.atlas.integration.domain.Coordinates;
import com.atlas.integration.domain.Status;
import com.atlas.integration.domain.User;



public interface GenericService {
	
    User findByUsername(String username);

    List<User> findAllUsers();

    List<Adress> findAllAdress();
    
    Status addUser(String adress);
    
    Coordinates getCoordinates(String adress);

	List<Coordinates> getCoordinates();
}
