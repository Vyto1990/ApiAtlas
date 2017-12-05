package com.atlas.integration.controller;

import com.atlas.integration.model.Adress;
import com.atlas.integration.model.Coordinates;
import com.atlas.integration.model.Status;
import com.atlas.integration.service.GenericService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ResourceController {
    @Autowired
    private GenericService genericService;

    @GetMapping(value ="/listAdress")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public List<Adress> getUser(){
        return genericService.findAllAdress();
    }

//    @GetMapping(value ="/users")
//    @PreAuthorize("hasAuthority('ADMIN_USER')")
//    public List<User> getUsers(){
//        return userService.findAllUsers();
//    }
    
    @GetMapping(value ="/addAdress/{adress}")
    @PreAuthorize("hasAuthority('STANDARD_USER')")
    public Status saveAdress(@PathVariable("adress") final String adress){
        return genericService.addAdress(adress);
    }
    
    @GetMapping(value ="/getCoord/{adress}")
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public Coordinates getCoords(@PathVariable("adress") final String adress){
        return genericService.getCoordinates(adress);
    }
    
    @GetMapping(value ="/getAllCoord")
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public List<Coordinates> getCoords(){
        return genericService.getCoordinates();
    }
    
    
}
