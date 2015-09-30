package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Actor;

public interface ActorRepository extends JpaRepository<Actor, Integer>{

}
