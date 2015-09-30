package com.app.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Actor;
import com.app.service.Service;

@RestController
@RequestMapping(value="/actor")
public class ActorController {

    @Autowired 
    private Service<Actor> actorService;
    
    @RequestMapping(value="/list", method=RequestMethod.GET)
    public List<Actor> findAll(){
	return actorService.findAll();
    }
    
    @RequestMapping(value="/find/{id}", method=RequestMethod.GET)
    public Actor findById(@PathVariable int id){
	return actorService.findById(id);
    }

}
