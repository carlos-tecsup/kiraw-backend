package com.kiraw.LoginKiraw.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.kiraw.LoginKiraw.entity.Users;

public interface IUsuarioDao extends CrudRepository<Users, Integer>{
//	public Users findByUsername(String username);
public Users findByUsername(String username);

}
