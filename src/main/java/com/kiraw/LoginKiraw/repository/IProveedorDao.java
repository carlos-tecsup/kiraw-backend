package com.kiraw.LoginKiraw.repository;

import org.springframework.data.repository.CrudRepository;

import com.kiraw.LoginKiraw.entity.Provider;

public interface IProveedorDao extends CrudRepository<Provider,Integer >{
	public Provider findByName(String username);
}
