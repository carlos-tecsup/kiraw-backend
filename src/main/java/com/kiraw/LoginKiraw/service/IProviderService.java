package com.kiraw.LoginKiraw.service;

import java.util.List;
import java.util.Optional;

import com.kiraw.LoginKiraw.entity.Clients;
import com.kiraw.LoginKiraw.entity.Provider;
import com.kiraw.LoginKiraw.entity.Users;
import org.springframework.web.bind.annotation.RequestParam;

public interface IProviderService {
	
	public Provider save(Provider provider);
	public Provider findProviderById(int id);
	
}
