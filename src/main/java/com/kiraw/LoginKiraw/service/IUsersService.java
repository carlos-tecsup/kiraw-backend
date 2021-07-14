package com.kiraw.LoginKiraw.service;

import java.util.Optional;

import com.kiraw.LoginKiraw.entity.Provider;
import com.kiraw.LoginKiraw.entity.Users;

public interface IUsersService {
	
	public Users findProviderByUsers(int id);
	
}
