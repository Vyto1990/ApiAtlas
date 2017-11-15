package com.atlas.integration.controller;

import com.atlas.integration.domain.Adress;
import com.atlas.integration.domain.Coordinates;
import com.atlas.integration.domain.Status;
import com.atlas.integration.domain.User;
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
    private GenericService userService;

    @GetMapping(value ="/listAdress")
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public List<Adress> getUser(){
        return userService.findAllAdress();
    }

//    @GetMapping(value ="/users")
//    @PreAuthorize("hasAuthority('ADMIN_USER')")
//    public List<User> getUsers(){
//        return userService.findAllUsers();
//    }
    
    @GetMapping(value ="/addAdress/{adress}")
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public Status saveUser(@PathVariable("adress") final String adress){
        return userService.addUser(adress);
    }
    
    @GetMapping(value ="/getCoord/{adress}")
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public Coordinates getCoords(@PathVariable("adress") final String adress){
        return userService.getCoordinates(adress);
    }
    
    @GetMapping(value ="/getAllCoord")
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public List<Coordinates> getCoords(){
        return userService.getCoordinates();
    }
    
    
}
