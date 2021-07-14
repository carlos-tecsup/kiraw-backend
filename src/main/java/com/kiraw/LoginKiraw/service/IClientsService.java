package com.kiraw.LoginKiraw.service;

import com.kiraw.LoginKiraw.entity.Clients;
import com.kiraw.LoginKiraw.entity.Users;

public interface IClientsService {
	public Clients save(Clients clients);
	public  Clients findClientsById(Integer id);
}
