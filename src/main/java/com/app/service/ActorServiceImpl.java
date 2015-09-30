/**
 * 
 */
package com.app.service;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import com.app.model.Actor;
import com.app.repository.ActorRepository;

/**
 * @author AD79272
 *
 */
@org.springframework.stereotype.Service
public class ActorServiceImpl implements Service<Actor> {

    @Resource
    private ActorRepository actorRepository;
    
    @Transactional
    public Actor create(Actor entity) {
	// TODO Auto-generated method stub
	return null;
    }

    @Transactional
    public Actor delete(int id) throws EntityNotFoundException {
	// TODO Auto-generated method stub
	return null;
    }

    @Transactional
    public List<Actor> findAll() {	
	return actorRepository.findAll();
    }

    @Transactional
    public Actor update(Actor entity) throws EntityNotFoundException {
	// TODO Auto-generated method stub
	return null;
    }

    @Transactional
    public Actor findById(int id) {
	return actorRepository.findOne(id);
    }

}
