package com.atlas.integration.service;



import java.util.List;

import com.atlas.integration.model.Adress;
import com.atlas.integration.model.Coordinates;
import com.atlas.integration.model.Status;
import com.atlas.integration.model.User;



public interface GenericService {
	
    User findByUsername(String username);

    List<User> findAllUsers();

    List<Adress> findAllAdress();
    
    Status addAdress(String adress);
    
    Coordinates getCoordinates(String adress);

	List<Coordinates> getCoordinates();
}
