package com.app.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

public interface Service<T> {
    	public T create(T entity);
	public T delete(int id) throws EntityNotFoundException;
	public List<T> findAll();
	public T update(T entity) throws EntityNotFoundException;
	public T findById(int id);
}
