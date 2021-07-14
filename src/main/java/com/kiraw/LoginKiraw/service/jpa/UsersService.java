package com.kiraw.LoginKiraw.service.jpa;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiraw.LoginKiraw.entity.Provider;
import com.kiraw.LoginKiraw.entity.Users;
import com.kiraw.LoginKiraw.repository.IPublicationDao;
import com.kiraw.LoginKiraw.repository.IUsuarioDao;
import com.kiraw.LoginKiraw.repository.UsersRepository;
import com.kiraw.LoginKiraw.service.IUsersService;

@Service
public class UsersService implements IUsersService {
	@Autowired
	private IUsuarioDao usuarioDao;

	

	@Override
	public Users findProviderByUsers(int id) {
		Users users= usuarioDao.findById(id).orElse(null);
		
		return users;
	
	}
	 
}
