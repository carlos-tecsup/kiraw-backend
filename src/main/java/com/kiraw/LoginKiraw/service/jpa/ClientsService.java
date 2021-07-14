package com.kiraw.LoginKiraw.service.jpa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import com.kiraw.LoginKiraw.entity.Provider;
import com.kiraw.LoginKiraw.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kiraw.LoginKiraw.entity.Clients;
import com.kiraw.LoginKiraw.entity.Roles;
import com.kiraw.LoginKiraw.repository.ClientsRepository;
import com.kiraw.LoginKiraw.service.IClientsService;
import com.kiraw.LoginKiraw.utils.Constant;

@Service
public class ClientsService implements IClientsService {
	   @Autowired
	    BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private ClientsRepository clientedao;
	@Autowired
	private ClientRepository clientRepository;


	@Override
	@Transactional
	public Clients save(Clients cliente) {
		cliente.getUsers().setPassword(passwordEncoder.encode(cliente.getUsers().getPassword()));
		List<Roles>roles=new ArrayList<>();
		roles.add(new Roles(Constant.ROLE_CLIENT));
		
		cliente.getUsers().setRoles(roles);
		return clientedao.save(cliente);


	}
	@Transactional
	@Override
	public Clients findClientsById(Integer id) {
		return  clientedao.findById(id).orElse(null);
	}


	public void crear(Clients clients) {
		clientRepository.crear(clients);
	}

	public void crear2(Clients clients) {
		clientRepository.crearimg(clients);
	}



}
