package com.kiraw.LoginKiraw.service.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kiraw.LoginKiraw.entity.Provider;
import com.kiraw.LoginKiraw.entity.Roles;
import com.kiraw.LoginKiraw.repository.IProviderDao;
import com.kiraw.LoginKiraw.service.IProviderService;
import com.kiraw.LoginKiraw.utils.Constant;
@Service	
public class ProviderService implements IProviderService{
	@Autowired
	private IProviderDao providerDao;
	  @Autowired
	    BCryptPasswordEncoder passwordEncoder;
	@Transactional
	@Override
	public Provider save(Provider provider) {
		provider.getUsers().setPassword(passwordEncoder.encode(provider.getUsers().getPassword()));
		List<Roles>roles=new ArrayList<>();
		roles.add(new Roles(Constant.ROLE_PROVIDER));
		
		provider.getUsers().setRoles(roles);
		
		return providerDao.save(provider);
	}
	@Transactional
	@Override
	public Provider findProviderById(int id) {
		return providerDao.findById(id).orElse(null);
	}
	

	

}
