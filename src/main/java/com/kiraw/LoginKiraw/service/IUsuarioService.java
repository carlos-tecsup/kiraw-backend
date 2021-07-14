package com.kiraw.LoginKiraw.service;

import org.springframework.data.jpa.repository.Query;

import com.kiraw.LoginKiraw.entity.Provider;
import com.kiraw.LoginKiraw.entity.Users;
import com.kiraw.LoginKiraw.service.jpa.UsuarioService;

public interface IUsuarioService {

	

	public Users findByUsername(String username);



	@Query("SELECT p FROM provider p fech join users u  ON u.id = p.id WHERE u.id = ?1")
	public Provider findProviderByUsersId(int id);

}
